package carpentern.response;

public interface ResponseBuilder {
  public abstract void setStatusCode(String code);
  public abstract void setStatusMessage(String message);
  public abstract void setDefaultHeaders();
  public abstract void setHeader(String key, String value);
  public abstract void setBody(byte[] bodyContent);
  public abstract HttpResponse getResponse();
  public abstract void buildOkResponse();
  public abstract void buildPartialFileResponse(String range);
  public abstract void buildUnauthorizedResponse();
  public abstract void buildMethodNotAllowedResponse();
  public abstract void buildNotFoundResponse();
  public abstract void buildPatchedContentResponse();
  public abstract void buildRedirectResponse();
  public abstract void buildCoffeeResponse();
}