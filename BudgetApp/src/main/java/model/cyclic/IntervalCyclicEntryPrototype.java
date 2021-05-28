package model.cyclic;

import exception.IllegalCyclicIntervalException;
import model.Entry;

import java.time.LocalDate;

public class IntervalCyclicEntryPrototype extends CyclicEntryPrototype {
    private final int cyclicIntervalInDays;

    public IntervalCyclicEntryPrototype(Entry prototypeEntry, int cyclicIntervalInDays) {
        super(prototypeEntry);
        validateInterval(cyclicIntervalInDays);
        this.cyclicIntervalInDays = cyclicIntervalInDays;
    }

    public boolean shouldBeReplicatedToday() {
        LocalDate lastDate = derivedEntries.get(derivedEntries.size() - 1).getDate();
        return lastDate.plusDays(cyclicIntervalInDays).equals(LocalDate.now());
    }

    public void validateInterval(int cyclicIntervalInDays) {
        if (cyclicIntervalInDays < 1) {
            throw new IllegalCyclicIntervalException();
        }
    }

    @Override
    public String toString() {
        return "model.cyclic.IntervalCyclicEntryPrototype{" +
                "prototypeEntry=" + getPrototypeEntry() +
                ", intervalInDays=" + cyclicIntervalInDays +
                '}';
    }
}
