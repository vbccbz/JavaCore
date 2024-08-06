package u10.webserver;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ClientSocketHandler {
  private Socket socket;
  private HTTPRequest httpRequest;
  private String page;

  public ClientSocketHandler(Socket socket) {
    this.socket = socket;
  }

  public void maintask() throws Exception {
    readHTTPRequestFromSocket();
    routingHTTPRequest();
    writeToSocket();
  }

  public void readHTTPRequestFromSocket() throws Exception {
    httpRequest = HTTPRequest.parse(socket);
    httpRequest.writeHTTPRequestToFile("data/server_log.txt");
  }

  public String loadPage(String filePath) throws IOException {
    StringBuilder stringBuilder = new StringBuilder();
    Path path = Path.of(filePath);
    if (Files.isRegularFile(path)) {
      String currentLine = null;
      // why does PrintWriter has SOO, but buffered doesn't?
      BufferedReader bufferedReader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
      while((currentLine = bufferedReader.readLine())!= null){
        stringBuilder.append(currentLine);
      }
    }
    return stringBuilder.toString();
  }

  public void storeComment(String pathString) throws IOException {
    Path path = Path.of(pathString);
    if (Files.isRegularFile(path)) {
      PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND));
      printWriter.write(httpRequest.body);
      printWriter.println();
      printWriter.close();
    }
  }

  public void routingHTTPRequest() throws IOException {
    if (httpRequest.method.equals("GET")) {
      try{
        page = loadPage(httpRequest.path);
        // page.println("HTTP/1.1 200 OK");//version; answer code;
        // page.println("Content-Type: text/html; charset=utf-8");//version; answer code;
        // page.println();
      } catch (Exception exception){
        //   page.println("HTTP/1.1 404 NOT_FOUND");
        //   page.println("Content-Type: text/html; charset=utf-8");//version; answer code;
        //   page.println();
        page = loadPage("404.html");
      }

    } else if (httpRequest.method.equals("POST")) {
      storeComment("comments.txt");
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

  public void writeToSocket() throws IOException {
    PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), false, Charset.forName("UTF-8"));
    toSocket.write(page);
    toSocket.flush();
    // bufferedReader.transferTo(toSocket);
  }
}
