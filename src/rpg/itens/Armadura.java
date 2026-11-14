package rpg.itens;

public class Armadura extends ItemBase implements Equipavel {
    private final int bonusDefesa;

    public Armadura(String nome, int bonusDefesa) {
        super(nome, "armadura, +" + bonusDefesa + " de defesa");
        this.bonusDefesa = bonusDefesa;
    }

    @Override
    public TipoEquipamento getTipo() {
        return TipoEquipamento.ARMADURA;
    }

    @Override
    public int getBonusAtaque() {
        return 0;
    }

    @Override
    public int getBonusDefesa() {
        return bonusDefesa;
    }
}
