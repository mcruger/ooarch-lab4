import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: LEWIS
 * Date: 7/25/14
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserInterface implements UserInterfaceFacade {

    //allow user to manually set control values in hardware system
    //user to read control values
    //start system

    //SetControlValue, GetControlValue, ManualRun, and ExecuteRecipe.

    MachineControl mMachineControlLayer;

    public UserInterface() {
        this.mMachineControlLayer = new MachineControl();
    }

    @Override
    public void setControlValues(ControlValues controlValues) {
        //pass control values on to control layer
        mMachineControlLayer.setControlValues(controlValues);
    }

    @Override
    public ControlValues getControlValues() {
        return mMachineControlLayer.getControlValues();
    }

    @Override
    public String manualRun() {
        mMachineControlLayer.setmMode(MachineMode.MANUAL);
        String output = mMachineControlLayer.turnHardwareOn();
        return output;
    }

    @Override
    public String executeRecipe(String recipeName) {
        return mMachineControlLayer.executeRecipe(recipeName);
        /*mMachineControlLayer.setmMode(mode);
        String output = mMachineControlLayer.turnHardwareOn();
       return output;*/
    }
}
