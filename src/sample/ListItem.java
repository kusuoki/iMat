package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.*;

import java.io.IOException;

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
        if (model.getShoppingCart().getItems().contains(model.getShoppingItemMap().get(product.getProductId()))) { //Kolla om produkten redan finns med i shoppingcarten, i så fall ska plus/minusknapparna visas
            listItemPlusMinusPane.toFront();
            updateTextfieldWithAmountOfProduct();
        }

        if (model.getFavorites().contains(product)){        //Kolla om produkten ligger i favoriter
            fullHeartPane.toFront();      // JA: Visa det fulla hjärtat.
        } else {
            emptyHeartPane.toFront();     //NEJ: Visa det tomma hjärtat.
        }

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

    @FXML
    void openDetailedView() {
        parentController.openLightBox(this);
    }
}
