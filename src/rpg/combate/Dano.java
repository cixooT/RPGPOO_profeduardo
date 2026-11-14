package rpg.combate;

public record Dano(int valor, boolean critico) {
    public String marcador() {
        return critico ? " (CRÍTICO!)" : "";
    }
}
