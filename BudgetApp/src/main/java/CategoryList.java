import java.util.LinkedList;
import java.util.List;

public class CategoryList {
    private List<String> categories;

    public CategoryList() {
        this.categories = new LinkedList<>();
    }

    public List<String> getCategories() {
        return this.categories;
    }

    public void addCategory(String category) {
        this.categories.add(category);
    }

    @Override
    public String toString() {
        return "CategoryList{" +
                "categories=" + categories +
                '}';
    }
}
