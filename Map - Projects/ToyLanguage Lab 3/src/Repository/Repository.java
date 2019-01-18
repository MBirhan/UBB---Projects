package Repository;

import Exceptions.FileException;
import Model.FileHandeler.FileData;
import Model.FileHandeler.IFileTable;
import Model.ProgramState;
import java.util.ArrayList;
import java.util.List;
import Model.*;
import Model.Statements.Statement;
import Model.Utils.IMyDictionary;
import Model.Utils.IMyList;
import Model.Utils.IMyStack;

import java.io.*;

public class Repository implements IRepository {

    private List<ProgramState> listPrg = new ArrayList<>();
    private int current = 0;


    @Override
    public void addPrg(ProgramState prg) {

        listPrg.add(prg);
    }


    @Override
    public ProgramState getCurrentPrg() {

        return listPrg.get(current);
    }

    @Override
    public void setPrgList(List<ProgramState> list) {
        this.listPrg = list;
    }

    @Override
    public void logPrgStateExec() {
        try(PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter("text2.txt", true)))){
            ProgramState state = this.getCurrentPrg();
            IMyStack<Statement> stack = state.getStack();
            IMyDictionary<String, Integer>dict = state.getSymTable();
            IMyList<Integer> out = state.getList();
            IFileTable<Integer, FileData> fileTable = state.getFileTable();

            logFile.println("ExeStack:");
            for(Statement S: stack.getElements())
                logFile.println(""+ S);


            logFile.println("\nSymTable:\n");
            for(String key: dict.getElements())
                logFile.println(key + "-->" + dict.getValue(key) +'\n');

            logFile.println("\nOut:\n");
            for(Integer i: out.getElements())
                logFile.println(""+i+"\n" );


            logFile.println("\nFileTable:\n");
            for(Integer i: fileTable.getAllKeys())
                logFile.println("" + i + fileTable.get(i).getFileName() + "\n");


            logFile.println("\n");

        } catch (FileNotFoundException e) {
            throw new FileException("File not found in PrintWriter");
        } catch (IOException e) {
            throw new FileException("IO exception at PrintWriter");
        }
    }



//    @Override
//    public String toString(){
//        StringBuilder bld = new StringBuilder();
//
//        for (ProgramState state : listPrg){
//            bld.append(state).append("\n");
//        }
//        return bld.toString();
//    }
}