package rpg.itens;

import rpg.personagens.herois.Heroi;

public class PocaoDeEnergia extends ItemBase implements Usavel {
    private final int energia;

    public PocaoDeEnergia(String nome, int energia) {
        super(nome, "recupera " + energia + " de energia");
        this.energia = energia;
    }

    @Override
    public String usar(Heroi heroi) {
        int recuperado = heroi.recuperarEnergia(energia);
        return heroi.getNome() + " bebe " + getNome() + " e recupera " + recuperado + " de energia.";
    }
}
