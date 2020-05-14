package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.*;

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
        this.product = product;
        this.model = model;

        listItemName.setText(product.getName());
        listItemUnit.setText(product.getUnit() + product.getUnitSuffix());
        listItemPrice.setText(Double.toString(product.getPrice()));
        listItemImage.setImage(model.getImage(product));

        if (model.getShoppingCart().getItems().contains(new ShoppingItem(product))){ //Kolla om produkten redan finns med i shoppingcarten, i så fall ska plus/minusknapparna visas
            listItemPlusMinusPane.toFront();

        }
    }

    public void addFirstProduct(){
        model.addToShoppingCart(product);   //Lägg till produkten i shoppingcart
        listItemPlusMinusPane.toFront();    //Visa "plus, minus och antal" istället för "Lägg till"-knappen
        listItemQuantityTextField.setText("1"); //Sätt "antalet tillagda"-textfältet till 1
    }

    public void addMoreOfProduct(){

    }



}
