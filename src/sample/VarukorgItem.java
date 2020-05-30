package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class VarukorgItem extends AnchorPane {

    ShoppingItem shoppingItem;
    Model model;

    @FXML
    Label labelVarukorgVara;
    @FXML
    TextField labelVarukorgAntal;
    @FXML
    Label labelVarukorgPris;
    @FXML ImageView varukorgItemPlusButton;
    @FXML ImageView varukorgItemMinusButton;
    @FXML ImageView trashObjectButton;


    public VarukorgItem(ShoppingItem shoppingItem, Model model){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("varukorgitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.model = model;
        this.shoppingItem = shoppingItem;
        updateThisItem();

        //Listener for textfield to update if focus left
        labelVarukorgAntal.focusedProperty().addListener(new ChangeListener<Boolean>()
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
                    manualNumberEnterOfProduct(labelVarukorgAntal.getText());
                }
            }
        });


        //Listener for textfield to update if enterkey is pressed
        labelVarukorgAntal.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    manualNumberEnterOfProduct(labelVarukorgAntal.getText());
                }
            }
        });


    }

    public void updateThisItem(){
        labelVarukorgAntal.setText(Integer.toString( (int) shoppingItem.getAmount() ));
        labelVarukorgVara.setText(shoppingItem.getProduct().getName());
        double roundedTotal = Math.round(shoppingItem.getTotal()*100.0)/100.0;
        labelVarukorgPris.setText(roundedTotal + "kr");
    }

    @FXML
    public void trashObjectButton(){
        model.removeFromShoppingCart(shoppingItem.getProduct());

    }

    @FXML
    public void plusButton(){
        if (shoppingItem.getAmount()<999){
        model.updateShoppingCart(shoppingItem.getProduct(), 1);
        model.getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
    }}

    @FXML
    public void minusButton(){
        model.updateShoppingCart(shoppingItem.getProduct(), -1);
        model.getShoppingCart().fireShoppingCartChanged(shoppingItem, false);
        if ((int) shoppingItem.getAmount() == 0){
            trashObjectButton();
        }

    }

    @FXML
    public void plusButtonMouseEntered(){
        varukorgItemPlusButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Buttons/Plus-knapp-hover.png")));
    }

    @FXML
    public void plusButtonMouseExited(){
        varukorgItemPlusButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Buttons/Plus-knapp.png")));
    }

    @FXML
    public void plusButtonMouseClicked(){
        varukorgItemPlusButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Buttons/Plus-knapp-pressed.png")));
    }

    public void manualNumberEnterOfProduct(String s) {
        try {
            int i = Integer.parseInt(s);
            if (i < 0) {
                updateThisItem();
            } else if (i == 0) {
                model.setShoppingCartItem(shoppingItem.getProduct(), 1);
                minusButton();
            } else if (i < 1000) {
                model.setShoppingCartItem(shoppingItem.getProduct(), i);
                updateThisItem();
            } else if (i > 999) {
                model.setShoppingCartItem(shoppingItem.getProduct(), 999);
                updateThisItem();
            }

        } catch (NumberFormatException nfe) {
            updateThisItem();
        }
    }


    @FXML
    public void minusButtonMouseEntered(){
        varukorgItemMinusButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Buttons/Minus-knapp-hover.png")));
    }

    @FXML
    public void minusButtonMouseExited(){
        varukorgItemMinusButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Buttons/Minus-knapp.png")));
    }

    @FXML
    public void minusButtonMouseClicked(){
        varukorgItemMinusButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Buttons/Minus-knapp-pressed.png")));
    }

    @FXML
    public void deleteButtonMouseEntered(){
        trashObjectButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Icons/ic_delete_forever_hover.png")));
    }

    @FXML
    public void deleteButtonMouseExited(){
        trashObjectButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Icons/ic_delete_forever_black_48dp.png")));
    }

    @FXML
    public void deleteButtonMouseClicked(){
        trashObjectButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Icons/ic_delete_forever_black_48dp.png")));
    }
}
