package rpg.itens;

public interface Equipavel extends Item {
    TipoEquipamento getTipo();

    int getBonusAtaque();

    int getBonusDefesa();
}
