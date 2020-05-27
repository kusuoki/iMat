package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.*;

import javax.swing.*;
import java.io.IOException;
import java.net.http.WebSocket;

import static javafx.application.ConditionalFeature.SWT;

public class ListItem extends AnchorPane {

    @FXML
    protected Label listItemName;
    @FXML
    protected Label listItemUnit;
    @FXML
    protected Label listItemPrice;
    @FXML
    protected ImageView listItemImage;


    @FXML
    protected Pane listItemAddPane;
    @FXML
    protected Button listItemFirstAddButton;

    @FXML
    protected ImageView fullHeartButton;
    @FXML
    protected ImageView emptyHeartButton;
    @FXML
    protected Pane fullHeartPane;
    @FXML
    protected Pane emptyHeartPane;


    @FXML
    protected Pane listItemPlusMinusPane;
    @FXML
    protected TextField listItemQuantityTextField;
    @FXML
    protected ImageView listItemMinusButton;
    @FXML
    protected ImageView listItemPlusButton;

    @FXML
    protected Pane panePreviousPurchase;
    @FXML
    protected Label labelPreviousPurchase;



    MainPageController parentController;
    ProductA product;
    Model model;

    public ListItem(ProductA product, Model model, MainPageController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;
        this.model = model;
        this.parentController = parentController;

        listItemName.setText(product.getName());
        listItemUnit.setText(product.getPrice() + product.getUnit());
        //listItemPrice.setText(Double.toString(product.getPrice()) + "kr");
        listItemImage.setImage(model.getImage(product));

        model.setImageViewOnHoverEvent(listItemImage,null);

        //TODO KOMMER GE PROBLEM (KANSKE FIXAD??)
        for (ShoppingItem item : model.getShoppingCart().getItems()) { //Kolla om produkten redan finns med i shoppingcarten, i så fall ska plus/minusknapparna visas
            if (item.getProduct().equals(product)) {
                listItemPlusMinusPane.toFront();
                updateTextfieldWithAmountOfProduct();
                break;
            }
        }

        if (model.getFavorites().contains(product)){        //Kolla om produkten ligger i favoriter
            fullHeartPane.toFront();      // JA: Visa det fulla hjärtat.
        } else {
            emptyHeartPane.toFront();     //NEJ: Visa det tomma hjärtat.
        }

        //Listener for textfield to update if focus left
        listItemQuantityTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                manualNumberEnterOfProduct(listItemQuantityTextField.getText());
            }
        });

        //Listener for textfield to update if enterkey is pressed
        listItemQuantityTextField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    manualNumberEnterOfProduct(listItemQuantityTextField.getText());
                }
            }
        });


    }

    @FXML
    public void addFirstProduct() {          //Metod som kallas på då produkten inte finns i varukorgen
        model.addToShoppingCart(product);   //Lägg till produkten i shoppingcart
        listItemPlusMinusPane.toFront();    //Visa "plus, minus och antal" istället för "Lägg till"-knappen
        updateTextfieldWithAmountOfProduct();   //Uppdatera textfältet med det nya antalet tillagda (alltid 1 efter att man klickar på "LÄGG TILL" knappen)
    }

    @FXML
    public void addOneOfProduct() {
        model.updateShoppingCart(product, 1);
        updateTextfieldWithAmountOfProduct();
    }

    @FXML
    public void manualNumberEnterOfProduct(String s){
        try {
            int i = Integer.parseInt(s);
            if (i < 0){
                updateTextfieldWithAmountOfProduct();
            } else if (i == 0) {
                model.setShoppingCartItem(product, 1);
                removeOneOfProduct();
            } else {
                model.setShoppingCartItem(product, i);
                updateTextfieldWithAmountOfProduct();
            }

        } catch (NumberFormatException nfe) {
            updateTextfieldWithAmountOfProduct();
        }
    }

    @FXML
    public void removeOneOfProduct() {
        /*
        hämta listan
        hämta antalet av produkten i listan
         */
        if (model.getAmountOfThisProductInShoppinCart(product) == 1) {   //Kollar om antalet av den specifika proukten är 1
            model.removeFromShoppingCart(product);   //Om antalet är 1 tas varan bort från shoppingcarten och varan måste tas bort från HashMap:en också
            listItemAddPane.toFront();  //Flytta fram den andra knappen
        } else {
            model.updateShoppingCart(product, -1);
            updateTextfieldWithAmountOfProduct();
        }
    }

    public void switchButtons(){
        if (model.getAmountOfThisProductInShoppinCart(product) == 0){
            listItemAddPane.toFront();
        } else {
            listItemPlusMinusPane.toFront();
        }
    }

    public void updateTextfieldWithAmountOfProduct() {
        int amount = model.getAmountOfThisProductInShoppinCart(product);       //Hämtar hur mycket av varan som finns i varukorgen
        listItemQuantityTextField.setText(Integer.toString(amount));        //Uppdaterar textfältet på kortet med antalet som finns i varukorgen
    }

    @FXML
    public void favorite() {    //Klicka på tomma hjärtat
        model.addFavorite(product);
        fullHeartPane.toFront();
    }

    @FXML
    public void unFavorite() {      //Klicka på ifyllda hjärtat
        model.removeFavorite(product);
        emptyHeartPane.toFront();
    }

    public void setOrderPreviousOrderAmount(int amount){
        labelPreviousPurchase.setText("Tidigare köptes: " + amount + "st");
        panePreviousPurchase.toFront();
    }


    @FXML
    void openDetailedView() {
        parentController.openLightBox(this);
    }

    @FXML
    public void plusButtonMouseEntered(){
        listItemPlusButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Buttons/Plus-knapp-hover.png")));
    }

    @FXML
    public void plusButtonMouseExited(){
        listItemPlusButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Buttons/Plus-knapp.png")));
    }

    @FXML
    public void plusButtonMouseClicked(){
        listItemPlusButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Buttons/Plus-knapp-pressed.png")));
    }

    @FXML
    public void minusButtonMouseEntered(){
        listItemMinusButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Buttons/Minus-knapp-hover.png")));
    }

    @FXML
    public void minusButtonMouseExited(){
        listItemMinusButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Buttons/Minus-knapp.png")));
    }

    @FXML
    public void minusButtonMouseClicked(){
        listItemMinusButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Buttons/Minus-knapp-pressed.png")));
    }
}
