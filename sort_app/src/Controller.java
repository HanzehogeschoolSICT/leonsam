import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * Created by samikroon on 3/15/17.
 */
public class Controller implements Initializable{

    @FXML
    private Canvas canvas;
    public GraphicsContext g;
    private Model model;

    private static int[] xValues = {0, 20, 40, 60, 80, 100, 120, 140, 160, 180, 200, 220, 240, 260, 280, 300,
                                    320, 340, 360, 380, 400};



    private int widthObjects = 18;



    //fillRect(double x, double y, double w, double h

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        g = canvas.getGraphicsContext2D();

        this.model = new Model();


        g.setFill(Color.LIGHTBLUE);
        g.fillRect(0, 0, 400, 400);
        g.save();
        g.setFill(Color.DARKGREY);
        g.fillRect(0, 380, 18, 20);
        g.fillRect(20, 360, 18, 40);
        g.fillRect(40, 340, 18, 60);
        g.fillRect(60, 320, 18, 80);
        g.fillRect(80, 300, 18, 100);
        g.fillRect(100, 280, 18, 120);


    }

    @FXML
    public void reset () {
        g.fillRect(120, 260, 18, 140);
    }

    @FXML
    public void step() {
        model.bubbleStep();
        System.out.println("\n");
    }




}
