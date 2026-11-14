package rpg.combate.acoes;

import java.util.ArrayList;
import java.util.List;
import rpg.combate.ContextoBatalha;
import rpg.habilidades.Habilidade;
import rpg.personagens.herois.Heroi;

public class AcaoHabilidade implements AcaoDeCombate {
    @Override
    public String getNome() {
        return "Usar habilidade";
    }

    @Override
    public boolean estaDisponivel(Heroi heroi) {
        return !heroi.getHabilidades().isEmpty();
    }

    @Override
    public boolean executar(ContextoBatalha contexto) {
        Heroi heroi = contexto.heroi();
        List<Habilidade> habilidades = heroi.getHabilidades();
        List<String> opcoes = new ArrayList<>();
        for (Habilidade habilidade : habilidades) {
            opcoes.add(habilidade.getNome() + " (" + habilidade.getCusto() + " EN) - " + habilidade.getDescricao());
        }
        opcoes.add("Voltar");

        int escolha = contexto.ui().escolherOpcao("Qual habilidade? (energia atual: " + heroi.getEnergia() + ")", opcoes);
        if (escolha == habilidades.size()) {
            return false;
        }

        Habilidade habilidade = habilidades.get(escolha);
        if (!heroi.gastarEnergia(habilidade.getCusto())) {
            contexto.ui().mostrar("Energia insuficiente para usar " + habilidade.getNome() + ".");
            return false;
        }
        contexto.ui().mostrar(habilidade.executar(heroi, contexto.inimigo(), contexto.calculadora()));
        return true;
    }
}
