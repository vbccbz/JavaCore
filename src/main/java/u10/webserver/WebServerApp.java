package u10.webserver;

import java.net.*;
import java.io.*;
import java.nio.charset.*;
import java.util.ArrayList;
import java.util.List;

public class WebServerApp {
  private static List<String> buffer = new ArrayList<String>();

  public static void readFromBrowser(Socket connector) throws IOException {
    var bufferedReader = new BufferedReader(new InputStreamReader(connector.getInputStream()));
    String str = null;
    while ((str = bufferedReader.readLine()) != null) {

      if (str.isEmpty()) {
        break;
      }
      buffer.add(str);
    }
  }

  public static void logBufferToFile() throws Exception {
    File file = new File("data/log.txt");
    PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));
    printWriter.println("\nNEW LOG!");
    buffer.stream().forEach(printWriter::println);
    printWriter.flush();
  }

  public static void sendToBrowserOk(Socket connector) throws Exception {
    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(connector.getOutputStream(), Charset.forName("UTF8")));
    printWriter.println("HTTP/1.1 200 OK");//version; answer code;
    printWriter.println("Content-Type: text/html; charset=utf-8");//version; answer code;
    printWriter.println();
    printWriter.println("<html><head></head><body><p>Hello!</p</body></html>");
    printWriter.flush();
  }

  public static void sendToBrowserNotFound(Socket connector) throws Exception {
    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(connector.getOutputStream(), Charset.forName("UTF8")));
    printWriter.println("HTTP/1.1 404 NOT_FOUND");
    printWriter.println("Content-Type: text/html; charset=utf-8");//version; answer code;
    printWriter.println();
    printWriter.println("<p>sad:0</p>");
    printWriter.println("<html><head></head><body><:-(</p</body></html>");
    printWriter.flush();
  }

  public static void main(String[] args) throws Exception {
    ServerSocket listener = new ServerSocket(8081);
    while (true) {
      Socket connector = listener.accept();
      readFromBrowser(connector);
      logBufferToFile();
      if (buffer.get(0).contains("GET /index.html HTTP/1.1")) sendToBrowserOk(connector);
      else sendToBrowserNotFound(connector);
      connector.close();
    }

    // listener.close();
  }
}
