package Model;

public class ShiftCannotBeFilledException extends Exception {

    public ShiftCannotBeFilledException(String s) {
        System.out.println("Shift Could not be filled on day: " + s);
    }
}
