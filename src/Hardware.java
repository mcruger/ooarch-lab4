import java.util.ArrayList;
import java.util.HashMap;
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
    private int mElectricalCurrent;
    private int mRunTime;
    private boolean mIsHardwareRunning;
    private static final int AIR_PRESSURE_MIN = 0;
    private static final int AIR_PRESSURE_MAX = 200;
    private static final int ELECTRICAL_CURRENT_MIN = 0;
    private static final int ELECTRICAL_CURRENT_MAX = 200;
    private static final int TIME_MAX = 100;

    public Hardware() {
        //init everything to 0 so it's like we just turned the 'hardware' on
        this.mAirPressure = 0;
        this.mElectricalCurrent = 0;
        this.mRunTime = 0;
        this.mIsHardwareRunning = false;
    }

    @Override
    public void setControlValues(ControlValues controlValues) {

        //assume controlValues contains control vals in same order every time
        //order: [air_pressure, air_run_time, elec_current, elec_run_time]
        int airPressure = controlValues.getmAirPressure();
        int elecCur = controlValues.getmElecCurrent();
        int runTime = controlValues.getmRunTime();

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
        if (runTime > TIME_MAX){
            runTime = TIME_MAX;
        }

        mAirPressure = airPressure;
        mElectricalCurrent = elecCur;
        mRunTime = runTime;
    }

    @Override
    public ControlValues getControlValues() {
        //same order as values were sent in as

        //ArrayList<Integer> controlValues = new ArrayList<Integer>();
        ControlValues controlValues = new ControlValues(mAirPressure, mElectricalCurrent, mRunTime);
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
