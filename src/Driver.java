import java.util.InputMismatchException;
import java.util.Scanner;



public class Driver {
    /**
     * Driver class to manipulate inventory of food items
     * @param args stores the arguments from the command line
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);        //Scanner object to scan inputs
        Inventory i = new Inventory();        //instantiate Inventory class
        int choice = 0;        //declare choice for switch
        boolean test = false;        //boolean variable to test true to adding ,buying or selling
        do {        //do while
            try {       //try catch statement
                displayMenu();      //method to print main menu
                choice = Integer.parseInt(TestInputs.testEmptyString(input));        //input statement to choose needed

                switch (choice) {       //switch statement
                    case 1:
                        test = i.addItem(input,true);      //add item method and return true or false
                        if(!test){     //test if there is an error in adding item
                            System.out.println("Item could not be added!");  //print error statement
                        }
                        break;
                    case 2:
                        System.out.println(i);       //print the items
                        break;
                    case 3:
                        test = i.updateQuantity(input, true);     //method to buy item ,send true to buy item
                        if(!test){     //test if there is an error in adding item
                            System.out.println("Item could not be bought!");     //print error statement
                        }
                        break;
                    case 4:
                        test = i.updateQuantity(input, false);      //method to buy item ,send true to sell item
                        if(!test){     //test if there is an error in adding item
                            System.out.println("Item could not be sold!");         //print error statement
                        }
                        break;
                    case 5:
                        test = i.searchForItem(input); //searches for item
                        if(!test){
                            System.out.println("Item is not in inventory!");
                        }
                        break;
                    case 6:
                        i.saveFoodItemToFile(input); //saves items
                        break;
                    case 7:
                        i.addItem(input,false); //reads from file
                        input.nextLine(); //clears input buffer
                        break;
                    case 8:
                        System.out.println("Thank you for using Algonquin Marketplace!");       //print statement
                        break;
                    default:
                        System.out.println("Please enter numbers between 1-5. Try Again!");     //print error if it's outside the select menu
                }
            }catch (InputMismatchException e){      //catch input Mismatch statement
                System.err.println("<<<< Invalid entry >>>>");      //print error to the user
            }catch (NumberFormatException e) {        //catch incorrect number format
                System.err.println("<<<< Please enter a valid number >>>>");
            }catch (IllegalAccessException e){
                System.out.println(e.getMessage());
                input.nextLine();
            }
        }while(choice != 8);      //loop condition

    }

    /**
     * Method print the main menu
     */
    public static void displayMenu(){
        System.out.print("Please select one of the following:\n" +
                "1: Add Item to Inventory\n" +
                "2: Display Current Inventory\n" +
                "3: Buy Item(s)\n" +
                "4: Sell Item(s)\n" +
                "5: Search for Item\n" +
                "6: Save Inventory to File\n" +
                "7: Read Inventory from File\n" +
                "8: Exit\n" +
                "Choose an option: ");

    }
}
