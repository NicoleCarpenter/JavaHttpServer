package carpentern.coreServer.util;

public class HtmlFormatter {
  private final String htmlHead = "<!DOCTYPE html><html><body>";
  private final String htmlFoot = "</body></html>";

  public byte[] getFormattedDirectoryFiles(String[] files, String uri) {
    return new String(htmlHead + buildFileLinks(files, uri) + htmlFoot).getBytes();
  }

  private String buildFileLinks(String[] files, String uri) {
    StringBuilder directoryContents = new StringBuilder();
    for (String file : files) {
      directoryContents.append(buildDirectoryLink(file, uri));
    }
    return directoryContents.toString();
  }

  private String buildDirectoryLink(String file, String uri) {
    String pathSlash = "";
    if (!uri.endsWith("/")) {
      pathSlash = "/";
    }
    return "<a href=\"" + uri + pathSlash + file + "\">" + file + "</a><br>";
  }

}