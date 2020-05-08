import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Theatre {
    private final String theatreName;
    public List<Seat> seats = new ArrayList();

    //CREATING A THEATER WITH SEATS ON ROWS AND COLUMNS,ADDING THE SEAT DIRECTLY TO THE LIST
    public Theatre(String thearteName, int numRows, int seatsPerRow) {
        this.theatreName = thearteName;
        int lastRow = 'A' + (numRows - 1);
        for (char row = 'A'; row <= lastRow; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                Seat seat = new Seat(row + String.format("%02d", seatNum));
                seats.add(seat);
            }
        }
    }

    //RETURN THEATRE NAME
    public String getTheatreName() {
        return theatreName;
    }

    //BINARY SEARCH HERE, IF THE LIST IS ORDERED
    public boolean reserveSeat(String seatNumber) {
        //COMPARABLE OBJECT
        Seat requestedSeat = new Seat(seatNumber);
        int foundSeat = Collections.binarySearch(seats, requestedSeat, null);
        //IF THE FOUND SEAT IS GREATER OR EQUAL TO ZERO THE SEAT NUMBER WAS FOUND
        if (foundSeat >= 0) {
            return seats.get(foundSeat).reserve();
        } else {
            System.out.println("The is not seat with the number" + seatNumber);
        }
        return false;
    }

    //RETURN THE LIST OF ALL SEATS
    public void getSeats() {
        for (Seat seat : seats) {
            System.out.print(seat.getSeatNumber() + " ");
        }
    }

    //SEAT CLASS implements Comparable to implement binary search instead of brute force search
    public class Seat implements Comparable<Seat> {
        private final String seatNumber;
        private boolean reserved = false;

        //CONSTRUCTOR
        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        //IF THIS SEAT WASEN't RESERVER YET, IT WILL GET THE VALUE OF TRUE AND WILL PRINT THAT SOUT
        public boolean reserve() {
            if (!this.reserved) {
                this.reserved = true;
                System.out.println("Seat " + seatNumber + " reserved");
                return true;
            }
            return false;
        }

        //IF THE SEAT IS RESERVED IT WILL BE CANCELED
        public boolean cancel() {
            if (this.reserved) {
                this.reserved = false;
                System.out.println("Reservation of seat " + seatNumber + " cancelled");
                return true;
            }
            return false;
        }

        //RETURNING THE SEAT NUMBER
        public String getSeatNumber() {
            return seatNumber;
        }

        //Override compareTo from Comparable interface
        @Override
        public int compareTo(Seat seat) {
            //USING THE COMPARE METHOD WHICH IS BUILT IN TO STRING CLASS
            return this.seatNumber.compareTo(seat.getSeatNumber());
        }
    }
}

