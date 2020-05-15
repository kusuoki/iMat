package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.List;

public class BetalsidaItem extends AnchorPane {
    @FXML private Label betalItemVara;
    @FXML private Label betalItemMangd;
    @FXML private Label betalItemPris;
    Model model;

    private BetalsidaController parentController;

    public BetalsidaItem(ProductA product, Model parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("betlasidaitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.model = parentController;


        betalItemVara.setText(product.getName());
        betalItemMangd.setText(product.getUnit());
        betalItemPris.setText(product.getPrice() + "");
    }

    private int getOrderTotalCost(Order order) {
        List<ShoppingItem> items = order.getItems();
        int totalCost = 0;
        for (ShoppingItem item : items) {
            totalCost += item.getTotal();
        }
        return totalCost;
    }

}
