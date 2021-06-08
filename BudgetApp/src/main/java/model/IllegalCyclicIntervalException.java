package model;

public class IllegalCyclicIntervalException extends IllegalArgumentException{
    public IllegalCyclicIntervalException() {
        super("Illegal cyclic interval - it must be a positive number!");
    }
}
