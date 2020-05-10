import java.util.ArrayList;
import java.util.List;

public class GroceryList {

    private final List<String> groceryList = new ArrayList<>();
    private static List<String> addAllArray=new ArrayList<>();;
    private static List<String> shallowCopyArray=new ArrayList<>();

    //1 ADD
    public void addGroceryItem(String item) {
        groceryList.add(item);
    }

    //2 SET
    public void modifyGroceryItem(int position, String newitem) {
        groceryList.set(position, newitem);
        System.out.println("Grocery item at the index" + position + "has been modified");
    }
    //3 REMOVE
    public void removeGroceryItem(int position) {
        String theItem = groceryList.get(position);
        System.out.println(groceryList.get(position));
        groceryList.remove(position);
    }
    //4 CONTAINS
    //return true if the item was found
    public boolean containsItem(String searchItem) {
        boolean exists = groceryList.contains(searchItem);
        return exists;
    }
    //5 INDEX OF
    //returns -1 if the item wasn't found
    public int indexOfTheItem(String searchItem) {
        int itemIndex = groceryList.indexOf(searchItem);
        return itemIndex;
    }
    //6 SIZE
    public int sizeOfTheList(){
        return groceryList.size();
    }
    //7 ADD ALL
    public void createArrayByAddAll(){
        addAllArray=new ArrayList<>();
        addAllArray.addAll(groceryList);
        System.out.println("Copy list used addAll created");

    }
    //8 SHALLOW COPY
    public void  createShallowCopy(){
        shallowCopyArray=new ArrayList<>(groceryList);
        System.out.println("Shallow copy created");
    }
    //9 PARSE  A LIST
    public void printGroceryList() {
        System.out.println("You have " + groceryList.size() + " items in your grocery list");
        for (int i = 0; i < groceryList.size(); i++) {
            System.out.println("index " + i + ". : "+ groceryList.get(i));
        }
    }
    public void showAddAllList(){
        System.out.println("You have " + addAllArray.size() + " items in your ADD ALL list");
        for (int i = 0; i < addAllArray.size(); i++) {
            System.out.println("index " + i + ". : "+ addAllArray.get(i));
        }
    }
    public void showShallowList(){
        System.out.println("You have " + shallowCopyArray.size() + " items in your SHALLOW list");
        for (int i = 0; i < shallowCopyArray.size(); i++) {
            System.out.println("index " + i + ". : "+ shallowCopyArray.get(i));
        }
    }




}