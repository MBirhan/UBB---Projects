package View;

import Controller.Controller;
import Model.Expressions.ArithmeticExpressions;
import Model.Expressions.ConstantExpressions;
import Model.Expressions.VariableExpressions;
import Model.Statements.*;
import Model.Utils.*;
import Repository.*;
import Model.*;

import java.util.Scanner;


public class View {
    public static void main(String[] args) {
        while(true){
            IMyStack<Statement> ExeStack = new MyStack<>();
            IMyDictionary<String, Integer> SymTable = new MyDictionary<String, Integer>();
            IMyList<Integer> out = new MyList<Integer>();
            System.out.println("0. Exit");
            System.out.println("1. v=2; Print(v)");
            System.out.println("2. a=2+3*5; b=a+1; Print(b)");
            System.out.println("3. a=2-2; If a Then v=2 Else v=3; Print(v)");
            System.out.print("Please enter the command that you want:");
            Scanner in = new Scanner(System.in);
            int option  = in.nextInt();
            if(option == 0)
                break;
            switch (option){
                case 1:{
                    try {
                        //IMyStack<Statement> ExeStack = new MyStack<>();
                        //IMyDictionary<String, Integer> SymTable = new MyDictionary<String, Integer>();
                        //IMyList<Integer> out = new MyList<Integer>();
                        Statement expr1 = new CompareStatement(new AssignStatement("v", new ConstantExpressions(2)),
                                new PrintStatement(new VariableExpressions("v")));
                        ExeStack.add(expr1);
                        ProgramState prgCurrent = new ProgramState(ExeStack, SymTable, out);
                        IRepository repo = new Repository();
                        repo.addPrg(prgCurrent);
                        Controller ctrl = new Controller(repo);
                        ctrl.allSteps();
                    }catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                }
                case 2:{
                    try {
                        //IMyStack<Statement> ExeStack = new MyStack<>();
                        //IMyDictionary<String, Integer> SymTable = new MyDictionary<String, Integer>();
                        //IMyList<Integer> out = new MyList<Integer>();
                        Statement expr2 = new CompareStatement(new AssignStatement("a", new ArithmeticExpressions("+", new ConstantExpressions(2),
                                new ArithmeticExpressions("*", new ConstantExpressions(3), new ConstantExpressions(5)))), new CompareStatement(new AssignStatement("b",
                                new ArithmeticExpressions("+", new VariableExpressions("a"), new ConstantExpressions(1))), new PrintStatement(new VariableExpressions("b"))));
                        ExeStack.add(expr2);
                        ProgramState prgCurrent = new ProgramState(ExeStack, SymTable, out);
                        IRepository repo = new Repository();
                        repo.addPrg(prgCurrent);
                        Controller ctrl = new Controller(repo);
                        ctrl.allSteps();
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                }
                case 3:{
                    try {
                        //IMyStack<Statement> ExeStack = new MyStack<>();
                        //IMyDictionary<String, Integer> SymTable = new MyDictionary<String, Integer>();
                        //IMyList<Integer> out = new MyList<Integer>();
                        Statement expr3 = new CompareStatement(new AssignStatement("a", new ArithmeticExpressions("-", new ConstantExpressions(2), new ConstantExpressions(2))),
                                new CompareStatement(new IfStatement(new VariableExpressions("a"), new AssignStatement("v", new ConstantExpressions(2)), new AssignStatement("v",
                                        new ConstantExpressions(3))), new PrintStatement(new VariableExpressions("v"))));
                        ExeStack.add(expr3);
                        ProgramState prgCurrent = new ProgramState(ExeStack, SymTable, out);
                        IRepository repo = new Repository();
                        repo.addPrg(prgCurrent);
                        Controller ctrl = new Controller(repo);
                        ctrl.allSteps();
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                }
            }
        }
    }
}

