package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.EntryList;
import model.Entry;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class EntrylistStepdefs {

    private EntryList entryList;
    private Entry entry;

    @Given("No entries were provided yet")
    public void noEntriesWereProvidedYet() { entryList = new EntryList(); }

    @When("I check entries now")
    public void iCheckEntriesNow() {}

    @When("I add new entry {string} with category {string}")
    public void iAddNewEntryWithCategory(String arg0, String arg1) {
        int amount = Integer.parseInt(arg0);
        List<String> catList = Arrays.asList(arg1.split(";"));
        entry = new Entry(amount, catList);
        entryList.addEntry(entry);
    }

    @Then("There are no entries in entry list")
    public void thereAreNoEntriesInEntryList() { assertEquals(0, entryList.length()); }

    @Then("My entry list contains provided entry")
    public void myEntryListContainsProvidedEntry() { assertTrue(entryList.getEntries().contains(entry)); }
}
