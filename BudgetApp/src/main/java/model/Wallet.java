package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Wallet implements Serializable {
    private String name;
    private EntryList entryList;
    private CyclicPrototypeList cyclicPrototypes;
    private CategoryList categoryList;
    private boolean isArchived = false;
    private String ownerEmail;
    private List<String> sharedUsersEmails;


    public Wallet(String name, String ownerEmail) {
        this.name = name;
        this.ownerEmail = ownerEmail;
        this.entryList = new EntryList();
        this.cyclicPrototypes = new CyclicPrototypeList();
        this.categoryList = new CategoryList();
        this.sharedUsersEmails = new LinkedList<>();
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

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public List<String> getSharedUsersEmails() {
        return sharedUsersEmails;
    }

    public void shareWithUser(String userEmail) {
        sharedUsersEmails.add(userEmail);
    }

    public void removeUser(String userEmail) {
        if (sharedUsersEmails.contains(userEmail)){
            sharedUsersEmails.remove(userEmail);
        }
    }

    public void addEntry(Entry entry) {
        entryList.addEntry(entry);
    }

    public void addCategory(String category) {
        categoryList.addCategory(category);
    }

    public void addCyclicPrototype(CyclicEntryPrototype cyclicEntryPrototype) {
        this.cyclicPrototypes.addPrototype(cyclicEntryPrototype);
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

    public boolean hasCategory(String category) {
        return categoryList.contains(category);
    }
}
