import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Entry {
    private int amount;
    private List<String> categories;
    private LocalDate date = LocalDate.now();
    private int cyclicIntervalInDays;
    private int cyclicDayOfMonth = 0;
    private Entry cyclicDuplicateOf;

    public Entry(int amount){
        this.amount = amount;
        this.categories = new LinkedList<>();
        this.cyclicIntervalInDays = 0;
    }

    public Entry(int amount, List<String> cat){
        this(amount);
        this.categories = cat;
    }

    public Entry(int amount,  int cyclicIntervalInDays){
        this(amount);
        this.cyclicIntervalInDays = cyclicIntervalInDays;
    }

    public Entry(int amount, List<String> cat, int cyclicIntervalInDays){
        this.amount = amount;
        this.categories = cat;
        this.cyclicIntervalInDays = cyclicIntervalInDays;
    }

    public Entry(int amount, List<String> cat, int cyclicIntervalInDays, int cyclicDayOfMonth) {
        if (cyclicIntervalInDays != 0 && cyclicDayOfMonth != 0){
            throw new BothIntervalAndDayOfMonthSpecifiedException();
        }
        assertDayOfMonth(cyclicDayOfMonth);

        this.amount = amount;
        this.categories = cat;
        this.cyclicIntervalInDays = cyclicIntervalInDays;
        this.cyclicDayOfMonth = cyclicDayOfMonth;
    }

    public int getCyclicIntervalInDays() {
        return cyclicIntervalInDays;
    }

    public void setCyclicIntervalInDays(int cyclicIntervalInDays) {
        this.cyclicIntervalInDays = cyclicIntervalInDays;
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
                ", cyclicDay=" + cyclicIntervalInDays +
                '}';
    }


    public int getCyclicDayOfMonth() {
        return cyclicDayOfMonth;
    }

    public boolean isCyclic() {
        return cyclicIntervalInDays != 0 || cyclicDayOfMonth != 0;
    }

    private void assertDayOfMonth(int dayOfMonth) {
        if (dayOfMonth > 31 || dayOfMonth < 1) {
            throw new ImproperDayOfMonthException();
        }
    }

    public Entry createNewCyclicInstance() {
        Entry newEntry;
        newEntry = new Entry(amount, categories, cyclicIntervalInDays);
        newEntry.cyclicDuplicateOf = this;
        return newEntry;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public Entry isCyclicDuplicateOf() {
        return cyclicDuplicateOf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return amount == entry.amount && cyclicIntervalInDays == entry.cyclicIntervalInDays && cyclicDayOfMonth == entry.cyclicDayOfMonth && Objects.equals(categories, entry.categories) && Objects.equals(date, entry.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, categories, date, cyclicIntervalInDays, cyclicDayOfMonth);
    }
}
