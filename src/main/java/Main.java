import exceptions.GuestServiceException;
import repositories.GuestRepository;
import repositories.ReservationRepository;
import repositories.TableRepository;
import services.GuestService;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GuestService guestService = new GuestService(new GuestRepository(), new ReservationRepository(),
            new TableRepository());
        Scanner scanner = new Scanner(System.in);
        printMenu();
        String command = scanner.nextLine();
        while(!Objects.equals(command, "0")) {
            switch (command) {
                case "1":
                    System.out.println("Введите имя");
                    String name = scanner.nextLine();
                    System.out.println("Введите номер телефона в формате +7----------");
                    String phoneNumber = scanner.nextLine();
                    try {
                        guestService.signUp(name, phoneNumber);
                    } catch (GuestServiceException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    System.out.println("Введите номер телефона");
                    String oldPhoneNumber = scanner.nextLine();
                    System.out.println("Введите имя: ");
                    name = scanner.nextLine();
                    System.out.println("Введите фамилию: ");
                    String surname = scanner.nextLine();
                    System.out.println("Введите новый номер телефона(если номер не изменился введите прежний номер: )");
                    String newPhoneNumber = scanner.nextLine();
                    guestService.updateInfo(name, surname, oldPhoneNumber, newPhoneNumber);
                    System.out.println("Информация обновлена");
                    break;
                case "3":
                    System.out.println("Введите номер телефона");
                    phoneNumber = scanner.nextLine();
                    System.out.println("Введите имя: ");
                    name = scanner.nextLine();
                    System.out.println("Введите дату и время начала брони: ");
                    String reservationDateStart = scanner.nextLine();
                    System.out.println("Введите дату и время окончания брони: ");
                    String reservationDateEnd = scanner.nextLine();
                    System.out.println("На какое кол-во персон будет заброинрован стол?");
                    int numberOfPersons = Integer.parseInt(scanner.nextLine());
                    try {
                        guestService.book(phoneNumber, reservationDateStart, reservationDateEnd, numberOfPersons, name);
                        System.out.println("Бронь успешно создана");
                    } catch (GuestServiceException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "4":
                    System.out.println("Введите номер телефона");
                    phoneNumber = scanner.nextLine();
                    guestService.delete(phoneNumber);
                    System.out.println("Ваш аккаунт удален");
                    break;
                default:
                    System.out.println("Неверно введена команда. Пожалуйста, выберите цифру из меню");
                    break;
            }
            printMenu();
            command = scanner.nextLine();
        }
        System.out.println("Программа завершена");

//        GuestService guestService = new GuestService(new GuestRepository(), new ReservationRepository(),
//                new TableRepository());
//        try {
//            guestService.book("+89198889922", "2023-09-29 22:00:00",
//                    "2023-09-30 00:00:00", 4, "Anton");
//        } catch (GuestServiceException e) {
//            System.out.println(e.getMessage());
//            String jsonResult = "operation: book," +
//                    "result: exception," +
//                    "message: " + e.getMessage();
//        }


    }

    private static void printMenu() {
        System.out.println("1 - Зарегистрироваться");
        System.out.println("2 - Обновить информацию о себе");
        System.out.println("3 - Забронировать столик");
        System.out.println("4 - Удалить аккаунт");
        System.out.println("0 - Выйти");
    }
}


//регистрация
//обновить информацию
//забронировать столик
//удалить аккаунт