package rpg.habilidades;

import rpg.combate.CalculadoraDeDano;
import rpg.combate.Dano;
import rpg.personagens.Combatente;
import rpg.personagens.herois.Heroi;

public class GolpeBrutal implements Habilidade {
    @Override
    public String getNome() {
        return "Golpe Brutal";
    }

    @Override
    public String getDescricao() {
        return "golpe pesado com 180% do ataque";
    }

    @Override
    public int getCusto() {
        return 10;
    }

    @Override
    public String executar(Heroi usuario, Combatente alvo, CalculadoraDeDano calculadora) {
        Dano dano = calculadora.calcular(usuario.getAtaque() * 18 / 10, alvo.getDefesa());
        int causado = alvo.receberDano(dano.valor());
        return usuario.getNome() + " desce a espada com tudo num Golpe Brutal e causa " + causado + " de dano." + dano.marcador();
    }
}
