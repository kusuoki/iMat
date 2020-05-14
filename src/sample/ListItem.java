package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    protected Pane listItemPlusMinusPane;
    @FXML
    protected TextField listItemQuantityTextField;
    @FXML
    protected ImageView listItemMinusButton;
    @FXML
    protected ImageView listItemPlusButton;


    ProductA product;
    Model model;

    public ListItem(ProductA product, Model model){
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

        listItemName.setText(product.getName());
        listItemUnit.setText(product.getPrice() + product.getUnit());
        //listItemPrice.setText(Double.toString(product.getPrice()) + "kr");
        listItemImage.setImage(model.getImage(product));

        if (model.getShoppingCart().getItems().contains(new ShoppingItem(product))){ //Kolla om produkten redan finns med i shoppingcarten, i så fall ska plus/minusknapparna visas
            listItemPlusMinusPane.toFront();

        }
    }

    @FXML
    public void addFirstProduct(){
        model.addToShoppingCart(product);   //Lägg till produkten i shoppingcart
        listItemPlusMinusPane.toFront();    //Visa "plus, minus och antal" istället för "Lägg till"-knappen
        listItemQuantityTextField.setText("1"); //Sätt "antalet tillagda"-textfältet till 1
    }

    public void addMoreOfProduct(){

    }


}
