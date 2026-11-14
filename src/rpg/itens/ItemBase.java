package rpg.itens;

public abstract class ItemBase implements Item {
    private final String nome;
    private final String descricao;

    protected ItemBase(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }
}
