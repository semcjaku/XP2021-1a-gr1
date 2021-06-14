package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.*;

import java.util.List;

public class CyclicEntryListStepdefs {
    CyclicPrototypeList cyclicPrototypeList;
    CyclicEntryPrototype cyclicEntryPrototype;

    @Given("No cyclic entries were provided yet")
    public void noCyclicEntriesWereProvidedYet() {
        cyclicPrototypeList = new CyclicPrototypeList();
    }

    @When("I add new entry with day of month {int} amount of {int} and category {string}")
    public void iAddNewEntryWithDayOfMonthAmountOfAndCategory(int dom, int amount, String category) {
        Entry entry = new Entry(amount, List.of(category));
        cyclicEntryPrototype = new MonthlyCyclicEntryPrototype(entry,dom);
        cyclicPrototypeList.addPrototype(cyclicEntryPrototype);
    }

    @When("I add new entry with interval {int} amount of {int} and category {string}")
    public void iAddNewEntryWithIntervalAmountOfAndCategory(int interval, int amount, String category) {
        Entry entry = new Entry(amount, List.of(category));
        cyclicEntryPrototype = new IntervalCyclicEntryPrototype(entry,interval);
        cyclicPrototypeList.addPrototype(cyclicEntryPrototype);
    }

    @When("I add new entry with day of month {int} amount of {int}")
    public void iAddNewEntryWithDayOfMonthAmountOf(int dom, int amount) {
        Entry entry = new Entry(amount);
        cyclicEntryPrototype = new MonthlyCyclicEntryPrototype(entry,dom);
        cyclicPrototypeList.addPrototype(cyclicEntryPrototype);
    }

    @When("I add new entry with interval {int} amount of {int}")
    public void iAddNewEntryWithIntervalAmountOf(int interval, int amount) {
        Entry entry = new Entry(amount);
        cyclicEntryPrototype = new IntervalCyclicEntryPrototype(entry,interval);
        cyclicPrototypeList.addPrototype(cyclicEntryPrototype);
    }

    @Then("My cyclic entry list contains provided cyclic entry")
    public void myCyclicEntryListContainsProvidedCyclicEntry() {
        cyclicPrototypeList.getCyclicEntry(0).equals(cyclicEntryPrototype);
    }
}
