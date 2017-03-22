import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML private Canvas canvas;
    @FXML private ListView<String> listView;
    public GraphicsContext g;
    private Model model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new Model();
        model.dictonaryBuilder();
        g = canvas.getGraphicsContext2D();
        repaint();
    }

    public void repaint() {
        g.setFill(Color.LIGHTBLUE);
        g.fillRect(0, 0, 500, 500);

    }

}
