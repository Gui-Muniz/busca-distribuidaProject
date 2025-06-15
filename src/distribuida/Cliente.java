import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        System.out.println("Cliente iniciado. Digite o termo de busca ou 'sair' para terminar.");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String searchTerm = scanner.nextLine();

            if ("sair".equalsIgnoreCase(searchTerm.trim())) {
                break;
            }

            if (searchTerm.trim().isEmpty()) {
                continue;
            }

            try (Socket socket = new Socket("localhost", 9090)) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Envia o termo de busca para o Servidor A
                out.println(searchTerm);

                // Lê a resposta do Servidor A
                String response = in.readLine();
                
                // Imprime a resposta
                System.out.println("----- Resultados da Busca -----");
                System.out.println(response);
                System.out.println("-----------------------------\n");

            } catch (IOException e) {
                System.err.println("Não foi possível conectar ao Servidor A: " + e.getMessage());
            }
        }
        
        scanner.close();
        System.out.println("Cliente encerrado.");
    }
}
