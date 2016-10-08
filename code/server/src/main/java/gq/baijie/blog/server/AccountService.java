package gq.baijie.blog.server;

import java.util.Arrays;
import java.util.stream.Collectors;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CorsHandler;

public class AccountService implements Service {

  @Override
  public Router newRouter() {
    final Router router = Router.router(Main.getInstance().vertx);
    // Cross-origin resource sharing (CORS)
    router.route().handler(CorsHandler.create(".*").allowedMethods(
        Arrays.stream(HttpMethod.values()).collect(Collectors.toSet())));
    router.post("/account/tokens").handler(this::handleCreateToken);
    router.delete("/account/accounts/:accountId/tokens/:token").handler(this::handleDeleteToken);
    return router;
  }

  private void handleCreateToken(RoutingContext routingContext) {
    final String accountId = routingContext.request().getParam("accountId");
    final String password = routingContext.request().getParam("password");
    final HttpServerResponse response = routingContext.response();
    if (accountId == null || password == null) {
      sendError(400, response);
    } else {
      boolean isLegalAccount = authenticate(accountId, password);
      if (isLegalAccount) {
        JsonObject result = new JsonObject();
        result.put("token", "token_here");//TODO
        result.put("accountId", accountId);
        response
            .putHeader("content-type", "application/json")
            .end(new JsonObject().put("data", result).encode());
      } else {
        sendUnauthenticatedError(response);
      }
    }
  }

  private void handleDeleteToken(RoutingContext routingContext) {
    final String accountId = routingContext.request().getParam("accountId");
    final String token = routingContext.request().getParam("token");
    final HttpServerResponse response = routingContext.response();
    if (accountId == null || token == null) {
      sendError(400, response);
    } else {
      boolean isLegalAccount = authenticateToken(accountId, token);
      if (isLegalAccount) {
        deleteToken(accountId, token);
        JsonObject result = new JsonObject();
        result.put("result", "ok");
        response
            .putHeader("content-type", "application/json")
            .end(new JsonObject().put("data", result).encode());
      } else {
        sendUnauthenticatedError(response);
      }
    }
  }

  private boolean authenticate(String accountId, String password) {
    //TODO need implement
    return !(accountId.isEmpty() || password.isEmpty());
  }

  private boolean authenticateToken(String accountId, String token) {
    //TODO need implement
    return !(accountId.isEmpty() || token.isEmpty());
  }

  private void deleteToken(String accountId, String token) {
    //TODO need implement
  }

  private void sendUnauthenticatedError(HttpServerResponse response) {
    response.setStatusCode(401).putHeader("WWW-Authenticate", "x-SimpleAuth").end();
  }

  private void sendError(int statusCode, HttpServerResponse response) {
    response.setStatusCode(statusCode).end();
  }

}
