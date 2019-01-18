package Exceptions;

public class EmptyStack extends RuntimeException {

    public EmptyStack(String msg){
        super(msg);
    }
}