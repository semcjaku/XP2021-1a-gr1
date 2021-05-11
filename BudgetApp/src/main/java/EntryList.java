import java.util.LinkedList;
import java.util.List;

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

    @Override
    public String toString() {
        return "EntryList{" +
                "entries=" + entries +
                '}';
    }
}
