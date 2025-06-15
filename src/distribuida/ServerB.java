import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class ServerB {
    private static final String DATA_FILE = "dados_servidor_b.json";

    public static void main(String[] args) throws IOException {
        System.out.println("Servidor B iniciado...");
        try (ServerSocket serverSocket = new ServerSocket(9091)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Servidor A conectado ao Servidor B");
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                    String searchTerm = in.readLine();
                    System.out.println("Buscando em B por: " + searchTerm);

                    String jsonContent = new String(Files.readAllBytes(Paths.get(DATA_FILE)));
                    JSONArray articles = new JSONArray(jsonContent);
                    JSONArray results = new JSONArray();

                    for (int i = 0; i < articles.length(); i++) {
                        JSONObject article = articles.getJSONObject(i);
                        String title = article.optString("title", "");
                        String abstractText = article.optString("abstract", "");

                        if (title.toLowerCase().contains(searchTerm.toLowerCase()) || abstractText.toLowerCase().contains(searchTerm.toLowerCase())) {
                            results.put(article);
                        }
                    }

                    out.println(results.toString());
                    System.out.println("Resultados de B enviados para o Servidor A.");
                    
                } catch (IOException e) {
                    System.err.println("Erro na comunicação no Servidor B: " + e.getMessage());
                }
            }
        }
    }
}
