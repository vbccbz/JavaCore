package u10.webserver;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServerApp {
  public static void main(String[] args) throws Exception {
    ServerSocket listener = new ServerSocket();
    listener.bind(new InetSocketAddress(InetAddress.getByName("0.0.0.0"), 8081), 100);//void bind(SocketAddress endpoint, int backlog)
    // ExecutorService executorService = Executors.newFixedThreadPool(2);
    Socket socket = null;
    while (true) {
      try {
        socket = listener.accept();
        ClientSocketHandler clientSocketHandler = new ClientSocketHandler(socket);
        // executorService.execute(clientSocketHandler);
        clientSocketHandler.handle();
      } catch (IOException ioException) {// only accept problems, another thread concerns about closing()
        ioException.printStackTrace();
      }
    }
    // listener.close();
  }

}
