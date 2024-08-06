package u10.webserver;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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
            httpRequest.path = url[1].substring(0, url[1].indexOf("?") + 1);
            httpRequest.query = url[1].substring(url[1].indexOf("?") + 1);
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
      if (columnIndex != -1) {
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

  public void writeHTTPRequestToFile(String pathToFile) throws Exception {
    Path path = Path.of(pathToFile);
    if (Files.isRegularFile(path)){
      BufferedWriter log = Files.newBufferedWriter(path, Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
      log.write(toString());
      log.flush();
    }

  }

  @Override
  public String toString() {
    StringWriter str = new StringWriter();
    PrintWriter strDecorator = new PrintWriter(str);

    if (method != null) {
      strDecorator.write(method);
      strDecorator.write(" ");
    }
    if (path != null) {
      strDecorator.write(path);
    }
    if (query != null) {
      strDecorator.write("?");
      strDecorator.write(query);
      strDecorator.write(" ");
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
