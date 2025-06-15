
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONArray;

public class ServerB {
    public static void main(String[] args) throws IOException {
        System.out.println("Servidor B (versão inicial) iniciado na porta 9091...");
        try (ServerSocket serverSocket = new ServerSocket(9091)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    String searchTerm = in.readLine();

                    // BUG: JSON está fixo e vazio, não lê o arquivo de dados.
                    String jsonContent = "[]";
                    JSONArray articles = new JSONArray(jsonContent);
                    JSONArray results = new JSONArray();
                    // A lógica de busca nem chegaria a ser executada aqui.

                    out.println(results.toString());
                }
            }
        }
    }
}
