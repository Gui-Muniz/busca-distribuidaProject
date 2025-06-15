import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONArray;

public class ServerA {
    public static void main(String[] args) throws IOException {
        System.out.println("Servidor A (Coordenador) iniciado...");
        try (ServerSocket serverSocket = new ServerSocket(9090)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Cliente conectado ao Servidor A");
                    BufferedReader clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter clientOut = new PrintWriter(clientSocket.getOutputStream(), true);

                    String searchTerm = clientIn.readLine();
                    System.out.println("Recebida busca por: '" + searchTerm + "'. Repassando para os servidores B e C.");

                    String responseB = queryServer("localhost", 9091, searchTerm);
                    String responseC = queryServer("localhost", 9092, searchTerm);

                    JSONArray resultsB = new JSONArray(responseB);
                    JSONArray resultsC = new JSONArray(responseC);
                    JSONArray combinedResults = new JSONArray();

                    for (int i = 0; i < resultsB.length(); i++) {
                        combinedResults.put(resultsB.getJSONObject(i));
                    }
                    for (int i = 0; i < resultsC.length(); i++) {
                        combinedResults.put(resultsC.getJSONObject(i));
                    }

                    clientOut.println(combinedResults.toString());
                    System.out.println("Resultados combinados enviados para o Cliente.");
                } catch (IOException e) {
                    System.err.println("Erro na comunicação com o cliente: " + e.getMessage());
                }
            }
        }
    }

    private static String queryServer(String host, int port, String searchTerm) {
        try (Socket socket = new Socket(host, port)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println(searchTerm);
            return in.readLine();
        } catch (IOException e) {
            System.err.println("Erro ao conectar ao servidor em " + host + ":" + port + " -> " + e.getMessage());
            return "[]";
        }
    }
}

