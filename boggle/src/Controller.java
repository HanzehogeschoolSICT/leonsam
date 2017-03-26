import java.util.Observable;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.swing.*;
import java.net.URL;
import java.util.Observer;
import java.util.ResourceBundle;

public class Controller implements Initializable, Observer{

    @FXML private Canvas canvas;
    @FXML private ListView<String> listView;
    @FXML private TextField sizeBoard;
    public GraphicsContext g;

    private Model model;
    protected ListProperty<String> listProperty = new SimpleListProperty<>();
    Image image = new Image("dice.png");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new Model(this);
        listView.itemsProperty().bind(listProperty);
        g = canvas.getGraphicsContext2D();
        repaint();
    }

    public void repaint() {
        String[][] currPlayBoard = model.getPlayBoard();
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, 500, 500);
        g.setFill(Color.BLACK);
        for(int i = 0; i < model.getSize(); i++){
            for(int j = 0; j < model.getSize(); j++){
                g.drawImage(this.image, 500/model.getSize()*i,500/model.getSize()*j, (500/model.getSize()),(500/model.getSize()));
                g.setFont(Font.font(200/model.getSize()));
                g.fillText(currPlayBoard[i][j],500/model.getSize()*i+500/model.getSize()/2-10,500/model.getSize()*j+500/model.getSize()/2+10);
            }
        }
    }

    @FXML
    public void solve() {
        model.solver();

    }

    public void update(Observable obs, Object x) {
        listProperty.set(FXCollections.observableArrayList(model.getResults()));
    }



    @FXML
    public void setBoardSize() {
        int boardSize = 4;
        String tf = sizeBoard.getText();
        if (!tf.equals("")) {
            try {
                boardSize = Integer.parseInt(tf);
                model.setSize(boardSize);
                repaint();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "You did not fill in a number.", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
    }

}
