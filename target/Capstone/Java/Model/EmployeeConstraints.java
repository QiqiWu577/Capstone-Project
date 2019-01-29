package Model;

public class EmployeeConstraints {

    private boolean[] availableTimeMonday;
    private boolean[] availableTimeTuesday;
    private boolean[] availableTimeWednesday;
    private boolean[] availableTimeThursday;
    private boolean[] availableTimeFriday;
    private boolean[] availableTimeSaturday;
    private boolean[] availableTimeSunday;

    private boolean[] preferredTimeMonday;
    private boolean[] preferredTimeTuesday;
    private boolean[] preferredTimeWednesday;
    private boolean[] preferredTimeThursday;
    private boolean[] preferredTimeFriday;
    private boolean[] preferredTimeSaturday;
    private boolean[] preferredTimeSunday;
    private String constraints;

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

            if(list[0].charAt(hourOfDay) == '1') {
                availableTimeMonday[hourOfDay] = true;
                if(list[1].charAt(hourOfDay) == '1') {
                    preferredTimeMonday[hourOfDay] = true;
                } else if(list[1].charAt(hourOfDay) == '0') {
                    preferredTimeMonday[hourOfDay] = false;
                } else {
                    System.out.println("Error in Constraints String Preferred Monday - Exiting");
                }
            } else if(list[0].charAt(hourOfDay) == '0') {
                availableTimeMonday[hourOfDay] = false;
                preferredTimeMonday[hourOfDay] = false;
            } else {
                System.out.println("Error in Constraints String Available Monday - Exiting");
                return;
            }
        }

        for(hourOfDay = 0; hourOfDay< HOURSPERDAY; hourOfDay++) {

            if(list[2].charAt(hourOfDay) == '1') {
                availableTimeTuesday[hourOfDay] = true;
                if(list[3].charAt(hourOfDay) == '1') {
                    preferredTimeTuesday[hourOfDay] = true;
                } else if(list[3].charAt(hourOfDay) == '0') {
                    preferredTimeTuesday[hourOfDay] = false;
                } else {
                    System.out.println("Error in Constraints String Preferred Tuesday - Exiting");
                }
            } else if(list[2].charAt(hourOfDay) == '0') {
                availableTimeTuesday[hourOfDay] = false;
                preferredTimeTuesday[hourOfDay] = false;
            } else {
                System.out.println("Error in Constraints String Available Tuesday - Exiting");
                return;
            }
        }

        for(hourOfDay = 0; hourOfDay< HOURSPERDAY; hourOfDay++) {

            if(list[4].charAt(hourOfDay) == '1') {
                availableTimeWednesday[hourOfDay] = true;
                if(list[5].charAt(hourOfDay) == '1') {
                    preferredTimeWednesday[hourOfDay] = true;
                } else if(list[5].charAt(hourOfDay) == '0') {
                    preferredTimeWednesday[hourOfDay] = false;
                } else {
                    System.out.println("Error in Constraints String Preferred Wednesday - Exiting");
                }
            } else if(list[4].charAt(hourOfDay) == '0') {
                availableTimeWednesday[hourOfDay] = false;
                preferredTimeWednesday[hourOfDay] = false;
            } else {
                System.out.println("Error in Constraints String Available Wednesday - Exiting");
                return;
            }
        }

        for(hourOfDay = 0; hourOfDay< HOURSPERDAY; hourOfDay++) {

            if(list[6].charAt(hourOfDay) == '1') {
                availableTimeThursday[hourOfDay] = true;
                if(list[7].charAt(hourOfDay) == '1') {
                    preferredTimeThursday[hourOfDay] = true;
                } else if(list[7].charAt(hourOfDay) == '0') {
                    preferredTimeThursday[hourOfDay] = false;
                } else {
                    System.out.println("Error in Constraints String Preferred Thursday - Exiting");
                }
            } else if(list[6].charAt(hourOfDay) == '0') {
                availableTimeThursday[hourOfDay] = false;
                preferredTimeThursday[hourOfDay] = false;
            } else {
                System.out.println("Error in Constraints String Available Thursday - Exiting");
                return;
            }
        }

        for(hourOfDay = 0; hourOfDay< HOURSPERDAY; hourOfDay++) {

            if(list[8].charAt(hourOfDay) == '1') {
                availableTimeFriday[hourOfDay] = true;
                if(list[9].charAt(hourOfDay) == '1') {
                    preferredTimeFriday[hourOfDay] = true;
                } else if(list[9].charAt(hourOfDay) == '0') {
                    preferredTimeFriday[hourOfDay] = false;
                } else {
                    System.out.println("Error in Constraints String Preferred Friday - Exiting");
                }
            } else if(list[8].charAt(hourOfDay) == '0') {
                availableTimeFriday[hourOfDay] = false;
                preferredTimeFriday[hourOfDay] = false;
            } else {
                System.out.println("Error in Constraints String Available Friday - Exiting");
                return;
            }
        }

        for(hourOfDay = 0; hourOfDay< HOURSPERDAY; hourOfDay++) {

            if(list[10].charAt(hourOfDay) == '1') {
                availableTimeSaturday[hourOfDay] = true;
                if(list[11].charAt(hourOfDay) == '1') {
                    preferredTimeSaturday[hourOfDay] = true;
                } else if(list[11].charAt(hourOfDay) == '0') {
                    preferredTimeSaturday[hourOfDay] = false;
                } else {
                    System.out.println("Error in Constraints String Preferred Saturday - Exiting");
                }
            } else if(list[10].charAt(hourOfDay) == '0') {
                availableTimeSaturday[hourOfDay] = false;
                preferredTimeSaturday[hourOfDay] = false;
            } else {
                System.out.println("Error in Constraints String Available Saturday - Exiting");
                return;
            }
        }

        for(hourOfDay = 0; hourOfDay< HOURSPERDAY; hourOfDay++) {

            if(list[12].charAt(hourOfDay) == '1') {
                availableTimeSunday[hourOfDay] = true;
                if(list[13].charAt(hourOfDay) == '1') {
                    preferredTimeSunday[hourOfDay] = true;
                } else if(list[13].charAt(hourOfDay) == '0') {
                    preferredTimeSunday[hourOfDay] = false;
                } else {
                    System.out.println("Error in Constraints String Preferred Sunday - Exiting");
                }
            } else if(list[12].charAt(hourOfDay) == '0') {
                availableTimeSunday[hourOfDay] = false;
                preferredTimeSunday[hourOfDay] = false;
            } else {
                System.out.println("Error in Constraints String Available Sunday - Exiting");
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

    public String getConstraints() {
        return constraints;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }
}