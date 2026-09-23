// Importando Bibliotecas
package animatronics;
import map.Room;
import java.util.ArrayList;

public class Bonnie extends Animatronics {

    // Construtor do Bonnie
    public Bonnie(Room room, double dificuldade) {
        super(
                "Bonnie",
                2.8,
                "Você devia ter olhado para a esquerda...",
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
            return;
        }
        // Bonnie só pega o 1° Destino, o Esquerdo
        this.localizacao = destinos.getFirst();
    }
}
