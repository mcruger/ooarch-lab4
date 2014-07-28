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

        //create an instance of UI
        UserInterface ui = new UserInterface();

        //setup manual run tests
        System.out.println("Running manual test...");
        System.out.println("Setting vals");
        ControlValues values = new ControlValues(1,2,10,10);
        ui.setControlValues(values);
        ControlValues returnedValues = ui.getControlValues();
        System.out.println("Vals from Hardware:");
        System.out.println(returnedValues.getmAirPressure());
        System.out.println(returnedValues.getmElecCurrent());
        System.out.println("Manual run output:");
        String output3 = ui.manualRun();
        System.out.println(output3 + System.lineSeparator());

        //set up recipe run tests
        System.out.println("Running Ramp test recipe...");
        String output = ui.executeRecipe("LAB4_Ramp.csv");
        System.out.println(output + System.lineSeparator());

        System.out.println("Running Ramp test recipe with < 50 part size...");
        String output4 = ui.executeRecipe("LAB4_Ramp_bad.csv");
        System.out.println(output4 + System.lineSeparator());

        System.out.println("Running ConstantCurrent test recipe...");
        String output1 = ui.executeRecipe("LAB4_ConstantCurrent.csv");
        System.out.println(output1 + System.lineSeparator());

        System.out.println("Running ConstantPressure test recipe...");
        String output2 = ui.executeRecipe("LAB4_recipe1.csv");
        System.out.println(output2 + System.lineSeparator());

        System.out.println("Running ConstantCurrent test recipe w/ bad reference file...");
        String output5 = ui.executeRecipe("LAB4_ConstantCurrent_bad.csv");
        System.out.println(output5 + System.lineSeparator());


    }

}
