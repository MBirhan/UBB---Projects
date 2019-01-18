import Controller.*;
import Model.Expressions.ArithmeticExpressions;
import Model.Expressions.ConstantExpressions;
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


//        Statement s1 = new OpenRFile("var_f", "text.txt");
//        Statement s2 = new FileReader(new VariableExpressions("var_f"), "var_c");
//        Statement thenS1 = new FileReader(new VariableExpressions("var_f"), "var_c");
//        Statement thenS2 = new PrintStatement(new VariableExpressions("var_c"));
//        Statement thenS = new CompoundStatement(thenS1, thenS2);
//        Statement elseS = new PrintStatement(new ConstantExpressions(0));
//
//        Statement s3 = new IfStatement(new VariableExpressions("var_c"), thenS, elseS);
//        Statement s4 = new CloseRFile(new VariableExpressions("var_f"));
//
//        Statement s5 = new CompoundStatement(s1,s2);
//        Statement s6 = new CompoundStatement(s3, s4);
//        Statement exe1 = new CompoundStatement(s5, s6);




        //v=10;new(v,20);new(a,22);print(v)
//        Statement z = new AssignStatement("v", new ConstantExpressions(10));
//        Statement z1 =  new NewStatement("v", new ConstantExpressions(20));
//        Statement z2 = new NewStatement("a", new ConstantExpressions(22));
//        Statement z3 = new PrintStatement(new VariableExpressions("v"));
//
//        Statement a1 = new CompoundStatement(z,z1);
//        Statement a2 = new CompoundStatement(z2,z3);
//
//        Statement exe1 = new CompoundStatement(a1,a2);


        //v=10;new(v,20);new(a,22);print(100+rH(v));print(100+rH(a))
        Statement y =  new NewStatement("v", new ConstantExpressions(20));
        Statement y1 = new NewStatement("a", new ConstantExpressions(22));
        Statement y2 = new PrintStatement(new ArithmeticExpressions("+",new ConstantExpressions(100),new HeapRead("v")));
        Statement y3 = new PrintStatement(new ArithmeticExpressions("+",new ConstantExpressions(100),new HeapRead("a")));

        Statement b1 = new CompoundStatement(y, y1);
        Statement b2 = new CompoundStatement(y2, y3);

        Statement exe1 = new CompoundStatement(b1,b2);



        IMyStack<Statement> exeStack = new MyStack<>();
        IMyDictionary<String, Integer> dict = new MyDictionary<>();
        IMyList<Integer> list = new  MyList<>();
        IFileTable<Integer, FileData> fileTable = new FileTable<>();
        IMyHeap<Integer,Integer> heap = new MyHeap<>();

        exeStack.add(exe1);

        ProgramState state = new ProgramState(exeStack, dict, list, null, fileTable, heap);

        IRepository repo = new Repository();
        Controller ctrl = new Controller(repo);
        repo.addPrg(state);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",  exe1.toString(), ctrl));
        menu.show();

    }

}
