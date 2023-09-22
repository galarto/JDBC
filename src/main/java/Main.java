import models.Guest;
import models.Reservation;
import models.Table;
import repositories.GuestRepository;
import repositories.ReservationRepository;
import repositories.TableRepository;

public class Main {
    public static void main(String[] args) {
        Guest guest1 = new Guest(1, "Дмитрий", "Иванов", "+79999999999");
        ReservationRepository reservationRepository = new ReservationRepository();
        Table table2 = new Table(2, 6, true, 2);
        TableRepository tableRepository = new TableRepository();
        tableRepository.add(table2);
        System.out.println(tableRepository.getTable(2));
        
    }
}
