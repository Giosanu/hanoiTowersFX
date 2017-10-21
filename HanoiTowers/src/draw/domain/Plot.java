package draw.domain;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


class Plot extends BorderPane {
    public Plot(String f, double xMin, double xMax, double xInc, Axes axes) {

        setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Path path = new Path();
        path.setStroke(Color.BLACK.deriveColor(0, 1, 1, 0.6));
        path.setStrokeWidth(2);

        path.setClip(new Rectangle(0, 0,axes.getPrefWidth(),axes.getPrefHeight()));

        Expression e = new ExpressionBuilder(f)
                .variables("x")
                .build()
                .setVariable("x",xMin);

        double x = xMin;
        double y = e.evaluate();
        path.getElements().add(new MoveTo(mapX(x, axes), mapY(y, axes)));

        x += xInc;
        while (x < xMax) {
            y = e.evaluate();

            path.getElements().add(new LineTo(mapX(x, axes), mapY(y, axes)));


            e.setVariable("x",x);
            x += xInc;
        }
        
        setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        setPrefSize(axes.getPrefWidth(), axes.getPrefHeight());
        setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        getChildren().setAll(axes, path);
    }

    private double mapX(double x, Axes axes) {
        double tx = axes.getPrefWidth() / 2;
        double sx = axes.getPrefWidth() /(axes.getXAxis().getUpperBound() - axes.getXAxis().getLowerBound());

        return x * sx + tx;
    }

    private double mapY(double y, Axes axes) {
        double ty = axes.getPrefHeight() / 2;
        double sy = axes.getPrefHeight() / (axes.getYAxis().getUpperBound() - axes.getYAxis().getLowerBound());

        return -y * sy + ty;
    }
}