package rpg.personagens;

public interface Combatente {
    String getNome();

    int getVida();

    int getVidaMaxima();

    int getAtaque();

    int getDefesa();

    boolean estaVivo();

    int receberDano(int dano);
}
