package rpg.itens;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class Equipamentos {
    private final Map<TipoEquipamento, Equipavel> slots = new EnumMap<>(TipoEquipamento.class);

    public Optional<Equipavel> equipar(Equipavel item) {
        return Optional.ofNullable(slots.put(item.getTipo(), item));
    }

    public Optional<Equipavel> getEquipado(TipoEquipamento tipo) {
        return Optional.ofNullable(slots.get(tipo));
    }

    public int getBonusAtaque() {
        return slots.values().stream().mapToInt(Equipavel::getBonusAtaque).sum();
    }

    public int getBonusDefesa() {
        return slots.values().stream().mapToInt(Equipavel::getBonusDefesa).sum();
    }
}
