package Model;

public class InvalidConstraintException extends Throwable {

    public InvalidConstraintException(String dayOfWeek) {
        System.out.println("Invalid Constraint on day: " + dayOfWeek);
    }
}
