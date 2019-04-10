package Model;

/**
 * @author Anthony Doucet
 */
public class ConstraintWrongSizeException extends Throwable {
    /**
     * An Exception that the given constraint is the wrong size
     * @param dayOfWeek day of the week that is the wrong size
     */
    public ConstraintWrongSizeException(String dayOfWeek) {
        System.out.println("Constraint too long on day: " + dayOfWeek);
    }
}
