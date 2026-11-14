package rpg.jogo;

import java.util.ArrayList;
import java.util.List;
import rpg.itens.Equipavel;
import rpg.itens.Item;
import rpg.itens.Usavel;
import rpg.personagens.herois.Heroi;
import rpg.ui.FormatadorStatus;
import rpg.ui.InterfaceJogo;

public class Acampamento {
    private static final List<String> OPCOES = List.of(
            "Ver ficha do herói",
            "Usar item",
            "Equipar item",
            "Seguir para o próximo andar");
    private final InterfaceJogo ui;
    private final FormatadorStatus formatador;

    public Acampamento(InterfaceJogo ui, FormatadorStatus formatador) {
        this.ui = ui;
        this.formatador = formatador;
    }

    public void visitar(Heroi heroi) {
        ui.mostrarTitulo("Acampamento");
        while (true) {
            int escolha = ui.escolherOpcao("Você acende uma fogueira. O que quer fazer?", OPCOES);
            switch (escolha) {
                case 0 -> ui.mostrar(formatador.fichaDoHeroi(heroi));
                case 1 -> usarItem(heroi);
                case 2 -> equiparItem(heroi);
                default -> {
                    return;
                }
            }
        }
    }

    private void usarItem(Heroi heroi) {
        List<Usavel> usaveis = heroi.getInventario().getUsaveis();
        Usavel item = escolherItem("Qual item usar?", usaveis);
        if (item != null) {
            ui.mostrar(item.usar(heroi));
            heroi.getInventario().remover(item);
        }
    }

    private void equiparItem(Heroi heroi) {
        List<Equipavel> equipaveis = heroi.getInventario().getEquipaveis();
        Equipavel item = escolherItem("Qual item equipar?", equipaveis);
        if (item != null) {
            heroi.equipar(item).ifPresentOrElse(
                    anterior -> ui.mostrar(heroi.getNome() + " equipou " + item.getNome() + " e guardou " + anterior.getNome() + " na mochila."),
                    () -> ui.mostrar(heroi.getNome() + " equipou " + item.getNome() + "."));
        }
    }

    private <T extends Item> T escolherItem(String pergunta, List<T> itens) {
        if (itens.isEmpty()) {
            ui.mostrar("Você não tem nenhum item desse tipo.");
            return null;
        }
        List<String> opcoes = new ArrayList<>();
        for (T item : itens) {
            opcoes.add(item.getNome() + " - " + item.getDescricao());
        }
        opcoes.add("Voltar");
        int escolha = ui.escolherOpcao(pergunta, opcoes);
        return escolha == itens.size() ? null : itens.get(escolha);
    }
}
