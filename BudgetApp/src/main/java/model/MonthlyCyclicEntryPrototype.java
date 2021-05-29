package model;

import java.time.LocalDate;
import java.util.Objects;

public class MonthlyCyclicEntryPrototype extends CyclicEntryPrototype {
    private int cyclicDayOfMonth;


    public MonthlyCyclicEntryPrototype(Entry prototypeEntry, int cyclicDayOfMonth) {
        super(prototypeEntry);
        validateDayOfMonth(cyclicDayOfMonth);
        this.cyclicDayOfMonth = cyclicDayOfMonth;
    }

    public boolean shouldBeReplicatedToday() {
        LocalDate lastDate = derivedEntries.get(derivedEntries.size() - 1).getDate();
        return !lastDate.equals(LocalDate.now())
                && cyclicDayOfMonth == LocalDate.now().getDayOfMonth()
                || (
                        LocalDate.now().getDayOfMonth() == LocalDate.now().lengthOfMonth()
                                && cyclicDayOfMonth > LocalDate.now().getDayOfMonth());
    }

    @Override
    public void setCyclicParameter(int newValue) {
        validateDayOfMonth(newValue);
        cyclicDayOfMonth = newValue;
    }

    private void validateDayOfMonth(int cyclicDayOfMonth) {
        if (cyclicDayOfMonth < 1 || cyclicDayOfMonth > 31) {
            throw new IllegalDayOfMonthException();
        }
    }

    @Override
    public String toString() {
        return "model.MonthlyCyclicEntryPrototype{" +
                "prototypeEntry=" + getPrototypeEntry() +
                ", dayOfMonth=" + cyclicDayOfMonth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlyCyclicEntryPrototype prototype = (MonthlyCyclicEntryPrototype) o;
        return cyclicDayOfMonth == prototype.cyclicDayOfMonth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cyclicDayOfMonth);
    }
}
