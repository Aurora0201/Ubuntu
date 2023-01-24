package Hotel;

public class Test {
    public static void main(String[] args) {
        Hotel hotel = new Hotel(10);
        hotel.checkIn(0, 0, 2);
        hotel.checkIn(0, 0, 3);
        hotel.checkIn(0, 1, 2);
        hotel.checkIn(11, 1, 2);
//        hotel.printRoomStatus();
    }
}
