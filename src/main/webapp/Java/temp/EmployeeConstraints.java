/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
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

    @JoinColumn(name = "emp_id", referencedColumnName = "Emp_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Employee employees;

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

    public EmployeeConstraints() {
    }

    public EmployeeConstraints(String constraints) {
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

        this.constraints = constraints;
        parseConstraints(constraints);
    }

    private void parseConstraints(String constraints) {
        String [] list = constraints.split(",");
        final int HOURSPERDAY = 24;
        int hourOfDay;

        for(hourOfDay = 0; hourOfDay< HOURSPERDAY; hourOfDay++) {
            if(list[0].length() != 24) {
                if (list[0].charAt(hourOfDay) == '1') {
                    availableTimeMonday[hourOfDay] = true;
                    if (list[1].charAt(hourOfDay) == '1') {
                        preferredTimeMonday[hourOfDay] = true;
                    } else if (list[1].charAt(hourOfDay) == '0') {
                        preferredTimeMonday[hourOfDay] = false;
                    } else {
                        System.out.println("Error in Constraints String Preferred Monday - Exiting");
                    }
                } else if (list[0].charAt(hourOfDay) == '0') {
                    availableTimeMonday[hourOfDay] = false;
                    preferredTimeMonday[hourOfDay] = false;
                } else {
                    System.out.println("Error in Constraints String Available Monday - Exiting");
                    return;
                }
            } else {
                System.out.println("Too Long");
            }
        }

        for(hourOfDay = 0; hourOfDay< HOURSPERDAY; hourOfDay++) {
            if(list[0].length() != 24) {
                if (list[2].charAt(hourOfDay) == '1') {
                    availableTimeTuesday[hourOfDay] = true;
                    if (list[3].charAt(hourOfDay) == '1') {
                        preferredTimeTuesday[hourOfDay] = true;
                    } else if (list[3].charAt(hourOfDay) == '0') {
                        preferredTimeTuesday[hourOfDay] = false;
                    } else {
                        System.out.println("Error in Constraints String Preferred Tuesday - Exiting");
                    }
                } else if (list[2].charAt(hourOfDay) == '0') {
                    availableTimeTuesday[hourOfDay] = false;
                    preferredTimeTuesday[hourOfDay] = false;
                } else {
                    System.out.println("Error in Constraints String Available Tuesday - Exiting");
                    return;
                }
            } else {
                System.out.println("Too Long");
            }
        }

        for(hourOfDay = 0; hourOfDay< HOURSPERDAY; hourOfDay++) {
            if(list[0].length() != 24) {
                if (list[4].charAt(hourOfDay) == '1') {
                    availableTimeWednesday[hourOfDay] = true;
                    if (list[5].charAt(hourOfDay) == '1') {
                        preferredTimeWednesday[hourOfDay] = true;
                    } else if (list[5].charAt(hourOfDay) == '0') {
                        preferredTimeWednesday[hourOfDay] = false;
                    } else {
                        System.out.println("Error in Constraints String Preferred Wednesday - Exiting");
                    }
                } else if (list[4].charAt(hourOfDay) == '0') {
                    availableTimeWednesday[hourOfDay] = false;
                    preferredTimeWednesday[hourOfDay] = false;
                } else {
                    System.out.println("Error in Constraints String Available Wednesday - Exiting");
                    return;
                }
            } else {
                System.out.println("Too Long");
            }
        }

        for(hourOfDay = 0; hourOfDay< HOURSPERDAY; hourOfDay++) {
            if(list[0].length() != 24) {
                if (list[6].charAt(hourOfDay) == '1') {
                    availableTimeThursday[hourOfDay] = true;
                    if (list[7].charAt(hourOfDay) == '1') {
                        preferredTimeThursday[hourOfDay] = true;
                    } else if (list[7].charAt(hourOfDay) == '0') {
                        preferredTimeThursday[hourOfDay] = false;
                    } else {
                        System.out.println("Error in Constraints String Preferred Thursday - Exiting");
                    }
                } else if (list[6].charAt(hourOfDay) == '0') {
                    availableTimeThursday[hourOfDay] = false;
                    preferredTimeThursday[hourOfDay] = false;
                } else {
                    System.out.println("Error in Constraints String Available Thursday - Exiting");
                    return;
                }
            } else {
                System.out.println("Too Long");
            }
        }

        for(hourOfDay = 0; hourOfDay< HOURSPERDAY; hourOfDay++) {
            if(list[0].length() != 24) {
                if (list[8].charAt(hourOfDay) == '1') {
                    availableTimeFriday[hourOfDay] = true;
                    if (list[9].charAt(hourOfDay) == '1') {
                        preferredTimeFriday[hourOfDay] = true;
                    } else if (list[9].charAt(hourOfDay) == '0') {
                        preferredTimeFriday[hourOfDay] = false;
                    } else {
                        System.out.println("Error in Constraints String Preferred Friday - Exiting");
                    }
                } else if (list[8].charAt(hourOfDay) == '0') {
                    availableTimeFriday[hourOfDay] = false;
                    preferredTimeFriday[hourOfDay] = false;
                } else {
                    System.out.println("Error in Constraints String Available Friday - Exiting");
                    return;
                }
            } else {
                System.out.println("Too Long");
            }
        }

        for(hourOfDay = 0; hourOfDay< HOURSPERDAY; hourOfDay++) {
            if(list[0].length() != 24) {
                if (list[10].charAt(hourOfDay) == '1') {
                    availableTimeSaturday[hourOfDay] = true;
                    if (list[11].charAt(hourOfDay) == '1') {
                        preferredTimeSaturday[hourOfDay] = true;
                    } else if (list[11].charAt(hourOfDay) == '0') {
                        preferredTimeSaturday[hourOfDay] = false;
                    } else {
                        System.out.println("Error in Constraints String Preferred Saturday - Exiting");
                    }
                } else if (list[10].charAt(hourOfDay) == '0') {
                    availableTimeSaturday[hourOfDay] = false;
                    preferredTimeSaturday[hourOfDay] = false;
                } else {
                    System.out.println("Error in Constraints String Available Saturday - Exiting");
                    return;
                }
            } else {
                System.out.println("Too Long");
            }
        }

        for(hourOfDay = 0; hourOfDay< HOURSPERDAY; hourOfDay++) {
            if(list[0].length() != 24) {
                if (list[12].charAt(hourOfDay) == '1') {
                    availableTimeSunday[hourOfDay] = true;
                    if (list[13].charAt(hourOfDay) == '1') {
                        preferredTimeSunday[hourOfDay] = true;
                    } else if (list[13].charAt(hourOfDay) == '0') {
                        preferredTimeSunday[hourOfDay] = false;
                    } else {
                        System.out.println("Error in Constraints String Preferred Sunday - Exiting");
                    }
                } else if (list[12].charAt(hourOfDay) == '0') {
                    availableTimeSunday[hourOfDay] = false;
                    preferredTimeSunday[hourOfDay] = false;
                } else {
                    System.out.println("Error in Constraints String Available Sunday - Exiting");
                    return;
                }
            } else {
                System.out.println("Too Long");
            }
        }


    }

    public EmployeeConstraints(Integer empId, String constraints) {

        this.empId = empId;
        this.constraints = constraints;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getConstraints() {
        return constraints;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }

    public Employee getEmployees() {
        return employees;
    }

    public void setEmployees(Employee employees) {
        this.employees = employees;
    }

    public boolean[] getAvailableTimeMonday() {
        return availableTimeMonday;
    }

    public void setAvailableTimeMonday(boolean[] availableTimesMonday) {
        this.availableTimeMonday = availableTimesMonday;
    }

    public boolean[] getAvailableTimeTuesday() {
        return availableTimeTuesday;
    }

    public void setAvailableTimeTuesday(boolean[] availableTimeTuesday) {
        this.availableTimeTuesday = availableTimeTuesday;
    }

    public boolean[] getAvailableTimeWednesday() {
        return availableTimeWednesday;
    }

    public void setAvailableTimeWednesday(boolean[] availableTimeWednesday) {
        this.availableTimeWednesday = availableTimeWednesday;
    }

    public boolean[] getAvailableTimeThursday() {
        return availableTimeThursday;
    }

    public void setAvailableTimeThursday(boolean[] availableTimeThrusday) {
        this.availableTimeThursday = availableTimeThrusday;
    }

    public boolean[] getAvailableTimeFriday() {
        return availableTimeFriday;
    }

    public void setAvailableTimeFriday(boolean[] availableTimeFirday) {
        this.availableTimeFriday = availableTimeFirday;
    }

    public boolean[] getAvailableTimeSaturday() {
        return availableTimeSaturday;
    }

    public void setAvailableTimeSaturday(boolean[] availableTimeSaturday) {
        this.availableTimeSaturday = availableTimeSaturday;
    }

    public boolean[] getAvailableTimeSunday() {
        return availableTimeSunday;
    }

    public void setAvailableTimeSunday(boolean[] availableTimeSunday) {
        this.availableTimeSunday = availableTimeSunday;
    }

    public boolean[] getPreferredTimeMonday() {
        return preferredTimeMonday;
    }

    public void setPreferredTimsMonday(boolean[] preferredTimesMonday) {
        this.preferredTimeMonday = preferredTimesMonday;
    }

    public boolean[] getPreferredTimeTuesday() {
        return preferredTimeTuesday;
    }

    public void setPreferredTimeTuesday(boolean[] preferredTimesTuesday) {
        this.preferredTimeTuesday = preferredTimesTuesday;
    }

    public boolean[] getPreferredTimeWednesday() {
        return preferredTimeWednesday;
    }

    public void setPreferredTimeWednesday(boolean[] preferredTimesWednesday) {
        this.preferredTimeWednesday = preferredTimesWednesday;
    }

    public boolean[] getPreferredTimeThursday() {
        return preferredTimeThursday;
    }

    public void setPreferredTimeThursday(boolean[] preferredTimesThursday) {
        this.preferredTimeThursday = preferredTimesThursday;
    }

    public boolean[] getPreferredTimeFriday() {
        return preferredTimeFriday;
    }

    public void setPreferredTimeFriday(boolean[] preferredTimesFirday) {
        this.preferredTimeFriday = preferredTimesFirday;
    }

    public boolean[] getPreferredTimeSaturday() {
        return preferredTimeSaturday;
    }

    public void setPreferredTimeSaturday(boolean[] preferredTimesSaturday) {
        this.preferredTimeSaturday = preferredTimesSaturday;
    }

    public boolean[] getPreferredTimeSunday() {
        return preferredTimeSunday;
    }

    public void setPreferredTimeSunday(boolean[] preferredTimesSunday) {
        this.preferredTimeSunday = preferredTimesSunday;
    }

    @Override
    public String toString() {
        String result = "";
        result += "Monday Avail: ";
        for(boolean b: availableTimeMonday) {
            result += b + " ";
        }
        result += "\n";
        result += "Monday Pref: ";
        for(boolean b: preferredTimeMonday) {
            result += b + " ";
        }
        result += "\n";

        result += "Tuesday Avail: ";
        for(boolean b: availableTimeTuesday) {
            result += b + " ";
        }
        result += "\n";
        result += "Tuesday Pref: ";
        for(boolean b: preferredTimeTuesday) {
            result += b + " ";
        }
        result += "\n";

        result += "Wednesday Avail: ";
        for(boolean b: availableTimeWednesday) {
            result += b + " ";
        }
        result += "\n";
        result += "Wednesday Pref: ";
        for(boolean b: preferredTimeWednesday) {
            result += b + " ";
        }
        result += "\n";

        result += "Thrusday Avail: ";
        for(boolean b: availableTimeThursday) {
            result += b + " ";
        }
        result += "\n";
        result += "Thursday Pref: ";
        for(boolean b: preferredTimeThursday) {
            result += b + " ";
        }
        result += "\n";

        result += "Friday Avail: ";
        for(boolean b: availableTimeFriday) {
            result += b + " ";
        }
        result += "\n";
        result += "Friday Pref: ";
        for(boolean b: preferredTimeFriday) {
            result += b + " ";
        }
        result += "\n";


        result += "Saturday Avail: ";
        for(boolean b: availableTimeSaturday) {
            result += b + " ";
        }
        result += "\n";
        result += "Saturday Pref: ";
        for(boolean b: preferredTimeSaturday) {
            result += b + " ";
        }
        result += "\n";

        result += "Sunday Avail: ";
        for(boolean b: availableTimeSunday) {
            result += b + " ";
        }
        result += "\n";
        result += "Sunday Pref: ";
        for(boolean b: preferredTimeSunday) {
            result += b + " ";
        }
        result += "\n";

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empId != null ? empId.hashCode() : 0);
        return hash;
    }

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
}