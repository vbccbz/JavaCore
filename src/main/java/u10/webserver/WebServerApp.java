package u10.webserver;

import java.net.*;
import java.io.*;
import java.nio.charset.*;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class WebServerApp {

    public static void handler(Socket socket) throws IOException {
        System.out.println("Accepted!");
        BufferedReader input = null;
        PrintWriter output = null;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            output = new PrintWriter(socket.getOutputStream());

            Deque<String> strings = new ArrayDeque<>();
            for (int i = 0; input.ready(); ++i) {
                strings.addLast(input.readLine());
            }
            //String[] parts = strings.getFirst().split(" ");// first line: GET index.html HTTP
            // Path path = Paths.get(parts[1]);// file not found
            //Path path = Paths.get(".", parts[1]);// index.html
            //Path path = Paths.get(".\\www", parts[1]);
            //Path path = Paths.get("C:\\Users\\User\\IdeaProjects\\MyJava11\\www", parts[1]);
            //Path path = Paths.get("\\MyJava11\\www\\dir", parts[1]);

            // if (!Files.exists(path)) {
            if (false) {
                output.println("HTTP/1.1 404 NOT_FOUND");
                output.println("Content-Type: text/html; charset=utf-8");//version; answer code;
                output.println();
                // output.println("<p> THERE IS NO </p>");
                output.println("<p>  NO </p>");
                // output.flush();
            } else {
                output.println("HTTP/1.1 200 OK");//version; answer code;
                output.println("Content-Type: text/html; charset=utf-8");//version; answer code;
                output.println();// an empty line, which serves as the separator between the HTTP headers and the actual content of the response body. This empty line is a required part of the HTTP response format and is used to indicate the end of the headers section.
                // output.flush();//  а вот флаш необязателен


                LocalTime localTime = LocalTime.now();
                output.print(localTime.toString());
                // java 11
                // BufferedReader trans = Files.newBufferedReader(path);
                // trans.transferTo(output);

                output.flush();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            input.close();// stop button is shown until this
            output.close();// or here
        }

        // while (!input.ready()) {
        //     System.out.println("waiting buffer...");
        //     ;
        // }
        // while ((str = input.readLine()) == null) {
        //     System.out.println("waiting buffer...");
        //     ;
        // }
        System.out.println("END");
    }

    public static void send_page(Socket socket)  throws IOException{

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));

        List<String> list = new ArrayList<>();
        while (bufferedReader.ready()) {
            list.add(bufferedReader.readLine());
        }
        // String chrome_get = list.get(0);
        // String page_name = chrome_get.split(" ")[1];
        // File file = new File("."+ page_name);
        BufferedReader file_page = new BufferedReader(new FileReader("index.html", Charset.forName("UTF-8")));

        String tmp = null;
        ArrayList<String> page = new ArrayList<>();
        while( (tmp = file_page.readLine())!= null){
            page.add(tmp);
        }

        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Content-Type: text/html; charset=utf-8");
        printWriter.println();

        // printWriter.println("<p>Kek!</p>");
        page.forEach(printWriter::println);

        printWriter.flush();

        printWriter.close();
        bufferedReader.close();
        socket.close();
    }

    public static void receive_form(Socket socket)  throws IOException{

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));

        List<String> list = new ArrayList<>();
        while (bufferedReader.ready()) {
            list.add(bufferedReader.readLine());
        }

        bufferedReader.close();

        // PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));
        // printWriter.println("HTTP/1.1 200 OK");
        // printWriter.println("Content-Type: text/html; charset=utf-8");
        // printWriter.println();

        // printWriter.println("<p>Kek!</p>");



        socket.close();
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        serverSocket = new ServerSocket(8081);

        Socket server = serverSocket.accept();
        send_page(server);
        serverSocket.close();

        serverSocket = new ServerSocket(8081);
        Socket client = serverSocket.accept();
        receive_form(client);




//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        try (ServerSocket serverSocket = new ServerSocket(8080)) {
//            for (int i = 0; i < 2; ++i) {
//                Socket socket = serverSocket.accept();
//                executorService.execute(()-> Server.handler(socket));
//            }
//        } catch (IOException exception) {// close socket
//            exception.printStackTrace();
//        }
//        executorService.shutdown();
    }
}
