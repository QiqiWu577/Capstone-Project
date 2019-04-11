package Model;

import java.io.Serializable;

/**
 * CalendarDAO.java - Class describing all attributes and operations for a CalendarDAO object
 * @author Qiqi Wu
 *
 */
public class CalendarDAO implements Serializable {

    private int id;
    private String title;
    private String start;
    private String end;
    private String color;
    private int shiftId;
    private int dayId;
    private int empId;

    /**
     * Constructs a new CalendarDAO object with specified values for its attributes
     * @param id        index of the ArrayList
     * @param title     the employee's name
     * @param start     the start time of the shift
     * @param end       the end time of the shift
     * @param color     the color of the shift
     * @param shiftId   the shift id from the shift table in the database
     * @param dayId     the day id from the day table in the database
     * @param empId     the employee id from the employees table in the database
     */
    public CalendarDAO(int id, String title, String start, String end, String color,int shiftId,int dayId,int empId) {

        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.color = color;
        this.shiftId = shiftId;
        this.dayId = dayId;
        this.empId = empId;
    }

    /**
     * Method to return the index of the ArrayList
     * @return id the index of the ArrayList
     */
    public int getId() {
        return id;
    }

    /**
     * Method to set the index of the ArrayList to the specified value
     * @param id the index of the ArrayList
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method to return the employee's name
     * @return the employee's name
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method to set the employee's name
     * @param title the employee's name
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Method to return the start time of the shift
     * @return the start time of the shift
     */
    public String getStart() {
        return start;
    }

    /**
     * Method to set the start time of the shift
     * @param start the start time of the shift
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * Method to return end time of the shift
     * @return end time of the shift
     */
    public String getEnd() {
        return end;
    }

    /**
     * Method to set the end time of the shift
     * @param end end time of the shift
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     * Method to return the color of the shift
     * @return the color of the shift
     */
    public String getColor() {
        return color;
    }

    /**
     * Method to set the color of the shift
     * @param color the color of the shift
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Method to return the shift id from the database
     * @return the shift id from the database
     */
    public int getShiftId() {
        return shiftId;
    }

    /**
     * Method to set the shift id from the database
     * @param shiftId the shift id from the database
     */
    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    /**
     * Method to return the day id from the database
     * @return the day id from the database
     */
    public int getDayId() {
        return dayId;
    }

    /**
     * Method to set the day id from the database
     * @param dayId the day id from the database
     */
    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    /**
     * Method to return the employee id from the database
     * @return the employee id from the database
     */
    public int getEmpId() {
        return empId;
    }

    /**
     * Method to set the employee id from the database
     * @param empId the employee id from the database
     */
    public void setEmpId(int empId) {
        this.empId = empId;
    }
}
