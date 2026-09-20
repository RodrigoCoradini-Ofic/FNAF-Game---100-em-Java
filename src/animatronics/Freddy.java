// Importando Bibliotecas
package animatronics;

import map.Room;

public class Freddy extends Animatronics {

    // Construtor do Bonnie
    public Freddy(Room room) {
        super(
                "Freddy",
                1.6,
                "A festa só termina quando eu digo."
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
