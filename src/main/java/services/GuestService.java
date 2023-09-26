package services;

import models.Guest;
import models.Table;
import repositories.GuestRepository;
import repositories.ReservationRepository;
import repositories.TableRepository;

import java.util.ArrayList;


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

    public void book(String phoneNumber, String reservationDateStart, int numberOfPersons, String name) {
        //if(/*ошибка в данных*/){
        // ошибки нет:
        signUp(name, phoneNumber);
        ArrayList<Table> tables = tableRepository.getTables();
        for (Table t : tables) {
            if(t.isAvailable() && t.getCapacity() >= numberOfPersons) {

            }
        }
    }

    public Guest getInfo(String phoneNumber) {
        return repository.getGuest(phoneNumber);
    }
}

//select * from reservations inner join tables on reservations.table_id = tables.id
//where 2023-09-24 22:00:00 > reservation_date_start
//	and 2023-09-24 22:00:00 < reservation_date_end
//	and number_of_persons >= ?