
import java.util.InputMismatchException;
import java.util.Scanner;


public class TestInputs {
    /**
     * Method to detect if user empty is empty or not
     * @param scan Scanner object
     * @return return string that is not empty
     */
    public static String testEmptyString(Scanner scan) {
        String str = scan.nextLine();
        if (str.isEmpty() || str.isBlank()) {
            System.err.println("Entry cannot be empty!");
            //str = scan.next();
            //scan.nextLine();//clear stream input
        }
        return str;
    }

    /**
     * Method to validate proper integer input
     * @param scan Scanner object
     * @param message printout for user
     * @return integer
     */
    public static int inputInteger(Scanner scan,String message) {
        boolean test = true;     //declare boolean
        do{     //do while loop
            try {       //try statement
                System.out.print(message);      //print the message sent to the method
                int result = scan.nextInt();        //input integer
                if (result < 0) {       //test if result is negative
                    throw new IllegalAccessException("Number must be positive!");        //throw exception with error message
                }
                scan.nextLine();        // clean up the input stream
                return result;      //return the result
            } catch (IllegalAccessException e){      //Exception handling statement
                System.err.println(e.getMessage());     //print error message
                scan.nextLine(); // clean up the input stream
            } catch (InputMismatchException e){      //Exception handling statement
                System.err.println("Number entered is not an integer! Please retry!");      //print error message
                scan.nextLine(); // clean up the input stream
            }
        } while (test);     //loop condition

        return -1;      //return -1
    }
    /**
     * Method to validate proper float-point input
     * @param scan Scanner parameter
     * @param message printout for user
     * @return float
     */
    public static float testFloat(Scanner scan,String message) {
        do {        //do while loop
            try {       //try statement
                System.out.print(message);      //print the message sent to the method
                float result = scan.nextFloat();         //input integer
                if (result < 0) {       //test if result is negative
                    throw new IllegalAccessException("Number Must be Positive");        //throw exception with error message
                }
                scan.nextLine(); // clean up the input stream
                return result;      //return the result
            } catch (IllegalAccessException e) {        //Exception handling statement
                System.out.println(e.getMessage());     //print error message
                scan.nextLine(); // clean up the input stream
            } catch (InputMismatchException e){          //Exception handling statement
                System.err.println("Number entered is not float please enter again");       //print error message
                scan.nextLine(); // clean up the input stream
            }
        }while (true);  //loop condition

    }

}
