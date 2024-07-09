package u10.webserver;

import java.net.*;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WebServerApp {

  public static void main(String[] args) throws Exception {
    // ServerSocket listener = new ServerSocket(80);
    // ServerSocket listener = new ServerSocket(80, 100, InetAddress.getByName("localhost"));
    // ServerSocket listener = new ServerSocket(80, 100, InetAddress.getByName("192.168.1.2"));
    var s = InetAddress.getByName("localhost").getHostAddress();
    ServerSocket listener = new ServerSocket(8081, 100, InetAddress.getByName("0.0.0.0"));
    // ServerSocket listener = new ServerSocket( 8081, 100,  InetAddress.getByName("localhost") );
    while (true) {
      Socket connector = listener.accept();
      ClientSocketHandler clientSocketHandler = new ClientSocketHandler(connector);
      clientSocketHandler.readHTTPRequestFromSocket();
      clientSocketHandler.writeHTTPRequestToFile("data/server_log.txt");
      clientSocketHandler.proceedHTTPRequest();
      clientSocketHandler.writeToSocket();
      connector.close();
    }
    // listener.close();
  }

}
