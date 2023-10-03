package services;

import exceptions.GuestServiceException;
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
    private GuestRepository guestRepository;
    private ReservationRepository reservationRepository;
    private TableRepository tableRepository;

    public GuestService(GuestRepository repository, ReservationRepository reservationRepository,
                        TableRepository tableRepository) {
        this.guestRepository = repository;
        this.reservationRepository = reservationRepository;
        this.tableRepository = tableRepository;
    }

    public void signUp(String name, String phoneNumber) throws GuestServiceException {
        if(((phoneNumber.length() != 12) && !phoneNumber.startsWith("+7")) || !name.matches("[a-zA-Z]+")) {
            throw new GuestServiceException("Неправильно введен номер телефона или неправильно введено имя");
        }
        Guest guest = new Guest(name, phoneNumber);
        guestRepository.add(guest);
    }

    public void updateInfo(String name, String surname, String oldPhoneNumber, String newPhoneNumber) {
        Guest guest = guestRepository.getGuest(oldPhoneNumber);
        guest.setName(name);
        guest.setSurname(surname);
        guest.setPhoneNumber(newPhoneNumber);
        guestRepository.updateGuest(guest);
    }

    public void delete(String phoneNumber) {
        guestRepository.deleteGuest(phoneNumber);
    }

    public void book(String phoneNumber, String reservationDateStart, String reservationDateEnd,
                     int numberOfPersons, String name) throws GuestServiceException {
        //if(/*ошибка в данных*/){
        if(((phoneNumber.length() != 12) && !phoneNumber.startsWith("+7")) || !name.matches("[a-zA-Z]+")) {
            throw new GuestServiceException("Неправильно введен номер телефона или неправильно введено имя");
        }
        // ошибки нет:
        if(guestRepository.getGuest(phoneNumber) == null) {
            signUp(name, phoneNumber);
        }
        Optional<Table> t = tableRepository.getFreeTables(reservationDateStart, reservationDateEnd).stream().
                filter(table -> table.getCapacity() >= numberOfPersons).findAny();
        if(t.isEmpty()) {
            throw new GuestServiceException("Нет свободного столика в данное время");
        }
        Table table1 = t.get();
        Guest guest1 = guestRepository.getGuest(phoneNumber);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(reservationDateStart, formatter);
        Reservation reservation1 = new Reservation(1, localDateTime, numberOfPersons,table1, guest1);
        reservationRepository.add(reservation1);
        }

    public Guest getInfo(String phoneNumber) {
        return guestRepository.getGuest(phoneNumber);
    }
}

//select * from reservations inner join tables on reservations.table_id = tables.id
//where 2023-09-24 22:00:00 > reservation_date_start
//	and 2023-09-24 22:00:00 < reservation_date_end
//	and number_of_persons >= ?