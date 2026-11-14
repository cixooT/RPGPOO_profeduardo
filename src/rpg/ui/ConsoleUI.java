package rpg.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI implements InterfaceJogo {
    private final Scanner scanner;
    private final PrintStream saida;

    public ConsoleUI(InputStream entrada, PrintStream saida) {
        this.scanner = new Scanner(entrada);
        this.saida = saida;
    }

    @Override
    public void mostrar(String mensagem) {
        saida.println(mensagem);
    }

    @Override
    public void mostrarTitulo(String titulo) {
        saida.println();
        saida.println("==================== " + titulo + " ====================");
    }

    @Override
    public int escolherOpcao(String pergunta, List<String> opcoes) {
        saida.println();
        saida.println(pergunta);
        for (int i = 0; i < opcoes.size(); i++) {
            saida.println("  [" + (i + 1) + "] " + opcoes.get(i));
        }
        while (true) {
            saida.print("> ");
            Integer escolha = converterNumero(lerLinha());
            if (escolha != null && escolha >= 1 && escolha <= opcoes.size()) {
                return escolha - 1;
            }
            saida.println("Opção inválida. Digite um número entre 1 e " + opcoes.size() + ".");
        }
    }

    @Override
    public String lerTexto(String pergunta) {
        saida.print(pergunta + " ");
        return lerLinha().trim();
    }

    @Override
    public void aguardarEnter() {
        saida.print("(pressione ENTER para continuar)");
        lerLinha();
    }

    private Integer converterNumero(String texto) {
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String lerLinha() {
        if (!scanner.hasNextLine()) {
            throw new EntradaEncerradaException();
        }
        return scanner.nextLine();
    }
}
