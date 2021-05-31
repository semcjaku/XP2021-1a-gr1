package model;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class EntryList {
    private List<Entry> entries;

    public EntryList() {
        this.entries = new LinkedList<>();
    }

    public EntryList(List<Entry> entries) {
        this.entries = entries;
    }

    public List<Entry> getEntries() {
        return this.entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public void addEntry(Entry entry) {
        this.entries.add(entry);
    }

    public void removeEntry(int index) {
        this.entries.remove(index);
    }

    public Entry getEntry(int index) {
        return this.entries.get(index);
    }

    public int length() {
        return this.entries.size();
    }

    public String getOrderedEntriesString() {
        String output = "";
        ListIterator<Entry> entryListIterator = this.entries.listIterator();
        int counter = 1;
        while (entryListIterator.hasNext()) {
            output += String.format("%d. %s\n", counter, entryListIterator.next());
            counter++;
        }
        return output;
    }

    @Override
    public String toString() {
        return "model.EntryList{" +
                "entries=" + entries +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntryList entryList = (EntryList) o;
        return entries.equals(entryList.entries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entries);
    }
}
