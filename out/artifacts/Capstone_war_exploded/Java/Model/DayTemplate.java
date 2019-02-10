package Model;

import org.joda.time.LocalTime;

import java.util.ArrayList;

public class DayTemplate {

    public LocalTime openTime;
    public LocalTime closeTime;
    public String dayOfWeek;
    public ArrayList<ShiftTemplate> shiftList;

    public DayTemplate(LocalTime openTime, LocalTime closeTime, String dayOfWeek, ArrayList<ShiftTemplate> shiftList) {
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.dayOfWeek = dayOfWeek;
        this.shiftList = shiftList;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    //TODO ShiftTemplate Management
}
