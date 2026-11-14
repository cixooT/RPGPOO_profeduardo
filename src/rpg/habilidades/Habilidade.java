package rpg.habilidades;

import rpg.combate.CalculadoraDeDano;
import rpg.personagens.Combatente;
import rpg.personagens.herois.Heroi;

public interface Habilidade {
    String getNome();

    String getDescricao();

    int getCusto();

    String executar(Heroi usuario, Combatente alvo, CalculadoraDeDano calculadora);
}
