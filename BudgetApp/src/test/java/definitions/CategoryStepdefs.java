package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.CategoryList;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class CategoryStepdefs {

    private CategoryList categoryList;

    @Given("My category list is new category list")
    public void myCategoryListIsNewCategoryList() {
        categoryList = new CategoryList();
    }

    @When("I get default categories")
    public void iGetDefaultCategories() {
    }

    @When("I add category {string}")
    public void iAddCategory(String category) {
        categoryList.addCategory(category);
    }

    @Then("My categories contains {string}")
    public void myCategoriesContains(String category) {
        assertTrue(categoryList.getCategories().contains(category));
    }


}
