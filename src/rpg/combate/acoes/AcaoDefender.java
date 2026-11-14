package rpg.combate.acoes;

import rpg.combate.ContextoBatalha;
import rpg.personagens.herois.Heroi;

public class AcaoDefender implements AcaoDeCombate {
    private static final int ENERGIA_RECUPERADA = 5;

    @Override
    public String getNome() {
        return "Defender (metade do dano no próximo golpe e +" + ENERGIA_RECUPERADA + " EN)";
    }

    @Override
    public boolean estaDisponivel(Heroi heroi) {
        return true;
    }

    @Override
    public boolean executar(ContextoBatalha contexto) {
        Heroi heroi = contexto.heroi();
        heroi.defender();
        int recuperado = heroi.recuperarEnergia(ENERGIA_RECUPERADA);
        contexto.ui().mostrar(heroi.getNome() + " se prepara para o golpe e recupera " + recuperado + " de energia.");
        return true;
    }
}
