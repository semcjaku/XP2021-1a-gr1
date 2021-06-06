package model;

public class IllegalDayOfMonthException extends IllegalArgumentException {
    public IllegalDayOfMonthException() {
        super("Illegal day of month!");
    }
}
