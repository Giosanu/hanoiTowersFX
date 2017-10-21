package draw;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    @FXML
    TextField towersFieldSC;

    @FXML
    TextField disksFieldSC;

    @FXML
    MenuButton StratChoose;

    @FXML
    MenuButton GRChoose;

    int currStrat = -1;
    int viewOption = -1;

    @FXML
    public void initialize() {

    }

//First Tab
    public void randomStratListener(ActionEvent actionEvent) {
        StratChoose.setText(((MenuItem)actionEvent.getSource()).getText());
        currStrat = 0;
    }

    public void bktStratListener(ActionEvent actionEvent) {
        StratChoose.setText(((MenuItem)actionEvent.getSource()).getText());
        currStrat = 1;
    }

    public void hcStratListener(ActionEvent actionEvent) {
        StratChoose.setText(((MenuItem)actionEvent.getSource()).getText());
        currStrat = 2;
    }

    public void aStratListener(ActionEvent actionEvent) {
        StratChoose.setText(((MenuItem)actionEvent.getSource()).getText());
        currStrat = 3;
    }

    public void probDomainListener(ActionEvent actionEvent) {
        GRChoose.setText(((MenuItem)actionEvent.getSource()).getText());
        viewOption = 0;
    }

    public void towersListener(ActionEvent actionEvent) {
        GRChoose.setText(((MenuItem)actionEvent.getSource()).getText());
        viewOption = 1;
    }

    public void runListenerSC(ActionEvent actionEvent) {
        try{
            int towers = Integer.parseInt(towersFieldSC.getText());
            int disks = Integer.parseInt(disksFieldSC.getText());
            if(viewOption != -1 && currStrat!=-1){
                System.out.println("no");
            }
        }
        catch (Exception e){
            System.out.println("DEAL WITH ERROR. ADD NOTIF.");
        }
    }


//Second Tab

    public void towersFieldListenerMC(ActionEvent actionEvent) {
    }

    public void disksFieldListenerMC(ActionEvent actionEvent) {
    }

    public void casesFieldListenerMC(ActionEvent actionEvent) {
    }

    public void runListenerMC(ActionEvent actionEvent) {
    }
}