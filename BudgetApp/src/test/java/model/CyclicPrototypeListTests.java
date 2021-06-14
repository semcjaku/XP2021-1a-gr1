package model;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CyclicPrototypeListTests {
    @Test
    public void CyclicPrototypeListAddPrototypeTest(){
        // Arrange
        CyclicPrototypeList prototypeList = new CyclicPrototypeList();
        Entry entry = new Entry(150);
        CyclicEntryPrototype prototype = new IntervalCyclicEntryPrototype(entry, 1);

        List<CyclicEntryPrototype> testPrototypes = new LinkedList<>();
        testPrototypes.add(prototype);

        // Act
        prototypeList.add(prototype);

        // Assert
        assertEquals(prototypeList.asList(), testPrototypes);
    }

    @Test
    public void CyclicPrototypeListConstructorTest() {
        // Arrange
        CyclicPrototypeList prototypeList = new CyclicPrototypeList();

        // Assert
        assertEquals(prototypeList.asList(), new LinkedList<>());
    }

    @Test
    public void CyclicPrototypeListLengthNotEmptyTest(){
        // Arrange
        CyclicPrototypeList prototypeList = new CyclicPrototypeList();
        CyclicEntryPrototype firstPrototype = new IntervalCyclicEntryPrototype(new Entry(150), 3);
        CyclicEntryPrototype secondPrototype = new MonthlyCyclicEntryPrototype(new Entry(250), 1);

        int expected = 2;

        // Act
        prototypeList.add(firstPrototype);
        prototypeList.add(secondPrototype);

        // Assert
        assertEquals(prototypeList.getLength(), expected);
    }

    @Test
    public void CyclicPrototypeListLengthEmptyTest(){
        // Arrange
        CyclicPrototypeList prototypeList = new CyclicPrototypeList();

        int expected = 0;

        // Assert
        assertEquals(prototypeList.getLength(), expected);
    }

    @Test
    public void CyclicPrototypeListRemovePrototypeTest(){
        // Arrange
        CyclicPrototypeList prototypeList = new CyclicPrototypeList();
        CyclicEntryPrototype firstPrototype = new IntervalCyclicEntryPrototype(new Entry(150), 3);
        CyclicEntryPrototype secondPrototype = new MonthlyCyclicEntryPrototype(new Entry(250), 1);

        List<CyclicEntryPrototype> testPrototypes = new LinkedList<>();
        testPrototypes.add(secondPrototype);

        // Act
        prototypeList.add(firstPrototype);
        prototypeList.add(secondPrototype);
        prototypeList.removeAt(0);

        // Assert
        assertEquals(prototypeList.asList(), testPrototypes);
    }

    @Test
    public void CyclicPrototypeListGetOrderedEntriesStringTest() {
        // Arrange
        CyclicPrototypeList prototypeList = new CyclicPrototypeList();
        CyclicEntryPrototype firstPrototype = new IntervalCyclicEntryPrototype(new Entry(150), 12);
        CyclicEntryPrototype secondPrototype = new MonthlyCyclicEntryPrototype(new Entry(3), 19);

        // Act
        prototypeList.add(firstPrototype);
        prototypeList.add(secondPrototype);

        // Assert
        assertEquals(prototypeList.getOrderedEntriesString(), "1. " + firstPrototype + "\n" + "2. " + secondPrototype + "\n");
    }

    @Test
    public void EntryListToStringTest() {
        // Arrange
        int amount = 515;
        List<String> testList = new LinkedList<>() {};
        testList.add("food");
        testList.add("rent");
        Entry entry = new Entry(amount, testList);

        int cyclicIntervalInDays = 7;
        CyclicEntryPrototype firstPrototype = new IntervalCyclicEntryPrototype(entry, cyclicIntervalInDays);

        int cyclicDayOfMonth = 14;
        CyclicEntryPrototype secondPrototype = new MonthlyCyclicEntryPrototype(entry, cyclicDayOfMonth);

        CyclicPrototypeList prototypeList = new CyclicPrototypeList();
        prototypeList.add(firstPrototype);
        prototypeList.add(secondPrototype);

        // Assert
        assertEquals(prototypeList.toString(), "CyclicPrototypeList{" +
                "prototypes=" + "[" +
                firstPrototype + ", " +
                secondPrototype +
                "]" +
                '}');
    }
}
