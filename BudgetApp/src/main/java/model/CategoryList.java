package model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CategoryList {
    private List<String> categories;

    public CategoryList() {
        this.categories = new LinkedList<>(Arrays.asList("clothes", "entertainment", "food", "friendly exchange", "home", "paycheck", "taxes"));
        java.util.Collections.sort(this.categories);
    }

    public List<String> getCategories() {
        return this.categories;
    }

    public void addCategory(String category) {
        this.categories.add(category);
        java.util.Collections.sort(this.categories);
    }

    @Override
    public String toString() {
        return "CategoryList{" +
                "categories=" + categories +
                '}';
    }
}
