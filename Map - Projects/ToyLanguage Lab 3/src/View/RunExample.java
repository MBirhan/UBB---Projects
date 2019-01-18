package View;

import Controller.Controller;
import Exceptions.*;
import Model.*;

public class RunExample extends Command {
    private Controller ctrl;
    public RunExample(String key, String desc, Controller ctr){
        super(key, desc);
        this.ctrl=ctr;
    }
    @Override
    public void execute() {
        try {
            ctrl.executeAll();
        }
        catch (EmptyStack eStk){
            System.out.println(eStk);
        }
        catch(DivisionBy0 div){
            System.out.println(div);
        }
        catch(InvalidOperator invO){
            System.out.println(invO);
        }
        catch(UndefinedVariable varND){
            System.out.println(varND);
        }
        catch(FileException f){
            System.out.println(f);
        }

    }
}