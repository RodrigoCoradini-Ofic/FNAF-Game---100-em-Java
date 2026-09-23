// Importando Bibliotecas
package animatronics;

import map.Room;

public class Freddy extends Animatronics {

    // Construtor do Bonnie
    public Freddy(Room room, double dificuldade) {
        super(
                "Freddy",
                1.6,
                "A festa só termina quando eu digo.",
                dificuldade
        );
        this.localizacao = room;
    }
}
