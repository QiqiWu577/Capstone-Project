/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import Model.ConstraintWrongSizeException;
import Model.InvalidConstraintException;

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

    private boolean isPrefMonday;
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





    public EmployeeConstraints() {
    }

    public EmployeeConstraints(Integer empId) {
        this.empId = empId;
    }

    public EmployeeConstraints(Integer empId, String constraints) throws InvalidConstraintException, ConstraintWrongSizeException{
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

        parseConstraints(constraints);

    }

    private void parseConstraints(String constraints) throws InvalidConstraintException, ConstraintWrongSizeException {
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    @Override
    public String toString() {
        return "data.EmployeeConstraints[ empId=" + empId + " ]";
    }



    public boolean[] getAvailableTimeMonday() {
        return availableTimeMonday;
    }

    public void setAvailableTimeMonday(boolean[] availableTimeMonday) {
        this.availableTimeMonday = availableTimeMonday;
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

    public void setAvailableTimeThursday(boolean[] availableTimeThursday) {
        this.availableTimeThursday = availableTimeThursday;
    }

    public boolean[] getAvailableTimeFriday() {
        return availableTimeFriday;
    }

    public void setAvailableTimeFriday(boolean[] availableTimeFriday) {
        this.availableTimeFriday = availableTimeFriday;
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

    public void setPreferredTimeMonday(boolean[] preferredTimeMonday) {
        this.preferredTimeMonday = preferredTimeMonday;
    }

    public boolean[] getPreferredTimeTuesday() {
        return preferredTimeTuesday;
    }

    public void setPreferredTimeTuesday(boolean[] preferredTimeTuesday) {
        this.preferredTimeTuesday = preferredTimeTuesday;
    }

    public boolean[] getPreferredTimeWednesday() {
        return preferredTimeWednesday;
    }

    public void setPreferredTimeWednesday(boolean[] preferredTimeWednesday) {
        this.preferredTimeWednesday = preferredTimeWednesday;
    }

    public boolean[] getPreferredTimeThursday() {
        return preferredTimeThursday;
    }

    public void setPreferredTimeThursday(boolean[] preferredTimeThursday) {
        this.preferredTimeThursday = preferredTimeThursday;
    }

    public boolean[] getPreferredTimeFriday() {
        return preferredTimeFriday;
    }

    public void setPreferredTimeFriday(boolean[] preferredTimeFriday) {
        this.preferredTimeFriday = preferredTimeFriday;
    }

    public boolean[] getPreferredTimeSaturday() {
        return preferredTimeSaturday;
    }

    public void setPreferredTimeSaturday(boolean[] preferredTimeSaturday) {
        this.preferredTimeSaturday = preferredTimeSaturday;
    }

    public boolean[] getPreferredTimeSunday() {
        return preferredTimeSunday;
    }

    public void setPreferredTimeSunday(boolean[] preferredTimeSunday) {
        this.preferredTimeSunday = preferredTimeSunday;
    }

    public boolean isAvailMonday() {
        return isAvailMonday;
    }

    public void setAvailMonday(boolean availMonday) {
        isAvailMonday = availMonday;
    }

    public boolean isAvailTuesday() {
        return isAvailTuesday;
    }

    public void setAvailTuesday(boolean availTuesday) {
        isAvailTuesday = availTuesday;
    }

    public boolean isAvailWednesday() {
        return isAvailWednesday;
    }

    public void setAvailWednesday(boolean availWednesday) {
        isAvailWednesday = availWednesday;
    }

    public boolean isAvailThursday() {
        return isAvailThursday;
    }

    public void setAvailThursday(boolean availThursday) {
        isAvailThursday = availThursday;
    }

    public boolean isAvailFriday() {
        return isAvailFriday;
    }

    public void setAvailFriday(boolean availFriday) {
        isAvailFriday = availFriday;
    }

    public boolean isAvailSaturday() {
        return isAvailSaturday;
    }

    public void setAvailSaturday(boolean availSaturday) {
        isAvailSaturday = availSaturday;
    }

    public boolean isAvailSunday() {
        return isAvailSunday;
    }

    public void setAvailSunday(boolean availSunday) {
        isAvailSunday = availSunday;
    }

    public boolean isPrefMonday() {
        return isPrefMonday;
    }

    public void setPrefMonday(boolean prefMonday) {
        isPrefMonday = prefMonday;
    }

    public boolean isPrefTuesday() {
        return isPrefTuesday;
    }

    public void setPrefTuesday(boolean prefTuesday) {
        isPrefTuesday = prefTuesday;
    }

    public boolean isPrefWednesday() {
        return isPrefWednesday;
    }

    public void setPrefWednesday(boolean prefWednesday) {
        isPrefWednesday = prefWednesday;
    }

    public boolean isPrefThursday() {
        return isPrefThursday;
    }

    public void setPrefThursday(boolean prefThursday) {
        isPrefThursday = prefThursday;
    }

    public boolean isPrefFriday() {
        return isPrefFriday;
    }

    public void setPrefFriday(boolean prefFriday) {
        isPrefFriday = prefFriday;
    }

    public boolean isPrefSaturday() {
        return isPrefSaturday;
    }

    public void setPrefSaturday(boolean prefSaturday) {
        isPrefSaturday = prefSaturday;
    }

    public boolean isPrefSunday() {
        return isPrefSunday;
    }

    public void setPrefSunday(boolean prefSunday) {
        isPrefSunday = prefSunday;
    }




}
