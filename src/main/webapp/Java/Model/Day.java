package Model;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

public class Day {

    public LocalDateTime openTime;
    public LocalDateTime closeTime;
    public LocalDate date;

    public Day(LocalDateTime openTime, LocalDateTime closeTime, LocalDate date) {
        this.closeTime = closeTime;
        this.openTime = openTime;
        this.date = LocalDate.now();
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
}
