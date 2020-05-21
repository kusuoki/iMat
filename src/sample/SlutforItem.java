package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class SlutforItem extends AnchorPane {

    @FXML private ImageView slutforItemImage;
    @FXML private Label slutforItemName;
    @FXML private Label slutforItemAmount;
    @FXML private Label slutforItemTotalPrice;

    private final Model model = Model.getInstance();

    public SlutforItem(ShoppingItem shoppingItem) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("slutforitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        slutforItemImage.setImage(model.getImage(shoppingItem.getProduct()));
        slutforItemName.setText(shoppingItem.getProduct().getName());
        slutforItemAmount.setText((int)shoppingItem.getAmount() + " st");
        slutforItemTotalPrice.setText(model.doubleToString(shoppingItem.getTotal()) + " kr");
    }
}
