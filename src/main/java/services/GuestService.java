package services;

import models.Guest;
import repositories.GuestRepository;

public class GuestService {
    private GuestRepository repository;

    public GuestService(GuestRepository repository) {
        this.repository = repository;
    }

    public void signUp(String name, String phoneNumber) {
        Guest guest = new Guest(name, phoneNumber);
        repository.add(guest);
    }

    public void updateInfo(String name, String surname, String oldPhoneNumber, String newPhoneNumber) {
        Guest guest = new Guest(0, name, surname, newPhoneNumber);
        repository.updateGuest(guest);
    }
}
