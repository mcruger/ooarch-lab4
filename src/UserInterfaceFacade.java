import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: LEWIS
 * Date: 7/25/14
 * Time: 6:57 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserInterfaceFacade {

    //public void setControlValues(ArrayList<Integer> controlValues);
    public void setControlValues(ControlValues controlValues);
    //public ArrayList<Integer> getControlValues();
    public ControlValues getControlValues();
    public String manualRun();
    //public String executeRecipe(Enum mode);
    public String executeRecipe(String recipeName);

}
