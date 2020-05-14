package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.List;

public class TidigareKopItem extends AnchorPane {
    @FXML private Label labelTidigareDatum;
    @FXML private Label labelTidigarePris;
    @FXML private Label labelTidigareAntal;

    private Order prevOrder;
    private TidigareKopController parentController;

    public TidigareKopItem(Order prevOrder, TidigareKopController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tidigareköpitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.prevOrder = prevOrder;
        this.parentController = parentController;

        labelTidigareDatum.setText(prevOrder.getDate().toString());
        labelTidigarePris.setText(String.valueOf(getOrderTotalCost(prevOrder)));
        labelTidigareAntal.setText(String.valueOf(prevOrder.getItems().size()));
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
