package u10.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;

public class HTTPRequest {
  public String method = null;
  public String path = null;
  public String query = null;
  public String version = null;
  public Map<String, String> headers = new LinkedHashMap<>();
  public String body = null;

  public static HTTPRequest parse(Socket socket) throws Exception {
    HTTPRequest httpRequest = new HTTPRequest();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    String currentLine = bufferedReader.readLine();
    if (currentLine != null) {
      String[] url = currentLine.split(" ");
      for (int clpart = 0; clpart < url.length; ++clpart) {
        if (clpart == 0) {
          httpRequest.method = url[0];
        } else if (clpart == 1) {
          if (url[1].contains("?")) {
            httpRequest.path = url[1].substring(0, url[1].indexOf("?"));
            httpRequest.query = url[1].substring(url[1].indexOf("?"));
          } else {
            httpRequest.path = url[1];
          }
        } else if (clpart == 2) {
          httpRequest.version = url[2];
        }
      }
    }
    // while (currentLine != null && !(currentLine = bufferedReader.readLine()).isEmpty()) {
    while ((currentLine = bufferedReader.readLine()) != null && !(currentLine.isEmpty())) {
      int columnIndex = currentLine.indexOf(":");
      if (columnIndex!= -1){
        String headerName = currentLine.substring(0, columnIndex).trim();
        String headerValue = currentLine.substring(columnIndex + 1).trim();
        httpRequest.headers.put(headerName, headerValue);
      }
    }
    String contentLength = httpRequest.headers.get("Content-Length");
    if (contentLength != null) {
      char[] content = new char[Integer.parseInt(contentLength)];
      bufferedReader.read(content);
      httpRequest.body = String.valueOf(content);
    }
    return httpRequest;
  }

  @Override
  public String toString() {
    return "";
  }
}
