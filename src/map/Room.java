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

    // Construtor da Classe
    public Room(String nome, int idRoom, String descricao) {
        this.nome = nome;
        this.idRoom = idRoom;
        this.descricao = descricao;
        this.comodoAntes = new ArrayList<Room>();
        this.comodoDepois = new ArrayList<Room>();
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
