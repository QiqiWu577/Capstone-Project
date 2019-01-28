package Model;

public class ScheduledEmployee {
    int dailyHours;
    Employee emp;

    public ScheduledEmployee(Employee emp) {
        this.emp = emp;
    }

    public int getDailyHours() {
        return dailyHours;
    }
}
