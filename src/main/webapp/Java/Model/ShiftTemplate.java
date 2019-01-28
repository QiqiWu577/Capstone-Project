package Model;

import org.joda.time.LocalTime;

public class ShiftTemplate {

    private int shiftId;
    private LocalTime startTime;
    private LocalTime endTime;
    private int minimumEmployees;

    public ShiftTemplate(int shiftId, LocalTime startTime, LocalTime endTime, int minimumEmployees) {
        this.shiftId = shiftId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.minimumEmployees = minimumEmployees;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getMinimumEmployees() {
        return minimumEmployees;
    }

    public void setMinimumEmployees(int minimumEmployees) {
        this.minimumEmployees = minimumEmployees;
    }
}
