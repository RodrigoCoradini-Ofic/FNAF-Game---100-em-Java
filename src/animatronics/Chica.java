// Importando Bibliotecas
package animatronics;
import map.Room;

import java.util.ArrayList;

public class Chica extends Animatronics {

    // Construtor do Bonnie
    public Chica(Room room, double dificuldade) {
        super(
                "Chica",
                1.8,
                "Eu estava esperando você olhar para o outro lado.",
                dificuldade
        );
        this.localizacao = room;
    }

    // Reescrevendo a Lógica de Movimentação
    @Override
    public void movimentarFrente() {
        // Se Movimenta: Com N% de chance de ir mais perto do jogador
        ArrayList<Room> destinos = localizacao.getComodoDepois();
        Room origem = this.localizacao;
        if (destinos.isEmpty()) {
            return;}
        if (destinos.size() == 1) {
            this.localizacao = destinos.getFirst();
            return;
        } else {
            // Chica Sempre escolhe o 2° Destino, a Direita
            this.localizacao = destinos.get(1);
        }
    }
}
