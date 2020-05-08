import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Theatre theatre = new Theatre("Olympian", 8, 12);
        //1
        //SHALLOW COPY ->
        // CREATED BY PASSING ANOTHER LIST TO THE CONSTRUCTOR
        //SHALLOW COPY OF THE SAME DATA, CREATING ANOTHER LIST REFERENCE POINTED ON THE SAME OBJECT
        //TWO LIST SHARING THE SAME DATA, BUT LIST CAN HAVE DIFFERENT STRUCTURE
        //THEY HAVE SAME DATA BUT CAN HAVE DIFFERENT ORDER
        //WHEN YOU MODIFY ONE DATA THE ANOTHE LSIT DATA WILL BE MODIFY,
        //BUT IF YOU MODIFY ONE LIST ORDER OF THE ELEMENTS THE TWO LIST WILL HAVE SAME ELEMNTS WITH DIFFERENT ORDER
        List<Theatre.Seat> seatCopy = new ArrayList<>(theatre.seats);
        printList(seatCopy);
        //TWO SEPARATE ARRAYS SHARING THE SAME DATA
        //RESERVING THE FIRST SEAT ON THE BOTH LISTS -> RESULTS ON SHARING THE SAME DATA
        seatCopy.get(0).reserve();
        if (theatre.reserveSeat("A01")) {
            System.out.println("Please pay for A02");
        } else {
            System.out.println("Seat already reserved");
        }
        //2
        //COLLECTIONS REVERSE METHOD
        //BECAUSE ONE OF THE LIST IS THE SHALLOW LIST THEY SHARE SAME DATA
        //BUT THEY CAN HAVE DIFFERENT ELEMENTS ORDER
        Collections.reverse(seatCopy);
        System.out.println("PRINTING REVERSED SEAT COPY");
        printList(seatCopy);
        System.out.println("Printing theatre.seat");
        theatre.getSeats();
        //3
        //COLLECTIONS SHUFFLE METHOD
        Collections.shuffle(seatCopy);
        System.out.println("PRINTING SHUFFLED SEAT COPY");
        printList(seatCopy);
        //4
        // COLLECTIONS MIN, MAX
        // MIN AND MAX WORK EVEN IF THE LIST IS NOT ORDERED
        // THE  MAX AND MIN ARE GOTTEN BY THE IMPLEMENTED COMPARABLE METHOD LOGIC WITHIN THE SEAT CLASS
        Theatre.Seat minSeat = Collections.min(seatCopy);
        Theatre.Seat maxSeat = Collections.max(seatCopy);
        System.out.println("MIN SEAT NUMBER = " + minSeat.getSeatNumber() + "\nMAX SEAT NUMBER = " + maxSeat.getSeatNumber());

        //5
        //COLLECTIONS COPY  ->USELESS->
        //CREATE ANOTHER INDIVIDUAL LIST
        //TAKES 2 PARAMETERS -> SOURCE AND DESTINATION
        //DESTINATION HAS TO BE OF THE GENERIC TYPE -> COLLECTION, ITERABLE AND LIST
        //SOURCE MUST BE A LIST
        //VERY USELESS -> WILL THROW ERROR BECAUSE IT EXPECTS THAT THE NEW LIST TO ALREADY HAVE THE SAME NUMBER OF ELEMENTS AS THE DESIRED ONE
        //VERY HARD TO THINK OF AT ACTUAL US
//        ArrayList<Theatre.Seat> newList=new ArrayList<>(seatCopy.size());
//        Collections.copy(newList,seatCopy);

        //6
        //COLLECTION SWAP -> SWAP INTO BUBBLE SORT
        System.out.println("Seat copy shuffled");
        Collections.shuffle(seatCopy);
        printList(seatCopy);
        System.out.println("Seat copy bubble sorted copy");
        bubbleSort(seatCopy);
        printList(seatCopy);

    }
    //6
    //COLLECTION SWAP -> SWAP INTO BUBBLE SORT
    //SWAPPING IS DONE BY OBJECT INDEXES
    //BUBBLE SORT USING SWAP AND COMPARE TO COLLECTION METHOD
    //MERGE SORT REQUIRES FAR MORE MEMORY THAN BUBBLE SORT AND IT'S WAY FASTER
    public static void bubbleSort(List<Theatre.Seat> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            //List may be already sorted
            boolean sortedFlag = true;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).compareTo(list.get(j)) > 0) {
                    Collections.swap(list, i, j);
                    //List is not sorted yet
                    sortedFlag = false;
                }
            }
            //List already sorted
            if (sortedFlag) break;
        }
    }
    public static void printList(List<Theatre.Seat> list) {
        for (Theatre.Seat seat : list) {
            System.out.print(seat.getSeatNumber() + " ");
        }
        System.out.println();
        System.out.println("============================================");
    }
}









