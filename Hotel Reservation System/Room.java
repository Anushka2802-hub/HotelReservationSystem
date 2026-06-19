public class Room {
    int roomNumber;
    String category;
    boolean available;

    public Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.available = true;
    }
}