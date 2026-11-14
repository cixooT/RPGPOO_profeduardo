package rpg.jogo;

import java.util.List;
import rpg.combate.Batalha;
import rpg.combate.ResultadoBatalha;
import rpg.itens.Item;
import rpg.personagens.herois.Heroi;
import rpg.personagens.inimigos.Inimigo;
import rpg.ui.InterfaceJogo;

public class Jogo {
    private static final String NOME_PADRAO = "Aventureiro";
    private static final int PORCENTAGEM_CURA_DESCANSO = 40;
    private final InterfaceJogo ui;
    private final FabricaDeHerois fabricaDeHerois;
    private final Masmorra masmorra;
    private final Batalha batalha;
    private final Acampamento acampamento;
    private final GeradorDeRecompensas recompensas;

    public Jogo(InterfaceJogo ui, FabricaDeHerois fabricaDeHerois, Masmorra masmorra, Batalha batalha,
            Acampamento acampamento, GeradorDeRecompensas recompensas) {
        this.ui = ui;
        this.fabricaDeHerois = fabricaDeHerois;
        this.masmorra = masmorra;
        this.batalha = batalha;
        this.acampamento = acampamento;
        this.recompensas = recompensas;
    }

    public void executar() {
        exibirAbertura();
        Heroi heroi = criarHeroi();
        List<Inimigo> encontros = masmorra.gerarEncontros();

        for (int i = 0; i < encontros.size(); i++) {
            Inimigo inimigo = encontros.get(i);
            ui.mostrarTitulo("Andar " + (i + 1) + " de " + encontros.size());
            if (batalha.iniciar(heroi, inimigo) == ResultadoBatalha.DERROTA) {
                exibirDerrota(heroi, inimigo, i + 1);
                return;
            }
            ui.mostrar("\n" + inimigo.getNome() + " foi derrotado!");
            boolean ultimoAndar = i == encontros.size() - 1;
            if (!ultimoAndar) {
                recompensar(heroi);
                descansar(heroi);
                acampamento.visitar(heroi);
            }
        }
        exibirVitoria(heroi);
    }

    private void exibirAbertura() {
        ui.mostrarTitulo("A MASMORRA DO DRAGÃO");
        ui.mostrar("Dizem que no fundo desta masmorra vive um dragão antigo guardando um tesouro enorme.");
        ui.mostrar("Muitos entraram. Ninguém voltou. Hoje é a sua vez.");
    }

    private Heroi criarHeroi() {
        String nome = ui.lerTexto("\nQual o nome do seu herói?");
        if (nome.isBlank()) {
            nome = NOME_PADRAO;
        }
        int classe = ui.escolherOpcao("Escolha a classe de " + nome + ":", fabricaDeHerois.getDescricoes());
        Heroi heroi = fabricaDeHerois.criar(classe, nome);
        ui.mostrar("\n" + heroi.getNome() + " (" + heroi.getClasse() + ") entra na masmorra com algumas poções na mochila.");
        ui.aguardarEnter();
        return heroi;
    }

    private void recompensar(Heroi heroi) {
        Item item = recompensas.sortear();
        heroi.getInventario().adicionar(item);
        ui.mostrar("Você encontrou: " + item.getNome() + " (" + item.getDescricao() + ")");
    }

    private void descansar(Heroi heroi) {
        int curado = heroi.curar(heroi.getVidaMaxima() * PORCENTAGEM_CURA_DESCANSO / 100);
        int energia = heroi.recuperarEnergia(heroi.getEnergiaMaxima());
        ui.mostrar("Depois da luta você respira fundo: +" + curado + " de vida e +" + energia + " de energia.");
    }

    private void exibirDerrota(Heroi heroi, Inimigo inimigo, int andar) {
        ui.mostrarTitulo("FIM DE JOGO");
        ui.mostrar(heroi.getNome() + " caiu diante de " + inimigo.getNome() + " no andar " + andar + ".");
        ui.mostrar("Quem sabe na próxima...");
    }

    private void exibirVitoria(Heroi heroi) {
        ui.mostrarTitulo("VITÓRIA!");
        ui.mostrar(heroi.getNome() + " derrotou o Dragão Ancestral e saiu da masmorra carregando o tesouro!");
        ui.mostrar("Vida restante: " + heroi.getVida() + "/" + heroi.getVidaMaxima());
    }
}
