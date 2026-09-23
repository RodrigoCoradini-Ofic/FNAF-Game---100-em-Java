// Importando Bibliotecas
package map;
import java.util.ArrayList;

// Iniciando a classe
public class Room {
    protected String nome;
    protected Integer idRoom;
    protected String descricao;
    protected ArrayList<Room>  comodoAntes;
    protected ArrayList<Room> comodoDepois;
    protected Door door;
    protected TipoComodo tipoComodo;

    // Enum do Tipo do Cômodo
    public enum TipoComodo {
        PALCO("Palco Principal", 1, "O palco principal permanece imóvel sob luzes fracas. As cortinas estão abertas, revelando..."),
        SALAO("Salão Principal", 2, "Mesas vazias ocupam o salão, iluminadas por lâmpadas que piscam ocasionalmente. Balões e desenhos infantis decoram as paredes."),
        CAVERNA_PIRATA("Caverna Pirata", 3, "Uma pequena área temática de piratas surge atrás de cortinas vermelhas. A iluminação é quase inexistente, tornando difícil enxergar o que há atrás delas."),
        DEPOSITO_MATERIAIS("Depósito de Materiais",4,"Caixas, peças e objetos esquecidos estão empilhados em um pequeno depósito. O espaço parece apertado demais para alguém se esconder ali..."),
        AREA_DOS_BASTIDORES("Área dos Bastidores", 5, "Peças de animatrônicos e equipamentos antigos estão espalhados pelo ambiente. Rostos mecânicos observam a câmera de lugares diferentes."),
        COZINHA("Cozinha", 6, "A cozinha permanece mergulhada na escuridão. Panelas e equipamentos podem ser vistos apenas parcialmente, enquanto sons metálicos ecoam pelo ambiente."),
        BANHEIRO("Banheiro", 7, "Os banheiros estão vazios e silenciosos. Azulejos antigos e luzes fluorescentes criam uma atmosfera fria e desconfortável."),
        CORREDOR_ESQUERDO("Corredor Esquerdo", 8, "Um corredor estreito se estende até a escuridão. As paredes exibem desenhos infantis antigos, enquanto uma luz fraca ilumina apenas parte do caminho."),
        CORREDOR_DIREITO("Corredor Direito", 9,"Outro corredor vazio atravessa a pizzaria. As luzes são fracas e o silêncio contrasta com os desenhos coloridos espalhados pelas paredes."),
        CANTO_ESQUERDO("Canto Esquerdo", 10, "O corredor termina em um canto escuro, próximo ao escritório. A câmera mal consegue alcançar o fim do corredor."),
        CANTO_DIREITO("Canto Direito", 11, "O corredor termina diante da porta do escritório. A iluminação falha por alguns instantes, deixando o canto completamente escuro."),
        ESCRITORIO_PORTA_DIREITA("Porta Direita", 12, "A porta permanece diante do corredor vazio. A luz fraca revela paredes silenciosas, mas não o que pode estar escondido além delas."),
        ESCRITORIO_PORTA_ESQUERDA("Porta Esquerda", 13, "Uma pesada porta de metal separa o escritório do corredor escuro. Uma pequena luz acima dela ilumina apenas o suficiente para revelar o que se aproxima...");

        private final String nome;
        private final int idRoom;
        private final String descricaoComodo;

        // Construtor do Enum
        TipoComodo(String nome, int idRoom, String descricao) {
            this.nome = nome;
            this.idRoom = idRoom;
            this.descricaoComodo = descricao;
        }

        public String getNome() {
            return nome;
        }

        public String getDescricaoComodo() {
            return descricaoComodo;
        }

        public int getIdRoom() {
            return idRoom;
        }
    }

    // Construtor da Classe
    public Room(TipoComodo tipoComodo) {
        this.nome = tipoComodo.getNome();
        this.idRoom = tipoComodo.getIdRoom();
        this.descricao = tipoComodo.getDescricaoComodo();
        this.comodoAntes = new ArrayList<Room>();
        this.comodoDepois = new ArrayList<Room>();
        this.tipoComodo = tipoComodo;
    }

    // Metodo Adicionar Comodos Antecedentes
    public void adicionarComodoAntes(Room comodoAnte){
        this.comodoAntes.add(comodoAnte);
    }

    // Metodo Adicionar Comodos Sucessores
    public void adicionarComodoDepois(Room comodoDepoi){
        this.comodoDepois.add(comodoDepoi);
    }

    // Metodo Conectar Porta ao Room
    public void conectarDoor(Door door){
        this.door = door;
    }
    // GETTERs
    public String getDescricao() {
        return descricao;
    }

    public Door getDoor() {
        return door;
    }

    public ArrayList<Room> getComodoAntes() {
        return comodoAntes;
    }

    public ArrayList<Room> getComodoDepois() {
        return comodoDepois;
    }

    public String getNome() {
        return nome;
    }

    public int getIdRoom() {
        return idRoom;
    }
}
