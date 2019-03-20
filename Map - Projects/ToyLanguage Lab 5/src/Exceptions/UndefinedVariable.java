package Exceptions;

public class UndefinedVariable extends RuntimeException {

    public UndefinedVariable(String msg){
        super(msg);
    }
}
