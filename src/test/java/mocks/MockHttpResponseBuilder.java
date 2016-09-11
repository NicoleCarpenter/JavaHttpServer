package response;

import response.HttpResponse;
import response.ResponseBuilder;

public class MockHttpResponseBuilder implements ResponseBuilder {
  private boolean setStatusCodeCalled;
  private boolean setStatusMessageCalled;
  private boolean setDefaultHeadersCalled;
  private boolean setHeaderCalled;
  private boolean setBodyCalled;
  private boolean getResponseCalled;
  private boolean buildOkResponseCalled;
  private boolean buildPartialFileResponseCalled;
  private boolean buildUnauthorizedResponseCalled;
  private boolean buildMethodNotAllowedResponseCalled;
  private boolean buildNotFoundResponseCalled;
  private boolean buildPatchedContentResponseCalled;
  private boolean buildRedirectResponseCalled;
  private boolean buildCoffeeResponseCalled;
  
  public MockHttpResponseBuilder() {
    setStatusCodeCalled = false;
    setStatusMessageCalled = false;
    setDefaultHeadersCalled = false;
    setHeaderCalled = false;
    setBodyCalled = false;
    getResponseCalled = false;
    buildOkResponseCalled = false;
    buildPartialFileResponseCalled = false;
    buildUnauthorizedResponseCalled = false;
    buildMethodNotAllowedResponseCalled = false;
    buildNotFoundResponseCalled = false;
    buildPatchedContentResponseCalled = false;
    buildRedirectResponseCalled = false;
    buildCoffeeResponseCalled = false;
  }

  public void setStatusCode(String code) {
    setStatusCodeCalled = true;
  }

  public void setStatusMessage(String message) {
    setStatusMessageCalled = true;
  }

  public void setDefaultHeaders() {
    setDefaultHeadersCalled = true;
  }

  public void setHeader(String key, String value) {
    setHeaderCalled = true;
  }

  public void setBody(byte[] bodyContent) {
    setBodyCalled = true;
  }

  public HttpResponse getResponse() {
    getResponseCalled = true;
    return new HttpResponse();
  }

  public void buildOkResponse() {
    buildOkResponseCalled = true;
  }

  public void buildPartialFileResponse(String range) {
    buildPartialFileResponseCalled = true;
  }

  public void buildUnauthorizedResponse() {
    buildUnauthorizedResponseCalled = true;
  }

  public void buildMethodNotAllowedResponse() {
    buildMethodNotAllowedResponseCalled = true;
  }

  public void buildNotFoundResponse() {
    buildNotFoundResponseCalled = true;
  }

  public void buildPatchedContentResponse() {
    buildPatchedContentResponseCalled = true;
  }

  public void buildRedirectResponse() {
    buildRedirectResponseCalled = true;
  }

  public void buildCoffeeResponse() {
    buildCoffeeResponseCalled = true;
  }

}
