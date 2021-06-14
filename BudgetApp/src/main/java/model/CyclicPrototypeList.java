package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class CyclicPrototypeList implements Serializable, ListManager<CyclicEntryPrototype>, PrintableList {
    private final List<CyclicEntryPrototype> prototypes;

    public CyclicPrototypeList() {
        this.prototypes = new LinkedList<>();
    }

    public List<CyclicEntryPrototype> asList() {
        return this.prototypes;
    }

    public void add(CyclicEntryPrototype prototype) {
        this.prototypes.add(prototype);
    }

    public CyclicEntryPrototype getAt(int index) {
        return this.prototypes.get(index);
    }

    public void removeAt(int index) {
        prototypes.remove(index);
    }

    public int getLength() {
        return prototypes.size();
    }

    public String getOrderedEntriesString() {
        StringBuilder output = new StringBuilder();
        ListIterator<CyclicEntryPrototype> entryListIterator = this.prototypes.listIterator();
        int counter = 1;
        while (entryListIterator.hasNext()) {
            output.append(String.format("%d. %s\n", counter, entryListIterator.next()));
            counter++;
        }
        return output.toString();
    }

    @Override
    public String toString() {
        return "CyclicPrototypeList{" +
                "prototypes=" + prototypes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CyclicPrototypeList that = (CyclicPrototypeList) o;
        return Objects.equals(prototypes, that.prototypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prototypes);
    }
}
