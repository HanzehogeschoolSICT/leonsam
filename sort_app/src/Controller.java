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


    //override JavaFX initializer
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        g = canvas.getGraphicsContext2D();
        this.model = new Model();
        repaint();
    }

    //method for refreshing GUI
    public void repaint() {
        int[] data = model.getSortableObjects();
        g.setFill(Color.LIGHTBLUE);
        g.fillRect(0, 0, 400, 400);

        for(int i = 0; i < data.length; i++) {
            g.setFill(Color.DARKGRAY);
            g.fillRect(i * 20, 400 - data[i], 18, data[i]);
        }

    }

    //refreshing GUI
    @FXML
    public void reset () {
        this.initialize(null, null);
    }

    //get sort algorithm from choicebox and execute doStep method
    @FXML
    public void step() {
        algorithm = choicebox.getValue();
        doStep(algorithm);
        repaint();
    }

    //executes one step of given algorithm
    public boolean doStep(String algorithm) {
        boolean notDone = true;
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
        if (notDone == false) {JOptionPane.showMessageDialog(null, "Array fully sorted!", "Done", JOptionPane.INFORMATION_MESSAGE);}
        return notDone;
    }

    //executes steps of algorithm given specific interval
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

    //starting interval thread
    public void intervalThread () {
        thread = new Thread() {
            public void run() {
                boolean notDone = true;
                while (notDone) {
                    notDone = doStep(algorithm);
                    repaint();
                    try {
                        sleep(getInterval());
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    //stopping interval thread
    public void stopIntervalThread() {
        thread.interrupt();
    }

    //get interval
    public long getInterval() {
        return interval;
    }

}
