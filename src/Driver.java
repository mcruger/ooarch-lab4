import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: LEWIS
 * Date: 7/25/14
 * Time: 8:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Driver {



    public static void main(String[] args){

        //create an instance of UI and make it work
        UserInterface ui = new UserInterface();
        ArrayList<Integer> testVals1 = new ArrayList<Integer>();
        testVals1.add(1);
        testVals1.add(2);
        testVals1.add(3);
        testVals1.add(4);
        testVals1.add(10);
        ui.setControlValues(testVals1);

        ArrayList<Integer> returnedVales = ui.getControlValues();

        for (int val : returnedVales){

            System.out.println(val);

        }

    }

}
