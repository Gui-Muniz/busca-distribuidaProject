import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONArray;

public class ServerA {
    public static void main(String[] args) throws IOException {
        System.out.println("Servidor A (versão inicial) iniciado na porta 9090...");
        try (ServerSocket serverSocket = new ServerSocket(9090)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    BufferedReader clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter clientOut = new PrintWriter(clientSocket.getOutputStream(), true);
                    String searchTerm = clientIn.readLine();

                    // Contata Servidor B
                    Socket serverBSocket = new Socket("localhost", 9091);
                    PrintWriter serverBOut = new PrintWriter(serverBSocket.getOutputStream(), true);
                    BufferedReader serverBIn = new BufferedReader(new InputStreamReader(serverBSocket.getInputStream()));
                    serverBOut.println(searchTerm);
                    String responseB = serverBIn.readLine();
                    serverBSocket.close();

                    // Contata Servidor C
                    Socket serverCSocket = new Socket("localhost", 9092);
                    PrintWriter serverCOut = new PrintWriter(serverCSocket.getOutputStream(), true);
                    BufferedReader serverCIn = new BufferedReader(new InputStreamReader(serverCSocket.getInputStream()));
                    serverCOut.println(searchTerm);
                    String responseC = serverCIn.readLine();
                    serverCSocket.close();

                    // BUG: 'combinedResults' é reiniciado com a resposta de C, ignorando a resposta de B.
                    JSONArray combinedResults = new JSONArray(responseB); // Carrega B
                    combinedResults = new JSONArray(responseC);         // Sobrescreve com C

                    clientOut.println(combinedResults.toString());
                }
            }
        }
    }
}
