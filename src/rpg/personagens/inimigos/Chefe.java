package rpg.personagens.inimigos;

public abstract class Chefe extends Inimigo {
    protected Chefe(String nome, int vidaMaxima, int ataque, int defesa) {
        super(nome, vidaMaxima, ataque, defesa);
    }

    protected abstract String getFalaDeEntrada();

    @Override
    public final String getApresentacao() {
        return "!!! CHEFE: " + getNome().toUpperCase() + " !!!\n\"" + getFalaDeEntrada() + "\"";
    }
}
