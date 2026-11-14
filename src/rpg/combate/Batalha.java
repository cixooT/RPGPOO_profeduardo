package rpg.combate;

import java.util.List;
import rpg.combate.acoes.AcaoDeCombate;
import rpg.personagens.herois.Heroi;
import rpg.personagens.inimigos.Inimigo;
import rpg.ui.FormatadorStatus;
import rpg.ui.InterfaceJogo;
import rpg.util.Dado;

public class Batalha {
    private static final int ENERGIA_POR_RODADA = 3;
    private final InterfaceJogo ui;
    private final CalculadoraDeDano calculadora;
    private final Dado dado;
    private final List<AcaoDeCombate> acoes;
    private final FormatadorStatus formatador;

    public Batalha(InterfaceJogo ui, CalculadoraDeDano calculadora, Dado dado, List<AcaoDeCombate> acoes, FormatadorStatus formatador) {
        this.ui = ui;
        this.calculadora = calculadora;
        this.dado = dado;
        this.acoes = List.copyOf(acoes);
        this.formatador = formatador;
    }

    public ResultadoBatalha iniciar(Heroi heroi, Inimigo inimigo) {
        ContextoBatalha contexto = new ContextoBatalha(heroi, inimigo, ui, calculadora);
        ui.mostrar(inimigo.getApresentacao());

        int rodada = 1;
        while (heroi.estaVivo() && inimigo.estaVivo()) {
            ui.mostrarTitulo("Rodada " + rodada);
            ui.mostrar(formatador.statusHeroi(heroi));
            ui.mostrar(formatador.statusCombatente(inimigo));

            heroi.encerrarDefesa();
            turnoDoHeroi(contexto);

            if (inimigo.estaVivo()) {
                inimigo.encerrarDefesa();
                ui.mostrar(inimigo.agir(heroi, calculadora, dado));
            }
            heroi.recuperarEnergia(ENERGIA_POR_RODADA);
            rodada++;
        }
        heroi.encerrarDefesa();
        return heroi.estaVivo() ? ResultadoBatalha.VITORIA : ResultadoBatalha.DERROTA;
    }

    private void turnoDoHeroi(ContextoBatalha contexto) {
        boolean turnoConcluido = false;
        while (!turnoConcluido) {
            List<AcaoDeCombate> disponiveis = acoes.stream()
                    .filter(acao -> acao.estaDisponivel(contexto.heroi()))
                    .toList();
            List<String> nomes = disponiveis.stream().map(AcaoDeCombate::getNome).toList();
            int escolha = ui.escolherOpcao("O que " + contexto.heroi().getNome() + " vai fazer?", nomes);
            turnoConcluido = disponiveis.get(escolha).executar(contexto);
        }
    }
}
