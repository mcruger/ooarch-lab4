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

    public MachineControl() {
        this.mHardwareLayer = new Hardware();
    }

    @Override
    public void setControlValues(ArrayList<Double> controlValues) {
        //send control values on to hardware layer
        mHardwareLayer.setControlValues(controlValues);
    }

    @Override
    public ArrayList getControlValues() {
        return mHardwareLayer.getControlValues();
    }

    @Override
    public void turnHardwareOn() {

    }

    @Override
    public void turnHardwareOff() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
