package gq.baijie.blog.server;

import java.util.HashMap;
import java.util.Map;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CorsHandler;

public class BlogService implements Service {

  private static final String SAMPLE_PARAGRAPH =
      "Angular is built by a team of engineers who share a passion for making web development feel "
      + "effortless. We believe that writing beautiful apps should be joyful and fun. We're "
      + "building a platform for the future.";

  private Map<String, JsonObject> blogs = new HashMap<>();

  { // init
    setUpInitialData();
  }

  private void setUpInitialData() {
    for (int i = 0; i <= 10; i++) {
      addBlog(new JsonObject()
                  .put("id", String.valueOf(i))
                  .put("title", "blog " + i)
                  .put("content", String.format("sample paragraph %d.\n%s", i, SAMPLE_PARAGRAPH)));
    }
  }

  private void addBlog(JsonObject blog) {
    blogs.put(blog.getString("id"), blog);
  }

  @Override
  public Router newRouter() {
    final Router router = Router.router(Main.getInstance().vertx);
    // Cross-origin resource sharing (CORS)
    router.route().handler(CorsHandler.create(".*").allowedMethod(HttpMethod.GET));
    router.get("/blogs/:blogId").handler(this::handleGetBlog);
    router.get("/blogs").handler(this::handleListBlogs);
    return router;
  }

  private void handleListBlogs(RoutingContext routingContext) {
    JsonArray arr = new JsonArray();
    blogs.forEach((k, v) -> arr.add(v));
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
      JsonObject product = blogs.get(blogId);
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

}
