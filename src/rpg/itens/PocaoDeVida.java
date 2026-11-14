package rpg.itens;

import rpg.personagens.herois.Heroi;

public class PocaoDeVida extends ItemBase implements Usavel {
    private final int cura;

    public PocaoDeVida(String nome, int cura) {
        super(nome, "recupera " + cura + " de vida");
        this.cura = cura;
    }

    @Override
    public String usar(Heroi heroi) {
        int curado = heroi.curar(cura);
        return heroi.getNome() + " bebe " + getNome() + " e recupera " + curado + " de vida.";
    }
}
