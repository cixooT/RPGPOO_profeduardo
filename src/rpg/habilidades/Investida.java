package rpg.habilidades;

import rpg.combate.CalculadoraDeDano;
import rpg.combate.Dano;
import rpg.personagens.Combatente;
import rpg.personagens.herois.Heroi;

public class Investida implements Habilidade {
    @Override
    public String getNome() {
        return "Investida com Escudo";
    }

    @Override
    public String getDescricao() {
        return "ataca com 120% do ataque e já fica em posição de defesa";
    }

    @Override
    public int getCusto() {
        return 8;
    }

    @Override
    public String executar(Heroi usuario, Combatente alvo, CalculadoraDeDano calculadora) {
        Dano dano = calculadora.calcular(usuario.getAtaque() * 12 / 10, alvo.getDefesa());
        int causado = alvo.receberDano(dano.valor());
        usuario.defender();
        return usuario.getNome() + " avança com o escudo, causa " + causado + " de dano e fica na defensiva." + dano.marcador();
    }
}
