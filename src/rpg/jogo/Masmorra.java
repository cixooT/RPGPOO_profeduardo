package rpg.jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import rpg.personagens.inimigos.Inimigo;

public class Masmorra {
    private final List<Supplier<Inimigo>> andares = new ArrayList<>();

    public Masmorra adicionarAndar(Supplier<Inimigo> criadorDeInimigo) {
        andares.add(criadorDeInimigo);
        return this;
    }

    public List<Inimigo> gerarEncontros() {
        return andares.stream().map(Supplier::get).toList();
    }
}
