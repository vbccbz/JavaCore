package u10.webserver;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class HTTPRequest {
  public String method = null;
  public String url = null;
  public String version = null;
  public Map<String, String> headers = new LinkedHashMap<>();
  public String body = null;

  public static HTTPRequest parse(Socket socket) throws Exception {
    HTTPRequest httpRequest = new HTTPRequest();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
    String currentLine = bufferedReader.readLine();
    String[] url = currentLine.split(" ");
    httpRequest.method = url[0];
    httpRequest.url = url[1];
    httpRequest.version = url[2];

    while ((currentLine = bufferedReader.readLine()) != null && !(currentLine.isEmpty())) {
      String[] headerParts = currentLine.split(":");
      String headerName = headerParts[0].trim();
      String headerValue = headerParts[1].trim();
      httpRequest.headers.put(headerName, headerValue);
    }

    String contentLength = httpRequest.headers.get("Content-Length");
    if (contentLength != null) {
      int contentLengthInt = Integer.parseInt(contentLength);
      if (contentLengthInt != 0) {
        char[] content = new char[contentLengthInt];
        bufferedReader.read(content);
        httpRequest.body = String.valueOf(content);
      } else {
        httpRequest.body = "";
      }
    }

    return httpRequest;
  }


  @Override
  public String toString() {
    StringWriter str = new StringWriter();
    PrintWriter strDecorator = new PrintWriter(str);

    if (method != null) {
      strDecorator.write(method);
      strDecorator.write(" ");
    }
    if (url != null) {
      strDecorator.write(url);
    }
    if (version != null) {
      strDecorator.write(" ");
    }
    strDecorator.println();

    headers.forEach((key, value) -> {
      strDecorator.write(key);
      strDecorator.write(": ");
      strDecorator.write(value);
      strDecorator.println();
    });
    strDecorator.println();

    if (body != null) {
      strDecorator.write(body);
      strDecorator.println();
    }
    strDecorator.println();

    strDecorator.flush();

    return str.toString();
  }
}
