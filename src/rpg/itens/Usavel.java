package rpg.itens;

import rpg.personagens.herois.Heroi;

public interface Usavel extends Item {
    String usar(Heroi heroi);
}
