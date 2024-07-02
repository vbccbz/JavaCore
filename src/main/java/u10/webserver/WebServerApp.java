package u10.webserver;

import java.net.*;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class WebServerApp {

  public static void main(String[] args) throws Exception {
    // ServerSocket listener = new ServerSocket(8081);
    // ServerSocket listener = new ServerSocket(8081, 100, InetAddress.getByName("localhost"));
    ServerSocket listener = new ServerSocket(80, 100, InetAddress.getByName("192.168.1.6"));
    while (true) {
      Socket connector = listener.accept();
      ClientSocketHandler clientSocketHandler = new ClientSocketHandler(connector);
      clientSocketHandler.readFromSocket();
      clientSocketHandler.writeHandlerBufferToFile();
      clientSocketHandler.proceedBuffer();
      clientSocketHandler.writeToSocket();
      connector.close();
    }
    // listener.close();
  }

}
