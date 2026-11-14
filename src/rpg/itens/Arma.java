package rpg.itens;

public class Arma extends ItemBase implements Equipavel {
    private final int bonusAtaque;

    public Arma(String nome, int bonusAtaque) {
        super(nome, "arma, +" + bonusAtaque + " de ataque");
        this.bonusAtaque = bonusAtaque;
    }

    @Override
    public TipoEquipamento getTipo() {
        return TipoEquipamento.ARMA;
    }

    @Override
    public int getBonusAtaque() {
        return bonusAtaque;
    }

    @Override
    public int getBonusDefesa() {
        return 0;
    }
}
