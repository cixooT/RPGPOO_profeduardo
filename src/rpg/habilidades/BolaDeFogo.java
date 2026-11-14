package rpg.habilidades;

import rpg.combate.CalculadoraDeDano;
import rpg.combate.Dano;
import rpg.personagens.Combatente;
import rpg.personagens.herois.Heroi;

public class BolaDeFogo implements Habilidade {
    @Override
    public String getNome() {
        return "Bola de Fogo";
    }

    @Override
    public String getDescricao() {
        return "dano mágico de 200% do ataque, ignora metade da defesa";
    }

    @Override
    public int getCusto() {
        return 15;
    }

    @Override
    public String executar(Heroi usuario, Combatente alvo, CalculadoraDeDano calculadora) {
        Dano dano = calculadora.calcular(usuario.getAtaque() * 2, alvo.getDefesa() / 2);
        int causado = alvo.receberDano(dano.valor());
        return usuario.getNome() + " conjura uma Bola de Fogo que explode em " + alvo.getNome() + " causando " + causado + " de dano." + dano.marcador();
    }
}
