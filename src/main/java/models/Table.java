package models;

public class Table {
    private int capacity;
    private boolean isAvailable;
    private int number;

    public Table(int capacity, boolean isAvailable, int number) {
        this.capacity = capacity;
        this.isAvailable = isAvailable;
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getNumber() {
        return number;
    }
}
