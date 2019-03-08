package temp;

import java.io.Serializable;

public class CalendarDAO implements Serializable {

    private int shiftId;
    private String title;
    private String start;
    private String end;
    private String color;

    public CalendarDAO(int shiftId, String title, String start, String end, String color) {

        this.shiftId = shiftId;
        this.title = title;
        this.start = start;
        this.end = end;
        this.color = color;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
