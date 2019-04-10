package Model;

/**
 * @author Anthony Doucet
 */
public class ShiftCannotBeFilledException extends Exception {
    /**
     * Is thrown when a shift cannot be filled
     * @param s the day that the shift cannot be filled on
     */
    public ShiftCannotBeFilledException(String s) {
        System.out.println("Shift Could not be filled on day: " + s);
    }
}
