package rpg.personagens.herois;

import java.util.List;
import rpg.habilidades.DisparoDuplo;
import rpg.habilidades.TiroPreciso;

public class Arqueiro extends Heroi {
    public Arqueiro(String nome) {
        super(nome, 100, 16, 7, 40, List.of(new TiroPreciso(), new DisparoDuplo()));
    }

    @Override
    public String getClasse() {
        return "Arqueiro";
    }
}
