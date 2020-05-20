package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class menuItem {

    @FXML javafx.scene.control.Button button;
    @FXML Pane pane;
    @FXML ImageView arrow;
    @FXML AnchorPane anchorPane;
    ArrayList<Button> sMenu = new ArrayList<Button>();

    public menuItem(Button button, Pane pane, ImageView arrow, AnchorPane anchorPane, ArrayList<Button> subMenu) {
        this.button = button;
        this.pane = pane;
        this.arrow = arrow;
        this.anchorPane = anchorPane;
        if (subMenu != null) {
            for (int i = 0; i < subMenu.size(); i++) {
                sMenu.add(subMenu.get(i));
            }
        }
    }
}
