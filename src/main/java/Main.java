import models.Guest;
import repositories.GuestRepository;

public class Main {
    public static void main(String[] args) {
        Guest guest1 = new Guest(1, "Дмитрий", "Иванов", "+79999999999");
        GuestRepository guestRepository = new GuestRepository();
        guestRepository.add(guest1);
        Guest guest2 = guestRepository.getGuest(1);
        System.out.println(guest2);
    }
}
