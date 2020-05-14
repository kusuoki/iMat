package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

public class VarukorgItem implements ShoppingCartListener {

    ShoppingItem shoppingItem;
    Model model;

    @FXML
    Label labelVarukorgVara;
    @FXML
    Label labelVarukorgAntal;
    @FXML
    Label labelVarukorgPris;


    public VarukorgItem(ShoppingItem shoppingItem, Model model){
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
