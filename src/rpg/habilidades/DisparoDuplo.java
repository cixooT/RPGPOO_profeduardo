package rpg.habilidades;

import rpg.combate.CalculadoraDeDano;
import rpg.combate.Dano;
import rpg.personagens.Combatente;
import rpg.personagens.herois.Heroi;

public class DisparoDuplo implements Habilidade {
    @Override
    public String getNome() {
        return "Disparo Duplo";
    }

    @Override
    public String getDescricao() {
        return "duas flechas seguidas, cada uma com 100% do ataque";
    }

    @Override
    public int getCusto() {
        return 12;
    }

    @Override
    public String executar(Heroi usuario, Combatente alvo, CalculadoraDeDano calculadora) {
        Dano primeira = calculadora.calcular(usuario.getAtaque(), alvo.getDefesa());
        int causadoPrimeira = alvo.receberDano(primeira.valor());
        Dano segunda = calculadora.calcular(usuario.getAtaque(), alvo.getDefesa());
        int causadoSegunda = alvo.receberDano(segunda.valor());
        return usuario.getNome() + " solta um Disparo Duplo: " + causadoPrimeira + primeira.marcador()
                + " + " + causadoSegunda + segunda.marcador() + " = " + (causadoPrimeira + causadoSegunda) + " de dano.";
    }
}
