package Model.FileHandeler;


import Exceptions.FileException;
import Model.*;
import Model.Expressions.ExpressionInterface;
import Model.Statements.Statement;
import Model.Utils.IMyDictionary;
import Model.Utils.IMyHeap;

import java.io.BufferedReader;
import java.io.IOException;


public class CloseRFile implements Statement {

    private ExpressionInterface exp;

    public CloseRFile(ExpressionInterface exp){
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) {

        IMyDictionary<String, Integer> dict = state.getSymTable();
        IFileTable<Integer, FileData> fileTable = state.getFileTable();
        IMyHeap<Integer, Integer> heap = state.getHeap();

        int id = exp.evaluate(dict, heap);

        boolean ok = false;
        for(Integer d: fileTable.getAllKeys())
            if(id == d)
                ok = true;

        if(!ok)
            throw new FileException("Can't close the file because don't exist in FileTable");

        FileData fileData = fileTable.get(id);
        BufferedReader bufferedReader = fileData.getReader();

        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new FileException("Can't close the file");
        }

        fileTable.delete(id);
        //dict.delete();

        return state;
    }

    @Override
    public String toString(){
        return "close("+exp+")";
    }


}
