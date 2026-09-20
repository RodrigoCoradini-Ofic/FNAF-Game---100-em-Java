// Importando Bibliotecas
package map;

// Iniciando a porta
public class Door {
    private boolean fechado = false;

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
}
