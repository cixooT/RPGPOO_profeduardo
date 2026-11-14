package rpg.personagens.inimigos;

import rpg.combate.CalculadoraDeDano;
import rpg.combate.Dano;
import rpg.personagens.Combatente;
import rpg.personagens.Personagem;
import rpg.util.Dado;

public abstract class Inimigo extends Personagem {
    protected Inimigo(String nome, int vidaMaxima, int ataque, int defesa) {
        super(nome, vidaMaxima, ataque, defesa);
    }

    public String getApresentacao() {
        return "Um " + getNome() + " aparece no caminho!";
    }

    public abstract String agir(Combatente alvo, CalculadoraDeDano calculadora, Dado dado);

    protected String golpear(Combatente alvo, int poder, CalculadoraDeDano calculadora, String acao) {
        Dano dano = calculadora.calcular(poder, alvo.getDefesa());
        int causado = alvo.receberDano(dano.valor());
        return getNome() + " " + acao + " e causa " + causado + " de dano em " + alvo.getNome() + "." + dano.marcador();
    }
}
