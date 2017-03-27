import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by samikroon on 3/15/17.
 */
public class Controller extends Thread implements Initializable {

    @FXML private Canvas canvas;
    @FXML private ChoiceBox<String> choicebox;
    @FXML private TextField sizeInterval;
    public GraphicsContext g;
    private Model model;
    private long interval;
    private Thread thread;
    private String algorithm;
    public static volatile boolean killFlag = false;


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

        for(int i = 0; i < data.length; i++) {
            g.setFill(Color.DARKGRAY);
            g.fillRect(i * 20, 400 - data[i], 18, data[i]);
        }

    }

    @FXML
    public void reset () {
        this.initialize(null, null);
    }

    @FXML
    public void step() {
        switch (choicebox.getValue()) {
            case "bubblesort":
                boolean notDone;
                notDone = model.bubbleStep();
                if (notDone == false) {JOptionPane.showMessageDialog(null, "Array fully sorted!", "Done", JOptionPane.INFORMATION_MESSAGE);}
                repaint();
                break;
            case "insertionsort":
                notDone = model.insertionStep();
                if (notDone == false){JOptionPane.showMessageDialog(null, "Array fully sorted!", "Done", JOptionPane.INFORMATION_MESSAGE);}
                repaint();
                break;
            case "quicksort":
                notDone = model.quickStep();
                if (notDone == false){JOptionPane.showMessageDialog(null, "Array fully sorted!", "Done", JOptionPane.INFORMATION_MESSAGE);}
                repaint();
                break;
        }
    }

    @FXML
    public void startWithInterval() {
        this.interval = 100;
        this.algorithm = choicebox.getValue();
        String tf = sizeInterval.getText();
        if (!tf.equals("")) {
            try {
                this.interval = Long.parseLong(tf);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "You did not fill in a number.", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        intervalThread();
    }

    public void intervalThread () {
        thread = new Thread() {
            public void run() {
                boolean notDone = true;
                while (notDone) {
                    switch (algorithm) {
                        case "bubblesort":
                            notDone = model.bubbleStep();
                            break;
                        case "insertionsort":
                            notDone = model.insertionStep();
                            break;
                        case "quicksort":
                            notDone = model.quickStep();
                            break;
                    }
                    repaint();
                    try {
                        sleep(getInterval());
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Array fully sorted!", "Done", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        thread.setDaemon(true);
        thread.start();
    }


    public void stopIntervalThread() {
        thread.interrupt();
    }


    public long getInterval() {
        return interval;
    }

}
