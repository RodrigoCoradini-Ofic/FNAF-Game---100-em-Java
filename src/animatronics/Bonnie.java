// Importando Bibliotecas
package animatronics;
import map.Room;

public class Bonnie extends Animatronics {

    // Construtor do Bonnie
    public Bonnie(Room room) {
        super(
                "Bonnie",
                2.8,
                "Você devia ter olhado para a esquerda..."
        );
        this.localizacao = room;
    }

    // Reescrevendo a Lógica de Movimentação
    @Override
    public void movimentarFrente() {
        super.movimentarFrente();
    }
}
