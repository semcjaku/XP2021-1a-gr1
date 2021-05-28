package model.cyclic;

import exception.IllegalCyclicIntervalException;
import model.Entry;

import java.time.LocalDate;

public class MonthlyCyclicEntryPrototype extends CyclicEntryPrototype {
    private final int cyclicDayOfMonth;


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

    public void validateDayOfMonth(int cyclicDayOfMonth) {
        if (cyclicDayOfMonth < 1 || cyclicDayOfMonth > 31) {
            throw new IllegalCyclicIntervalException();
        }
    }

    @Override
    public String toString() {
        return "model.cyclic.MonthlyCyclicEntryPrototype{" +
                "prototypeEntry=" + getPrototypeEntry() +
                ", dayOfMonth=" + cyclicDayOfMonth +
                '}';
    }
}
