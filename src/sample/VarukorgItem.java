package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class VarukorgItem extends AnchorPane implements ShoppingCartListener {

    ShoppingItem shoppingItem;
    Model model;

    @FXML
    Label labelVarukorgVara;
    @FXML
    TextField labelVarukorgAntal;
    @FXML
    Label labelVarukorgPris;


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

        /*
        labelVarukorgAntal.setText(Integer.toString( (int) shoppingItem.getAmount() ));
        labelVarukorgVara.setText(shoppingItem.getProduct().getName());
        labelVarukorgPris.setText(Integer.toString( (int) shoppingItem.getTotal() ));

         */

        model.getShoppingCart().addShoppingCartListener(this);

    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        if (cartEvent.getShoppingItem().getProduct().getProductId() == shoppingItem.getProduct().getProductId()) {
            updateThisItem();
        }

    }

    private void updateThisItem(){
        labelVarukorgAntal.setText(Integer.toString( (int) shoppingItem.getAmount() ));
        labelVarukorgVara.setText(shoppingItem.getProduct().getName());
        labelVarukorgPris.setText(Integer.toString( (int) shoppingItem.getTotal() ));
    }
}
