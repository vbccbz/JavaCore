package u10.webserver;

import java.net.*;
import java.io.*;
import java.nio.charset.*;
import java.util.ArrayList;
import java.util.List;

public class WebServerApp {
  private static List<String> browserRequestBuffer = new ArrayList<String>();
  private static int counter = 0;

  public static void readFromBrowser(Socket connector) throws IOException {
    var bufferedReader = new BufferedReader(new InputStreamReader(connector.getInputStream()));
    String str = null;
    String flag = null;
    int lng = 0;
    while ((str = bufferedReader.readLine()) != null) {
      if (str.contains("GET")) {
        flag = "GET";
      } else if (str.contains("POST")) {
        flag = "POST";
      }
      if (str.contains("Content-Length:")){
        String lengthInString = str.substring("Content-Length:".length()).trim();
        lng = Integer.parseInt(lengthInString);
      }
      if (str.isEmpty() && flag.equals("GET")) {
        break;
      }
      if (str.isEmpty() && flag.equals("POST")) {
        char[] msg = new char[lng];
        bufferedReader.read(msg,0,lng);
        String s = String.valueOf(msg);
        browserRequestBuffer.add(s);
        break;
      }
      browserRequestBuffer.add(str);
    }
  }

  public static void logBufferToFile() throws Exception {
    File file = new File("data/log.txt");
    PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));
    printWriter.println("NEW LOG " + counter);
    ++counter;
    browserRequestBuffer.stream().forEach(printWriter::println);
    printWriter.println();
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
    printWriter.println("<html><head></head><body><p>:-(</p</body></html>");
    printWriter.flush();
  }

  public static void sendToBrowserForm(Socket connector) throws Exception {
    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(connector.getOutputStream()));
    printWriter.println("HTTP/1.1 200 OK");//version; answer code;
    printWriter.println("Content-Type: text/html; charset=utf-8");//version; answer code;
    printWriter.println();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("data/form.html"), Charset.forName("UTF8")));
    while (true) {
      String str = bufferedReader.readLine();
      if (str == null) break;
      printWriter.println(str);
    }
    printWriter.flush();
  }
  public static  void sendToBrowserOkForm(Socket connector)throws Exception {
    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(connector.getOutputStream(), Charset.forName("UTF-8")));
    printWriter.println("HTTP/1.1 200 OK");//version; answer code;
    printWriter.println("Content-Type: text/html; charset=utf-8");//version; answer code;
    printWriter.println();
    printWriter.println("<html><head></head><body<p>Sended!</p</body></html>");
    printWriter.flush();
  }

  public static void main(String[] args) throws Exception {
    ServerSocket listener = new ServerSocket(8081);
    while (true) {
      Socket connector = listener.accept();
      readFromBrowser(connector);
      logBufferToFile();
      if (browserRequestBuffer.get(0).contains("GET /123 HTTP/1.1")) {
        sendToBrowserOk(connector);
      } else if (browserRequestBuffer.get(0).contains("GET /index HTTP/1.1")) {
        sendToBrowserOk(connector);
      } else if (browserRequestBuffer.get(0).contains("GET /form.html HTTP/1.1")) {
        sendToBrowserForm(connector);
      } else if (browserRequestBuffer.get(0).contains("POST /form.html HTTP/1.1")) {
        sendToBrowserOkForm(connector);
      }
      else {
        sendToBrowserNotFound(connector);
      }
      browserRequestBuffer.clear();
      connector.close();
    }

    // listener.close();
  }
}
