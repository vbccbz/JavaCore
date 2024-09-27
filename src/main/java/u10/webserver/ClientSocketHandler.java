package u10.webserver;

import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Date;

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
      log("data/server_log.txt", socket.getRemoteSocketAddress() + "\n" + httpRequest.toString());
      routing(httpRequest);
    } catch (Exception exception) {
      exception.printStackTrace();
    } finally {
      if ((socket != null) || (!socket.isClosed())) {
        try {
          socket.close();
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
      }
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
    try {
      if (httpRequest.method.equals("GET")) {
        if (httpRequest.url.equals("/")) {
          httpRequest.url = "main";
          String page = fetching(httpRequest.url + ".html");
          sendRespond200(page);
        } else if (httpRequest.url.equals("/chat-room")) {
          String messages = fetching("messages.txt");
          String page = fetching(httpRequest.url + ".html");
          page = rendering(page, messages);
          sendRespond200(page);
        } else {
          String page = fetching(httpRequest.url + ".html");
          sendRespond200(page);
        }
      } else if (httpRequest.method.equals("POST")) {
        mutation("data/messages.txt", httpRequest.body);
        sendRespond303(httpRequest.url);
      } else if (httpRequest.url.contains("/api")) {
        String messages = fetching("messages.txt");
        sendRespond200(messages);
      } else {
        sendRespond400("400 Bad Request");
      }
    } catch (IOException ioException1) {
      try {
        String page = fetching("404.html");
        sendRespond404(page);
      } catch (IOException ioException2) {
        sendRespond500("500 Internal Server Error");
      }
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

  public String rendering(String page, String messages) throws IOException {
    StringBuilder stringBuilderMessages = new StringBuilder();
    String[] lines = messages.split("\n");
    for (var line : lines) {
      stringBuilderMessages.append("\n<p>").append(line).append("</p>\n");
    }
    StringBuilder stringBuilderPage = new StringBuilder(page);
    int position = stringBuilderPage.indexOf("<div class=\"chat\">") + "<div class=\"chat\">".length();
    String result = stringBuilderPage.insert(position, stringBuilderMessages.toString()).toString();
    return result;
  }

  public void mutation(String path, String body) throws IOException {
    try (PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(Path.of(path), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND));) {
      // PrintWriter printWriter1 = new PrintWriter(new FileWriter(path.toFile(), StandardCharsets.UTF_8, true));
      // PrintWriter printWriter2 = new PrintWriter(path.toFile(),StandardCharsets.UTF_8);
      printWriter.print(socket.getRemoteSocketAddress());
      printWriter.print(" ");
      Date date = new Date();
      printWriter.print(date);
      printWriter.print(" ");
      int userID = 0;
      printWriter.print("anonymous said: ");
      printWriter.println(URLDecoder.decode(body, StandardCharsets.UTF_8));
    }
  }

  public void sendRespond200(String body) throws IOException {
    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), false, StandardCharsets.UTF_8);
    printWriter.println("HTTP/1.1 200 OK");
    printWriter.println("Content-Type: text/html; charset=utf-8");
    printWriter.println("Content-Length: " + body.getBytes().length);
    printWriter.println();
    printWriter.println(body);
    printWriter.flush();
  }

  public void sendRespond303(String body) throws IOException {
    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), false, StandardCharsets.UTF_8);
    printWriter.println("HTTP/1.1 303 See Other");
    printWriter.println("Content-Type: text/html; charset=utf-8");
    printWriter.println("Location: " + body);
    printWriter.println();
    printWriter.flush();
  }

  public void sendRespond400(String body) throws IOException {
    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), false, StandardCharsets.UTF_8);
    printWriter.println("HTTP/1.1 400 Bad Request");
    printWriter.println("Content-Type: text/html; charset=utf-8");
    printWriter.println("Content-Length: " + body.getBytes().length);
    printWriter.println();
    printWriter.println(body);
    printWriter.flush();
  }

  public void sendRespond404(String body) throws IOException {
    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), false, StandardCharsets.UTF_8);
    printWriter.println("HTTP/1.1 404 Not Found");
    printWriter.println("Content-Type: text/html; charset=utf-8");
    printWriter.println("Content-Length: " + body.getBytes().length);
    printWriter.println();
    printWriter.println(body);
    printWriter.flush();
  }

  public void sendRespond500(String body) throws IOException {
    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), false, StandardCharsets.UTF_8);
    printWriter.println("HTTP/1.1 500 Internal Server Error");
    printWriter.println("Content-Type: text/html; charset=utf-8");
    printWriter.println("Content-Length: " + body.getBytes().length);
    printWriter.println();
    printWriter.println(body);
    printWriter.flush();
  }
}
