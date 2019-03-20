package Model.Statements;

public class ForkID {
    private static int number=1;
    public static  int getID() {
        return number++;
    }
}
