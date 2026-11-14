package rpg.personagens.herois;

import java.util.List;
import rpg.habilidades.GolpeBrutal;
import rpg.habilidades.Investida;

public class Guerreiro extends Heroi {
    public Guerreiro(String nome) {
        super(nome, 130, 15, 10, 30, List.of(new GolpeBrutal(), new Investida()));
    }

    @Override
    public String getClasse() {
        return "Guerreiro";
    }
}
