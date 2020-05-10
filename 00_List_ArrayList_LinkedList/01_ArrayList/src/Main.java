import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static GroceryList groceryList = new GroceryList();


    public static void main(String[] args) {
        boolean quit = false;
        int choice = 0;

        while (!quit) {
            printInstructions();
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            //Clear the buffer
            //Always add nextLine after nextInt -> because nextInt doesn't consume the separators
            switch (choice) {

                case 0:
                    groceryList.printGroceryList();
                    break;
                case 1:
                    addItem();
                    break;
                case 2:
                    modifyItem();
                    break;
                case 3:
                    removeItem();
                    break;
                case 4:
                    searchForItem();
                    break;
                case 5:
                    createArrayByAddAll();
                    break;
                case 6:
                    createShallowArray();
                    break;
                case 7:
                    printCopyList();
                    break;
            }
        }
    }


    private static void printInstructions() {
        System.out.println("\t 0 - To print the list of grocery items");
        System.out.println("\t 1 - To add an item to the list");
        System.out.println("\t 2 - To modify an item from the list");
        System.out.println("\t 3 - To remove an item of a list");
        System.out.println("\t 4 - To search from an item in the list");
        System.out.println("\t 5 - Create list by using addAll");
        System.out.println("\t 6 - Create shallow copy array");
        System.out.println("\t 7 - Display all lists");
        System.out.println("\t 8 - To quit the application");
    }

    private static void addItem() {
        System.out.println("Please enter the grocery item");
        groceryList.addGroceryItem(scanner.nextLine());
    }

    private static void modifyItem() {
        System.out.println("Please enter the item index number : ");
        int itemNo = scanner.nextInt();
        //Clear the buffer
        scanner.nextLine();

        if (itemNo >= groceryList.sizeOfTheList()) {
            System.out.println("Item index doesn't exist");
        } else {

            System.out.println("Enter replacement item");
            String newItem = scanner.nextLine();
            groceryList.modifyGroceryItem(itemNo, newItem);
        }
    }

    private static void removeItem() {
        System.out.println("Enter the item index number :");
        int itemNo = scanner.nextInt();
        //Clear the buffer
        scanner.nextLine();
        if (itemNo >= groceryList.sizeOfTheList()) {
            System.out.println("Item doesn't exist");
        } else {
            groceryList.removeGroceryItem(itemNo);
        }
    }

    private static void searchForItem() {
        System.out.println("Item to search for: ");
        String searchItem = scanner.nextLine();
        if (groceryList.containsItem(searchItem)) {
            System.out.println("Item found with the index position of : " + groceryList.indexOfTheItem(searchItem));
        } else {
            System.out.println("Item is not in the shopping list");
        }
    }


    private static void createArrayByAddAll() {
        groceryList.createArrayByAddAll();
    }

    private static void createShallowArray() {
        groceryList.createShallowCopy();
    }

    private static void printCopyList() {
        groceryList.printGroceryList();
        groceryList.showAddAllList();
        groceryList.showShallowList();
    }


}
