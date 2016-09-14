package parser;

import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class HttpParamParser {

  public HashMap<String, String> getParams(String uriWithParams) {
    if (paramsExist(uriWithParams)) {
      String params = separateParamsFromUri(uriWithParams);
      String[] splitParams = splitParamSets(params);
      return buildParams(splitParams);
    } 
    return new HashMap<>();
  }

  private boolean paramsExist(String uriWithParams) {
    return uriWithParams.contains("?");
  }

  private String separateParamsFromUri(String uriWithParams) {
    String params = "";
    String[] uri = uriWithParams.split("\\?");
    if (uri.length > 1) {
      params = uri[1];
    }
    return params;
  }

  private String[] splitParamSets(String params) {
    return params.split("&");
  }

  private HashMap<String, String> buildParams(String[] params) {
    HashMap<String, String> decodedParams = new HashMap<>();
    try {
      for (String param : params) {
        if (param.contains("=")) {
          String[] parts = param.split("=");
          decodedParams.put(parts[0], decode(parts[1]));
        } else {
          decodedParams.put(decode(param), null);
        }
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return decodedParams;
  }

  private String decode(String param) throws UnsupportedEncodingException {
    return URLDecoder.decode(param, "UTF-8");
  }

}