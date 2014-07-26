import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: LEWIS
 * Date: 7/25/14
 * Time: 6:57 PM
 * To change this template use File | Settings | File Templates.
 */
public interface HardwareFacade {

    public void setControlValues(ArrayList<Double> controlValues);
    public ArrayList<Double> getControlValues();
    public void turnHardwareOn();
    public void turnHardwareOff();


}
