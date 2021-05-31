package model;

import java.util.Objects;

public class Wallet {
    private final EntryList entryList = new EntryList();
    private final CyclicPrototypeList cyclicPrototypes = new CyclicPrototypeList();
    private final CategoryList categoryList = new CategoryList();

    private String name;
    private boolean isArchived = false;


    public Wallet() {
        name = "A wallet";
    }

    public Wallet(String name) {
        this.name = name;
    }

    public EntryList getEntryList() {
        return entryList;
    }

    public CyclicPrototypeList getCyclicPrototypes() {
        return cyclicPrototypes;
    }

    public CategoryList getCategoryList() {
        return categoryList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void markAsArchived() {
        isArchived = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return entryList.equals(wallet.entryList) && cyclicPrototypes.equals(wallet.cyclicPrototypes) && categoryList.equals(wallet.categoryList) && name.equals(wallet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryList, cyclicPrototypes, categoryList, name);
    }
}
