package rpg.combate.acoes;

import rpg.combate.ContextoBatalha;
import rpg.personagens.herois.Heroi;

public interface AcaoDeCombate {
    String getNome();

    boolean estaDisponivel(Heroi heroi);

    boolean executar(ContextoBatalha contexto);
}
