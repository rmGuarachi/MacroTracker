package edu.qc.cs370.macrotracker.db;


// ENUM created by RG
public enum MealTime {
    BREAKFAST(1), LUNCH(2), DINNER(3), SNACK(4);

    private final int value;
    MealTime(int value) { this.value = value; }
    public int getValue() { return value; }

}
