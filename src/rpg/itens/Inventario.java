package rpg.itens;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private final List<Item> itens = new ArrayList<>();

    public void adicionar(Item item) {
        itens.add(item);
    }

    public boolean remover(Item item) {
        return itens.remove(item);
    }

    public List<Item> getItens() {
        return List.copyOf(itens);
    }

    public List<Usavel> getUsaveis() {
        return filtrar(Usavel.class);
    }

    public List<Equipavel> getEquipaveis() {
        return filtrar(Equipavel.class);
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }

    private <T extends Item> List<T> filtrar(Class<T> tipo) {
        return itens.stream().filter(tipo::isInstance).map(tipo::cast).toList();
    }
}
