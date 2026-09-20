// Importando Bibliotecas
package animatronics;
import map.Room;

public class Foxy extends Animatronics {

    // Construtor do Bonnie
    public Foxy(Room room) {
        super(
                "Foxy",
                2.0,
                "Corra... se conseguir!"
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
