import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javax.swing.*;
import java.net.URL;
import java.text.ParseException;
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

        switch (choicebox.getValue()) {

            case "bubblesort":
                boolean notDone;
                notDone = model.bubbleStep();
                if (notDone == false) {
                    JOptionPane.showMessageDialog(null, "Shit is sorted", "Done", JOptionPane.INFORMATION_MESSAGE);
                }
                repaint();
                break;

            case "insertionsort":
                break;

            case "quicksort":
                break;

        }

    }

    @FXML
    public void startWithInterval() {
        this.interval = 100;
        String tf = sizeInterval.getText();
        if (!tf.equals("")) {
            try {
                this.interval = Long.parseLong(tf);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "You did not fill in a number dumbass", "Done", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }


        thread = new Thread() {
          public void run() {
              boolean notDone = true;

              while (notDone) {

                  switch (choicebox.getValue()) {

                      case "bubblesort":
                          notDone = model.bubbleStep();
                          break;

                      case "insertionsort":
                          break;

                      case "quicksort":
                          break;

                  }


                  repaint();
                  try {
                      sleep(getInterval());
                  } catch (InterruptedException e) {
                      return;
                  }
              }

              JOptionPane.showMessageDialog(null, "Shit is sorted", "Done", JOptionPane.INFORMATION_MESSAGE);

          }
        };

        thread.start();



    }

    public void stopIntervalThread() {
        thread.interrupt();
    }

    public long getInterval() {
        return interval;
    }

}
