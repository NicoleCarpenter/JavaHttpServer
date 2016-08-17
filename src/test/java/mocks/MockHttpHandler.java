import com.carpentern.*;

import java.util.HashMap;

public class MockHttpHandler implements Handler {
  boolean handleRouteCalled = false;

  public HttpResponse handleRoute(HttpRequest request) {
    handleRouteCalled = true;
    return new HttpResponse("HTTP/1.0", "200", "OK", new HashMap<String, String>(), "Hello World");
  }

  public boolean handleRouteCalled() {
    return handleRouteCalled;
  }
}