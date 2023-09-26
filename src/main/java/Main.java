import models.Guest;
import models.Reservation;
import models.Table;
import repositories.GuestRepository;
import repositories.ReservationRepository;
import repositories.TableRepository;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Guest guest = new Guest(3,"Anton", "Ivanov", "+89036261310");
        GuestRepository guestRepository = new GuestRepository();
        guestRepository.add(guest);
        System.out.println(guest);
        Table table2 = new Table(4, 5, true, 2);
        TableRepository tableRepository = new TableRepository();
        tableRepository.add(table2);
        System.out.println(table2);
        Reservation reservation = new Reservation(8,LocalDateTime.of(2023, 12, 1, 22, 0, 0), 5, table2, guest);
        ReservationRepository reservationRepository = new ReservationRepository();
        reservationRepository.add(reservation);
        reservationRepository.getReservation(guest);
        System.out.println(reservation);




    }
}
