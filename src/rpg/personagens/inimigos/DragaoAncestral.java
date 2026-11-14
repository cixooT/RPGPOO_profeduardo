package rpg.personagens.inimigos;

import rpg.combate.CalculadoraDeDano;
import rpg.personagens.Combatente;
import rpg.util.Dado;

public class DragaoAncestral extends Chefe {
    private static final int BONUS_FURIA = 5;
    private static final int INTERVALO_SOPRO = 3;
    private int turnos;
    private boolean furioso;

    public DragaoAncestral() {
        super("Dragão Ancestral", 160, 18, 9);
    }

    @Override
    protected String getFalaDeEntrada() {
        return "Mais um tolo atrás do meu tesouro? Suas cinzas vão decorar meu salão.";
    }

    @Override
    public int getAtaque() {
        return super.getAtaque() + (furioso ? BONUS_FURIA : 0);
    }

    @Override
    public String agir(Combatente alvo, CalculadoraDeDano calculadora, Dado dado) {
        turnos++;
        StringBuilder mensagem = new StringBuilder();
        if (!furioso && getVida() <= getVidaMaxima() / 2) {
            furioso = true;
            mensagem.append(getNome()).append(" ruge e entra em FÚRIA! O ataque dele aumentou.\n");
        }
        if (turnos % INTERVALO_SOPRO == 0) {
            mensagem.append(golpear(alvo, getAtaque() * 17 / 10, calculadora, "solta um SOPRO DE FOGO"));
        } else {
            mensagem.append(golpear(alvo, getAtaque(), calculadora, "ataca com as garras"));
        }
        if (turnos % INTERVALO_SOPRO == INTERVALO_SOPRO - 1) {
            mensagem.append("\n").append(getNome()).append(" começa a puxar o ar... algo grande vem aí!");
        }
        return mensagem.toString();
    }
}
