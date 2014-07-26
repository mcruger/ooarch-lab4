import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: LEWIS
 * Date: 7/25/14
 * Time: 6:57 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MachineControlFacade {

    public void setControlValues(ArrayList<Integer> controlValues);
    public ArrayList<Integer> getControlValues();
    public void turnHardwareOn();
    public void turnHardwareOff();


}
