package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Entry;
import model.EntryList;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class EntrylistStepdefs {

    private EntryList entryList;
    private Entry entry;

    @Given("No entries were provided yet")
    public void noEntriesWereProvidedYet() {
        entryList = new EntryList();
    }

    @Given("Entry list contains one entry with amount {int} on start")
    public void entryListContainsOneEntryWithAmount(Integer amount) {
        entry = new Entry(amount);
        entryList = new EntryList();
        entryList.add(entry);
    }

    @When("I check entries now")
    public void iCheckEntriesNow() {
    }

    @When("I add new entry with amount of {int} and category {string}")
    public void iAddNewEntryWithAmountOfAndCategory(Integer amount, String category) {
        List<String> catList = Arrays.asList(category.split(";"));
        entry = new Entry(amount, catList);
        entryList.add(entry);
    }

    @When("I change amount of entry to {int}")
    public void iChangeAmountOfEntryTo(int newAmount) {
        entryList.getAt(0).setAmount(newAmount);
    }

    @When("I add category {string} to entry")
    public void iAddCategoryToEntry(String category) {
        entryList.getAt(0).addCategory(category);
    }

    @When("I set categories {string} to entry")
    public void iSetCategoriesToEntry(String categoryList) {
        List<String> categories = Arrays.asList(categoryList.split(","));
        entryList.getAt(0).setCategories(categories);
    }

    @When("I remove entry from the entry list")
    public void iRemoveEntryFromTheEntryList() {
        entryList.removeAt(0);
    }

    @Then("There are no entries in the entry list")
    public void thereAreNoEntriesInTheEntryList() {
        assertEquals(0, entryList.getLength());
    }

    @Then("My entry list contains provided entry")
    public void myEntryListContainsProvidedEntry() {
        assertTrue(entryList.asList().contains(entry));
    }

    @Then("Entry list contains one entry with amount {long}")
    public void entryListContainsOneEntryWithAmount(long amount) {
        assertEquals(1, entryList.asList().size());
        assertEquals(amount, entryList.asList().get(0).getAmount());
    }

    @Then("Entry list contains one entry with amount {long} and categories {string}")
    public void entryListContainsOneEntryWithAmount(long amount, String categoryList) {
        List<String> categories = Arrays.asList(categoryList.split(","));

        assertEquals(1, entryList.asList().size());
        assertEquals(amount, entryList.asList().get(0).getAmount());
        assertTrue(entryList.asList().get(0).getCategories().containsAll(categories));
    }

}
