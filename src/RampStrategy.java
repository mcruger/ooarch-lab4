import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: LEWIS
 * Date: 7/25/14
 * Time: 8:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class RampStrategy implements RecipeStrategy {

    //member vars
    private int mPSI;
    private int mAmps;
    private int mPartSize;
    private Hardware mHardware;
    private String mRecipeLine;
    private String mRecipeFileName;

    private static final int TIME = 30; //30 seconds
    private static final int PSI_MAX = 100;
    private static final int PSI_INCREMENT = 10;
    private static final int AMP_INCREMENT = 20;
    private static final int PART_SIZE_MIN = 50;
    public static final String STRATEGY = "Ramp";


    public RampStrategy(int partSize, Hardware hardware, String recipeLine, String recipeFileName){
        this.mPartSize = partSize;
        this.mPSI = 0; //starts at 50 assuming we're always starting at 0 time
        this.mAmps = partSize; //starts at 0 assuming time always starts at 0
        this.mHardware = hardware;
        this.mRecipeLine = recipeLine;
        this.mRecipeFileName = recipeFileName;
    }

    @Override
    public void runRecipe() {
        ArrayList<Integer> controlValues = new ArrayList<Integer>();

        int timeElapsed = 0;

        try {
            PrintWriter writer = new PrintWriter(mRecipeFileName + ".DAS.csv", "UTF-8");
            writer.println(mRecipeLine);

            //turn hardware on and run it for time specified in control value
            int elapsedTime = 0;

            //set initial hardware settings
            mHardware.setControlValues(new ControlValues(mPSI, mAmps, TIME));

            //adjust control values per second
            while(timeElapsed <= TIME){

                ControlValues values = mHardware.getControlValues();
                writer.println(timeElapsed + "," + values.getmAirPressure() + "," + values.getmElecCurrent());

                if (mPSI < PSI_MAX){
                    mPSI += PSI_INCREMENT;
                }
                mAmps += AMP_INCREMENT;
                mHardware.setControlValues(new ControlValues(mPSI, mAmps, TIME));

                timeElapsed++;
            }

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



    }
}
