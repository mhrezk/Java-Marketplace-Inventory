import javax.imageio.IIOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.*;
import java.nio.file.Paths;


public class Inventory {

    private static ArrayList<FoodItem> inventory;       //declare Food item object array
    private int numItems;       //declare number of items being added
    private FoodItem fooditem;  //declare item for inputting in the Array List
    private final char fruit = 'f';     //declare char for fruit
    private final char vegetable = 'v';     //declare char for vegetable
    private final char preserve = 'p';

    /**
     * Non-Parameterized Constructor for initialization
     */
    public Inventory() {
        inventory = new ArrayList<>();      //instantiate the array
        numItems = 0;       //instantiate the num items
    }


    /**
     * Method of to string to print the array of inventory
     * @return string of the items in one line
     */
    public String toString() {
        String printer = "";
        System.out.println("Inventory:");
        for (int i = 0; i < numItems; i++) {
            printer = printer + inventory.get(i).toString() + "\n";
        }
        return printer;
    }

    /**
     * Method to test if item exists in inventory
     * @param item food time class object
     * @return 1 if item code is equal or -1 if it is unequal
     */
    public int alreadyExists(FoodItem item) {
        int index = -1;
        if (item.isEqual(item)) {       //testing similarities
             index = recursiveBinarySearch(item.getItemCode(),inventory,0,numItems-1);
            return index;
        }
        return index;
    }


    /**
     * Method to run recursive binary search within array
     * @param itemCode parameter to search in the array
     * @param itemArray Food item class array
     * @param first first index of the array
     * @param last last index of the array
     * @return index of key if found or -1 if it does not exist in array
     */
    public int recursiveBinarySearch(int itemCode, ArrayList<FoodItem> itemArray, int first, int last) {
        if (first <= last) {      //condition first smaller than last
            int mid = first + (last - first) / 2;     //the new start of the search
            if (itemArray.get(mid).getItemCode() < itemCode) {     //statement if array in place of smaller than key we are searching
                first = mid + 1;        //instantiate first to mid +1
                return recursiveBinarySearch(itemCode, itemArray, first, last);     //run the method again
            } else if (itemArray.get(mid).getItemCode() == itemCode) {     //statement if array in place of smaller than key we are searching
                return mid;     //return the index of the search key found
            } else {        //else statement
                last = mid - 1;     //instantiate last to mid +1
                return recursiveBinarySearch(itemCode, itemArray, first, last);     //run the method again
            }
        }
        return -1;      //return -1 if there is no key found
    }

    /**
     * Method for adding items to inventory
     * @param scan scanner object
     * @param fromFile testing to see if items are being added from file
     * @return boolean if process was successful
     * @throws IllegalAccessException Exception is thrown for errors in input or file absence
     */
    public boolean addItem(Scanner scan, boolean fromFile) throws IllegalAccessException {
        boolean test = false;      //declare boolean
        char choice;

        do {        //do while loop
            if (fromFile) {
                System.out.print("What would you like to add?\n(f)ruit, (v)egetable or a (p)reserve?\nChoose an option: ");     //print statement
                choice = TestInputs.testEmptyString(scan).charAt(0);       //input char for the type of food item
                test = itemSelect(choice, scan, fromFile);
            } else {
                try {
                    System.out.print("Enter the filename to read from: ");
                    Path fileName = Paths.get(scan.next());
                    scan = new Scanner(fileName);
                    while (scan.hasNext()) {
                        choice = scan.nextLine().charAt(0);
                        test = itemSelect(choice, scan, fromFile);
                    }
                } catch (FileNotFoundException e) {//exception for file not found
                    System.out.println("<<<< File not found >>>>");
                } catch (NoSuchFileException e) {//exception for file io
                    System.out.println("<<<< File Not Found >>>>");
                    break;
                }catch (NoSuchElementException e) {
                    System.out.println("<<<< Invalid file name >>>>");
                    scan.nextLine();
                } catch (IOException e) {//exception for file i/o
                    System.out.println(e.getMessage());
                }

                }
        } while (!test);       //loop condition

        return test;       //return boolean
    }


