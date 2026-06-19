import java.util.*;
import java.io.*;

public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(201, "Deluxe"));
        rooms.add(new Room(202, "Deluxe"));
        rooms.add(new Room(301, "Suite"));

        while (true) {

            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Booking Details");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    viewRooms();
                    break;

                case 2:
                    bookRoom();
                    break;

                case 3:
                    cancelBooking();
                    break;

                case 4:
                    viewBookings();
                    break;

                case 5:
                    System.out.println("Thank You!");
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    static void viewRooms() {
        System.out.println("\nAvailable Rooms:");

        for (Room room : rooms) {
            if (room.available) {
                System.out.println(
                        "Room No: " + room.roomNumber +
                        " | Category: " + room.category);
            }
        }
    }

    static void bookRoom() {

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        viewRooms();

        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        for (Room room : rooms) {

            if (room.roomNumber == roomNo && room.available) {

                System.out.println("Payment Successful!");

                room.available = false;

                try {
                    FileWriter fw = new FileWriter("bookings.txt", true);

                    fw.write(name + "," +
                            room.roomNumber + "," +
                            room.category + "\n");

                    fw.close();

                } catch (Exception e) {
                    System.out.println("Error!");
                }

                System.out.println("Room Booked Successfully!");
                return;
            }
        }

        System.out.println("Room Not Available!");
    }

    static void cancelBooking() {

        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();

        for (Room room : rooms) {

            if (room.roomNumber == roomNo) {

                room.available = true;

                System.out.println("Booking Cancelled!");
                return;
            }
        }
    }

    static void viewBookings() {

        try {

            File file = new File("bookings.txt");

            Scanner fileReader = new Scanner(file);

            System.out.println("\nBooking Details:");

            while (fileReader.hasNextLine()) {
                System.out.println(fileReader.nextLine());
            }

            fileReader.close();

        } catch (Exception e) {
            System.out.println("No Bookings Found!");
        }
    }
}
