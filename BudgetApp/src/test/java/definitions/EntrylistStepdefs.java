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

    @Given("Entry list contains one entry with amount {string}")
    public void entryListContainsOneEntryWithAmount(String arg0) {
        int amount = Integer.parseInt(arg0);
        entry = new Entry(amount);
        entryList = new EntryList();
        entryList.addEntry(entry);
    }

    @When("I check entries now")
    public void iCheckEntriesNow() {}

    @When("I add new entry with amount of {string} and category {string}")
    public void iAddNewEntryWithAmountOfAndCategory(String arg0, String arg1) {
        int amount = Integer.parseInt(arg0);
        List<String> catList = Arrays.asList(arg1.split(";"));
        entry = new Entry(amount, catList);
        entryList.addEntry(entry);
    }

    @When("I remove entry from the entry list")
    public void iRemoveEntryFromTheEntryList() { entryList.removeEntry(0); }

    @Then("There are no entries in the entry list")
    public void thereAreNoEntriesInTheEntryList() { assertEquals(0, entryList.length()); }

    @Then("My entry list contains provided entry")
    public void myEntryListContainsProvidedEntry() { assertTrue(entryList.getEntries().contains(entry)); }

}
