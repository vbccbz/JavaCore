package u10.webserver;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ClientSocketHandler {
  private Socket socket;
  // private HTTPRequest httpRequest;
  private String page;

  public ClientSocketHandler(Socket socket) {
    this.socket = socket;
  }

  public void maintask() throws Exception {
    try {
      HTTPRequest httpRequest = parsing();
      log(Path.of("data/server_log.txt"), httpRequest.toString());
      routing(httpRequest);
      rendering();//
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public HTTPRequest parsing() throws Exception {
    return HTTPRequest.parse(socket);
  }

  public void log(Path path, String message) throws IOException {
    if (Files.isRegularFile(path)) {
      try (BufferedWriter log = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {//Charset.forName("UTF-8")
        log.write(message);
        log.flush();
      }
    }
  }

  public void routing(HTTPRequest httpRequest ) throws IOException{
    if (httpRequest.method.equals("GET")) {
      try {
        page = loadPage(httpRequest.path);
        // page.println("HTTP/1.1 200 OK");//version; answer code;
        // page.println("Content-Type: text/html; charset=utf-8");//version; answer code;
        // page.println();
      } catch (Exception exception) {
        //   page.println("HTTP/1.1 404 NOT_FOUND");
        //   page.println("Content-Type: text/html; charset=utf-8");//version; answer code;
        //   page.println();
        page = loadPage("404.html");
      }

    } else if (httpRequest.method.equals("POST")) {
      mutation(httpRequest);
      // page.println("HTTP/1.1 303 See Other");//version; answer code;
      // page.println("Location: /pform");//version; answer code;
      // page.println();

      // page("HTTP/1.1 302 Found");//version; answer code;

      page = loadPage(httpRequest.path);//all about action=""

    } else {
      //   page.println("HTTP/1.1 404 NOT_FOUND");
      //   page.println("Content-Type: text/html; charset=utf-8");//version; answer code;
      //   page.println();
      page = loadPage("404.html");
    }
  }

  public void rendering() throws IOException {
    PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), false, StandardCharsets.UTF_8);
    toSocket.write(page);
    toSocket.flush();
    // bufferedReader.transferTo(toSocket);
  }

  public String loadPage(String filePath) throws IOException {
    StringBuilder stringBuilder = new StringBuilder();
    Path path = Path.of(filePath);
    if (Files.isRegularFile(path)) {
      String currentLine = null;
      // why does PrintWriter has SOO, but buffered doesn't?
      try(BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
        while ((currentLine = bufferedReader.readLine()) != null) {
          stringBuilder.append(currentLine);
        }
      }
    }
    return stringBuilder.toString();
  }

  public void mutation(HTTPRequest httpRequest) throws IOException {
    Path path = Path.of(httpRequest.path);
    if (Files.isRegularFile(path)) {
      PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND));
      printWriter.write(httpRequest.body);
      printWriter.println();
      printWriter.close();
    }
  }

}
