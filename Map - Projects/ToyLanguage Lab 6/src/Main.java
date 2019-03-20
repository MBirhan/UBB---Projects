import Controller.*;
import Exceptions.CtrlExcep;
import Model.BooleanExpressions.BoolExp;
import Model.Expressions.ArithmeticExpressions;
import Model.Expressions.ConstantExpressions;
import Model.Expressions.VariableExpressions;
import Model.FileHandeler.*;
import Model.HeapStatements.HeapWrite;
import Model.HeapStatements.NewStatement;
import Model.HeapStatements.HeapRead;
import Model.Statements.*;
import Model.Utils.*;
import Repository.*;
import Model.*;
import View.*;

public class Main {


    public static void main(String[] args) {


        Statement ex3 = new CompoundStatement(new AssignStatement("v", new ConstantExpressions(10)),
                new CompoundStatement(new NewStatement("a", new ConstantExpressions(22)),
                        new CompoundStatement(new ForkStatement(
                                new CompoundStatement(new HeapWrite("a", new ConstantExpressions(30)),
                                        new CompoundStatement(new AssignStatement("v", new ConstantExpressions(32)),
                                                new CompoundStatement(new PrintStatement(new VariableExpressions("v")), new PrintStatement(new HeapRead("a")))))),
                                new CompoundStatement(new PrintStatement(new VariableExpressions("v")), new PrintStatement(new HeapRead("a"))))));

        IMyStack<Statement> exeStack = new MyStack<>();
        IMyDictionary<String, Integer> dict = new MyDictionary<>();
        IMyList<Integer> list = new  MyList<>();
        IFileTable<Integer, FileData> fileTable = new FileTable<>();
        IMyHeap<Integer,Integer> heap = new MyHeap<>();

        exeStack.add(ex3);

        ProgramState state = new ProgramState(exeStack, dict, list, null, fileTable, heap, ForkID.getID());

        IRepository repo = new Repository();
        Controller ctrl = new Controller(repo);
        repo.addPrg(state);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",  ex3.toString(), ctrl));
        try {
            menu.show();
        }
        catch (CtrlExcep c){
            System.out.print(c.getMessage());
        }

    }

}
