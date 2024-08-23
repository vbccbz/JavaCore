package u10.webserver;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServerApp {
  /*
  SS -> S | TCPView | S | TCPView
  "localhost" |
  "127.0.0.1"
  "192.168.1.7"
  "0.0.0.0"
   */
  public static void main(String[] args) throws Exception {
    // ServerSocket listener = new ServerSocket(8081);
    // ServerSocket listener = new ServerSocket(8081, 100, InetAddress.getByName("localhost"));
    // ServerSocket listener = new ServerSocket(8081, 100, InetAddress.getByName("192.168.1.2"));
    ServerSocket listener = new ServerSocket();
    listener.bind(new InetSocketAddress(InetAddress.getByName("0.0.0.0"), 8081), 100);//void bind(SocketAddress endpoint, int backlog)
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    while (true) {
      Socket socket = listener.accept();
      ClientSocketHandler clientSocketHandler = new ClientSocketHandler(socket);
      executorService.execute(clientSocketHandler);
    }
    // listener.close();
  }

}
