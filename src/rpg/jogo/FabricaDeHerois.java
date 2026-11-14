package rpg.jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import rpg.itens.Item;
import rpg.personagens.herois.Heroi;

public class FabricaDeHerois {
    private final List<OpcaoDeHeroi> opcoes = new ArrayList<>();
    private final Supplier<List<Item>> kitInicial;

    public FabricaDeHerois(Supplier<List<Item>> kitInicial) {
        this.kitInicial = kitInicial;
    }

    public FabricaDeHerois registrar(String classe, String descricao, Function<String, Heroi> criador) {
        opcoes.add(new OpcaoDeHeroi(classe, descricao, criador));
        return this;
    }

    public List<String> getDescricoes() {
        return opcoes.stream().map(opcao -> opcao.classe() + " - " + opcao.descricao()).toList();
    }

    public Heroi criar(int indice, String nome) {
        Heroi heroi = opcoes.get(indice).criador().apply(nome);
        kitInicial.get().forEach(heroi.getInventario()::adicionar);
        return heroi;
    }

    private record OpcaoDeHeroi(String classe, String descricao, Function<String, Heroi> criador) {
    }
}
