package model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Entry {
    private final int amount;
    private List<String> categories;
    private LocalDate date = LocalDate.now();

    public Entry(int amount){
        this.amount = amount;
        this.categories = new LinkedList<>();
    }

    public Entry(int amount, List<String> cat){
        this(amount);
        this.categories = cat;
    }

    public Entry(int amount, List<String> cat, LocalDate date){
        this(amount);
        this.categories = cat;
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void addCategory(String category) {
        categories.add(category);
    }

    @Override
    public String toString() {
        return "model.Entry{" +
                "amount=" + amount +
                ", categories=" + categories +
                '}';
    }

    public Entry cloneAt(LocalDate date) {
        return new Entry(amount, categories, date);
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return amount == entry.amount && Objects.equals(categories, entry.categories) && Objects.equals(date, entry.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, categories, date);
    }
}
