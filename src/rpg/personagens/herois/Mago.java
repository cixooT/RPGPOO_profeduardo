package rpg.personagens.herois;

import java.util.List;
import rpg.habilidades.BolaDeFogo;
import rpg.habilidades.CuraArcana;

public class Mago extends Heroi {
    public Mago(String nome) {
        super(nome, 85, 17, 5, 50, List.of(new BolaDeFogo(), new CuraArcana()));
    }

    @Override
    public String getClasse() {
        return "Mago";
    }
}
