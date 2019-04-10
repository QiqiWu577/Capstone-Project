package Model;

/**
 * @author Anthony Doucet
 */
public class InvalidConstraintException extends Throwable {
    /**
     * Is thrown when an employee constraint is not 0 or 1
     * @param dayOfWeek day of the week that the error is in
     */
    public InvalidConstraintException(String dayOfWeek) {
        System.out.println("Invalid Constraint on day: " + dayOfWeek);
    }
}
