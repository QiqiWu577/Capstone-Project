package Model;

public class EmployeeConstraints {

    private boolean[] availableTimeMonday;
    private boolean[] availableTimeTuesday;
    private boolean[] availableTimeWednesday;
    private boolean[] availableTimeThrusday;
    private boolean[] availableTimeFirday;
    private boolean[] availableTimeSaturday;
    private boolean[] availableTimeSunday;

    private boolean[] preferredTimeMonday;
    private boolean[] preferredTimeTuesday;
    private boolean[] preferredTimeWednesday;
    private boolean[] preferredTimeThursday;
    private boolean[] preferredTimeFirday;
    private boolean[] preferredTimeSaturday;
    private boolean[] preferredTimeSunday;

    public EmployeeConstraints(String constraints) {
        availableTimeMonday = new boolean[24];
        availableTimeTuesday = new boolean[24];
        availableTimeWednesday = new boolean[24];
        availableTimeThrusday = new boolean[24];
        availableTimeFirday = new boolean[24];
        availableTimeSaturday = new boolean[24];
        availableTimeSunday = new boolean[24];

        preferredTimeMonday = new boolean[24];
        preferredTimeTuesday = new boolean[24];
        preferredTimeWednesday = new boolean[24];
        preferredTimeThursday = new boolean[24];
        preferredTimeFirday = new boolean[24];
        preferredTimeSaturday = new boolean[24];
        preferredTimeSunday = new boolean[24];

        parseConstraints(constraints);
    }

    private void parseConstraints(String constraints) {
        String [] list = constraints.split(",");

        for(int i = 0; i<24; i++) {

            if(list[0].charAt(i) == '1') {
                availableTimeMonday[i] = true;
                if(list[1].charAt(i) == '1') {
                    preferredTimeMonday[i] = true;
                } else if(list[1].charAt(i) == '0') {
                    preferredTimeMonday[i] = false;
                } else {
                    System.out.println("Error in Constraints String - Exiting");
                }
            } else if(list[0].charAt(i) == '0') {
                availableTimeMonday[i] = false;
                preferredTimeMonday[i] = false;
            } else {
                System.out.println("Error in Constraints String - Exiting");
                return;
            }
        }

        for(int i = 0; i<24; i++) {

            if(list[2].charAt(i) == '1') {
                availableTimeTuesday[i] = true;
                if(list[3].charAt(i) == '1') {
                    preferredTimeTuesday[i] = true;
                } else if(list[3].charAt(i) == '0') {
                    preferredTimeTuesday[i] = false;
                } else {
                    System.out.println("Error in Constraints String - Exiting");
                }
            } else if(list[2].charAt(i) == '0') {
                availableTimeTuesday[i] = false;
                preferredTimeTuesday[i] = false;
            } else {
                System.out.println("Error in Constraints String - Exiting");
                return;
            }
        }

        for(int i = 0; i<24; i++) {

            if(list[4].charAt(i) == '1') {
                availableTimeWednesday[i] = true;
                if(list[5].charAt(i) == '1') {
                    preferredTimeWednesday[i] = true;
                } else if(list[5].charAt(i) == '0') {
                    preferredTimeWednesday[i] = false;
                } else {
                    System.out.println("Error in Constraints String - Exiting");
                }
            } else if(list[4].charAt(i) == '0') {
                availableTimeWednesday[i] = false;
                preferredTimeWednesday[i] = false;
            } else {
                System.out.println("Error in Constraints String - Exiting");
                return;
            }
        }

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

    public boolean[] getAvailableTimeThrusday() {
        return availableTimeThrusday;
    }

    public void setAvailableTimeThrusday(boolean[] availableTimeThrusday) {
        this.availableTimeThrusday = availableTimeThrusday;
    }

    public boolean[] getAvailableTimeFirday() {
        return availableTimeFirday;
    }

    public void setAvailableTimeFirday(boolean[] availableTimeFirday) {
        this.availableTimeFirday = availableTimeFirday;
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

    public boolean[] getPreferredTimeFirday() {
        return preferredTimeFirday;
    }

    public void setPreferredTimeFirday(boolean[] preferredTimesFirday) {
        this.preferredTimeFirday = preferredTimesFirday;
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
}
