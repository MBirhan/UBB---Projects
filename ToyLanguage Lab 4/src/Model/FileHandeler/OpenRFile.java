package Model.FileHandeler;

import Exceptions.FileException;
import Model.*;
import Model.Statements.Statement;
import Model.Utils.IMyDictionary;
import Model.Utils.IMyHeap;

import java.io.*;
import java.io.FileReader;


public class OpenRFile implements Statement {

    private String fileName;
    private String varName;

    public OpenRFile(String varName, String fileName) {
        this.fileName = fileName;
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState state) {

        IFileTable<Integer, FileData> fileTable = state.getFileTable();
        IMyDictionary<String, Integer> dict = state.getSymTable();
        BufferedReader reader;

        for(FileData d: fileTable.getAllValues()) {
            if (d.getFileName().equals(fileName))
                throw new FileException("The file is already opened in FileTable");
        }

        try{
            reader = new BufferedReader(new FileReader(fileName));
        } catch (IOException e) {
            throw new FileException("File not found");
        }

        int id = GeneratorInteger.gen_ID();

        fileTable.add(id, new FileData(fileName, reader));
        dict.setValue(varName, id);

        return state;
    }

    @Override
    public String toString(){
        return "open("+varName+", "+fileName+')';
    }

}