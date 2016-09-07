package handler;

import util.RequestLogger;
import request.HttpRequest;
import response.Response;
import response.HttpResponseBuilder;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class BasicAuthHandler implements Handler {
  private HttpResponseBuilder responseBuilder;
  private String defaultUsername;
  private String defaultPassword;

  public BasicAuthHandler(HttpResponseBuilder responseBuilder) {
    this.responseBuilder = responseBuilder;
    this.defaultUsername = "admin";
    this.defaultPassword = "hunter2";
  }

  public Response handleRoute(HttpRequest request) {
    String authorizationHeader = request.getHeaderLines().get("Authorization");
    if (authorizationHeader != null && authorizationHeader.contains(" ")) {
      try {
        if (isAuthorized(authorizationHeader, request)) {
          handleAuthorizedRequest();
        } else {
          handleUnauthorizedRequest();
        }
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      } 
    } else { 
      handleUnauthorizedRequest();
    }
    return responseBuilder.getResponse();
  }

  private boolean isAuthorizationHeaderPresent(String authorizationHeader) {
    return authorizationHeader != null;
  }

  private String getCurrentUser(String authorizationHeader) {
    return authorizationHeader.split(" ")[0];
  }

  private String getPassPhrase(String authorizationHeader) {
    return authorizationHeader.split(" ")[1];
  }

  private boolean isAuthorized(String authorizationHeader, HttpRequest request) throws UnsupportedEncodingException {
    String userAndPass = authorizationHeader.split(" ")[1];
    String decoded = new String(Base64.getMimeDecoder().decode(userAndPass), "UTF-8");
    if (decoded.contains(":")) {
      String[] usernameAndPass = decoded.split(":");
      return usernameAndPasswordMatchGiven(usernameAndPass[0], usernameAndPass[1]);
    }
    return false;
  }

  private boolean usernameAndPasswordMatchGiven(String username, String password) {
    return username.equals(defaultUsername) && password.equals(defaultPassword);
  }

  private void handleAuthorizedRequest() {
    responseBuilder.setStatusCode("200");
    responseBuilder.setStatusMessage("OK");
    responseBuilder.setDefaultHeaders();
    responseBuilder.setBodyMessage(getLogData());
  }

  private void handleUnauthorizedRequest() {
    responseBuilder.setStatusCode("401");
    responseBuilder.setStatusMessage("Unauthorized");
    responseBuilder.setHeader("WWW-Authenticate", "Basic realm=\"Server\"");
    responseBuilder.setBodyMessage("Unauthorized");
  }

  private String getLogData() {
    String logData = "";
    for (String requestLine : RequestLogger.loggedRequests()) {
      logData += requestLine + "\n";
    }
    return logData;
  }
}