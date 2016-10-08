package gq.baijie.blog.server;

import java.util.Arrays;
import java.util.stream.Collectors;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;

public class Main implements Runnable {

  private static final Main INSTANCE = new Main();

  public final Vertx vertx = Vertx.vertx();

  public static Main getInstance() {
    return INSTANCE;
  }

  public static void main(String[] args) {
    getInstance().run();
  }

  @Override
  public void run() {
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

    // Cross-origin resource sharing (CORS)
    router.route().handler(
        CorsHandler
            .create(".*")
            .allowedMethods(Arrays.stream(HttpMethod.values()).collect(Collectors.toSet()))
    );

    router.mountSubRouter("/api", new BlogService().newRouter());
    router.mountSubRouter("/api", new AccountService().newRouter());

    server.requestHandler(router::accept).listen(80);
  }

}
