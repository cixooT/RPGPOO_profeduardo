package rpg.combate.acoes;

import java.util.ArrayList;
import java.util.List;
import rpg.combate.ContextoBatalha;
import rpg.itens.Usavel;
import rpg.personagens.herois.Heroi;

public class AcaoUsarItem implements AcaoDeCombate {
    @Override
    public String getNome() {
        return "Usar item";
    }

    @Override
    public boolean estaDisponivel(Heroi heroi) {
        return !heroi.getInventario().getUsaveis().isEmpty();
    }

    @Override
    public boolean executar(ContextoBatalha contexto) {
        Heroi heroi = contexto.heroi();
        List<Usavel> usaveis = heroi.getInventario().getUsaveis();
        List<String> opcoes = new ArrayList<>();
        for (Usavel item : usaveis) {
            opcoes.add(item.getNome() + " - " + item.getDescricao());
        }
        opcoes.add("Voltar");

        int escolha = contexto.ui().escolherOpcao("Qual item?", opcoes);
        if (escolha == usaveis.size()) {
            return false;
        }

        Usavel item = usaveis.get(escolha);
        contexto.ui().mostrar(item.usar(heroi));
        heroi.getInventario().remover(item);
        return true;
    }
}
