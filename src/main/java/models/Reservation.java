package models;

import java.time.LocalDateTime;

public class Reservation {
    private int id;
    private LocalDateTime reservationDateStart;
    private LocalDateTime reservationDateEnd;
    private Table table;
    private Guest guest;
    private int numberOfPersons;

    public Reservation(int id, LocalDateTime reservationDate, int numberOfPersons, Table table, Guest guest) {
        this.id = id;
        this.reservationDateStart = reservationDate;
        this.reservationDateEnd = reservationDate.plusHours(2);
        this.numberOfPersons = numberOfPersons;
        this.table = table;
        this.guest = guest;
    }

    public LocalDateTime getReservationDateStart() {
        return reservationDateStart;
    }

    public Table getTables() {
        return table;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public LocalDateTime getReservationDateEnd() {
        return reservationDateEnd;
    }

    public Guest getGuest() {
        return guest;
    }
}
