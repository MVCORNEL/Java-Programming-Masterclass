import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Theatre theatre = new Theatre("Olympian", 8, 12);
        if (theatre.reserveSeat("C05")) {
            System.out.println("Please pay for C05");
        } else {
            System.out.println("Seat already reserved");
        }
        printList(theatre.getSeats());

        List<Theatre.Seat> priceSeats = new ArrayList<>(theatre.getSeats());
        priceSeats.add(theatre.new Seat("B00", 11.00));
        priceSeats.add(theatre.new Seat("A00", 11.00));
        Collections.sort(priceSeats, Theatre.PRICE_ORDER);
        printList(priceSeats);
    }

    public static void printList(Collection<Theatre.Seat> list) {
        for (Theatre.Seat seat : list) {
            System.out.println(seat.getSeatNumber() + " - " + seat.getPrice() + " $");
        }
        System.out.println();
        System.out.println("============================================");
    }
}
