import java.util.ArrayList;
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
import javafx.scene.input.MouseEvent;
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
    Image image = new Image("dice.png");
    Image image2 = new Image("dice_2.png");

    private Model model;
    protected ListProperty<String> listProperty = new SimpleListProperty<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new Model(this,4);
        listView.itemsProperty().bind(listProperty);
        g = canvas.getGraphicsContext2D();
        repaint(null);
    }

    public void repaint(boolean[][] locationArray) {

        String[][] currPlayBoard = model.getPlayBoard();
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, 500, 500);
        g.setFill(Color.BLACK);
        g.setFont(Font.font(200/model.getSize()));
        for(int i = 0; i < model.getSize(); i++){
            for(int j = 0; j < model.getSize(); j++){
                if(locationArray!=null) {
                    if (locationArray[i][j]) {
                        g.drawImage(this.image2, 500 / model.getSize() * i, 500 / model.getSize() * j, (500 / model.getSize()), (500 / model.getSize()));
                        g.fillText(currPlayBoard[i][j], 500 / model.getSize() * i + 500 / model.getSize() / 2 - 10, 500 / model.getSize() * j + 500 / model.getSize() / 2 + 10);
                    } else {
                        g.drawImage(this.image, 500 / model.getSize() * i, 500 / model.getSize() * j, (500 / model.getSize()), (500 / model.getSize()));
                        g.fillText(currPlayBoard[i][j], 500 / model.getSize() * i + 500 / model.getSize() / 2 - 10, 500 / model.getSize() * j + 500 / model.getSize() / 2 + 10);
                    }
                }else{
                    g.drawImage(this.image, 500 / model.getSize() * i, 500 / model.getSize() * j, (500 / model.getSize()), (500 / model.getSize()));
                    g.fillText(currPlayBoard[i][j], 500 / model.getSize() * i + 500 / model.getSize() / 2 - 10, 500 / model.getSize() * j + 500 / model.getSize() / 2 + 10);
                }
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
                model = new Model(this,boardSize);
                repaint(null);

                if (listView.getItems() != null) {
                    listView.getItems().clear();
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "You did not fill in a number.", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
    }

    public void handleMouseClick(MouseEvent mouseEvent) {
        repaint(model.getResultMatrixes(listView.getSelectionModel().getSelectedIndex()));
    }
}
