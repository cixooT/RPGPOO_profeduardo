package rpg.ui;

public class EntradaEncerradaException extends RuntimeException {
    public EntradaEncerradaException() {
        super("A entrada do jogador foi encerrada.");
    }
}
