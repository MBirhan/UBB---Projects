package GUI;

import Model.FileHandling.FileData;
import Model.FileHandling.FileTable;
import Model.FileHandling.FileTableInterface;
import Model.ProgramState;
import Model.Statements.GenForkId;
import Model.Statements.StmtInterface;
import Model.Utils.*;
import Model.UtilsTables.BarrierTableView;
import Model.UtilsTables.FileTableView;
import Model.UtilsTables.HeapTableView;
import Model.UtilsTables.SymTableView;
import Repo.Repo;
import Repo.RepoInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class RunProgramController {

    //HEAP TABLE
    @FXML private TableView<HeapTableView> heapTable;
    @FXML private TableColumn<HeapTableView, Integer> heapAddressColumn;
    @FXML private TableColumn<HeapTableView, Integer> heapValueColumn;

    //FILE TABLE
    @FXML private TableView<FileTableView> fileTable;
    @FXML private TableColumn<FileTableView, Integer> fileTableIdColumn;
    @FXML private TableColumn<FileTableView, String> fileTableNameColumn;


    //SYM TABLE
    @FXML private TableView<SymTableView> symTable;
    @FXML private TableColumn<SymTableView, String> symTableNameColumn;
    @FXML private TableColumn<SymTableView, Integer> symTableValueColumn;

    //OUT
    @FXML private ListView<String> outList;

    //EXE STACK
    @FXML private ListView<String> exeStack;

    //PROGRAM STATE
    @FXML private ListView<String> prgStateIdentifiers;
    @FXML private Button oneStepBTN;
    @FXML private TextField noPrgStateTextField;


    @FXML private TableView<BarrierTableView> barrierTable;
    @FXML private TableColumn<BarrierTableView, Integer> barrierID;
    @FXML private TableColumn<BarrierTableView, Integer> barrierValue;
    @FXML private TableColumn<BarrierTableView, String> barrierList;

    private Controller ctrl;

    @FXML
    public void initialize(){

        StackInterface<StmtInterface> exeStack = new Stack<>();
        DictionaryInterface<String, Integer> symTable = new Dictionary<String, Integer>();
        ListInterface<Integer> outList = new MyList<>();
        FileTableInterface<Integer, FileData> fileTable = new FileTable<>();
        HeapInterface<Integer, Integer> heap = new Heap<>();
        BarrierTableInterface<Integer, MyPair> barrierTable = new BarrierTable<>();
        exeStack.add(SelectProgramController.statement);
        ProgramState programState = new ProgramState(exeStack, symTable, outList, fileTable, heap, GenForkId.getID(), barrierTable);


        RepoInterface repo = new Repo();
        repo.addPrg(programState);
        ctrl = new Controller(repo);

        this.barrierID.setCellValueFactory(new PropertyValueFactory<>("address"));
        this.barrierValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        this.barrierList.setCellValueFactory(new PropertyValueFactory<>("list"));
        this.heapAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        this.heapValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        this.fileTableIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.fileTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        this.symTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("varName"));
        this.symTableValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        setNoPrgState();
        setPrgStateIdentifiers();
        prgStateIdentifiers.getSelectionModel().select(0);
        setExeStack();
    }

    private void setBarrierTable(){

        ObservableList<BarrierTableView> list = FXCollections.observableArrayList();
        ProgramState programState = getCurrentProgramState();

        for(Integer key: programState.getBarrierTable().getAll()){

            String l = new String();

            for(Integer s : programState.getBarrierTable().get(key).getFirst())
                l += " "+s;

            list.add(new BarrierTableView(key, programState.getBarrierTable().get(key).getSecond(), l) );
        }


        barrierTable.setItems(list);
    }
    private void setNoPrgState(){
        Integer nr = ctrl.noPrgStates();
        noPrgStateTextField.setText(String.valueOf(nr));
    }

    private void setPrgStateIdentifiers(){
        prgStateIdentifiers.setItems(ctrl.getPrgStatesID());
    }

    ProgramState getCurrentProgramState(){
        int index = prgStateIdentifiers.getSelectionModel().getSelectedIndex();
        if(index == -1)
            index = 0;
        return ctrl.getPrgStateByIndex(index);
    }

    private void setExeStack(){

        ObservableList<String> list = FXCollections.observableArrayList();
        ProgramState programState = getCurrentProgramState();


        for(StmtInterface i : programState.getStack().getElements())
            list.add(""+i);

        exeStack.setItems(list);
    }

    private void setHeapTable(){

        ObservableList<HeapTableView> list = FXCollections.observableArrayList();
        ProgramState programState = getCurrentProgramState();
        for(Integer key: programState.getHeap().getKeys())
            list.add(new HeapTableView(key, programState.getHeap().getVal(key)));

        heapTable.setItems(list);
    }

    private void setFileTable(){

        ObservableList<FileTableView> list = FXCollections.observableArrayList();
        ProgramState programState = getCurrentProgramState();
        for(Integer key: programState.getFileTable().getElements())
            list.add(new FileTableView(key, programState.getFileTable().get(key).getFileName()));

        fileTable.setItems(list);
    }

    private void setSymTable(){

        ObservableList<SymTableView> list = FXCollections.observableArrayList();
        ProgramState programState = getCurrentProgramState();
        for(String key: programState.getSymTable().getElements())
            list.add(new SymTableView(key, programState.getSymTable().getValue(key)));

        symTable.setItems(list);
    }

    private void setOutList(){
        ObservableList<String> list = FXCollections.observableArrayList();
        ProgramState programState = getCurrentProgramState();

        for(Integer i: programState.getList().getElements())
            list.add(""+i);

        outList.setItems(list);
    }


    private void setAll(){
        setNoPrgState();
        setPrgStateIdentifiers();
        setExeStack();
        setHeapTable();
        setFileTable();
        setSymTable();
        setOutList();
        setBarrierTable();
    }



    public void executeOneStep(ActionEvent ae){

        try {
            if (ctrl.allStepGUI()) {
                setAll();
            } else {
                setNoPrgState();
                setPrgStateIdentifiers();
                Alert a = new Alert(Alert.AlertType.INFORMATION, " Programul a fost finalizat! ");
                a.show();
            }
        }
        catch (RuntimeException e){
            Node source = (Node) ae.getSource();
            Stage theStage = (Stage) source.getScene().getWindow();
            Alert a = new Alert(Alert.AlertType.ERROR, e.getMessage());
            a.show();
            theStage.close();
        }
    }

    public void mouseClickPrgStateIdentifiers(){

        if(ctrl.noPrgStates() > 0)
            setAll();
    }

}
