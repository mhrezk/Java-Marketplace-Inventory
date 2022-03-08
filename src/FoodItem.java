import java.util.Formatter;
import java.util.Scanner;

public class FoodItem implements Comparable  {



    private int itemCode;       //declare the item code
    private String itemName;        //declare the item name
    private float itemPrice;        //declare the item price
    private int itemQuantityInStock;        //declare the item Quantity
    private float itemCost;     //declare the item cost

    /**
     * Method of to string to print the items
     * @return string of the items in inventory
     */
    @Override
    public String toString(){

        return "Item: "+itemCode +" "+ itemName + " price: $" +itemPrice+ " cost $"+itemCost;
    }

    /**
     * Method that will test if the quantity being removed is valid
     * @param amount value being added
     * @return true or false if quantity is valid
     */
    public boolean updateItem(int amount){
        int result = itemQuantityInStock + amount;      //the new stock  quantity
        if(result < 0){       //test if the quantity in the minus
            return false;       //return false
        }else {     //else it's in positive
            itemQuantityInStock = result;       //instantiate itemQuantityInStock from result
            return true;     //return true
        }
    }

    /**
     * Method to test if input has the same item code
     * @param item food item class object
     * @return boolean expiration if it's the same item or not
     */
    public boolean isEqual(FoodItem item){

        return itemCode == item.itemCode;     //test if it's the same item
    }

    /**
     * Method to add items to list
     * @param scan scanner class object
     * @param fromFile testing to see if items are being added from file
     * @return boolean if it was successfully or not
     */
    public boolean addItem(Scanner scan, boolean fromFile){
        boolean test = true;     //boolean variable
        do {        //do while loop for try and catch
            try {       //try statement
                if (fromFile) {
                    test = inputCode(scan);        //method to input item code
                    System.out.print("Enter the name of item: ");      //print statement
                    itemName = TestInputs.testEmptyString(scan);     //input the item name
                    itemQuantityInStock = TestInputs.inputInteger(scan, "Enter quantity of item: ");        //input item quantity from Testinputs method
                    itemCost = TestInputs.testFloat(scan, "Enter cost of item: ");        //input item cost from Testinputs method
                    itemPrice = TestInputs.testFloat(scan, "Enter sales price of item: ");        //input item price from Testinputs method
                    test = false;      //change boolean to false if no errors exist
                    return test;       //return the boolean
                } else {
                    addItemReadFromFile(scan); //read file items
                    return test;       //return the boolean
                }
            }catch (NumberFormatException e){       //catch input Mismatch statement
                System.err.println("----Enter Integer----");     //print error to the user
            }catch (Exception e){       //catch incorrect number format
                System.err.println("----Invalid item code----");        //print error to the user
                scan.nextLine();
            }
        }while (test);     //loop condition


        return test;       //return boolean
    }

    /**
     * Method for item code input
     * @param scan scanner object
     * @return true if there are no errors
     */
    public boolean inputCode(Scanner scan){
            System.out.print("Enter code for the item: ");
            String str=TestInputs.testEmptyString(scan);
            itemCode = Integer.parseInt(str);
            return true;
    }

    /**
     * Getter for item code
     * @return item code
     */
    public int getItemCode() {
        return itemCode;
    }


    /**
     * method instantiate the items from file
     * @param scan scanner class object
     */
    private void addItemReadFromFile(Scanner scan) {
        itemCode=scan.nextInt();
        scan.nextLine();
        itemName=scan.nextLine();
        itemQuantityInStock=scan.nextInt();
        scan.nextLine();
        itemPrice=scan.nextFloat();
        scan.nextLine();
        itemCost=scan.nextFloat();
        scan.nextLine();
    }

    /**
     * Method to write item to file
     * @param output Formatter class object
     */
    public void writeItemReadFromFile(Formatter output){
        output.format("%d\n%s\n%d\n%.2f\n%.2f\n",itemCode, itemName,itemQuantityInStock , itemPrice,itemCost);
    }

    /**
     * Method to sort array
     * @param comparesTo object that will receive the array
     * @return item user wants returned
     */
    @Override
    public int compareTo(Object comparesTo) {
        int compare = ((FoodItem)comparesTo).getItemCode();
        return this.itemCode-compare;
    }
}
