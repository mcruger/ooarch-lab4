import java.util.ArrayList;
import java.util.Timer;

/**
 * Created with IntelliJ IDEA.
 * User: LEWIS
 * Date: 7/25/14
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Hardware implements HardwareFacade {

    private int mAirPressure;
    private int mAirRunTime;
    private int mElectricalCurrent;
    private int mElecRunTime;
    private boolean mIsHardwareRunning;
    private int mPartSize;
    private static final int AIR_PRESSURE_MIN = 0;
    private static final int AIR_PRESSURE_MAX = 200;
    private static final int ELECTRICAL_CURRENT_MIN = 0;
    private static final int ELECTRICAL_CURRENT_MAX = 200;
    private static final int TIME_MIN = 0;
    private static final int TIME_MAX = 100;

    public Hardware() {
        //init everything to 0 so it's like we just turned the 'hardware' on
        this.mAirPressure = 0;
        this.mAirRunTime = 0;
        this.mElectricalCurrent = 0;
        this.mElecRunTime = 0;
        this.mPartSize = 0;
        this.mIsHardwareRunning = false;
    }

    @Override
    public void setControlValues(ArrayList<Integer> controlValues) {

        //assume controlValues contains control vals in same order every time
        //order: [air_pressure, air_run_time, elec_current, elec_run_time]
        int airPressure = controlValues.get(0);
        int airTime = controlValues.get(1);
        int elecCur = controlValues.get(2);
        int elecTime = controlValues.get(3);
        int partSize = controlValues.get(4);


        //check bounds of control values
        if (airPressure < AIR_PRESSURE_MIN){
            airPressure = AIR_PRESSURE_MIN;
        }
        if (airPressure > AIR_PRESSURE_MAX){
            airPressure = AIR_PRESSURE_MAX;
        }
        if(elecCur < ELECTRICAL_CURRENT_MIN){
            elecCur = ELECTRICAL_CURRENT_MIN;
        }
        if (elecCur > ELECTRICAL_CURRENT_MAX){
            elecCur = ELECTRICAL_CURRENT_MAX;
        }
        if (airTime < TIME_MIN){
            airTime = TIME_MIN;
        }
        if (airTime > TIME_MAX){
            airTime = TIME_MAX;
        }
        if (elecTime < TIME_MIN){
            elecTime = TIME_MIN;
        }
        if (elecTime > TIME_MAX){
            elecTime = TIME_MAX;
        }

        mAirPressure = airPressure;
        mAirRunTime = airTime;
        mElectricalCurrent = elecCur;
        mElecRunTime = elecTime;
    }

    @Override
    public ArrayList<Integer> getControlValues() {
        //same order as values were sent in as
        ArrayList<Integer> controlValues = new ArrayList<Integer>();
        controlValues.add(mAirPressure);
        controlValues.add(mAirRunTime);
        controlValues.add(mElectricalCurrent);
        controlValues.add(mElecRunTime);
        controlValues.add(mPartSize);

        return controlValues;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void turnHardwareOn() {
        mIsHardwareRunning = true;
    }

    @Override
    public void turnHardwareOff() {
        mIsHardwareRunning = false;
    }
}
