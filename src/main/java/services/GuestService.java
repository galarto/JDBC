package services;

import models.Guest;
import models.Reservation;
import models.Table;
import repositories.GuestRepository;
import repositories.ReservationRepository;
import repositories.TableRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


public class GuestService {
    private GuestRepository repository;
    private ReservationRepository reservationRepository;
    private TableRepository tableRepository;

    public GuestService(GuestRepository repository, ReservationRepository reservationRepository,
                        TableRepository tableRepository) {
        this.repository = repository;
        this.reservationRepository = reservationRepository;
        this.tableRepository = tableRepository;
    }

    public void signUp(String name, String phoneNumber) {
        Guest guest = new Guest(name, phoneNumber);
        repository.add(guest);
    }

    public void updateInfo(String name, String surname, String oldPhoneNumber, String newPhoneNumber) {
        Guest guest = repository.getGuest(oldPhoneNumber);
        guest.setName(name);
        guest.setSurname(surname);
        guest.setPhoneNumber(newPhoneNumber);
        repository.updateGuest(guest);
    }

    public void delete(String phoneNumber) {
        repository.deleteGuest(phoneNumber);
    }

    public void book(String phoneNumber, String reservationDateStart, String reservationDateEnd,
                     int numberOfPersons, String name) {
        //if(/*ошибка в данных*/){
        // ошибки нет:
        signUp(name, phoneNumber);
        TableRepository tableRepository1 = new TableRepository();
        Optional<Table> t = tableRepository1.getFreeTables(reservationDateStart, reservationDateEnd).stream().
                filter(table -> table.getCapacity() >= numberOfPersons).findAny();
        Table table1 = t.get();
        GuestRepository guestRepository1 = new GuestRepository();
        Guest guest1 = guestRepository1.getGuest(phoneNumber);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy HH:mm:ss a");
        LocalDateTime localDateTime = LocalDateTime.parse(reservationDateStart, formatter);
        Reservation reservation1 = new Reservation(1, localDateTime, numberOfPersons,table1, guest1);
        ReservationRepository reservationRepository1 = new ReservationRepository();
        reservationRepository1.add(reservation1);
        tableRepository1.updateTableStatus(reservation1);
        }

    public Guest getInfo(String phoneNumber) {
        return repository.getGuest(phoneNumber);
    }
}

//select * from reservations inner join tables on reservations.table_id = tables.id
//where 2023-09-24 22:00:00 > reservation_date_start
//	and 2023-09-24 22:00:00 < reservation_date_end
//	and number_of_persons >= ?