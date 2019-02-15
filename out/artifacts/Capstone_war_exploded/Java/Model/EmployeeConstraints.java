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

    private boolean isAvailMonday;
    private boolean isAvailTuesday;
    private boolean isAvailWednesday;
    private boolean isAvailThursday;
    private boolean isAvailFriday;
    private boolean isAvailSaturday;
    private boolean isAvailSunday;

    private boolean isPrefMonday;
    private boolean isPrefTuesday;
    private boolean isPrefWednesday;
    private boolean isPrefThursday;
    private boolean isPrefFriday;
    private boolean isPrefSaturday;
    private boolean isPrefSunday;

    private String constraints;

    public EmployeeConstraints(String constraints) throws InvalidConstraintException, ConstraintWrongSizeException {
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



    public void setPreferredTimeMonday(boolean[] preferredTimeMonday) {
        this.preferredTimeMonday = preferredTimeMonday;
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
