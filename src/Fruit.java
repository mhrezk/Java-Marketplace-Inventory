import java.util.Formatter;
import java.util.Scanner;

public class Fruit extends FoodItem{

    private String orchardName;     ////declare the orchard name item

    /**
     * Method of to string to print fruits
     * @return string of the items in one line
     */
    @Override
    public String toString(){
        String str = super.toString();
        return str + " orchard supplier: "+orchardName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addItem(Scanner scan,boolean fromFile){
        super.addItem(scan,fromFile);
        if (fromFile) {
            System.out.print("Enter the name of the orchard supplier: ");
        }
        orchardName=TestInputs.testEmptyString(scan);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeItemReadFromFile(Formatter output){
        super.writeItemReadFromFile(output);
        output.format("%s\n",orchardName);
    }
}
