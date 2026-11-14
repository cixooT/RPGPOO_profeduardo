package rpg.combate;

import rpg.personagens.herois.Heroi;
import rpg.personagens.inimigos.Inimigo;
import rpg.ui.InterfaceJogo;

public record ContextoBatalha(Heroi heroi, Inimigo inimigo, InterfaceJogo ui, CalculadoraDeDano calculadora) {
}
