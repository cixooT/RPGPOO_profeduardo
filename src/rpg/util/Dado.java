package rpg.util;

public interface Dado {
    int rolar(int minimo, int maximo);

    default boolean chance(int porcentagem) {
        return rolar(1, 100) <= porcentagem;
    }
}
