package Model.FileHandeler;

import Exceptions.FileException;
import Model.*;
import Model.Expressions.ExpressionInterface;
import Model.Statements.Statement;
import Model.Utils.IMyDictionary;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader implements Statement {

    ExpressionInterface exp;
    String varName;

    public FileReader(ExpressionInterface exp, String varName) {
        this.exp = exp;
        this.varName = varName;
    }


    @Override
    public ProgramState execute(ProgramState state) {

        IMyDictionary<String, Integer> dict = state.getSymTable();
        IFileTable<Integer, FileData> fileTable = state.getFileTable();
        int id = exp.evaluate(dict);


        if(!fileTable.contains(id))
            throw new FileException("This ID is not in FileTable");

        BufferedReader reader = fileTable.get(id).getReader();

        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new FileException("Can't read line");
        }

        int value = 0;
        if(line != null)
            value = Integer.parseInt(line);

        dict.setValue(varName, value);

        return state;
    }

    @Override
    public String toString(){
        return "read("+varName + ", " + exp+")";
    }

}