package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Entry implements Serializable {
    private int amount;
    private List<String> categories;
    private LocalDate date = LocalDate.now();
    private String creatorEmail;

    public Entry(String creatorEmail, int amount){
        this.creatorEmail = creatorEmail;
        this.amount = amount;
        this.categories = new LinkedList<>();
    }

    public Entry(int amount){
        this.creatorEmail = "admin";
        this.amount = amount;
        this.categories = new LinkedList<>();
    }

    public Entry(String creatorEmail, int amount, List<String> cat){
        this(creatorEmail, amount);
        this.categories = cat;
    }

    public Entry(int amount, List<String> cat){
        this(amount);
        this.categories = cat;
    }

    public Entry(String creatorEmail, int amount, List<String> cat, LocalDate date){
        this(creatorEmail, amount, cat);
        this.date = date;
    }

    public Entry(int amount, List<String> cat, LocalDate date){
        this(amount, cat);
        this.date = date;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void addCategory(String category) {
        if (! this.categories.contains(category)) {
            this.categories.add(category);
        }
    }

    public void removeCategory(String category) {
        this.categories.remove(category);
    }

    public Entry cloneAt(LocalDate date) {
        return new Entry(creatorEmail, amount, categories, date);
    }

    @Override
    public String toString() {
        String username;
        username = Objects.requireNonNullElse(creatorEmail, "admin");
        return "Entry{" +
                "amount=" + amount +
                ", categories=" + categories +
                ", created by=" + username +
                '}';
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
