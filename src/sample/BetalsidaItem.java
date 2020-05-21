package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class BetalsidaItem extends AnchorPane {
    @FXML private Label betalItemName;
    @FXML private Label betalItemTotalPrice;
    @FXML private Label betalItemPrice;
    @FXML private TextField betalItemQuantityTextField;
    @FXML private ImageView betalItemImageView;

    private final Model model;
    private ShoppingItem item;
    private BetalsidaController parentController;

    public BetalsidaItem(ShoppingItem shoppingItem, BetalsidaController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("betlasidaitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.model = Model.getInstance();
        this.item = shoppingItem;
        this.parentController = parentController;

        betalItemName.setText(item.getProduct().getName());
        betalItemPrice.setText(item.getProduct().getPrice() + item.getProduct().getUnit());
        betalItemTotalPrice.setText(item.getTotal() + " kr");
        betalItemQuantityTextField.setText(String.valueOf((int)item.getAmount()));
        betalItemImageView.setImage(model.getImage(item.getProduct()));
    }

    public int getAmount() {
        return (int)item.getAmount();
    }

    public Product getProduct() {
        return item.getProduct();
    }

    @FXML
    public void addOneOfProduct() {
        model.updateShoppingCart(item.getProduct(), 1);
        parentController.updateShoppingCart();
    }

    @FXML
    public void removeOneOfProduct() {
        if ((int)item.getAmount() > 1) {
            model.updateShoppingCart(item.getProduct(), -1);
        } else {
            model.removeFromShoppingCart(item.getProduct());
            parentController.removeFromMap(this);
        }
        parentController.updateShoppingCart();
    }

    @FXML
    public void removeProduct() {
        model.removeFromShoppingCart(item.getProduct());
        parentController.removeFromMap(this);
        parentController.updateShoppingCart();
    }

    public void updateTextFields() {
        betalItemQuantityTextField.setText(String.valueOf((int)item.getAmount()));
        betalItemTotalPrice.setText(item.getTotal() + " kr");
    }
}
