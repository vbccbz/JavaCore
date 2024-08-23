package u10.webserver;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ClientSocketHandler implements Runnable {
  private Socket socket;
  private HTTPRequest httpRequest;

  @Override
  public void run() {//throws Exception
    handle();
  }

  public ClientSocketHandler(Socket socket) {
    this.socket = socket;
  }

  public void handle() {
    try {
      httpRequest = HTTPRequest.parse(socket);
      log(Path.of("data/server_log.txt"), httpRequest.toString());
      routing();
      rendering();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public void log(Path path, String message) throws IOException {
    if (Files.isRegularFile(path)) {
      try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {//Charset.forName("UTF-8")
        bufferedWriter.write(message);
        bufferedWriter.flush();
      }
    }
  }

  public void routing() throws IOException {
    String page;
    if (httpRequest.method.equals("GET")) {
      if (true) {
        fetching();
        // page.println("HTTP/1.1 200 OK");//version; answer code;
        // page.println("Content-Type: text/html; charset=utf-8");//version; answer code;
        // page.println();
        rendering();
      }
    } else if (httpRequest.method.equals("POST")) {
      mutation();
      fetching();
      // page.println("HTTP/1.1 303 See Other");//version; answer code;
      // page.println("Location: /pform");//version; answer code;
      // page.println();
      rendering();

      // page("HTTP/1.1 302 Found");//version; answer code;
    } else {
      fetching();//"404.html"
      rendering();
      //   page.println("HTTP/1.1 404 NOT_FOUND");
      //   page.println("Content-Type: text/html; charset=utf-8");//version; answer code;
      //   page.println();
    }

  }

  public String fetching() throws IOException {
    StringBuilder stringBuilder = new StringBuilder();
    Path path = Path.of(httpRequest.path);
    if (Files.isRegularFile(path)) {
      String currentLine = null;
      // why does PrintWriter has SOO, but buffered doesn't?
      try (BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
        while ((currentLine = bufferedReader.readLine()) != null) {
          stringBuilder.append(currentLine);
        }
      }
    }
    return stringBuilder.toString();
  }

  public void mutation() throws IOException {
    Path path = Path.of(httpRequest.path);
    if (Files.isRegularFile(path)) {
      PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND));
      printWriter.write(httpRequest.body);
      printWriter.println();
      printWriter.close();
    }
  }

  public void rendering() throws IOException {
    PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), false, StandardCharsets.UTF_8);
    // toSocket.flush();
    // bufferedReader.transferTo(toSocket);
  }

}
