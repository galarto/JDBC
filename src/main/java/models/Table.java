package models;

public class Table {
    private int id;
    private int capacity;
    private boolean isAvailable;
    private int number;

    public Table(int id, int capacity, int number) {
        this.id = id;
        this.capacity = capacity;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", number=" + number +
                '}';
    }
}
