package rpg.habilidades;

import rpg.combate.CalculadoraDeDano;
import rpg.personagens.Combatente;
import rpg.personagens.herois.Heroi;

public class CuraArcana implements Habilidade {
    private static final int CURA_BASE = 25;

    @Override
    public String getNome() {
        return "Cura Arcana";
    }

    @Override
    public String getDescricao() {
        return "recupera " + CURA_BASE + " + ataque de vida";
    }

    @Override
    public int getCusto() {
        return 12;
    }

    @Override
    public String executar(Heroi usuario, Combatente alvo, CalculadoraDeDano calculadora) {
        int curado = usuario.curar(CURA_BASE + usuario.getAtaque());
        return "Uma luz azul envolve " + usuario.getNome() + " e cura " + curado + " de vida.";
    }
}
