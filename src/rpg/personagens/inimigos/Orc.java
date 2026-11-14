package rpg.personagens.inimigos;

import rpg.combate.CalculadoraDeDano;
import rpg.personagens.Combatente;
import rpg.util.Dado;

public class Orc extends Inimigo {
    private static final int CHANCE_PANCADA = 30;

    public Orc() {
        super("Orc", 85, 17, 8);
    }

    @Override
    public String agir(Combatente alvo, CalculadoraDeDano calculadora, Dado dado) {
        if (dado.chance(CHANCE_PANCADA)) {
            return golpear(alvo, getAtaque() * 16 / 10, calculadora, "solta uma Pancada Esmagadora");
        }
        return golpear(alvo, getAtaque(), calculadora, "acerta com o porrete");
    }
}
