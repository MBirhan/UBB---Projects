import Controller.*;
import Model.Expressions.ConstantExpressions;
import Model.Expressions.VariableExpressions;
import Model.FileHandeler.*;
import Model.Statements.CompareStatement;
import Model.Statements.IfStatement;
import Model.Statements.PrintStatement;
import Model.Statements.Statement;
import Model.Utils.*;
import Repository.*;
import Model.*;
import View.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {


        Statement s1 = new OpenRFile("var_f", "text.txt");
        Statement s2 = new FileReader(new VariableExpressions("var_f"), "var_c");
        Statement thenS1 = new FileReader(new VariableExpressions("var_f"), "var_c");
        Statement thenS2 = new PrintStatement(new VariableExpressions("var_c"));
        Statement thenS = new CompareStatement(thenS1, thenS2);
        Statement elseS = new PrintStatement(new ConstantExpressions(0));

        Statement s3 = new IfStatement(new VariableExpressions("var_c"), thenS, elseS);
        Statement s4 = new CloseRFile(new VariableExpressions("var_f"));

        Statement s5 = new CompareStatement(s1,s2);
        Statement s6 = new CompareStatement(s3, s4);
        Statement s7 = new CompareStatement(s5, s6);
//        Statement ifStmnt = new IfStatement(new VariableExpressions("var_c"), new CompareStatement(new FileReader(new VariableExpressions("var_f"), "var_c"), new PrintStatement(new VariableExpressions("var_c"))), new PrintStatement(new ConstantExpressions(0)));
//        Statement opStmnt = new OpenRFile("test.in", "var_f");
//        Statement readStmnt = new FileReader(new VariableExpressions("var_f"), "var_c");
//        Statement comp1 = new CompareStatement(readStmnt, new PrintStatement(new VariableExpressions("var_c")));
//        Statement closeFile = new CloseRFile(new VariableExpressions("var_f"));
//        Statement ex1 = new CompareStatement(opStmnt, new CompareStatement(readStmnt, new CompareStatement(new PrintStatement(new VariableExpressions("var_c")), new CompareStatement(ifStmnt, closeFile))));


        IMyStack<Statement> exeStack = new MyStack<>();
        IMyDictionary<String, Integer> dict = new MyDictionary<>();
        IMyList<Integer> list = new  MyList<>();
        IFileTable<Integer, FileData> fileTable = new FileTable<>();
        exeStack.add(s7);

        ProgramState state = new ProgramState(exeStack, dict, list, null, fileTable);

        IRepository repo = new Repository();
        Controller ctrl = new Controller(repo);
        repo.addPrg(state);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",  s7.toString(), ctrl));
        menu.show();

    }

}
