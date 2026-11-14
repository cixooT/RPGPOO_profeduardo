package rpg.personagens.inimigos;

import rpg.combate.CalculadoraDeDano;
import rpg.personagens.Combatente;
import rpg.util.Dado;

public class Goblin extends Inimigo {
    private static final int CHANCE_GOLPE_SUJO = 25;

    public Goblin() {
        super("Goblin", 45, 12, 4);
    }

    @Override
    public String agir(Combatente alvo, CalculadoraDeDano calculadora, Dado dado) {
        if (dado.chance(CHANCE_GOLPE_SUJO)) {
            return golpear(alvo, getAtaque() + 5, calculadora, "dá um golpe sujo com a adaga");
        }
        return golpear(alvo, getAtaque(), calculadora, "ataca com a adaga");
    }
}
