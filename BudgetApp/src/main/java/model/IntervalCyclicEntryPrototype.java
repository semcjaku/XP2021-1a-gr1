package model;

import java.time.LocalDate;
import java.util.Objects;

public class IntervalCyclicEntryPrototype extends CyclicEntryPrototype {
    private int cyclicIntervalInDays;

    public IntervalCyclicEntryPrototype(Entry prototypeEntry, int cyclicIntervalInDays) {
        super(prototypeEntry);
        validateInterval(cyclicIntervalInDays);
        this.cyclicIntervalInDays = cyclicIntervalInDays;
    }

    public boolean shouldBeReplicatedToday() {
        LocalDate lastDate = derivedEntries.get(derivedEntries.size() - 1).getDate();
        return lastDate.plusDays(cyclicIntervalInDays).equals(LocalDate.now());
    }

    @Override
    public void setCyclicParameter(int newValue) {
        validateInterval(newValue);
        cyclicIntervalInDays = newValue;
    }

    @Override
    public int getCyclicParameter() {
        return cyclicIntervalInDays;
    }

    private void validateInterval(int cyclicIntervalInDays) {
        if (cyclicIntervalInDays < 1) {
            throw new IllegalCyclicIntervalException();
        }
    }

    @Override
    public String toString() {
        return "IntervalCyclicEntryPrototype{" +
                "prototypeEntry=" + getPrototypeEntry() +
                ", intervalInDays=" + cyclicIntervalInDays +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntervalCyclicEntryPrototype prototype = (IntervalCyclicEntryPrototype) o;
        return cyclicIntervalInDays == prototype.cyclicIntervalInDays;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cyclicIntervalInDays);
    }
}
