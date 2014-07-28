import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: LEWIS
 * Date: 7/25/14
 * Time: 8:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConstantCurrentStrategy implements RecipeStrategy {

    private int mPSI;
    private int mAmps;
    private int mPartSize;
    private Hardware mHardware;
    private String mRecipeLine;
    private String mRecipeFileName;
    public static final int PSI_MIN = 10;
    public static final int TIME = 20; //runs for 20 seconds
    public static final String STRATEGY = "ConstantCurrent";

    public ConstantCurrentStrategy(int partSize, Hardware hardware, String recipeLine, String recipeFileName) {
        this.mPartSize = partSize;
        this.mPSI = 50; //starts at 50 assuming we're always starting at 0 time
        this.mAmps = partSize + 50;
        this.mHardware = hardware;
        this.mRecipeLine = recipeLine;
        this.mRecipeFileName = recipeFileName;
    }

    @Override
    public void runRecipe() {
        int timeElapsed = 0;

        try {
            PrintWriter writer = new PrintWriter(mRecipeFileName + ".DAS.csv", "UTF-8");
            writer.println(mRecipeLine);

            //turn hardware on and run it for time specified in control value
            int elapsedTime = 0;

            //set initial hardware settings
            mHardware.setControlValues(new ControlValues(mPSI, mAmps, TIME));

            //adjust control values per second
            while(timeElapsed < TIME){
                if (mPSI > PSI_MIN){
                    mPSI = 50 - (timeElapsed * 2);
                }
                mHardware.setControlValues(new ControlValues(mPSI, mAmps, TIME));
                ControlValues values = mHardware.getControlValues();
                writer.println(timeElapsed + "," + values.getmAirPressure() + "," + values.getmElecCurrent());
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
