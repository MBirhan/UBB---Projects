package Repository;

import Model.ProgramState;

import java.util.List;

public interface IRepository {

    void addPrg(ProgramState prg);
    ProgramState getCurrentPrg();
    List<ProgramState> getPrgList();
    void logPrgStateExec(ProgramState prg);
    void setPrgList(List<ProgramState> list);


}
