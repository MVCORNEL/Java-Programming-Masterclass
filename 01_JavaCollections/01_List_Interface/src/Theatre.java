import java.util.ArrayList;
import java.util.List;

public class Theatre {
    private final String theatreName;
    private List<Seat> seats = new ArrayList<>();
    //CREATING A THEATER WITH SEATS ON ROWS AND COLUMNS
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
    //RETURN THARTE NAME
    public String getTheatreName() {
        return theatreName;
    }
    //PARSING THE SEATS LIST, IF THE SEAT NUMBER ISN'T FOUND IT WILL BE NULL,IF THE SEAT NUMBER IS FOUND THE RESERVE METHOD FROM SEAT CLASS WILL BE CALLED
    public boolean reserveSeat(String seatNumber) {
        Seat requestedSeat = null;
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equals(seatNumber)) {
                requestedSeat = seat;
                break;
            }
        }
        //THE SEAT ISN'T FOUND RETURN FALSE
        if (requestedSeat == null) {
            return false;
        }
        //IF THE SEAT IS FOUND THIS METHOD GET CALLED
        return requestedSeat.reserve();
    }
    //RETURN THE LIST OF ALL SEATS
    public void getSeats() {
        for (Seat seat : seats) {
            System.out.println(seat.getSeatNumber());
        }
    }
    //SEAT CLASS
    private class Seat {
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
    }
}
