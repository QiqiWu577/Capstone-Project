package Model;

public class ConstraintWrongSizeException extends Throwable {
    public ConstraintWrongSizeException(String dayOfWeek) {
        System.out.println("Constraint too long on day: " + dayOfWeek);
    }
}
