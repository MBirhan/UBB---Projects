import Controller.*;
import Model.BooleanExpressions.BoolExp;
import Model.Expressions.ArithmeticExpressions;
import Model.Expressions.ConstantExpressions;
import Model.Expressions.VariableExpressions;
import Model.FileHandeler.*;
import Model.HeapStatements.NewStatement;
import Model.HeapStatements.HeapRead;
import Model.Statements.*;
import Model.Utils.*;
import Repository.*;
import Model.*;
import View.*;

public class Main {


    public static void main(String[] args) {


        //v=6; (while (v-4) print(v);v=v-1);print(v)
        Statement a1 =  new AssignStatement("v", new ConstantExpressions(6));
        Statement a2 = new WhileStatement(new ArithmeticExpressions("-",new VariableExpressions("v"), new ConstantExpressions(4) ),
                new CompoundStatement(new PrintStatement(new VariableExpressions("v")), new AssignStatement("v", new ArithmeticExpressions("-",new VariableExpressions("v"), new ConstantExpressions(1) ))));

        Statement a3 = new PrintStatement(new VariableExpressions("v"));

        Statement exe1 = new CompoundStatement(new CompoundStatement(a1,a2), a3);


        Statement exe2 = new CompoundStatement(new AssignStatement("a", new ConstantExpressions(-2)), new WhileStatement(new BoolExp("<", new VariableExpressions("a"), new ConstantExpressions(0)), new CompoundStatement(new PrintStatement(new VariableExpressions("a")), new AssignStatement("a", new ArithmeticExpressions("+", new VariableExpressions("a"), new ConstantExpressions(1))))));

        IMyStack<Statement> exeStack = new MyStack<>();
        IMyDictionary<String, Integer> dict = new MyDictionary<>();
        IMyList<Integer> list = new  MyList<>();
        IFileTable<Integer, FileData> fileTable = new FileTable<>();
        IMyHeap<Integer,Integer> heap = new MyHeap<>();

        exeStack.add(exe2);

        ProgramState state = new ProgramState(exeStack, dict, list, null, fileTable, heap);

        IRepository repo = new Repository();
        Controller ctrl = new Controller(repo);
        repo.addPrg(state);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",  exe2.toString(), ctrl));
        menu.show();

    }

}
