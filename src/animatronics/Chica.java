// Importando Bibliotecas
package animatronics;

import map.Room;

public class Chica extends Animatronics {

    // Construtor do Bonnie
    public Chica(Room room) {
        super(
                "Chica",
                1.8,
                "Eu estava esperando você olhar para o outro lado."
        );
        this.localizacao = room;
    }

    // Reescrevendo a Lógica de Movimentação
    @Override
    public void movimentarFrente() {
        super.movimentarFrente();
    }

    @Override
    public String toString() {
        return this.fraseMorte;
    }
}
