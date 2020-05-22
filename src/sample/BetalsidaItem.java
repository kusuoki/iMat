package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    @FXML private ImageView betalItemPlusButton;
    @FXML private ImageView betalItemMinusButton;
    @FXML private ImageView betalItemRemoveImageView;

    private final Model model;
    private ShoppingItem item;
    private BetalsidaController parentController;

    public BetalsidaItem(ShoppingItem shoppingItem, BetalsidaController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("betalsidaitem.fxml"));
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
        betalItemTotalPrice.setText(model.doubleToString(item.getTotal()) + " kr");
        betalItemQuantityTextField.setText(String.valueOf((int)item.getAmount()));
        betalItemImageView.setImage(model.getImage(item.getProduct()));

        //Listener for textfield to update if focus left
        betalItemQuantityTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    System.out.println("Textfield on focus");
                }
                else
                {
                    System.out.println("Textfield out focus");
                    manualNumberEnterOfProduct(betalItemQuantityTextField.getText());
                }
            }
        });


        //Listener for textfield to update if enterkey is pressed
        betalItemQuantityTextField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    manualNumberEnterOfProduct(betalItemQuantityTextField.getText());
                }
            }
        });

    }

    public int getAmount() {
        return (int)item.getAmount();
    }

    public Product getProduct() {
        return item.getProduct();
    }

    @FXML
    public void manualNumberEnterOfProduct(String s){
        try {
            int i = Integer.parseInt(s);
            if (i < 0){
                updateTextFields();
            } else if (i == 0) {
                model.setShoppingCartItem(item.getProduct(), 1);
                removeOneOfProduct();
            } else {
                model.setShoppingCartItem(item.getProduct(), i);
                updateTextFields();
            }

        } catch (NumberFormatException nfe) {
            updateTextFields();
        }
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
        betalItemTotalPrice.setText(model.doubleToString(item.getTotal()) + " kr");
    }

    @FXML
    public void plusButtonMouseEntered(){
        betalItemPlusButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Buttons/Plus-knapp-hover.png")));
    }

    @FXML
    public void plusButtonMouseExited(){
        betalItemPlusButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Buttons/Plus-knapp.png")));
    }

    @FXML
    public void plusButtonMouseClicked(){
        betalItemPlusButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Buttons/Plus-knapp-pressed.png")));
    }

    @FXML
    public void minusButtonMouseEntered(){
        betalItemMinusButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Buttons/Minus-knapp-hover.png")));
    }

    @FXML
    public void minusButtonMouseExited(){
        betalItemMinusButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Buttons/Minus-knapp.png")));
    }

    @FXML
    public void minusButtonMouseClicked(){
        betalItemMinusButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Buttons/Minus-knapp-pressed.png")));
    }

    @FXML
    public void deleteButtonMouseEntered(){
        betalItemRemoveImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Icons/ic_delete_forever_hover.png")));
    }

    @FXML
    public void deleteButtonMouseExited(){
        betalItemRemoveImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Icons/ic_delete_forever_black_48dp.png")));
    }

    @FXML
    public void deleteButtonMouseClicked(){
        betalItemRemoveImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Icons/ic_delete_forever_black_48dp.png")));
    }
}
