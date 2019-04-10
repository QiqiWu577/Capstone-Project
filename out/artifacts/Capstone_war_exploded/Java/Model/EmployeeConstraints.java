/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author Anthony Doucet
 */
@Entity
@Table(name = "employee_constraints")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeConstraints.findAll", query = "SELECT e FROM EmployeeConstraints e")
    , @NamedQuery(name = "EmployeeConstraints.findByEmpId", query = "SELECT e FROM EmployeeConstraints e WHERE e.empId = :empId")
    , @NamedQuery(name = "EmployeeConstraints.findByConstraints", query = "SELECT e FROM EmployeeConstraints e WHERE e.constraints = :constraints")})
public class EmployeeConstraints implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "emp_id")
    private Integer empId;
    @Basic(optional = false)
    @Column(name = "constraints")
    private String constraints;
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Employee employee;

    @Transient
    private boolean[] availableTimeMonday;
    @Transient
    private boolean[] availableTimeTuesday;
    @Transient
    private boolean[] availableTimeWednesday;
    @Transient
    private boolean[] availableTimeThursday;
    @Transient
    private boolean[] availableTimeFriday;
    @Transient
    private boolean[] availableTimeSaturday;
    @Transient
    private boolean[] availableTimeSunday;
    @Transient
    private boolean[] preferredTimeMonday;
    @Transient
    private boolean[] preferredTimeTuesday;
    @Transient
    private boolean[] preferredTimeWednesday;
    @Transient
    private boolean[] preferredTimeThursday;
    @Transient
    private boolean[] preferredTimeFriday;
    @Transient
    private boolean[] preferredTimeSaturday;
    @Transient
    private boolean[] preferredTimeSunday;

    @Transient
    private boolean isAvailMonday;
    @Transient
    private boolean isAvailTuesday;
    @Transient
    private boolean isAvailWednesday;
    @Transient
    private boolean isAvailThursday;
    @Transient
    private boolean isAvailFriday;
    @Transient
    private boolean isAvailSaturday;

    @Transient
    private boolean isAvailSunday;
    @Transient
    private boolean isPrefMonday=false;
    @Transient
    private boolean isPrefTuesday;
    @Transient
    private boolean isPrefWednesday;
    @Transient
    private boolean isPrefThursday;
    @Transient
    private boolean isPrefFriday;
    @Transient
    private boolean isPrefSaturday;
    @Transient
    private boolean isPrefSunday;

    /**
     * Default constructor for the employee constraints initializes arrays
     */
    public EmployeeConstraints() {
        availableTimeMonday = new boolean[24];
        availableTimeTuesday = new boolean[24];
        availableTimeWednesday = new boolean[24];
        availableTimeThursday = new boolean[24];
        availableTimeFriday = new boolean[24];
        availableTimeSaturday = new boolean[24];
        availableTimeSunday = new boolean[24];

        preferredTimeMonday = new boolean[24];
        preferredTimeTuesday = new boolean[24];
        preferredTimeWednesday = new boolean[24];
        preferredTimeThursday = new boolean[24];
        preferredTimeFriday = new boolean[24];
        preferredTimeSaturday = new boolean[24];
        preferredTimeSunday = new boolean[24];
    }

    /**
     * Non default constructor
     * @param e Employee object
     * @param constraints A string of 0's and 1's used to populate the arrays
     * @param empId id of the employee
     * @throws InvalidConstraintException if the constraint contains a non 0 or 1
     * @throws ConstraintWrongSizeException if the constraint does not match the required size
     */
    public EmployeeConstraints(Employee e, String constraints, Integer empId) throws InvalidConstraintException, ConstraintWrongSizeException{
        this.employee = e;
        this.empId = empId;
        this.constraints = constraints;
        availableTimeMonday = new boolean[24];
        availableTimeTuesday = new boolean[24];
        availableTimeWednesday = new boolean[24];
        availableTimeThursday = new boolean[24];
        availableTimeFriday = new boolean[24];
        availableTimeSaturday = new boolean[24];
        availableTimeSunday = new boolean[24];

        preferredTimeMonday = new boolean[24];
        preferredTimeTuesday = new boolean[24];
        preferredTimeWednesday = new boolean[24];
        preferredTimeThursday = new boolean[24];
        preferredTimeFriday = new boolean[24];
        preferredTimeSaturday = new boolean[24];
        preferredTimeSunday = new boolean[24];

        parseConstraints();

    }

    /**
     * Non Default Constructor
     * @param empId id of the employee
     * @param constraints constraints string of 0's and 1's
     * @param e employee object
     */
    public EmployeeConstraints(int empId,String constraints,Employee e){
        this.empId = empId;
        this.constraints = constraints;
        this.employee = e;
    }

    /**
     * parses through the constraints string and uses it to populate the boolean arrays
     * @throws InvalidConstraintException if the constrains are not 0 or 1
     * @throws ConstraintWrongSizeException if the constraints are not the correct size
     */
    public void parseConstraints() throws InvalidConstraintException, ConstraintWrongSizeException {
        String [] list = constraints.split(",");
        final int HOURSPERDAY = 24;
        int hourOfDay;
        if(list[1].length() == 24) {
            for (hourOfDay = 0; hourOfDay < HOURSPERDAY; hourOfDay++) {
                if (list[0].charAt(hourOfDay) == '1') {
                    setAvailMonday(true);
                    availableTimeMonday[hourOfDay] = true;
                    if (list[1].charAt(hourOfDay) == '1') {
                        setPrefMonday(true);
                        preferredTimeMonday[hourOfDay] = true;
                    } else if (list[1].charAt(hourOfDay) == '0') {
                        preferredTimeMonday[hourOfDay] = false;
                    } else {
                        throw new InvalidConstraintException("Monday");
                    }
                } else if (list[0].charAt(hourOfDay) == '0') {
                    availableTimeMonday[hourOfDay] = false;
                    preferredTimeMonday[hourOfDay] = false;
                } else {
                    throw new InvalidConstraintException("Monday");
                }
            }
        } else {
            throw new ConstraintWrongSizeException("Monday");
        }

        if(list[1].length() == 24) {
            for (hourOfDay = 0; hourOfDay < HOURSPERDAY; hourOfDay++) {

                if (list[2].charAt(hourOfDay) == '1') {
                    setAvailTuesday(true);
                    availableTimeTuesday[hourOfDay] = true;
                    if (list[3].charAt(hourOfDay) == '1') {
                        setPrefTuesday(true);
                        preferredTimeTuesday[hourOfDay] = true;
                    } else if (list[3].charAt(hourOfDay) == '0') {
                        preferredTimeTuesday[hourOfDay] = false;
                    } else {
                        throw new InvalidConstraintException("Tuesday");
                    }
                } else if (list[2].charAt(hourOfDay) == '0') {
                    availableTimeTuesday[hourOfDay] = false;
                    preferredTimeTuesday[hourOfDay] = false;
                } else {
                    throw new InvalidConstraintException("Tuesday");
                }
            }
        }else {
            throw new ConstraintWrongSizeException("Tuesday");
        }
        if(list[1].length() == 24) {
            for(hourOfDay = 0; hourOfDay< HOURSPERDAY; hourOfDay++) {
                if (list[4].charAt(hourOfDay) == '1') {
                    setAvailWednesday(true);
                    availableTimeWednesday[hourOfDay] = true;
                    if (list[5].charAt(hourOfDay) == '1') {
                        setPrefWednesday(true);
                        preferredTimeWednesday[hourOfDay] = true;
                    } else if (list[5].charAt(hourOfDay) == '0') {
                        preferredTimeWednesday[hourOfDay] = false;
                    } else {
                        throw new InvalidConstraintException("Wednesday");
                    }
                } else if (list[4].charAt(hourOfDay) == '0') {
                    availableTimeWednesday[hourOfDay] = false;
                    preferredTimeWednesday[hourOfDay] = false;
                } else {
                    throw new InvalidConstraintException("Wednesday");

                }
            }
        } else {
            throw new ConstraintWrongSizeException("Wednesday");
        }
        if(list[1].length() == 24) {
            for(hourOfDay = 0; hourOfDay< HOURSPERDAY; hourOfDay++) {
                if (list[6].charAt(hourOfDay) == '1') {
                    setAvailThursday(true);
                    availableTimeThursday[hourOfDay] = true;
                    if (list[7].charAt(hourOfDay) == '1') {
                        setPrefThursday(true);
                        preferredTimeThursday[hourOfDay] = true;
                    } else if (list[7].charAt(hourOfDay) == '0') {
                        preferredTimeThursday[hourOfDay] = false;
                    } else {
                        throw new InvalidConstraintException("Thursday");
                    }
                } else if (list[6].charAt(hourOfDay) == '0') {
                    availableTimeThursday[hourOfDay] = false;
                    preferredTimeThursday[hourOfDay] = false;
                } else {
                    throw new InvalidConstraintException("Thursday");

                }
            }
        } else {
            throw new ConstraintWrongSizeException("Thursday");
        }
        if(list[1].length() == 24) {
            for(hourOfDay = 0; hourOfDay< HOURSPERDAY; hourOfDay++) {
                if (list[8].charAt(hourOfDay) == '1') {
                    setAvailFriday(true);
                    availableTimeFriday[hourOfDay] = true;
                    if (list[9].charAt(hourOfDay) == '1') {
                        setPrefFriday(true);
                        preferredTimeFriday[hourOfDay] = true;
                    } else if (list[9].charAt(hourOfDay) == '0') {
                        preferredTimeFriday[hourOfDay] = false;
                    } else {
                        throw new InvalidConstraintException("Friday");
                    }
                } else if (list[8].charAt(hourOfDay) == '0') {
                    availableTimeFriday[hourOfDay] = false;
                    preferredTimeFriday[hourOfDay] = false;
                } else {
                    throw new InvalidConstraintException("Friday");

                }

            }
        } else {
            throw new ConstraintWrongSizeException("Friday");
        }
        if(list[1].length() == 24) {
            for(hourOfDay = 0; hourOfDay< HOURSPERDAY; hourOfDay++) {
                if (list[10].charAt(hourOfDay) == '1') {
                    setAvailSaturday(true);
                    availableTimeSaturday[hourOfDay] = true;
                    if (list[11].charAt(hourOfDay) == '1') {
                        setPrefSaturday(true);
                        preferredTimeSaturday[hourOfDay] = true;
                    } else if (list[11].charAt(hourOfDay) == '0') {
                        preferredTimeSaturday[hourOfDay] = false;
                    } else {
                        throw new InvalidConstraintException("Saturday");
                    }
                } else if (list[10].charAt(hourOfDay) == '0') {
                    availableTimeSaturday[hourOfDay] = false;
                    preferredTimeSaturday[hourOfDay] = false;
                } else {
                    throw new InvalidConstraintException("Saturday");

                }

            }
        } else {
            throw new ConstraintWrongSizeException("Saturday");
        }
        if(list[1].length() == 24) {
            for(hourOfDay = 0; hourOfDay< HOURSPERDAY; hourOfDay++) {
                if (list[12].charAt(hourOfDay) == '1') {
                    setAvailSunday(true);
                    availableTimeSunday[hourOfDay] = true;
                    if (list[13].charAt(hourOfDay) == '1') {
                        setPrefSunday(true);
                        preferredTimeSunday[hourOfDay] = true;
                    } else if (list[13].charAt(hourOfDay) == '0') {
                        preferredTimeSunday[hourOfDay] = false;
                    } else {
                        throw new InvalidConstraintException("Sunday");
                    }
                } else if (list[12].charAt(hourOfDay) == '0') {
                    availableTimeSunday[hourOfDay] = false;
                    preferredTimeSunday[hourOfDay] = false;
                } else {
                    throw new InvalidConstraintException("Sunday");

                }

            }
        } else {
            throw new ConstraintWrongSizeException("Sunday");
        }


    }


    /**
     * gets the employee id
     * @return the employee id
     */
    public Integer getEmpId() {
        return empId;
    }

    /**
     * sets the employee id
     * @param empId the employee id
     */
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    /**
     * gets the constraints string for the object
     * @return the constraints string
     */
    public String getConstraints() {
        return constraints;
    }

    /**
     * sets the constraints string for the object
     * @param constraints constraints string
     */
    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }

    /**
     * gets the employee object relating to this object
     * @return the employee owner of these constraints
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * sets the employee owner of this object
     * @param employee employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * gets the hash code of the object
     * @return the hash code of the object
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empId != null ? empId.hashCode() : 0);
        return hash;
    }

    /**
     * compares if 2 employees are the the same
     * @param object employee to compare
     * @return true if they're the same
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeConstraints)) {
            return false;
        }
        EmployeeConstraints other = (EmployeeConstraints) object;
        if ((this.empId == null && other.empId != null) || (this.empId != null && !this.empId.equals(other.empId))) {
            return false;
        }
        return true;
    }

    /**
     * A string representation of the object
     * @return A string representation of the object
     */
    @Override
    public String toString() {
        return "data.EmployeeConstraints[ empId=" + empId + " ]";
    }


    /**
     * gets the boolean array for the days availability
     * @return the boolean array for the days availability
     */
    public boolean[] getAvailableTimeMonday() {
        return availableTimeMonday;
    }

    /**
     * sets the boolean array for the current days availability
     * @param availableTimeMonday the boolean array for the current days availability
     */
    public void setAvailableTimeMonday(boolean[] availableTimeMonday) {
        this.availableTimeMonday = availableTimeMonday;
    }
    /**
     * gets the boolean array for the days availability
     * @return the boolean array for the days availability
     */
    public boolean[] getAvailableTimeTuesday() {
        return availableTimeTuesday;
    }

    /**
     * Sets the boolean array for the given days availability
     * @param availableTimeTuesday the boolean array for the given days availability
     */
    public void setAvailableTimeTuesday(boolean[] availableTimeTuesday) {
        this.availableTimeTuesday = availableTimeTuesday;
    }
    /**
     * gets the boolean array for the days availability
     * @return the boolean array for the days availability
     */
    public boolean[] getAvailableTimeWednesday() {
        return availableTimeWednesday;
    }

    /**
     * Sets the boolean array for the given days availability
     * @param availableTimeWednesday the boolean array for the given days availability
     */
    public void setAvailableTimeWednesday(boolean[] availableTimeWednesday) {
        this.availableTimeWednesday = availableTimeWednesday;
    }
    /**
     * gets the boolean array for the days availability
     * @return the boolean array for the days availability
     */
    public boolean[] getAvailableTimeThursday() {
        return availableTimeThursday;
    }

    /**
     * sets the boolean array for the given days availability
     * @param availableTimeThursday the boolean array for the given days availability
     */
    public void setAvailableTimeThursday(boolean[] availableTimeThursday) {
        this.availableTimeThursday = availableTimeThursday;
    }
    /**
     * gets the boolean array for the days availability
     * @return the boolean array for the days availability
     */
    public boolean[] getAvailableTimeFriday() {
        return availableTimeFriday;
    }

    /**
     *  sets the boolean array for the given days availability
     * @param availableTimeFriday the boolean array for the given days availability
     */
    public void setAvailableTimeFriday(boolean[] availableTimeFriday) {
        this.availableTimeFriday = availableTimeFriday;
    }
    /**
     * gets the boolean array for the days availability
     * @return the boolean array for the days availability
     */
    public boolean[] getAvailableTimeSaturday() {
        return availableTimeSaturday;
    }

    /**
     * sets the boolean array for the given days availability
     * @param availableTimeSaturday the boolean array for the given days availability
     */
    public void setAvailableTimeSaturday(boolean[] availableTimeSaturday) {
        this.availableTimeSaturday = availableTimeSaturday;
    }
    /**
     * gets the boolean array for the days availability
     * @return the boolean array for the days availability
     */
    public boolean[] getAvailableTimeSunday() {
        return availableTimeSunday;
    }

    /**
     * the boolean array for the given days availability
     * @param availableTimeSunday the boolean array for the given days availability
     */
    public void setAvailableTimeSunday(boolean[] availableTimeSunday) {
        this.availableTimeSunday = availableTimeSunday;
    }
    /**
     * gets the boolean array for the day
     * @return the boolean array for the day
     */
    public boolean[] getPreferredTimeMonday() {
        return preferredTimeMonday;
    }

    /**
     * sets the boolean array for the given days Preferences
     * @param preferredTimeMonday the boolean array for the given days Preferences
     */
    public void setPreferredTimeMonday(boolean[] preferredTimeMonday) {
        this.preferredTimeMonday = preferredTimeMonday;
    }
    /**
     * gets the boolean array for the day
     * @return the boolean array for the day
     */
    public boolean[] getPreferredTimeTuesday() {
        return preferredTimeTuesday;
    }

    /**
     * sets the boolean array for the given days Preferences
     * @param preferredTimeTuesday the boolean array for the given days Preferences
     */
    public void setPreferredTimeTuesday(boolean[] preferredTimeTuesday) {
        this.preferredTimeTuesday = preferredTimeTuesday;
    }
    /**
     * gets the boolean array for the day
     * @return the boolean array for the day
     */
    public boolean[] getPreferredTimeWednesday() {
        return preferredTimeWednesday;
    }

    /**
     * sets the boolean array for the given days Preferences
     * @param preferredTimeWednesday the boolean array for the given days Preferences
     */
    public void setPreferredTimeWednesday(boolean[] preferredTimeWednesday) {
        this.preferredTimeWednesday = preferredTimeWednesday;
    }
    /**
     * gets the boolean array for the day
     * @return the boolean array for the day
     */
    public boolean[] getPreferredTimeThursday() {
        return preferredTimeThursday;
    }

    /**
     * sets the boolean array for the given days Preferences
     * @param preferredTimeThursday the boolean array for the given days Preferences
     */
    public void setPreferredTimeThursday(boolean[] preferredTimeThursday) {
        this.preferredTimeThursday = preferredTimeThursday;
    }
    /**
     * gets the boolean array for the days preferences
     * @return the boolean array for the days preferences
     */
    public boolean[] getPreferredTimeFriday() {
        return preferredTimeFriday;
    }

    /**
     * sets the boolean array for the given days preferences
     * @param preferredTimeFriday the boolean array for the given days preferences
     */
    public void setPreferredTimeFriday(boolean[] preferredTimeFriday) {
        this.preferredTimeFriday = preferredTimeFriday;
    }
    /**
     * gets the boolean array for the day
     * @return the boolean array for the day
     */
    public boolean[] getPreferredTimeSaturday() {
        return preferredTimeSaturday;
    }

    /**
     * sets the boolean array for the given days preferences
     * @param preferredTimeSaturday the boolean array for the given days preferences
     */
    public void setPreferredTimeSaturday(boolean[] preferredTimeSaturday) {
        this.preferredTimeSaturday = preferredTimeSaturday;
    }
    /**
     * gets the boolean array for the days preferences
     * @return the boolean array for the days preferences
     */
    public boolean[] getPreferredTimeSunday() {
        return preferredTimeSunday;
    }

    /**
     * sets the boolean array for the given days preferences
     * @param preferredTimeSunday the boolean array for the given days preferences
     */
    public void setPreferredTimeSunday(boolean[] preferredTimeSunday) {
        this.preferredTimeSunday = preferredTimeSunday;
    }

    /**
     * checks if the employee is available on the current day
     * @return a boolean representing if they can work on the given day
     */
    public boolean isAvailMonday() {
        return isAvailMonday;
    }

    /**
     * sets if the employee is available on the given day
     * @param availMonday if the employee is available on the given day
     */
    public void setAvailMonday(boolean availMonday) {
        isAvailMonday = availMonday;
    }
    /**
     * checks if the employee is available on the current day
     * @return a boolean representing if they can work on the given day
     */
    public boolean isAvailTuesday() {
        return isAvailTuesday;
    }

    /**
     * sets if the employee is available on the given day
     * @param availTuesday if the employee is available on the given day
     */
    public void setAvailTuesday(boolean availTuesday) {
        isAvailTuesday = availTuesday;
    }
    /**
     * checks if the employee is available on the current day
     * @return a boolean representing if they can work on the given day
     */
    public boolean isAvailWednesday() {
        return isAvailWednesday;
    }

    /**
     * sets if the employee is available on the given day
     * @param availWednesday if the employee is available on the given day
     */
    public void setAvailWednesday(boolean availWednesday) {
        isAvailWednesday = availWednesday;
    }
    /**
     * checks if the employee is available on the current day
     * @return a boolean representing if they can work on the given day
     */
    public boolean isAvailThursday() {
        return isAvailThursday;
    }

    /**
     * sets if the employee is available on the given day
     * @param availThursday if the employee is available on the given day
     */
    public void setAvailThursday(boolean availThursday) {
        isAvailThursday = availThursday;
    }
    /**
     * checks if the employee is available on the current day
     * @return a boolean representing if they can work on the given day
     */
    public boolean isAvailFriday() {
        return isAvailFriday;
    }

    /**
     * sets if the employee is available on the given day
     * @param availFriday if the employee is available on the given day
     */
    public void setAvailFriday(boolean availFriday) {
        isAvailFriday = availFriday;
    }
    /**
     * checks if the employee is available on the current day
     * @return a boolean representing if they can work on the given day
     */
    public boolean isAvailSaturday() {
        return isAvailSaturday;
    }

    /**
     * sets if the employee is available on the given day
     * @param availSaturday if the employee is available on the given day
     */
    public void setAvailSaturday(boolean availSaturday) {
        isAvailSaturday = availSaturday;
    }
    /**
     * checks if the employee is available on the current day
     * @return a boolean representing if they can work on the given day
     */
    public boolean isAvailSunday() {
        return isAvailSunday;
    }

    /**
     * sets if the employee is available on the given day
     * @param availSunday if the employee is available on the given day
     */
    public void setAvailSunday(boolean availSunday) {
        isAvailSunday = availSunday;
    }
    /**
     * checks if the employee prefers to work on the current day
     * @return a boolean representing if they prefer work on the given day
     */
    public boolean isPrefMonday() {
        return isPrefMonday;
    }

    /**
     * sets if the employee prefers on the given day
     * @param prefMonday if the employee prefers on the given day
     */
    public void setPrefMonday(boolean prefMonday) {
        isPrefMonday = prefMonday;
    }
    /**
     * checks if the employee prefers to work on the current day
     * @return a boolean representing if they prefer work on the given day
     */
    public boolean isPrefTuesday() {
        return isPrefTuesday;
    }

    /**
     * sets if the employee prefers on the given day
     * @param prefTuesday if the employee prefers on the given day
     */
    public void setPrefTuesday(boolean prefTuesday) {
        isPrefTuesday = prefTuesday;
    }
    /**
     * checks if the employee prefers to work on the current day
     * @return a boolean representing if they prefer work on the given day
     */
    public boolean isPrefWednesday() {
        return isPrefWednesday;
    }

    /**
     * sets if the employee prefers on the given day
     * @param prefWednesday if the employee prefers on the given day
     */
    public void setPrefWednesday(boolean prefWednesday) {
        isPrefWednesday = prefWednesday;
    }
    /**
     * checks if the employee prefers to work on the current day
     * @return a boolean representing if they prefer work on the given day
     */
    public boolean isPrefThursday() {
        return isPrefThursday;
    }

    /**
     * sets if the employee prefers on the given day
     * @param prefThursday if the employee prefers on the given day
     */
    public void setPrefThursday(boolean prefThursday) {
        isPrefThursday = prefThursday;
    }
    /**
     * checks if the employee prefers to work on the current day
     * @return a boolean representing if they prefer work on the given day
     */
    public boolean isPrefFriday() {
        return isPrefFriday;
    }

    /**
     * sets if the employee prefers on the given day
     * @param prefFriday
     */
    public void setPrefFriday(boolean prefFriday) {
        isPrefFriday = prefFriday;
    }
    /**
     * checks if the employee prefers to work on the current day
     * @return a boolean representing if they prefer work on the given day
     */
    public boolean isPrefSaturday() {
        return isPrefSaturday;
    }

    /**
     * sets if the employee prefers on the given day
     * @param prefSaturday if the employee prefers on the given day
     */
    public void setPrefSaturday(boolean prefSaturday) {
        isPrefSaturday = prefSaturday;
    }
    /**
     * checks if the employee prefers to work on the current day
     * @return a boolean representing if they prefer work on the given day
     */
    public boolean isPrefSunday() {
        return isPrefSunday;
    }

    /**
     * sets if the employee prefers on the given day
     * @param prefSunday if the employee prefers on the given day
     */
    public void setPrefSunday(boolean prefSunday) {
        isPrefSunday = prefSunday;
    }




}
