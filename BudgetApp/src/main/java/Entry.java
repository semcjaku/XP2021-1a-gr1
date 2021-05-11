import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Entry {
    private int amount;
    private List<String> categories;
    private int cyclicDay;

    public Entry(int amount){
        this.amount = amount;
        this.categories = new LinkedList<>();
        this.cyclicDay = 0;
    }

    public Entry(int amount, List<String> cat){
        this(amount);
        this.categories = cat;
    }

    public Entry(int amount,  int cyclicDay){
        this(amount);
        this.cyclicDay = cyclicDay;
    }

    public Entry(int amount, List<String> cat, int cyclicDay){
        this.amount = amount;
        this.categories = cat;
        this.cyclicDay = cyclicDay;
    }

    public int getCyclicDay() {
        return cyclicDay;
    }

    public void setCyclicDay(int cyclicDay) {
        this.cyclicDay = cyclicDay;
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

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void addCategory(String category) {
        categories.add(category);
    }

    @Override
    public String toString() {
        return "Entry{" +
                "amount=" + amount +
                ", categories=" + categories +
                ", cyclicDay=" + cyclicDay +
                '}';
    }
}
