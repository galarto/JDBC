package models;

public class Guest {
    private int id;
   private String name;
   private String surname;
   private String phoneNumber;

    public Guest(int id, String name, String surname, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void book() {

    }

    public void cancelBooking() {

    }

    public void changeNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
