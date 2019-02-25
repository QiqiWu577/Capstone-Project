package Model;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;


public class Day {

    public LocalDateTime openTime;
    public LocalDateTime closeTime;
    public LocalDate date;
    public ArrayList<Shift> shiftList;

    public Day() {
        this.shiftList = new ArrayList<Shift>();
    }

    public Day(LocalDateTime openTime, LocalDateTime closeTime, ArrayList<Shift> shiftList) {
        this.closeTime = closeTime;
        this.openTime = openTime;
        this.date = LocalDate.now();
        this.shiftList = shiftList;
    }

    public LocalDateTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalDateTime openTime) {
        this.openTime = openTime;
    }

    public LocalDateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalDateTime closeTime) {
        this.closeTime = closeTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ArrayList<Shift> getShiftList() {
        return shiftList;
    }

    public void setShiftList(ArrayList<Shift> shiftList) {
        this.shiftList = shiftList;
    }

    @Override
    public String toString() {
        int i = 1;
        String result = "*************************************************************\n";
        result += "Open Time: " + getOpenTime() + "Close Time: " + getCloseTime() + "\n";
        for(Shift s : shiftList) {
            result += "-------------------------------------------------------------\n";
            result += "Shift " + i + "\n";
            result += "StartTime: " + s.getStartTime() + " Close Time: " + s.getCloseTime() + "\n";
//            for(ScheduledEmployee emp: s.getEmpList()) {
//                if(emp.getEmp()!= null) {
//                    result += emp.getEmp().getFirstname() + "\n";
//                } else {
//                    result += "null? \n";
//                }
//            }
            i++;
            result += "-------------------------------------------------------------\n";
        }


        return result;
    }
}
