package rpg.personagens.herois;

import java.util.List;
import java.util.Optional;
import rpg.habilidades.Habilidade;
import rpg.itens.Equipamentos;
import rpg.itens.Equipavel;
import rpg.itens.Inventario;
import rpg.personagens.Curavel;
import rpg.personagens.Personagem;

public abstract class Heroi extends Personagem implements Curavel {
    private final int energiaMaxima;
    private final List<Habilidade> habilidades;
    private final Inventario inventario = new Inventario();
    private final Equipamentos equipamentos = new Equipamentos();
    private int energia;

    protected Heroi(String nome, int vidaMaxima, int ataque, int defesa, int energiaMaxima, List<Habilidade> habilidades) {
        super(nome, vidaMaxima, ataque, defesa);
        this.energiaMaxima = energiaMaxima;
        this.energia = energiaMaxima;
        this.habilidades = List.copyOf(habilidades);
    }

    public abstract String getClasse();

    @Override
    public int getAtaque() {
        return super.getAtaque() + equipamentos.getBonusAtaque();
    }

    @Override
    public int getDefesa() {
        return super.getDefesa() + equipamentos.getBonusDefesa();
    }

    @Override
    public int curar(int quantidade) {
        return restaurarVida(quantidade);
    }

    public int getEnergia() {
        return energia;
    }

    public int getEnergiaMaxima() {
        return energiaMaxima;
    }

    public boolean gastarEnergia(int custo) {
        if (custo > energia) {
            return false;
        }
        energia -= custo;
        return true;
    }

    public int recuperarEnergia(int quantidade) {
        int energiaAnterior = energia;
        energia = Math.min(energiaMaxima, energia + Math.max(0, quantidade));
        return energia - energiaAnterior;
    }

    public List<Habilidade> getHabilidades() {
        return habilidades;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public Equipamentos getEquipamentos() {
        return equipamentos;
    }

    public Optional<Equipavel> equipar(Equipavel item) {
        inventario.remover(item);
        Optional<Equipavel> anterior = equipamentos.equipar(item);
        anterior.ifPresent(inventario::adicionar);
        return anterior;
    }
}
