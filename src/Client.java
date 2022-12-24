import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) throws UnknownHostException {
        String host = "netology.homework";
        int port = 8085;

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            System.out.println("Client connected to socket");

            String serverMessage;
            String clientMessage;
            while (true) {
                serverMessage = in.readLine();
                System.out.println(serverMessage);
                if (serverMessage.contains("name")) {
                    clientMessage = clientSocket.getInetAddress().toString();
                    out.println(clientMessage);
                    System.out.println(clientMessage);
                }
                serverMessage = in.readLine();
                if (serverMessage.contains("Are you child?")) {
                    clientMessage = "no";
                    out.println(clientMessage);
                    System.out.println(clientMessage);
                }
                serverMessage = in.readLine();
                System.out.println(serverMessage);
                break;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}