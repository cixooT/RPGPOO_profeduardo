package rpg.personagens;

public abstract class Personagem implements Combatente {
    private final String nome;
    private final int vidaMaxima;
    private final int ataqueBase;
    private final int defesaBase;
    private int vida;
    private boolean defendendo;

    protected Personagem(String nome, int vidaMaxima, int ataqueBase, int defesaBase) {
        this.nome = nome;
        this.vidaMaxima = vidaMaxima;
        this.ataqueBase = ataqueBase;
        this.defesaBase = defesaBase;
        this.vida = vidaMaxima;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getVida() {
        return vida;
    }

    @Override
    public int getVidaMaxima() {
        return vidaMaxima;
    }

    @Override
    public int getAtaque() {
        return ataqueBase;
    }

    @Override
    public int getDefesa() {
        return defesaBase;
    }

    @Override
    public boolean estaVivo() {
        return vida > 0;
    }

    @Override
    public int receberDano(int dano) {
        int danoFinal = defendendo ? Math.max(1, dano / 2) : Math.max(0, dano);
        vida = Math.max(0, vida - danoFinal);
        return danoFinal;
    }

    public void defender() {
        defendendo = true;
    }

    public void encerrarDefesa() {
        defendendo = false;
    }

    public boolean estaDefendendo() {
        return defendendo;
    }

    protected int restaurarVida(int quantidade) {
        if (!estaVivo() || quantidade <= 0) {
            return 0;
        }
        int vidaAnterior = vida;
        vida = Math.min(vidaMaxima, vida + quantidade);
        return vida - vidaAnterior;
    }
}
