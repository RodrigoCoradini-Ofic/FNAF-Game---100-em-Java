// Importando Bibliotecas
package map;

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
    public void fecharPorta() {
        this.fechado = true;
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
