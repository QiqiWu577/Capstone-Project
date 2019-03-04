/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "employee_constraints")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeConstraint.findAll", query = "SELECT e FROM EmployeeConstraints e")
    , @NamedQuery(name = "EmployeeConstraint.findByEmpId", query = "SELECT e FROM EmployeeConstraints e WHERE e.empId = :empId")
    , @NamedQuery(name = "EmployeeConstraint.findByConstraints", query = "SELECT e FROM EmployeeConstraints e WHERE e.constraints = :constraints")})
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

    public EmployeeConstraints() {
    }

    public EmployeeConstraints(Integer empId) {
        this.empId = empId;
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
    
}
