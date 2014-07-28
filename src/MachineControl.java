import java.io.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: LEWIS
 * Date: 7/25/14
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class MachineControl implements MachineControlFacade{

    Hardware mHardwareLayer;
    String mDASFileName;
    Enum mMode;
    String mRecipeFile;
    String mRecipeName;
    int mPartSize;
    String mRecipeFirstLine;

    public MachineControl() {
        this.mHardwareLayer = new Hardware();
        this.mDASFileName = "";
        this.mMode = null;
        this.mRecipeFile = null;
        this.mRecipeName = null;
        this.mPartSize = 0;
        this.mRecipeFirstLine = null;
    }

    @Override
    public void setControlValues(ControlValues controlValues) {
        //send control values on to hardware layer
        mHardwareLayer.setControlValues(controlValues);
    }

    @Override
    public ControlValues getControlValues() {
        return mHardwareLayer.getControlValues();
    }

    @Override
    public String executeRecipe(String recipeName) {

        String line ="";
        //get recipe info
        try {
            BufferedReader br = new BufferedReader(new FileReader(recipeName));
            line = br.readLine();
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        mRecipeFirstLine = line;
        //Widget,ConstantPressure,50
        String[] lineSplit = line.split(",");

        mRecipeFile = recipeName;
        mRecipeName = lineSplit[0];
        mPartSize = Integer.parseInt(lineSplit[2]);

        switch (lineSplit[1]){
            case "ConstantCurrent": this.setmMode(MachineMode.CONSTANT_CURRENT);
                break;
            case "ConstantPressure": this.setmMode(MachineMode.CONSTANT_PRESSURE);
                break;
            case "Ramp": this.setmMode(MachineMode.RAMP);
                break;
            default: this.setmMode(MachineMode.MANUAL);
                break;
        }

        return turnHardwareOn();
    }

    @Override
    public String turnHardwareOn() {
        String output = "";
        mHardwareLayer.turnHardwareOn();
        boolean compareFiles = false;

        if (mMode == MachineMode.MANUAL){
            try {
                PrintWriter writer = new PrintWriter("Manual.DAS.csv", "UTF-8");
                writer.println("Manual");

                //turn hardware on and run it for time specified in control value
                int elapsedTime = 0;
                int runTime = mHardwareLayer.getControlValues().getmRunTime();
                System.out.println(runTime);
                while (elapsedTime < runTime){
                    ControlValues values = mHardwareLayer.getControlValues();
                    writer.println(elapsedTime + "," + values.getmAirPressure() + "," + values.getmElecCurrent());
                    elapsedTime++;
                }
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //turn off hardware
            mHardwareLayer.turnHardwareOff();
            return "Manual run successful! Check the Manual.DAS.csv file!";
        }else{
            if (mMode == MachineMode.CONSTANT_CURRENT){
                runRecipe(new ConstantCurrentStrategy(mPartSize, mHardwareLayer, mRecipeFirstLine, mRecipeFile));
                return "Constant current strategy completed. Check " + mRecipeFile + ".DAS.csv";
            }
            if (mMode == MachineMode.CONSTANT_PRESSURE){
                runRecipe(new ConstantPressureStrategy(mPartSize, mHardwareLayer, mRecipeFirstLine, mRecipeFile));
                compareFiles = compareFiles(mRecipeFile);

                if (compareFiles){
                    return "Good part created. Check " + mRecipeFile + ".DAS.csv";
                }else{
                    return "BAD PART! Check " + mRecipeFile + ".DAS.csv";
                }
            }
            if (mMode == MachineMode.RAMP){
                if (mPartSize < 50){
                    return "Part size too small for Ramp mode. Part size must be 50 or bigger";
                }else{
                    runRecipe(new RampStrategy(mPartSize, mHardwareLayer, mRecipeFirstLine, mRecipeFile));
                }
            }
            compareFiles = compareFiles(mRecipeFile);

            if (compareFiles){
                return "Good part created. Check " + mRecipeFile + ".DAS.csv";
            }else{
                return "BAD PART! Check " + mRecipeFile + ".DAS.csv";
            }
        }

    }

    public boolean compareFiles(String recipeFileName){

        //read and compare contents of both files
        String fileDAS = recipeFileName + ".DAS.csv";
        String fileREF = recipeFileName + ".reference.csv";

        ArrayList<String> arrayDAS = new ArrayList<>();
        ArrayList<String> arrayREF = new ArrayList<>();

        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileDAS));
            line = br.readLine();

            while (line != null) {
                line = line.trim();
                arrayDAS.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileREF));
            line = br.readLine();

            while (line != null) {
                line = line.trim();
                arrayREF.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        if (arrayDAS.equals(arrayREF)){
            return true;
        }else{
            return false;
        }

    }

    public void runRecipe(RecipeStrategy recipe){
        recipe.runRecipe();
    }

    @Override
    public void turnHardwareOff() {
        //turn off hardware
    }

    public Hardware getmHardwareLayer() {
        return mHardwareLayer;
    }

    public void setmHardwareLayer(Hardware mHardwareLayer) {
        this.mHardwareLayer = mHardwareLayer;
    }

    public String getmDASFileName() {
        return mDASFileName;
    }

    public void setmDASFileName(String mDASFileName) {
        this.mDASFileName = mDASFileName;
    }

    public Enum getmMode() {
        return mMode;
    }

    public void setmMode(Enum mMode) {
        this.mMode = mMode;
    }
}
