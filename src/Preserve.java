import java.util.Formatter;
import java.util.Scanner;

public class Preserve extends FoodItem{

    private int jarSize;        //declare the jar size item

    /**
     * Method of to string to print preserves
     * @return string of the items in one line
     */
    @Override
    public String toString(){
        String str=super.toString();
        return str + " size: " + jarSize +"mL";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addItem(Scanner scan,boolean fromFile){
        super.addItem(scan,fromFile);
        if (fromFile) {
            jarSize = TestInputs.inputInteger(scan,"Enter size of the jar in millilitres: ");
        }else{
            jarSize = TestInputs.inputInteger(scan,"");
        }

        return true;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void writeItemReadFromFile(Formatter output){
        super.writeItemReadFromFile(output);
        output.format("%d\n",jarSize);
    }

}
