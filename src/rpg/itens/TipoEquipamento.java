package rpg.itens;

public enum TipoEquipamento {
    ARMA("Arma"),
    ARMADURA("Armadura");

    private final String descricao;

    TipoEquipamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
