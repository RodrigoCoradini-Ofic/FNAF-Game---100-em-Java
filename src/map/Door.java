// Importando Bibliotecas
package map;
import systems.Energy;

// Iniciando a porta
public class Door {
    protected boolean fechado = false;
    protected int idDoor;

    // Construtor Porta
    public Door(int idDoor) {
        this.idDoor = idDoor;
    }

    // Metodo Abrir Porta
    public void abrirPorta() {
        this.fechado = false;
    }

    // Metodo Fechar Porta
    public void fecharPorta(Energy energia) {
        if (energia.getEnergia() > 0){
            this.fechado = true;}
    }

    // Verificar Estado
    public boolean estaFechado() {
        return fechado;
    }

    // GETTERs
    public int getIdDoor() {
        return idDoor;
    }
}
