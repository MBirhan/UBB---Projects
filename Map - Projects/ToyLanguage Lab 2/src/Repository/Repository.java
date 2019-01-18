package Repository;

import Model.ProgramState;
import java.util.ArrayList;
import java.util.List;

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
    public String toString(){
        StringBuilder bld = new StringBuilder();

        for (ProgramState state : listPrg){
            bld.append(state).append("\n");
        }
        return bld.toString();
    }
}