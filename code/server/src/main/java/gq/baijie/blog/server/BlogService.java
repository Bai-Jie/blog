package gq.baijie.blog.server;

import java.util.List;

import gq.baijie.blog.business.Blog;
import gq.baijie.blog.business.BlogContent;
import gq.baijie.blog.business.MockBlogService;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class BlogService implements Service {

  private final gq.baijie.blog.business.BlogService service = new MockBlogService();

  @Override
  public Router newRouter() {
    final Router router = Router.router(Main.getInstance().vertx);
    router.get("/blogs/:blogId").handler(this::handleGetBlog);
    router.get("/blogs").handler(this::handleListBlogs);
    return router;
  }

  private void handleListBlogs(RoutingContext routingContext) {
    JsonArray arr = getBlogs();
    routingContext.response()
        .putHeader("content-type", "application/json")
        .end(new JsonObject().put("data", arr).encode());
  }

  private void handleGetBlog(RoutingContext routingContext) {
    final String blogId = routingContext.request().getParam("blogId");
    final HttpServerResponse response = routingContext.response();
    if (blogId == null) {
      sendError(400, response);
    } else {
      JsonObject product = getBlog(blogId);
      if (product == null) {
        sendError(404, response);
      } else {
        response
            .putHeader("content-type", "application/json")
            .end(new JsonObject().put("data", product).encode());
      }
    }
  }

  private void sendError(int statusCode, HttpServerResponse response) {
    response.setStatusCode(statusCode).end();
  }

  private JsonArray getBlogs() {
    try {
      return translateBlogList(service.getBlogs());
    } catch (Exception e) {
      new RuntimeException(e).printStackTrace();
      return null;
    }
  }

  private JsonObject getBlog(String id) {
    try {
      return translateBlog(service.getBlog(id));
    } catch (Exception e) {
      new RuntimeException(e).printStackTrace();
      return null;
    }
  }

  private JsonArray translateBlogList(List<Blog> blogs) {
    final JsonArray result = new JsonArray();
    blogs.forEach(blog -> result.add(translateBlog(blog)));
    return result;
  }

  private JsonObject translateBlog(Blog blog) {
    final JsonObject result = new JsonObject();
    result.put("id", blog.getId());
    result.put("title", blog.getTitle());
    result.put("content", translateBlogContent(blog.getContent()));
    return result;
  }

  private JsonObject translateBlogContent(BlogContent content) {
    final JsonObject result = new JsonObject();
    result.put("type", content.getType());
    result.put("data", content.getData());
    return result;
  }

}
