import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("server started");
        int port = 8085;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSoсket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSoсket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSoсket.getInputStream()))) {
                    System.out.printf("New connection accepted");

                    while (!clientSoсket.isClosed()) {
                        System.out.println("Start");
                        String clientName;
                        out.println("Write your name");
                        clientName = in.readLine();
                        out.printf("Hi, %s", clientName);
                        out.println("Are you child? (yes/no)");
                        final String age = in.readLine();
                        if (age.equals("yes")) {
                            out.printf("Welcome to the kids area, %s! Let's play!", clientName);
                        } else if (age.equals("no")) {
                            out.printf("Welcome to the adult zone, %s! Have a good rest, or a good working day!", clientName);
                        }
                        out.flush();
                        clientSoсket.close();
                        System.out.println("Connection closed");
                    }
                }
            }
        }
    }
}