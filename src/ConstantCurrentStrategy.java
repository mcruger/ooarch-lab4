/**
 * Created with IntelliJ IDEA.
 * User: LEWIS
 * Date: 7/25/14
 * Time: 8:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConstantCurrentStrategy implements RecipeStrategy {

    private int mTime;
    private int mPSI;
    private int mAmps;
    public static final int PSI_MIN = 0;
    public static final int PSI_MAX = 10;
    public static final int TIME_MIN = 0;
    public static final int TIME_MAX = 20;

    public ConstantCurrentStrategy() {

    }

    @Override
    public void runRecipe() {

    }
}
