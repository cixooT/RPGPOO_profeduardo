package rpg;

import java.util.List;
import java.util.Random;
import rpg.combate.Batalha;
import rpg.combate.CalculadoraDanoPadrao;
import rpg.combate.CalculadoraDeDano;
import rpg.combate.acoes.AcaoAtacar;
import rpg.combate.acoes.AcaoDeCombate;
import rpg.combate.acoes.AcaoDefender;
import rpg.combate.acoes.AcaoHabilidade;
import rpg.combate.acoes.AcaoUsarItem;
import rpg.itens.Arma;
import rpg.itens.Armadura;
import rpg.itens.Item;
import rpg.itens.PocaoDeEnergia;
import rpg.itens.PocaoDeVida;
import rpg.jogo.Acampamento;
import rpg.jogo.FabricaDeHerois;
import rpg.jogo.GeradorDeRecompensas;
import rpg.jogo.Jogo;
import rpg.jogo.Masmorra;
import rpg.personagens.herois.Arqueiro;
import rpg.personagens.herois.Guerreiro;
import rpg.personagens.herois.Mago;
import rpg.personagens.inimigos.DragaoAncestral;
import rpg.personagens.inimigos.Esqueleto;
import rpg.personagens.inimigos.Goblin;
import rpg.personagens.inimigos.Orc;
import rpg.ui.ConsoleUI;
import rpg.ui.EntradaEncerradaException;
import rpg.ui.FormatadorStatus;
import rpg.ui.InterfaceJogo;
import rpg.util.Dado;
import rpg.util.DadoAleatorio;

public class Main {
    public static void main(String[] args) {
        Dado dado = criarDado(args);
        InterfaceJogo ui = new ConsoleUI(System.in, System.out);
        FormatadorStatus formatador = new FormatadorStatus();
        CalculadoraDeDano calculadora = new CalculadoraDanoPadrao(dado);

        List<AcaoDeCombate> acoes = List.of(
                new AcaoAtacar(),
                new AcaoHabilidade(),
                new AcaoUsarItem(),
                new AcaoDefender());
        Batalha batalha = new Batalha(ui, calculadora, dado, acoes, formatador);

        FabricaDeHerois fabrica = new FabricaDeHerois(Main::kitInicial)
                .registrar("Guerreiro", "muita vida e defesa, bate pesado de perto", Guerreiro::new)
                .registrar("Mago", "pouca vida, mas magias devastadoras e cura", Mago::new)
                .registrar("Arqueiro", "equilibrado, flechas que ignoram armadura", Arqueiro::new);

        Masmorra masmorra = new Masmorra()
                .adicionarAndar(Goblin::new)
                .adicionarAndar(Esqueleto::new)
                .adicionarAndar(Orc::new)
                .adicionarAndar(DragaoAncestral::new);

        GeradorDeRecompensas recompensas = new GeradorDeRecompensas(dado)
                .registrar(() -> new PocaoDeVida("Poção de Vida", 40))
                .registrar(() -> new PocaoDeVida("Poção Grande de Vida", 70))
                .registrar(() -> new PocaoDeEnergia("Poção de Energia", 25))
                .registrar(() -> new Arma("Lâmina Afiada", 4))
                .registrar(() -> new Arma("Machado de Guerra", 6))
                .registrar(() -> new Armadura("Cota de Malha", 4))
                .registrar(() -> new Armadura("Armadura de Placas", 6));

        Jogo jogo = new Jogo(ui, fabrica, masmorra, batalha, new Acampamento(ui, formatador), recompensas);
        try {
            jogo.executar();
        } catch (EntradaEncerradaException e) {
            System.out.println();
            System.out.println("Entrada encerrada. Até a próxima!");
        }
    }

    private static List<Item> kitInicial() {
        return List.of(
                new PocaoDeVida("Poção de Vida", 40),
                new PocaoDeVida("Poção de Vida", 40),
                new PocaoDeEnergia("Poção de Energia", 25));
    }

    private static Dado criarDado(String[] args) {
        if (args.length == 2 && args[0].equals("--semente")) {
            return new DadoAleatorio(new Random(Long.parseLong(args[1])));
        }
        return new DadoAleatorio();
    }
}
