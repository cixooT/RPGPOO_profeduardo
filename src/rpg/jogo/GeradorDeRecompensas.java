package rpg.jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import rpg.itens.Item;
import rpg.util.Dado;

public class GeradorDeRecompensas {
    private final List<Supplier<Item>> possiveisItens = new ArrayList<>();
    private final Dado dado;

    public GeradorDeRecompensas(Dado dado) {
        this.dado = dado;
    }

    public GeradorDeRecompensas registrar(Supplier<Item> criadorDeItem) {
        possiveisItens.add(criadorDeItem);
        return this;
    }

    public Item sortear() {
        int indice = dado.rolar(0, possiveisItens.size() - 1);
        return possiveisItens.get(indice).get();
    }
}
