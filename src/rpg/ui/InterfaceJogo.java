package rpg.ui;

import java.util.List;

public interface InterfaceJogo {
    void mostrar(String mensagem);

    void mostrarTitulo(String titulo);

    int escolherOpcao(String pergunta, List<String> opcoes);

    String lerTexto(String pergunta);

    void aguardarEnter();
}
