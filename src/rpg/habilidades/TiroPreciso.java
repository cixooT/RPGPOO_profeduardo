package rpg.habilidades;

import rpg.combate.CalculadoraDeDano;
import rpg.combate.Dano;
import rpg.personagens.Combatente;
import rpg.personagens.herois.Heroi;

public class TiroPreciso implements Habilidade {
    @Override
    public String getNome() {
        return "Tiro Preciso";
    }

    @Override
    public String getDescricao() {
        return "flecha no ponto fraco com 150% do ataque, ignora toda a defesa";
    }

    @Override
    public int getCusto() {
        return 10;
    }

    @Override
    public String executar(Heroi usuario, Combatente alvo, CalculadoraDeDano calculadora) {
        Dano dano = calculadora.calcular(usuario.getAtaque() * 15 / 10, 0);
        int causado = alvo.receberDano(dano.valor());
        return usuario.getNome() + " mira com calma e acerta um Tiro Preciso causando " + causado + " de dano." + dano.marcador();
    }
}
