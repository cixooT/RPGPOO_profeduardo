package rpg.util;

import java.util.Random;

public class DadoAleatorio implements Dado {
    private final Random random;

    public DadoAleatorio() {
        this(new Random());
    }

    public DadoAleatorio(Random random) {
        this.random = random;
    }

    @Override
    public int rolar(int minimo, int maximo) {
        return minimo + random.nextInt(maximo - minimo + 1);
    }
}
