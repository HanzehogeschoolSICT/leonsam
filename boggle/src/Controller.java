import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML private Canvas canvas;
    @FXML private ListView<String> listView;
    @FXML private TextField sizeBoard;
    public GraphicsContext g;
    private Model model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new Model();
        g = canvas.getGraphicsContext2D();
        repaint();
        model.solver();
    }

    public void repaint() {
        g.setFill(Color.LIGHTBLUE);
        g.fillRect(0, 0, 500, 500);

    }

    @FXML
    public void setBoardSize() {
        int boardSize = 4;
        String tf = sizeBoard.getText();
        if (!tf.equals("")) {
            try {
                boardSize = Integer.parseInt(tf);
                model.setSize(boardSize);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "You did not fill in a number.", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
    }

}
