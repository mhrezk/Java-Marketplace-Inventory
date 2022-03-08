import java.util.Formatter;
import java.util.Scanner;


public class Vegetable extends FoodItem{

    private String farmName;         //declare the item farm name

    /**
     * Method of to string to print vegetables
     * @return string of the items in one line
     */
    @Override
    public String toString(){
        String str = super.toString();
        return str + " size: "+ farmName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addItem(Scanner scan,boolean fromFile){
        super.addItem(scan,fromFile);
        if (fromFile) {
            System.out.print("Enter the name of the farm supplier: ");
        }
        farmName=TestInputs.testEmptyString(scan);

        return true;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public void writeItemReadFromFile(Formatter output){
        super.writeItemReadFromFile(output);
        output.format("%s\n",farmName);
    }
}
