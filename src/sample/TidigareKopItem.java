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
    @FXML
    private Label labelTidigareDatum;
    @FXML
    private Label labelTidigarePris;
    @FXML
    private Label labelTidigareAntal;

    private Order prevOrder;
    private MainPageController parentController;

    public TidigareKopItem(Order prevOrder, MainPageController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tidigarekopitem.fxml"));
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
        labelTidigarePris.setText(getOrderTotalCost(prevOrder) + "kr");
        int antal = 0;
        for (ShoppingItem shoppingItem : prevOrder.getItems()) {
            antal += (int) shoppingItem.getAmount();
        }
        labelTidigareAntal.setText(antal + " varor");
    }

    public int getOrderTotalCost(Order order) {
        List<ShoppingItem> items = order.getItems();
        int totalCost = 0;
        for (ShoppingItem item : items) {
            totalCost += item.getTotal();
        }
        return totalCost;
    }

    public Order getOrder() {

        return prevOrder;
    }

    @FXML
    void openDetailedView() {
        parentController.enterOrderLightbox(this);
    }

}
