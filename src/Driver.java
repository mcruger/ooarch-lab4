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
        ArrayList<Double> testVals1 = new ArrayList<Double>();
        testVals1.add(1.0);
        testVals1.add(2.0);
        testVals1.add(3.0);
        testVals1.add(4.0);
        ui.setControlValues(testVals1);

        ArrayList<Double> returnedVales = ui.getControlValues();

        for (Double val : returnedVales){

            System.out.println(val);

        }

    }

}
