package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Wallet implements Serializable {

    private String walletId;
    private EntryList entryList;
    private String ownerEmail;
    private List<String> sharedUsersEmails;
    private MonthlyCyclicEntryPrototype monthlyCyclicEntryPrototype = null;
    private IntervalCyclicEntryPrototype intervalCyclicEntryPrototype = null;

    public Wallet(String walletId, String ownerEmail) {
        this.walletId = walletId;//UUID.randomUUID().toString();
        this.ownerEmail = ownerEmail;
        this.entryList = new EntryList();
        this.sharedUsersEmails = new LinkedList<>();
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public EntryList getEntryList() {
        return entryList;
    }

    public void setEntryList(EntryList entryList) {
        this.entryList = entryList;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public List<String> getSharedUsersEmails() {
        return sharedUsersEmails;
    }

    public void setSharedUsersEmails(List<String> sharedUsersEmails) {
        this.sharedUsersEmails = sharedUsersEmails;
    }

    public MonthlyCyclicEntryPrototype getMonthlyCyclicEntryPrototype() {
        return monthlyCyclicEntryPrototype;
    }

    public void setMonthlyCyclicEntryPrototype(MonthlyCyclicEntryPrototype monthlyCyclicEntryPrototype) {
        this.monthlyCyclicEntryPrototype = monthlyCyclicEntryPrototype;
    }

    public IntervalCyclicEntryPrototype getIntervalCyclicEntryPrototype() {
        return intervalCyclicEntryPrototype;
    }

    public void setIntervalCyclicEntryPrototype(IntervalCyclicEntryPrototype intervalCyclicEntryPrototype) {
        this.intervalCyclicEntryPrototype = intervalCyclicEntryPrototype;
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

    @Override
    public String toString() {
        return "Wallet{" +
                "walletId='" + walletId + '\'' +
                ", entryList=" + entryList +
                ", ownerEmail='" + ownerEmail + '\'' +
                ", sharedUsersEmails=" + sharedUsersEmails +
                ", monthlyCyclicEntryPrototype=" + monthlyCyclicEntryPrototype +
                ", intervalCyclicEntryPrototype=" + intervalCyclicEntryPrototype +
                '}';
    }
}
