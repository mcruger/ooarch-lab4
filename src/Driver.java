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
/*        ArrayList<Integer> testVals1 = new ArrayList<Integer>();
        testVals1.add(1);
        testVals1.add(2);
        testVals1.add(3);
        testVals1.add(4);
        testVals1.add(1); //part size for recipes
        ui.setControlValues(testVals1);

        ArrayList<Integer> returnedVales = ui.getControlValues();

        for (int val : returnedVales){
            System.out.println(val);
        }*/




        System.out.println("Running manual test...");
        System.out.println("Setting vals");
        ControlValues values = new ControlValues(1,2,10,10);
        ControlValues returnedValues = ui.getControlValues();
        System.out.println("Getting vals:");
        System.out.println(returnedValues.getmAirPressure());
        System.out.println("Getting vals:");
        System.out.println(returnedValues.getmElecCurrent());
        ui.setControlValues(values);
        String output3 = ui.manualRun();



        System.out.println("Running Ramp test receipt...");
        String output = ui.executeRecipe("LAB4_Ramp.csv");
        System.out.println(output + System.lineSeparator());

        System.out.println("Running Ramp test receipt with < 50 size...");
        String output4 = ui.executeRecipe("LAB4_Ramp_bad.csv");
        System.out.println(output4 + System.lineSeparator());

        System.out.println("Running ConstantCurrent test receipt...");
        String output1 = ui.executeRecipe("LAB4_ConstantCurrent.csv");
        System.out.println(output1 + System.lineSeparator());

        System.out.println("Running ConstantPressure test receipt...");
        String output2 = ui.executeRecipe("LAB4_recipe1.csv");
        System.out.println(output2 + System.lineSeparator());


    }

}
