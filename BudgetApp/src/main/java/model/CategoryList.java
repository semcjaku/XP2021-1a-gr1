package model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
        return "model.CategoryList{" +
                "categories=" + categories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryList that = (CategoryList) o;
        return categories.equals(that.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categories);
    }
}
