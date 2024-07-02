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
  private Map<String, String> handlerBufferMap = new LinkedHashMap<>();

  ClientSocketHandler(Socket socket) {
    this.socket = socket;
  }

  public void readFromSocket() throws IOException {
    var streamFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    String currentString = null;
    while ((currentString = streamFromClient.readLine()) != null) {
      if (!(currentString.isEmpty())) {
        var partsOfCurrentString = currentString.split(" ");
        handlerBufferMap.put(partsOfCurrentString[0], partsOfCurrentString[1]);
      } else if (currentString.isEmpty()) {
        if (handlerBufferMap.containsKey("GET")) {
          break;
        } else if (handlerBufferMap.containsKey("POST")) {
          int postMessageLength = Integer.parseInt(handlerBufferMap.get("Content-Length:"));
          char[] message = new char[postMessageLength];
          streamFromClient.read(message, 0, postMessageLength);
          handlerBufferMap.put("BrowserContent:", String.valueOf(message));
          break;
        }
      }
    }
  }

  public void writeHandlerBufferToFile() throws Exception {
    Path path = Path.of("data/server_log.txt");
    BufferedWriter bufferedWriter = Files.newBufferedWriter(path, Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    handlerBufferMap.forEach((key, value) -> {
      try {
        bufferedWriter.write(key);
        bufferedWriter.write(" ");
        bufferedWriter.write(value);
        bufferedWriter.newLine();
      } catch (IOException exception) {
        exception.printStackTrace();
      }
    });
    bufferedWriter.newLine();
    bufferedWriter.flush();
  }

  public void proceedBuffer() throws IOException {
    if (handlerBufferMap.containsKey("GET")) {

    } else if (handlerBufferMap.containsKey("PATH")) {

    }
    // if () {
    //   PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));
    //   printWriter.println("HTTP/1.1 404 NOT_FOUND");
    //   printWriter.println("Content-Type: text/html; charset=utf-8");//version; answer code;
    //   printWriter.println();
    //   printWriter.println("<html><head></head><body><p>404 NOT FOUND</p</body></html>");
    //   printWriter.flush();
    // }

    // PrintWriter toBrowserStream = new PrintWriter(connector.getOutputStream(), false, Charset.forName("UTF-8"));
    // toBrowserStream.println("HTTP/1.1 200 OK");//version; answer code;
    // toBrowserStream.println("Content-Type: text/html; charset=utf-8");//version; answer code;
    // toBrowserStream.println();

    // toBrowserStream.println("HTTP/1.1 302 Found");//version; answer code;

  }

  public void writeToSocket() throws IOException {
    // Path path = Path.of();
    // BufferedReader pageBufferedReader = Files.newBufferedReader();

    if (handlerBufferMap.containsKey("GET")) {
      Path path = Path.of("data/poster.html");
      if (!(Files.isRegularFile(path))) {
        return;
      }
      BufferedReader bufferedReader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
      PrintWriter socketPrintWriter = new PrintWriter(socket.getOutputStream(), false, Charset.forName("UTF-8"));
      // socketPrintWriter.println("HTTP/1.1 200 OK");//version; answer code;
      // socketPrintWriter.println("Content-Type: text/html; charset=utf-8");//version; answer code;
      // socketPrintWriter.println();
      bufferedReader.transferTo(socketPrintWriter);
      socketPrintWriter.flush();
    } else if (handlerBufferMap.containsKey("POST")) {
      Path path = Path.of("data/poster.html");
      if (!(Files.isRegularFile(path))) {
        return;
      }
      BufferedReader bufferedReader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
      PrintWriter socketPrintWriter = new PrintWriter(socket.getOutputStream(), false, Charset.forName("UTF-8"));
      // socketPrintWriter.println("HTTP/1.1 200 OK");//version; answer code;
      // socketPrintWriter.println("Content-Type: text/html; charset=utf-8");//version; answer code;
      // socketPrintWriter.println();
      bufferedReader.transferTo(socketPrintWriter);
      socketPrintWriter.flush();
    }
  }

}