package rpg.combate;

import rpg.util.Dado;

public class CalculadoraDanoPadrao implements CalculadoraDeDano {
    private static final int CHANCE_CRITICO = 10;
    private static final int VARIACAO_MINIMA = 85;
    private static final int VARIACAO_MAXIMA = 115;
    private final Dado dado;

    public CalculadoraDanoPadrao(Dado dado) {
        this.dado = dado;
    }

    @Override
    public Dano calcular(int poder, int defesa) {
        int base = Math.max(1, poder - defesa / 2);
        int comVariacao = Math.max(1, base * dado.rolar(VARIACAO_MINIMA, VARIACAO_MAXIMA) / 100);
        boolean critico = dado.chance(CHANCE_CRITICO);
        return new Dano(critico ? comVariacao * 3 / 2 : comVariacao, critico);
    }
}
