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

  public void readHTTPRequestFromSocket() throws IOException {
    httpRequest = HTTPRequest.parse(socket);
  }

  public void writeHTTPRequestToFile() throws Exception {
    Path path = Path.of("data/server_log.txt");
    BufferedWriter bufferedWriter = Files.newBufferedWriter(path, Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

    bufferedWriter.write(httpRequest.method);
    bufferedWriter.write(" ");
    bufferedWriter.write(httpRequest.path);
    if (httpRequest.query != null) {
      bufferedWriter.write(httpRequest.query);
    }
    bufferedWriter.write(" ");
    bufferedWriter.write(httpRequest.version);
    bufferedWriter.newLine();

    httpRequest.headers.forEach((key, value) -> {
      try {
        bufferedWriter.write(key);
        bufferedWriter.write(": ");
        bufferedWriter.write(value);
        bufferedWriter.newLine();
      } catch (IOException exception) {
        exception.printStackTrace();
      }
    });
    bufferedWriter.newLine();

    if (httpRequest.body != null) {
      bufferedWriter.write(httpRequest.body);
      bufferedWriter.newLine();
    }
    bufferedWriter.newLine();

    bufferedWriter.flush();
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

    Path path = null;
    if (httpRequest.path.equals("/") || httpRequest.path.equals("/index")) {
      path = Path.of("data/index.html");
      BufferedReader bufferedReader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
      PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), false, Charset.forName("UTF-8"));
      printWriter.println("HTTP/1.1 200 OK");//version; answer code;
      printWriter.println("Content-Type: text/html; charset=utf-8");//version; answer code;
      printWriter.println();
      bufferedReader.transferTo(printWriter);
      printWriter.flush();
    }
    else if (httpRequest.path.equals("/gform")) {
      path = Path.of("data/gform.html");
      BufferedReader bufferedReader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
      PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), false, Charset.forName("UTF-8"));
      printWriter.println("HTTP/1.1 200 OK");//version; answer code;
      printWriter.println("Content-Type: text/html; charset=utf-8");//version; answer code;
      printWriter.println();
      bufferedReader.transferTo(printWriter);
      printWriter.flush();
    } else {
      path = Path.of("data/404.html");
      BufferedReader bufferedReader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
      PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), false, Charset.forName("UTF-8"));
      printWriter.println("HTTP/1.1 404 NOT_FOUND");//version; answer code;
      printWriter.println("Content-Type: text/html; charset=utf-8");//version; answer code;
      printWriter.println();
      bufferedReader.transferTo(printWriter);
      printWriter.flush();
    }

  }

}