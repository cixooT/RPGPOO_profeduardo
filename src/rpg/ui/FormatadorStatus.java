package rpg.ui;

import rpg.habilidades.Habilidade;
import rpg.itens.Equipavel;
import rpg.itens.Item;
import rpg.itens.TipoEquipamento;
import rpg.personagens.Combatente;
import rpg.personagens.herois.Heroi;

public class FormatadorStatus {
    private static final int TAMANHO_BARRA = 20;

    public String statusCombatente(Combatente combatente) {
        return String.format("%-18s HP %s %d/%d",
                combatente.getNome(),
                barra(combatente.getVida(), combatente.getVidaMaxima()),
                combatente.getVida(),
                combatente.getVidaMaxima());
    }

    public String statusHeroi(Heroi heroi) {
        return statusCombatente(heroi) + String.format("   EN %d/%d", heroi.getEnergia(), heroi.getEnergiaMaxima());
    }

    public String fichaDoHeroi(Heroi heroi) {
        StringBuilder ficha = new StringBuilder();
        ficha.append(heroi.getNome()).append(" (").append(heroi.getClasse()).append(")\n");
        ficha.append("  Vida:    ").append(heroi.getVida()).append("/").append(heroi.getVidaMaxima()).append("\n");
        ficha.append("  Energia: ").append(heroi.getEnergia()).append("/").append(heroi.getEnergiaMaxima()).append("\n");
        ficha.append("  Ataque:  ").append(heroi.getAtaque()).append("\n");
        ficha.append("  Defesa:  ").append(heroi.getDefesa()).append("\n");
        ficha.append("  Equipamentos:\n");
        for (TipoEquipamento tipo : TipoEquipamento.values()) {
            String equipado = heroi.getEquipamentos().getEquipado(tipo).map(Equipavel::getNome).orElse("(nada)");
            ficha.append("    ").append(tipo.getDescricao()).append(": ").append(equipado).append("\n");
        }
        ficha.append("  Habilidades:\n");
        for (Habilidade habilidade : heroi.getHabilidades()) {
            ficha.append("    ").append(habilidade.getNome()).append(" (").append(habilidade.getCusto())
                    .append(" EN) - ").append(habilidade.getDescricao()).append("\n");
        }
        ficha.append("  Mochila:");
        if (heroi.getInventario().estaVazio()) {
            ficha.append(" vazia");
        }
        for (Item item : heroi.getInventario().getItens()) {
            ficha.append("\n    ").append(item.getNome()).append(" - ").append(item.getDescricao());
        }
        return ficha.toString();
    }

    private String barra(int atual, int maximo) {
        int preenchido = maximo == 0 ? 0 : (int) Math.round((double) atual * TAMANHO_BARRA / maximo);
        if (atual > 0 && preenchido == 0) {
            preenchido = 1;
        }
        return "[" + "#".repeat(preenchido) + "-".repeat(TAMANHO_BARRA - preenchido) + "]";
    }
}
