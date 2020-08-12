/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booking.conference.room.entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Dhiraj Rathod
 */
public class TimeSlot {

    private Date beginDate;
    private Date endDate;

    public TimeSlot(Date beginDate, Date endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public boolean collidesWith(TimeSlot timeSlot) {
        if (timeSlot.beginDate.getTime() > beginDate.getTime()
                && timeSlot.beginDate.getTime() < endDate.getTime()) {
            return true;
        }

        if (timeSlot.endDate.getTime() > beginDate.getTime()
                && timeSlot.endDate.getTime() < endDate.getTime()) {
            return true;
        }
        return false;
    }

    public static TimeSlot createNewSlot(Date beginDate, Date endDate) {
        try {
            return new TimeSlot(beginDate, endDate);
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }
        return null;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.beginDate);
        hash = 71 * hash + Objects.hashCode(this.endDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TimeSlot other = (TimeSlot) obj;
        if (!Objects.equals(this.beginDate, other.beginDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        return true;
    }
}
