// Importando Bibliotecas
package systems;

public class Relogio {
    private int hora;
    private boolean noiteTerminou;

    // Construtor da Classe
    public Relogio() {
        this.hora = 0;
        this.noiteTerminou = false;
    }

    // Metodo para passar a Hora
    public void passarHora(){
        this.hora++;

        // Vencer por Tempo
        if(this.hora == 6){
            this.noiteTerminou = true;
        }
    }

    // GETTERs
    public int getHora() {
        return hora;
    }

    public boolean isNoiteTerminou() {
        return noiteTerminou;
    }
}
