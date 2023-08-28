package models;

public class Guest {
   private String name;
   private String surname;
   private String phoneNumber;

    public Guest(String name, String surname, String phoneNumber) {
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
