package rpg.combate.acoes;

import rpg.combate.ContextoBatalha;
import rpg.combate.Dano;
import rpg.personagens.herois.Heroi;
import rpg.personagens.inimigos.Inimigo;

public class AcaoAtacar implements AcaoDeCombate {
    @Override
    public String getNome() {
        return "Atacar";
    }

    @Override
    public boolean estaDisponivel(Heroi heroi) {
        return true;
    }

    @Override
    public boolean executar(ContextoBatalha contexto) {
        Heroi heroi = contexto.heroi();
        Inimigo inimigo = contexto.inimigo();
        Dano dano = contexto.calculadora().calcular(heroi.getAtaque(), inimigo.getDefesa());
        int causado = inimigo.receberDano(dano.valor());
        contexto.ui().mostrar(heroi.getNome() + " ataca " + inimigo.getNome() + " e causa " + causado + " de dano." + dano.marcador());
        return true;
    }
}
