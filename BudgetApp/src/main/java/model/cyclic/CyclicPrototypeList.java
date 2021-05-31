package model.cyclic;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class CyclicPrototypeList {
    private final List<CyclicEntryPrototype> prototypes;

    public CyclicPrototypeList() {
        this.prototypes = new LinkedList<>();
    }

    public List<CyclicEntryPrototype> getPrototypes() {
        return this.prototypes;
    }

    public void addPrototype(CyclicEntryPrototype prototype) {
        this.prototypes.add(prototype);
    }

    public void removePrototype(int index) {
        prototypes.remove(index);
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

    public int length() {
        return prototypes.size();
    }

    @Override
    public String toString() {
        return "model.cyclic.CyclicPrototypeList{" +
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
