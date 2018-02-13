package ru.ac.uniyar.testingcourse;

/** 
 * The counter class that allows to increase the counter value,
 * get current value, and reset the counter.
 */
public class Counter {
    private int value = 0;

    /** Initializes the counter with 0 as an initial value. */
    public Counter() {
    }

    /** Gets current value of the counter. */
    public int getValue() {
        return value;
    }
    
    /** Adds 1 to the counter value. */
    public void increase() {
        ++value;
    }
    
    /** Resets the counter value to 0. */
    public void reset() {
        value = 1;
    }
}
