package model.cyclic;

import model.Entry;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public abstract class CyclicEntryPrototype {
    protected final List<Entry> derivedEntries = new LinkedList<>();

    protected CyclicEntryPrototype(Entry prototypeEntry) {
        derivedEntries.add(prototypeEntry);
    }

    public Entry generateNewEntry(LocalDate date) {
        Entry newEntry = getPrototypeEntry().cloneAt(date);
        derivedEntries.add(newEntry);
        return newEntry;
    }

    public abstract void setCyclicParameter(int newValue);

    public abstract boolean shouldBeReplicatedToday();

    public Entry getPrototypeEntry() {
        return derivedEntries.get(0);
    }
}
