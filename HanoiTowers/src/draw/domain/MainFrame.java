package draw.domain;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Objects;

public class MainFrame extends Application {


    StackPane layout = new StackPane();
    Scene sc = new javafx.scene.Scene(layout, 650, 924);
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) {
        myInit(stage);

    }

    private void myInit(final Stage stage) {
        double[] xn = new double[100];
        final int[] xnIndex = {0};
        double[] fxn = new double[100];
        final int[] fxnIndex = {0};
        Button draw = new Button("Draw");
        Button draw2 = new Button("Draw");
        Button clear = new Button("Clear");
        Button clear2 = new Button("Clear");
        Button swit = new Button("Interpolation");
        Button swit2 = new Button("Switch");
        Button save = new Button("Save");
        Button load = new Button("Load");
        Axes axes = new Axes(650, 800,
                -6, 6, 1,
                -8, 8, 1);
        final Plot[] plot = {new Plot("0",
                -8, 8, 0.1,
                axes)};
        plot[0].addEventHandler( MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    public void handle(final MouseEvent mouseEvent) {
                        xn[xnIndex[0]]=(mouseEvent.getX() - 325)/(650/12);
                        xnIndex[0]++;
                        System.out.println(xnIndex[0] + " " + xn[xnIndex[0]-1]);
                        fxn[fxnIndex[0]]=(mouseEvent.getY() - 400)/-(800/16);
                        fxnIndex[0]++;
                        System.out.println(fxnIndex[0] + " " + fxn[fxnIndex[0]-1]);
                        plot[0].getChildren().add(new Circle(mouseEvent.getX()+4,mouseEvent.getY(),4));
                    }
                });
        TextArea y = new TextArea();
        Pane panel = new Pane();
        Pane panel2 = new Pane();
        Pane panel3 = new Pane();
        Label x = new Label("Function:");
        Label NrSh = new Label("Shape nr:");
        Label sides = new Label("Sides:");
        TextArea NS = new TextArea();
        TextArea S = new TextArea();
        NS.setMaxSize(70, 20);
        NS.setCenterShape(true);
        S.setMaxSize(70, 20);
        S.setCenterShape(true);
        draw.setOnAction(e -> {
            if (!Objects.equals(y.getText(), "")) {
                plot[0] = new Plot(y.getText(),
                        -8, 8, 0.1,
                        axes);
                plot[0].addEventHandler( MouseEvent.MOUSE_PRESSED,
                        new EventHandler<MouseEvent>() {
                            public void handle(final MouseEvent mouseEvent) {
                                xn[xnIndex[0]]=(mouseEvent.getX() - 325)/(650/12);
                                xnIndex[0]++;
                                System.out.println(xnIndex[0] + " " + xn[xnIndex[0]-1]);
                                fxn[fxnIndex[0]]=(mouseEvent.getY() - 400)/-(800/16);
                                fxnIndex[0]++;
                                System.out.println(fxnIndex[0] + " " + fxn[fxnIndex[0]-1]);
                                plot[0].getChildren().add(new Circle(mouseEvent.getX()+4,mouseEvent.getY(),4));
                            }
                        });
                layout.getChildren().add(plot[0]);
                StackPane.setAlignment(plot[0], Pos.CENTER);
            }
        });

        clear.setOnAction(e -> {

            System.out.println(layout.getChildren().size());
            layout.getChildren().remove(3, layout.getChildren().size());
            System.out.println(layout.getChildren().size());
            plot[0] = new Plot("0",
                    -8, 8, 0.1,
                    axes);
            plot[0].addEventHandler( MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                        public void handle(final MouseEvent mouseEvent) {
                            xn[xnIndex[0]]=(mouseEvent.getX() - 325)/(650/12);
                            xnIndex[0]++;
                            System.out.println(xnIndex[0] + " " + xn[xnIndex[0]-1]);
                            fxn[fxnIndex[0]]=(mouseEvent.getY() - 400)/-(800/16);
                            fxnIndex[0]++;
                            System.out.println(fxnIndex[0] + " " + fxn[fxnIndex[0]-1]);
                            plot[0].getChildren().add(new Circle(mouseEvent.getX()+4,mouseEvent.getY(),4));
                        }
                    });
            layout.getChildren().add(plot[0]);
            StackPane.setAlignment(plot[0], Pos.CENTER);
        });

        clear2.setOnAction(e -> {

            System.out.println(layout.getChildren().size());
            layout.getChildren().remove(3, layout.getChildren().size());
            System.out.println(layout.getChildren().size());
            plot[0] = new Plot("0",
                    -8, 8, 0.1,
                    axes);
            plot[0].addEventHandler( MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                        public void handle(final MouseEvent mouseEvent) {
                            xn[xnIndex[0]]=(mouseEvent.getX() - 325)/(650/12);
                            xnIndex[0]++;
                            System.out.println(xnIndex[0] + " " + xn[xnIndex[0]-1]);
                            fxn[fxnIndex[0]]=(mouseEvent.getY() - 400)/-(800/16);
                            fxnIndex[0]++;
                            System.out.println(fxnIndex[0] + " " + fxn[fxnIndex[0]-1]);
                            plot[0].getChildren().add(new Circle(mouseEvent.getX()+4,mouseEvent.getY(),4));
                        }
                    });
            layout.getChildren().add(plot[0]);
            StackPane.setAlignment(plot[0], Pos.CENTER);
        });

        swit.setOnAction(e -> {
            try{
                plot[0] = new Plot(getInterpolation(xn,fxn,xnIndex[0]),
                        -8, 8, 0.1,
                        axes);
                plot[0].addEventHandler( MouseEvent.MOUSE_PRESSED,
                        new EventHandler<MouseEvent>() {
                            public void handle(final MouseEvent mouseEvent) {
                                xn[xnIndex[0]]=(mouseEvent.getX() - 325)/(650/12);
                                xnIndex[0]++;
                                System.out.println(xnIndex[0] + " " + xn[xnIndex[0]-1]);
                                fxn[fxnIndex[0]]=(mouseEvent.getY() - 400)/-(800/16);
                                fxnIndex[0]++;
                                System.out.println(fxnIndex[0] + " " + fxn[fxnIndex[0]-1]);
                                plot[0].getChildren().add(new Circle(mouseEvent.getX()+4,mouseEvent.getY(),4));
                            }
                        });
                layout.getChildren().add(plot[0]);
                StackPane.setAlignment(plot[0], Pos.CENTER);
                xnIndex[0] = 0;
                fxnIndex[0] = 0;
            }
            catch (ArithmeticException e2){
                System.out.println("Can't inerpolinate.");
                xnIndex[0] = 0;
                fxnIndex[0] = 0;
            }
        });
        swit2.setOnAction(e -> {
            panel.setVisible(true);
            panel2.setVisible(false);
        });
        save.setOnAction(e -> {
            panel.setVisible(true);
            panel2.setVisible(false);
        });
        load.setOnAction(e -> {
            panel.setVisible(true);
            panel2.setVisible(false);
        });


        y.setMaxSize(190, 20);
        y.setCenterShape(true);
        draw.setMaxSize(100, 50);
        layout.setStyle("-fx-background-color: rgb(255, 255, 255);");
        panel.setStyle("-fx-background-color: rgb(255,255,255);");
        panel2.setStyle("-fx-background-color: rgb(255,255,255);");
        panel.setMaxSize(650, 63);
        panel2.setMaxSize(650, 63);
        panel3.setMaxSize(650, 63);
        panel2.getChildren().addAll(swit2, NrSh, NS, sides, S, draw2, clear2);
        panel.getChildren().addAll(draw, x, y, clear, swit);
        load.setMinSize(80, 40);
        save.setMinSize(80, 40);
        panel3.getChildren().addAll(load, save);
        load.relocate(200, 13);
        save.relocate(370, 13);
        swit.relocate(340, 19);
        swit2.relocate(340, 19);
        draw.relocate(540, 19);
        draw2.relocate(540, 19);
        x.relocate(20, 24);
        sides.relocate(165, 24);
        NrSh.relocate(20, 24);
        y.relocate(90, 15);
        NS.relocate(90, 15);
        S.relocate(200, 15);
        clear.relocate(440, 19);
        clear2.relocate(440, 19);
        layout.getChildren().add(panel);
        layout.getChildren().add(panel2);
        layout.getChildren().add(panel3);
        layout.getChildren().add(plot[0]);
        StackPane.setAlignment(panel, Pos.TOP_CENTER);
        StackPane.setAlignment(panel2, Pos.TOP_CENTER);
        StackPane.setAlignment(panel3, Pos.BOTTOM_CENTER);
        panel3.setVisible(true);
        panel2.setVisible(false);
        StackPane.setAlignment(plot[0], Pos.CENTER);
        stage.setTitle("Draw Function");
        stage.setScene(sc);

        stage.show();
    }
    private String getInterpolation(double[] xn, double[] fxn, int lg){
        String equation = "";
        for (int i=0;i<lg;i++){
            equation += getElement(xn,fxn[i], i, lg);
        }
        equation += "0";
        System.out.println(equation);
        return equation;
    }

    private String getElement(double[] xn, double fxn, int avoidIndex, int lg){
        String element = fxn + "*";
        for (int i=0; i<lg;i++){
            if(i!=avoidIndex) {
                element += "(x- " + xn[i] + " )/( " + xn[avoidIndex] + " - " + xn[i] + " )*";
            }
        }
        element += "1+";
        return element;
    }


}