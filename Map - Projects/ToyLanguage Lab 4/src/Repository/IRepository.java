package Repository;

import Model.ProgramState;

import java.util.List;

public interface IRepository {

    void addPrg(ProgramState prg);
    ProgramState getCurrentPrg();
    void logPrgStateExec();
    void setPrgList(List<ProgramState> list);


}
