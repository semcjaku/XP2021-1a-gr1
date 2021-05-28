package model.cyclic;

import model.Entry;

import java.util.LinkedList;
import java.util.List;

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

    @Override
    public String toString() {
        return "model.cyclic.CyclicPrototypeList{" +
                "prototypes=" + prototypes +
                '}';
    }
}
