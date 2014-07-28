/**
 * Created with IntelliJ IDEA.
 * User: LEWIS
 * Date: 7/27/14
 * Time: 11:53 PM
    POJO to hold control values for a run of the hardware
 */
public class ControlValues {

    private int mAirPressure;
    private int mElecCurrent;
    private int mRunTime;
    private int mPartSize;

    public ControlValues(int mAirPressure, int mElecCurrent, int mRunTime, int mPartSize) {
        this.mAirPressure = mAirPressure;
        this.mElecCurrent = mElecCurrent;
        this.mRunTime = mRunTime;
        this.mPartSize = mPartSize;
    }

    public ControlValues(int mAirPressure, int mElecCurrent, int mRunTime) {
        this.mAirPressure = mAirPressure;
        this.mElecCurrent = mElecCurrent;
        this.mRunTime = mRunTime;
        this.mPartSize = 0;
    }

    public int getmAirPressure() {
        return mAirPressure;
    }

    public void setmAirPressure(int mAirPressure) {
        this.mAirPressure = mAirPressure;
    }

    public int getmRunTime() {
        return mRunTime;
    }

    public void setmRunTime(int mRunTime) {
        this.mRunTime = mRunTime;
    }

    public int getmElecCurrent() {
        return mElecCurrent;
    }

    public void setmElecCurrent(int mElecCurrent) {
        this.mElecCurrent = mElecCurrent;
    }

    public int getmPartSize() {
        return mPartSize;
    }

    public void setmPartSize(int mPartSize) {
        this.mPartSize = mPartSize;
    }
}
