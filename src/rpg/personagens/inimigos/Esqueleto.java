package rpg.personagens.inimigos;

import rpg.combate.CalculadoraDeDano;
import rpg.personagens.Combatente;
import rpg.util.Dado;

public class Esqueleto extends Inimigo {
    private static final int CHANCE_DE_DEFENDER = 20;

    public Esqueleto() {
        super("Esqueleto", 60, 14, 7);
    }

    @Override
    public String agir(Combatente alvo, CalculadoraDeDano calculadora, Dado dado) {
        if (dado.chance(CHANCE_DE_DEFENDER)) {
            defender();
            return getNome() + " ergue o escudo velho e se prepara para o próximo golpe.";
        }
        return golpear(alvo, getAtaque(), calculadora, "golpeia com a espada enferrujada");
    }
}
