/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Administrator
 */
@Embeddable
public class ShiftPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "shift_id")
    private int shiftId;

    @Basic(optional = false)
    @Column(name = "day_id")
    private int dayId;

    public ShiftPK() {
    }

    public ShiftPK(int shiftId, int dayId) {
        this.shiftId = shiftId;
        this.dayId = dayId;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) shiftId;
        hash += (int) dayId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShiftPK)) {
            return false;
        }
        ShiftPK other = (ShiftPK) object;
        if (this.shiftId != other.shiftId) {
            return false;
        }
        if (this.dayId != other.dayId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.ShiftPK[ shiftId=" + shiftId + ", dayId=" + dayId + " ]";
    }
    
}
