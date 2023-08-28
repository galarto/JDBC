package models;

public class Table {
    private int id;
    private int capacity;
    private boolean isAvailable;
    private int number;

    public Table(int id, int capacity, boolean isAvailable, int number) {
        this.id = id;
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
