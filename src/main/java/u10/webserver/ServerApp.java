package u10.webserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.time.LocalTime;
import java.util.*;

public class ServerApp {

    public static void main(String[] args) throws IOException {
        PrintStream printStream = System.out;
        ServerSocket serverSocket = new ServerSocket(8081);
        Socket socket = serverSocket.accept();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // while (bufferedReader.ready()) {
        //     System.out.println(bufferedReader.readLine());
        // }

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("HTTP/1.1 200 OK\n".getBytes(Charset.forName("UTF-8")));
        outputStream.write("Content-Type: text/html; charset=utf-8\n".getBytes(Charset.forName("UTF-8")));
        outputStream.write("\n".getBytes(Charset.forName("UTF-8")));
        outputStream.write("<p>OMG!</p>\n".getBytes(Charset.forName("UTF-8")));

        // PrintWriter outputStream = new PrintWriter(socket.getOutputStream());
        // outputStream.println("HTTP/1.1 200 OK");
        // outputStream.println("Content-Type: text/html; charset=utf-8");
        // outputStream.println();
        // outputStream.print("Hello!");

        outputStream.flush();

        bufferedReader.close();
        outputStream.close();
        serverSocket.close();
    }
}
