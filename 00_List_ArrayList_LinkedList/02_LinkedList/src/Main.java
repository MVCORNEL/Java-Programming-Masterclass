import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args) {

        LinkedList<String> placesToVisit = new LinkedList<>();

        addInOrder(placesToVisit,"Sydney");
        addInOrder(placesToVisit,"Melbourne");
        addInOrder(placesToVisit,"Brisbane");
        addInOrder(placesToVisit,"Perth");
        addInOrder(placesToVisit,"Canaberra");
        addInOrder(placesToVisit,"Adelaide");
        addInOrder(placesToVisit,"Darwine");
        addInOrder(placesToVisit, "Alice Spring");

        printList(placesToVisit);
    }

    // 1 ITERATOR
    private static void printList(LinkedList<String> linkedList) {
        Iterator<String> mIterator = linkedList.iterator();
        while (mIterator.hasNext()) {
            System.out.println("\t Now visiting :" + mIterator.next());
        }
        System.out.println("=============================================");
    }

    // 2 LIST ITERATOR -> going backwards
    //  ADD IN ORDER -> DON'T IMPLEMENT IT WITH ARRAY LIST, BECAUSE EVERY TIME WHEN YOU ADD AN ELEMENT THE OTHER ONES WILL BE MOVED IN CAS IS NOT THE LAST ONE
    private static boolean addInOrder(LinkedList<String> linkedList, String newCity) {
        ListIterator<String> mListIterator = linkedList.listIterator();
        while (mListIterator.hasNext()) {
            int comparison = mListIterator.next().compareTo(newCity);

            if (comparison == 0) {
                //equal do not add
                System.out.println(newCity + " Already included as destination");
                return false;
            }
            else if (comparison > 0) {
                mListIterator.previous();
                mListIterator.add(newCity);
                return true;
                //new city should appear before this one
                //
            }
            else {
              //comparison < 0
              // move to the next city
            }
        }
        //we went to entire lust without finding a suitable point to insert the entry
        //new item must go to the end of the list
        //item need to go to the end of the List
        mListIterator.add(newCity);
        return true;
    }

}
