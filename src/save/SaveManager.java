// Importando Bibliotecas
package save;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SaveManager {
    private final ObjectMapper mapper;
    private final File arquivo;
    Scanner entrada = new Scanner(System.in);

    // Construtor SaveManager
    public SaveManager() {
        this.mapper = new ObjectMapper();
        this.arquivo = new File("save.json");
    }

    // Metodo Salvar Arquivos
    public void salvar(SaveData dados) {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(arquivo, dados);
            System.out.println("Jogo salvo com Sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o jogo.");
            e.printStackTrace();
        }
    }

    // Metodo Carregar Save ou Cria um Save novo se não existe save
    public SaveData carregar() {
        try {
            if (!arquivo.exists()) {
                String nomeJogador = this.login();
                return new SaveData(nomeJogador, 1, 0, 0);
            }
            System.out.println("Progresso carregado com sucesso!");
            return mapper.readValue(arquivo, SaveData.class);
        } catch (IOException e) {
            System.out.println("Erro ao carregar o jogo.");
            e.printStackTrace();
            String nomeJogador = this.login();
            return new SaveData(nomeJogador, 1, 0, 0);
        }
    }

    // Metodo para Login
    public String login() {
        System.out.print("Digite seu nome: ");
        return entrada.nextLine();
    }
}