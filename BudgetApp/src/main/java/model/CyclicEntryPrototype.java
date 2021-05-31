package model;

import model.Entry;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public abstract class CyclicEntryPrototype implements Serializable {
    private final Entry prototypeEntry;
    protected final List<Entry> derivedEntries = new LinkedList<>();

    protected CyclicEntryPrototype(Entry firstEntry) {
        this.prototypeEntry = firstEntry.cloneAt(LocalDate.now());
        derivedEntries.add(firstEntry);
    }

    public Entry generateNewEntry(LocalDate date) {
        Entry newEntry = getPrototypeEntry().cloneAt(date);
        derivedEntries.add(newEntry);
        return newEntry;
    }

    public abstract void setCyclicParameter(int newValue);

    public abstract boolean shouldBeReplicatedToday();

    public Entry getPrototypeEntry() {
        return prototypeEntry;
    }
}
