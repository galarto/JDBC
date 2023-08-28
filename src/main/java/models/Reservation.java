package models;

import java.time.LocalDateTime;
import java.util.List;

public class Reservation {
    private LocalDateTime reservationDate;
    private LocalDateTime reservationDateEnd;
    private Table table;
    private int numberOfPersons;

    public Reservation(LocalDateTime reservationDate, int numberOfPersons) {
        this.reservationDate = reservationDate;
        this.reservationDateEnd = reservationDate.plusHours(2);
        this.numberOfPersons = numberOfPersons;
        if(table.getCapacity() >= numberOfPersons & table.isAvailable()) {

        }

    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public Table getTables() {
        return table;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }
}