    /**
     * Method to select item based on object created
     * @param choice character that determines which object is to be created
     * @param scan Scanner object parameter
     * @param fromFile Boolean to indicate if the items are being read from a file or not
     * @return True if no errors exist
     * @throws IllegalAccessException Thrown if item already exists in inventory
     */
    private boolean itemSelect(char choice, Scanner scan, boolean fromFile) throws IllegalAccessException{
        final char fruit = 'f';     //declare char for fruit
        final char vegetable = 'v';     //declare char for vegetable
        final char preserve = 'p';      //declare char for preserve
        boolean test = false;
        switch (choice) {       //switch statement
            case fruit:
                fooditem = new Fruit();      //declare new fruit object
                break;
            case vegetable:
                fooditem = new Vegetable();      //declare new vegetable object
                break;
            case preserve:
                fooditem = new Preserve();       //declare new preserve object
                break;
            default:
                System.out.println("Invalid entry");        //print statement
                break;

        }
        try {
            fooditem.addItem(scan, fromFile);        //call method addItem to input item
            int exist = alreadyExists(fooditem);        //make sure that the code exist
            if (exist == -1) {
                test = inventory.add(fooditem);       //method to an item in place of numItems for the array
                Collections.sort(inventory);
                numItems++;     //increase the numItems by 1
            } else {
                throw new IllegalAccessException("Item code already exists!");
            }
        }catch (NullPointerException ignored){

        }


        return test;
    }

    /**
     *method that will update the quantity of the item
     * @param scan scanner object
     * @param buyOrSell boolean variable to test if the item for sale or to buy
     * @return boolean if it was successful or not
     */
    public boolean updateQuantity(Scanner scan, boolean buyOrSell) {

        try {       //try statement
            if (numItems > 0) {       //make sure that there is items in the array
                int quantity;       //declare quantity
                System.out.print("Enter the code for the item: ");      //print statement
                int itemCode = Integer.parseInt(TestInputs.testEmptyString(scan));      //input the item code needed
                int index = recursiveBinarySearch(itemCode, inventory, 0, numItems - 1);        //search for the code in the array
                boolean quantityGoods = false;      //declare boolean
                if (index != -1) {      //if the item exist
                    if (buyOrSell) {        //what needed to be done true buy false sell
                        quantity = TestInputs.inputInteger(scan, "Enter valid quantity to buy: ");      //input quantity needed
                        quantityGoods = inventory.get(index).updateItem(quantity);      //method to update quantity
                    } else {
                        quantity = TestInputs.inputInteger(scan, "Enter valid quantity to sell: ");     //input quantity needed
                        quantityGoods =inventory.get(index).updateItem(quantity * -1);     //method to update quantity
                    }
                }
                return quantityGoods;       //return true or false
            }
        } catch (ArrayIndexOutOfBoundsException e) {        //exception catch
            System.out.println();
        } catch (NumberFormatException e) {     //exception catch
            System.err.println("Please Enter Integer");     //exception catch
        }
        return false;       //return false
    }

    /**
     * method that will save the inventory into a file
     * @param scan Scanner object
     */
    public void saveFoodItemToFile(Scanner scan){
        Formatter output = null;
        try{
            System.out.print("Enter name for file creation: ");
            String filename = TestInputs.testEmptyString(scan);
            output = new Formatter(filename);
            for (FoodItem item : inventory){
                if(item instanceof Fruit) {
                    output.format("%c\n", fruit);
                } else if(item instanceof Vegetable) {
                    output.format("%c\n", vegetable);
                } else if(item instanceof Preserve) {
                    output.format("%c\n", preserve);
                }
                item.writeItemReadFromFile(output);
            }
        }catch (SecurityException e){
            System.out.println("Writing error!");
        }catch (IOException e){
            System.out.println("File could not be opened!");
        }
        if (output != null){
            output.close();
        }
    }

    /**
     * Method that will search for item
     * @param scan scanner class to read info
     * @return true or false if the item found
     */
    public boolean searchForItem(Scanner scan) {
        int item = TestInputs.inputInteger(scan, "Enter the code for the item:");
        int index = recursiveBinarySearch(item, inventory, 0, numItems - 1);

        if (index == -1) {
            return false;
        } else {
            System.out.println(inventory.get(index));
            return true;
        }
    }
}

