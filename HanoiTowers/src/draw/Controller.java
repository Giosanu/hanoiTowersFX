package draw;

import items.ContextManager;
import items.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import static java.lang.Math.sqrt;

public class Controller {

    @FXML
    TextField towersFieldSC;

    @FXML
    TextField disksFieldSC;

    @FXML
    MenuButton StratChoose;

    @FXML
    MenuButton GRChoose;

    @FXML
    FlowPane canvasFlowPane;

    @FXML
    Button nextMoveButton;

    int currStrat = -1;
    int viewOption = -1;
    int towers;
    int disks;

    ArrayList<State> sol = new ArrayList<>();
    ArrayList<Canvas> towerCanvases = new ArrayList<>();
    int z;
    double w;
    double h;
    int currStep = 0;

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
        nextMoveButton.setDisable(false);
        canvasFlowPane.getChildren().clear();
        towers = Integer.parseInt(towersFieldSC.getText());
        disks = Integer.parseInt(disksFieldSC.getText());
        if(viewOption != -1 && currStrat!=-1){
            ContextManager contextManager = new ContextManager();
            sol = contextManager.getSolution(towers,disks,currStrat);
            if (sol != null)
                DrawSolution(viewOption);
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Time exceeded erorr");
                alert.setHeaderText("Finding the solution exceeded 20 sec!");
                alert.setContentText("Ooops, the solution you're looking for with the specified strategy took too long! Try again!");

                alert.showAndWait();
            }

        }
    }

    private void DrawSolution(int option) {
        if (option==1){
            DrawTowers();
            return;
        }
        DrawDomain();
    }

    private void DrawTowers() {
        z = ((int) sqrt(towers));
        h = canvasFlowPane.getHeight()/(towers/z)-2;
        if(!(Math.pow(sqrt(towers),2) == Math.pow(z,2))){
            z++;
            h = canvasFlowPane.getHeight()/(towers/z+1)-2;
        }
        w = canvasFlowPane.getWidth()/z-2;
        towerCanvases = new ArrayList<>();
        for (int i=0; i<towers;i++){
            towerCanvases.add(new Canvas(w,h));
            DrawOnCanvas(towerCanvases.get(i),w,h);
            canvasFlowPane.getChildren().add(towerCanvases.get(i));
        }
        DrawTowersSolution(w,h);
    }

    private void DrawTowersSolution(double w, double h) {
        currStep = 0;
        int disksPerTower;
        double base = 3.25*w/5;
        State state = sol.get(0);
        for ( Canvas canvas : towerCanvases) {
            canvas.getGraphicsContext2D().setFill(Color.CORNSILK);
            canvas.getGraphicsContext2D().fillRect(0,0,w,h);
            DrawOnCanvas(canvas,w,h);
        }
        for (Canvas canvas : towerCanvases) {
            disksPerTower = 0;
            for (int i = 0; i< state.DiskPlacement.length ; i++){
                if (towerCanvases.indexOf(canvas) == state.DiskPlacement[i]){
                    disksPerTower++;
                    canvas.getGraphicsContext2D().setFill(Color.AQUA);
                    canvas.getGraphicsContext2D().fillRoundRect( w/5 + (base / 20)*(i+1),
                            h/20 *(19 - disksPerTower),
                            w - 2*(w/5 + (base / 20)*(i+1)) + base/22,
                            h - h/20*(19 - disksPerTower),
                            10,10);
                    canvas.getGraphicsContext2D().setFill(Color.SANDYBROWN);
                    canvas.getGraphicsContext2D().fillRoundRect(w/5, 19*h/20, 3.25*w/5, h,10,10);
                }
            }
        }
        currStep++;
    }

    public void nextMoveListener(ActionEvent actionEvent) {
        if (currStep == sol.size()-1){
            ((Button)(actionEvent.getSource())).setDisable(true);
        }
        int disksPerTower;
        double base = 3.25*w/5;
        for ( Canvas canvas : towerCanvases) {
            canvas.getGraphicsContext2D().setFill(Color.CORNSILK);
            canvas.getGraphicsContext2D().fillRect(0,0,w,h);
            DrawOnCanvas(canvas,w,h);
        }
        for (Canvas canvas : towerCanvases) {
            disksPerTower = 0;
            int len = sol.get(currStep).DiskPlacement.length;
            for (int i = len-1; i > -1 ; i--){
                if (towerCanvases.indexOf(canvas) == sol.get(currStep).DiskPlacement[i]){
                    disksPerTower++;
                    canvas.getGraphicsContext2D().setFill(Color.AQUA);
                    canvas.getGraphicsContext2D().fillRoundRect( w/5 + (base / 15)*(len-i),
                            h/20 *(19 - disksPerTower),
                            w - 2*(w/5 + (base / 15)*(len-i)) + base/17,
                            h - h/20*(19 - disksPerTower),
                            10,10);
                    canvas.getGraphicsContext2D().setFill(Color.SANDYBROWN);
                    canvas.getGraphicsContext2D().fillRoundRect(w/5, 19*h/20, 3.25*w/5, h,10,10);
                }
            }
        }
        currStep++;
    }

    private void DrawOnCanvas(Canvas canvas, double w, double h) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.CORNSILK);
        gc.fillRoundRect(0, 0, w, h,10,10);
        gc.setFill(Color.SANDYBROWN);
        gc.fillRoundRect(w/2, h/5, w/23, h,10,10);
        gc.fillRoundRect(w/5, 19*h/20, 3.25*w/5, h,10,10);
    }


    private void DrawDomain() {
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