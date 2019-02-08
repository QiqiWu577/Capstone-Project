package Model;

import org.joda.time.LocalTime;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class DayTemplate {

    private LocalTime openTime;
    private LocalTime closeTime;
    private String dayOfWeek;
    private ArrayList<ShiftTemplate> shiftList;


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

    public ArrayList<ShiftTemplate> getShiftList() {

        return shiftList;
    }

    public void setShiftList(ArrayList<ShiftTemplate> shiftList) {
        this.shiftList = shiftList;
    }

    //TODO ShiftTemplate Management
}
