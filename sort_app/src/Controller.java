import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
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


        repaint();


    }

    public void repaint() {
        int[] data = model.getSortableObjects();
        g.setFill(Color.LIGHTBLUE);
        g.fillRect(0, 0, 400, 400);
        g.setFill(Color.DARKGREY);

        for(int i = 0; i < data.length; i++) {
            g.fillRect(i*20, 400-data[i], 18, data[i]);
        }

    }


    @FXML
    public void reset () {
        this.initialize(null, null);
    }

    @FXML
    public void step() {
        boolean notDone;
        notDone = model.bubbleStep();
        if (notDone == false) {
            JOptionPane.showMessageDialog(null, "Shit is sorted", "Done", JOptionPane.INFORMATION_MESSAGE);
        }
        repaint();
    }




}
