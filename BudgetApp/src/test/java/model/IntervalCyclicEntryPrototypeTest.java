package model;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class IntervalCyclicEntryPrototypeTest {
    @Test
    public void IntervalCyclicEntryPrototypeConstructorShouldFailOnValueLessThenOneTest() {
        // Arrange
        int improperInterval = 0;
        Entry mockEntry = new Entry(0);

        // Act
        ThrowingRunnable construction = () -> new IntervalCyclicEntryPrototype(mockEntry, improperInterval);

        // Assert
        assertThrows(IllegalCyclicIntervalException.class, construction);
    }

    @Test
    public void IntervalCyclicEntryPrototypeGenerateNewEntryTest() {
        // Arrange
        Entry prototypeEntry = new Entry(4, new ArrayList<>());
        int cyclicIntervalInDays = 1;
        CyclicEntryPrototype cyclicEntryPrototype = new IntervalCyclicEntryPrototype(prototypeEntry, cyclicIntervalInDays);
        LocalDate date = LocalDate.now();

        // Act
        Entry generatedEntry = cyclicEntryPrototype.generateNewEntry(date);

        // Assert
        assertEquals(prototypeEntry.getAmount(), generatedEntry.getAmount());
        assertEquals(prototypeEntry.getCategories(), generatedEntry.getCategories());
    }

    @Test
    public void IntervalCyclicEntryPrototypeShouldBeReplicatedTodayWhenItShouldTest() {
        // Arrange
        int cyclicIntervalInDays = 5;
        LocalDate date = LocalDate.now().minusDays(cyclicIntervalInDays);
        Entry prototypeEntry = new Entry(4, new ArrayList<>(), date);
        CyclicEntryPrototype cyclicEntryPrototype = new IntervalCyclicEntryPrototype(prototypeEntry, cyclicIntervalInDays);

        // Assert
        assertTrue(cyclicEntryPrototype.shouldBeReplicatedToday());
    }

    @Test
    public void IntervalCyclicEntryPrototypeShouldBeReplicatedTodayWhenItShouldNotTest() {
        // Arrange
        int cyclicIntervalInDays = 5;
        LocalDate date = LocalDate.now().minusDays(cyclicIntervalInDays - 1);
        Entry prototypeEntry = new Entry(4, new ArrayList<>(), date);
        CyclicEntryPrototype cyclicEntryPrototype = new IntervalCyclicEntryPrototype(prototypeEntry, cyclicIntervalInDays);

        // Assert
        assertFalse(cyclicEntryPrototype.shouldBeReplicatedToday());
    }

    @Test
    public void IntervalCyclicEntryPrototypeSetCyclicParameterWhenProperValueGivenTest() {
        // Arrange
        Entry entry = new Entry(515, List.of("food", "rent"));
        int intervalInDays = 8;
        IntervalCyclicEntryPrototype prototype = new IntervalCyclicEntryPrototype(entry, intervalInDays);

        // Act
        int newIntervalInDays = 3;
        prototype.setCyclicParameter(newIntervalInDays);

        // Assert
        assertTrue(prototype.toString().contains("intervalInDays=" + newIntervalInDays));
    }

    @Test
    public void IntervalCyclicEntryPrototypeSetCyclicParameterWhenWrongValueGivenTest() {
        // Arrange
        Entry entry = new Entry(515, List.of("food", "rent"));
        int intervalInDays = 8;
        CyclicEntryPrototype prototype = new IntervalCyclicEntryPrototype(entry, intervalInDays);

        // Act
        int newIntervalInDays = -2;
        ThrowingRunnable setting = () -> prototype.setCyclicParameter(newIntervalInDays);

        // Assert
        assertThrows(IllegalCyclicIntervalException.class, setting);
    }

    @Test
    public void IntervalCyclicEntryPrototypeToStringTest() {
        // Arrange
        Entry entry = new Entry(515, List.of("food", "rent"));
        int intervalInDays = 8;
        CyclicEntryPrototype prototype = new IntervalCyclicEntryPrototype(entry, intervalInDays);

        // Act
        String representation = prototype.toString();

        // Assert
        assertEquals("model.IntervalCyclicEntryPrototype{" +
                "prototypeEntry=" + entry +
                ", intervalInDays=" + intervalInDays +
                '}', representation);
    }
}
