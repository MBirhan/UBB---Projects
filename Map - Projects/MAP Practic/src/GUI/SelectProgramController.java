package GUI;

import Model.Expression.ArithmExpression;
import Model.Expression.ConstExpression;
import Model.Expression.VarExpression;
import Model.HeapStmt.HeapAlloc;
import Model.HeapStmt.ReadHeap;
import Model.HeapStmt.WriteInHeap;
import Model.Statements.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SelectProgramController {

    @FXML
    private ListView<String> listView;

    @FXML
    private Button runProgramBTN;

    public static StmtInterface statement;
    private List<StmtInterface> StmtList = new ArrayList<StmtInterface>();


    @FXML
    public void initialize() {

        StmtInterface ex1 = new CompStmt(
                new CompStmt(
                        new CompStmt(
                                new CompStmt(new HeapAlloc("v1", new ConstExpression(2)),new HeapAlloc("v2", new ConstExpression(3))),
                                new CompStmt(new HeapAlloc("v3", new ConstExpression(4)),new NewBarrier("cnt", new ReadHeap("v2")))),
                                new CompStmt(new ForkStmt(new
                                        CompStmt(new CompStmt(new Await("cnt"),new WriteInHeap("v1", new ArithmExpression("*", new ReadHeap("v1"), new ConstExpression(10)))),new PrintStmt(new ReadHeap("v1")))),
                                        new ForkStmt(new CompStmt(new Await("cnt"), new CompStmt(new CompStmt(new WriteInHeap("v2", new ArithmExpression("*", new ReadHeap("v2"), new ConstExpression(10))),new WriteInHeap("v2", new ArithmExpression("*", new ReadHeap("v2"), new ConstExpression(10)))), new PrintStmt(new ReadHeap("v2"))))))),
                new CompStmt(new Await("cnt"), new PrintStmt(new ReadHeap("v3"))));




        StmtInterface ex2 = new CompStmt(new AssignStmt("a", new ConstExpression(1)),
                new CompStmt(new AssignStmt("b", new ConstExpression(2)),
                        new CompStmt(new AssignStmt("c", new ConstExpression(5)),
                                new CompStmt(new SwitchStmt(new ArithmExpression("*", new VarExpression("a"), new ConstExpression(10)),
                                        new ArithmExpression("*", new VarExpression("b"), new VarExpression("c")), new CompStmt(new PrintStmt(new VarExpression("a")), new PrintStmt(new VarExpression("b"))),
                                        new ConstExpression(10), new CompStmt(new PrintStmt(new ConstExpression(100)), new PrintStmt(new ConstExpression(300))),
                                        new PrintStmt(new ConstExpression(300))),
                                        new PrintStmt(new ConstExpression(300))))));

        StmtList.add(ex1);
        StmtList.add(ex2);

        ObservableList<String> list = FXCollections.observableArrayList();
        for(StmtInterface i : StmtList)
            list.add(""+i);

        listView.setItems(list);

        listView.getSelectionModel().selectIndices(0);
    }

    @FXML
    public void buttonRun() throws IOException {
        statement = StmtList.get(listView.getSelectionModel().getSelectedIndex());

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("runProgram.fxml"));
        stage.setTitle("Running Program");
        stage.setScene(new Scene(root, 800, 600));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

}