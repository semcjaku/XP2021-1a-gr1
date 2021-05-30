package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class EntryList implements Serializable {
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
        return "EntryList{" +
                "entries=" + entries +
                '}';
    }
}
