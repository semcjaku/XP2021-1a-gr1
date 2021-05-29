package model;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class MonthlyCyclicEntryPrototypeTest {
    @Test
    public void MonthlyCyclicEntryPrototypeConstructorShouldFailOnDayOfMonthLessThan1Test() {
        // Arrange
        int improperDayOfMonth = 0;
        Entry mockEntry = new Entry(0);

        // Act
        ThrowingRunnable construction = () -> new MonthlyCyclicEntryPrototype(mockEntry, improperDayOfMonth);

        // Assert
        assertThrows(IllegalDayOfMonthException.class, construction);
    }

    @Test
    public void MonthlyCyclicEntryPrototypeConstructorShouldFailOnDayOfMonthGreaterThan31Test() {
        // Arrange
        int improperDayOfMonth = 32;
        Entry mockEntry = new Entry(0);

        // Act
        ThrowingRunnable construction = () -> new MonthlyCyclicEntryPrototype(mockEntry, improperDayOfMonth);

        // Assert
        assertThrows(IllegalDayOfMonthException.class, construction);
    }

    @Test
    public void MonthlyCyclicEntryPrototypeGenerateNewEntryTest() {
        // Arrange
        Entry prototypeEntry = new Entry(4, new ArrayList<>());
        int cyclicDayOfMonth = 3;
        CyclicEntryPrototype cyclicEntryPrototype = new MonthlyCyclicEntryPrototype(prototypeEntry, cyclicDayOfMonth);
        LocalDate date = LocalDate.now();

        // Act
        Entry generatedEntry = cyclicEntryPrototype.generateNewEntry(date);

        // Assert
        assertEquals(prototypeEntry.getAmount(), generatedEntry.getAmount());
        assertEquals(prototypeEntry.getCategories(), generatedEntry.getCategories());
    }

    @Test
    public void MonthlyCyclicEntryPrototypeShouldBeReplicatedTodayWhenItShould() {
        // Arrange
        int cyclicDayOfMonth = LocalDate.now().getDayOfMonth();
        LocalDate date = LocalDate.now().minusMonths(1);
        Entry prototypeEntry = new Entry(4, new ArrayList<>(), date);
        CyclicEntryPrototype cyclicEntryPrototype = new MonthlyCyclicEntryPrototype(prototypeEntry,cyclicDayOfMonth);

        // Assert
        assertTrue(cyclicEntryPrototype.shouldBeReplicatedToday());
    }

    @Test
    public void MonthlyCyclicEntryPrototypeShouldBeReplicatedTodayWhenItShouldNotFromCyclicDayOfMonthTest() {
        // Arrange
        int cyclicDayOfMonth = LocalDate.now().getDayOfMonth() + 1;
        LocalDate date = LocalDate.now().minusMonths(1);
        Entry prototypeEntry = new Entry(4, new ArrayList<>(), date);
        CyclicEntryPrototype cyclicEntryPrototype = new MonthlyCyclicEntryPrototype(prototypeEntry, cyclicDayOfMonth);

        // Assert
        assertFalse(cyclicEntryPrototype.shouldBeReplicatedToday());
    }

    @Test
    public void MonthlyCyclicEntryPrototypeGetPrototypeEntryTest() {
        // Arrange
        Entry prototypeEntry = new Entry(4, new ArrayList<>());
        int cyclicDayOfMonth = 3;
        CyclicEntryPrototype cyclicEntryPrototype = new MonthlyCyclicEntryPrototype(prototypeEntry, cyclicDayOfMonth);
        LocalDate date = LocalDate.now();

        // Act
        Entry generatedEntry = cyclicEntryPrototype.generateNewEntry(date);

        // Assert
        assertEquals(prototypeEntry.getAmount(), generatedEntry.getAmount());
        assertEquals(prototypeEntry.getCategories(), generatedEntry.getCategories());
    }

    @Test
    public void MonthlyCyclicEntryPrototypeSetCyclicParameterWhenProperValueGivenTest() {
        // Arrange
        Entry entry = new Entry(515, List.of("food", "rent"));
        int dayOfMonth = 8;
        CyclicEntryPrototype prototype = new MonthlyCyclicEntryPrototype(entry, dayOfMonth);

        // Act
        int newDayOfMonth = 3;
        prototype.setCyclicParameter(newDayOfMonth);

        // Assert
        assertTrue(prototype.toString().contains("dayOfMonth=" + newDayOfMonth));
    }

    @Test
    public void MonthlyCyclicEntryPrototypeSetCyclicParameterWhenWrongValueGivenTest() {
        // Arrange
        Entry entry = new Entry(515, List.of("food", "rent"));
        int dayOfMonth = 8;
        CyclicEntryPrototype prototype = new MonthlyCyclicEntryPrototype(entry, dayOfMonth);

        // Act
        int newDayOfMonth = -2;
        ThrowingRunnable setting = () -> prototype.setCyclicParameter(newDayOfMonth);

        // Assert
        assertThrows(IllegalDayOfMonthException.class, setting);
    }

    @Test
    public void MonthlyCyclicEntryPrototypeToStringTest() {
        // Arrange
        Entry entry = new Entry(515, List.of("food", "rent"));
        int dayOfMonth = 13;
        MonthlyCyclicEntryPrototype prototype = new MonthlyCyclicEntryPrototype(entry, dayOfMonth);

        // Act
        String representation = prototype.toString();

        // Assert
        assertEquals("model.MonthlyCyclicEntryPrototype{" +
                "prototypeEntry=" + entry +
                ", dayOfMonth=" + dayOfMonth +
                '}', representation);
    }
}
