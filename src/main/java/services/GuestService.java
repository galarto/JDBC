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
        Guest guest = repository.getGuest(oldPhoneNumber);
        guest.setName(name);
        guest.setSurname(surname);
        guest.setPhoneNumber(newPhoneNumber);
        repository.updateGuest(guest);
    }

    public void delete(String phoneNumber) {
        repository.deleteGuest(phoneNumber);
    }

    public Guest getInfo(String phoneNumber) {
        return repository.getGuest(phoneNumber);
    }
}
