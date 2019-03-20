package Model.HeapStatements;

public class HeapGeneratorInteger {

    private static int number = 1;
    public static int genID(){
        return number++;
    }
}
