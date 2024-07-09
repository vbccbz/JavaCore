package u10.webserver;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ClientSocketHandler {
  private Socket socket;
  private HTTPRequest httpRequest;

  ClientSocketHandler(Socket socket) {
    this.socket = socket;
  }

  public void readHTTPRequestFromSocket() throws Exception {
    httpRequest = HTTPRequest.parse(socket);
  }

  public void writeHTTPRequestToFile(String pathToFile) throws Exception {
    Path path = Path.of(pathToFile);
    BufferedWriter log = Files.newBufferedWriter(path, Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

    log.write((httpRequest.method == null) ? "" : httpRequest.method);
    log.write(" ");
    log.write((httpRequest.path == null) ? "" : httpRequest.path);
    log.write((httpRequest.query == null) ? "" : httpRequest.query);
    log.write(" ");
    log.write((httpRequest.version == null) ? "" : httpRequest.version);
    log.newLine();

    httpRequest.headers.forEach((key, value) -> {
      try {
        log.write(key);
        log.write(": ");
        log.write(value);
        log.newLine();
      } catch (IOException exception) {
        exception.printStackTrace();
      }
    });
    log.newLine();

    if (httpRequest.body != null) {
      log.write(httpRequest.body);
      log.newLine();
    }
    log.newLine();

    log.flush();
  }

  public void proceedHTTPRequest() throws IOException {

    //   printWriter.println("HTTP/1.1 404 NOT_FOUND");
    //   printWriter.println("Content-Type: text/html; charset=utf-8");//version; answer code;
    //   printWriter.println();

    // toBrowserStream.println("HTTP/1.1 200 OK");//version; answer code;
    // toBrowserStream.println("Content-Type: text/html; charset=utf-8");//version; answer code;
    // toBrowserStream.println();

    // toBrowserStream.println("HTTP/1.1 302 Found");//version; answer code;

  }

  public void writeToSocket() throws IOException {
    // Path path = Path.of();
    // BufferedReader pageBufferedReader = Files.newBufferedReader();
    // if (!(Files.isRegularFile(path))) {
    if (httpRequest.path == null) {
      Path path = Path.of("data/404.html");
      BufferedReader bufferedReader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
      PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), false, Charset.forName("UTF-8"));
      printWriter.println("HTTP/1.1 404 NOT_FOUND");//version; answer code;
      printWriter.println("Content-Type: text/html; charset=utf-8");//version; answer code;
      printWriter.println();
      bufferedReader.transferTo(printWriter);
      printWriter.flush();
    } else if (httpRequest.method.equals("GET")) {
      Path path = null;
      if (httpRequest.path.equals("/") || httpRequest.path.equals("/index")) {
        path = Path.of("data/index.html");
      } else {
        path = Path.of("data" + httpRequest.path + ".html");
        if (!Files.isReadable(path)) {
          path = Path.of("data/404.html");
        }
      }
      BufferedReader bufferedReader = bufferedReader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
      PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), false, Charset.forName("UTF-8"));
      printWriter.println("HTTP/1.1 200 OK");//version; answer code;
      printWriter.println("Content-Type: text/html; charset=utf-8");//version; answer code;
      printWriter.println();
      bufferedReader.transferTo(printWriter);
      printWriter.flush();
    } else if (httpRequest.method.equals("POST")) {
      Path path = null;
      path = Path.of("data" + httpRequest.path + ".html");

      BufferedReader fromFile = Files.newBufferedReader(path, Charset.forName("UTF-8"));
      String currentLine = null;
      StringBuilder sb = new StringBuilder();
      while ((currentLine = fromFile.readLine()) != null) {
        sb.append(currentLine);
      }
      fromFile.close();

      sb.insert(sb.indexOf("</div>"), "<p>" + httpRequest.body.substring(httpRequest.body.indexOf("=")) + "</p>");
      String allString = sb.toString();

      PrintWriter toFile = new PrintWriter(Files.newBufferedWriter(path, Charset.forName("UTF-8"), StandardOpenOption.CREATE));
      toFile.write(allString);
      toFile.close();

      fromFile = Files.newBufferedReader(path, Charset.forName("UTF-8"));
      sb = new StringBuilder();
      while ((currentLine = fromFile.readLine()) != null) {
        sb.append(currentLine);
      }
      allString = sb.toString();

      PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), false, Charset.forName("UTF-8"));
      toSocket.println("HTTP/1.1 303 See Other");//version; answer code;
      toSocket.println("Location: /pform");//version; answer code;
      toSocket.println();
      toSocket.write(allString);
      // bufferedReader.transferTo(toSocket);
      toSocket.flush();
    }
  }
}