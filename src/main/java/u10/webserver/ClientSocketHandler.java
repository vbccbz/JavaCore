package u10.webserver;

import u2.homework.Participant;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ClientSocketHandler implements Runnable {
  private Socket socket;
  // private HTTPRequest httpRequest;
  // private String page;

  @Override
  public void run() {//what would happen if the handle throws an Exception?
    handle();
  }

  public ClientSocketHandler(Socket socket) {
    this.socket = socket;
  }

  public void handle() {
    try {
      HTTPRequest httpRequest = HTTPRequest.parse(socket);
      log("data/server_log.txt", httpRequest.toString());
      routing(httpRequest);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public void log(String path, String message) throws IOException {
    Path path_to_log = Path.of(path);
    if (Files.isRegularFile(path_to_log)) {
      try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path_to_log, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {//Charset.forName("UTF-8")
        bufferedWriter.write(message);
        bufferedWriter.flush();
      }
    }
  }

  public void routing(HTTPRequest httpRequest) throws IOException {
    String page;
    switch (httpRequest.method) {
      case "GET":
        try {
          page = fetching(httpRequest.path + ".html");
          sendRespond(200, page);
        } catch (IOException exception) {
          try {
            page = fetching("404.html");
            sendRespond(404, page);
          } catch (IOException exception2) {
            sendRespond(505, "500 Internal Server Error");//kill server
            sendRespond(505, "500 Internal Server Error");
          }
        }
        break;
      case "POST":
        // mutation();
        sendRespond(303,  httpRequest.path);
        break;
      default:
        sendRespond(400, "400 Bad Request");
        break;
    }
  }

  public String fetching(String path) throws IOException {
    StringBuilder stringBuilder = new StringBuilder();
    String currentLine = null;
    // why does PrintWriter has SOO, but buffered doesn't?
    Path ppath = Path.of("data", path);
    try (BufferedReader bufferedReader = Files.newBufferedReader(ppath, StandardCharsets.UTF_8)) {
      while ((currentLine = bufferedReader.readLine()) != null) {
        stringBuilder.append(currentLine);
        stringBuilder.append('\n');
      }
    }
    return stringBuilder.toString();
  }

  public void rendering() throws IOException {
    // String page = fetching(httpRequest.path);
    String base = fetching("data/messages.txt");

  }

  public void mutation(String path, String body) throws IOException {
    try (PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(Path.of(path), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND));) {
      // PrintWriter printWriter1 = new PrintWriter(new FileWriter(path.toFile(), StandardCharsets.UTF_8, true));
      // PrintWriter printWriter2 = new PrintWriter(path.toFile(),StandardCharsets.UTF_8);
      printWriter.println("anonymous said: ");
      printWriter.println(body);// if null - what to do?
    }
  }

  public void sendRespond(int statusCode, String body) {
    try (PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), false, StandardCharsets.UTF_8);) {
      switch (statusCode) {
        case 200:
          printWriter.println("HTTP/1.1 200 OK");
          printWriter.println("Content-Type: text/html; charset=utf-8");
          printWriter.println("Content-Length: " + body.getBytes().length);
          printWriter.println();
          printWriter.println(body);
          break;
        case 303:
          printWriter.println("HTTP/1.1 303 See Other");
          printWriter.println("Content-Type: text/html; charset=utf-8");
          printWriter.println("Location: " + body);
          printWriter.println();
          break;
        case 400:
          printWriter.println("HTTP/1.1 400 Bad Request");
          printWriter.println("Content-Type: text/html; charset=utf-8");
          printWriter.println("Content-Length: " + body.getBytes().length);
          printWriter.println();
          printWriter.println(body);
          break;
        case 404:
          printWriter.println("HTTP/1.1 404 Not Found");
          printWriter.println("Content-Type: text/html; charset=utf-8");
          printWriter.println("Content-Length: " + body.getBytes().length);
          printWriter.println();
          printWriter.println(body);
          break;
        case 500:
          printWriter.println("HTTP/1.1 500 Internal Server Error");
          printWriter.println("Content-Type: text/html; charset=utf-8");
          printWriter.println("Content-Length: " + body.getBytes().length);
          printWriter.println();
          printWriter.println(body);
          break;
        default:
          throw new IllegalArgumentException();
      }
      printWriter.flush();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

}
