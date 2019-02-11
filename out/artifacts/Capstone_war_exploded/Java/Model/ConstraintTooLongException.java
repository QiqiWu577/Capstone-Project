package Model;

public class ConstraintTooLongException extends Throwable {
    public ConstraintTooLongException(String dayOfWeek) {
        System.out.println("Constraint too long on day: " + dayOfWeek);
    }
}
