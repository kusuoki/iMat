package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TidigareKopController implements Initializable {

    @FXML private FlowPane centerStage;


    private IMatDataHandler handler;
    private List<TidigareKopItem> prevOrders;
    private BackButton backButton = BackButton.getBackButton();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        handler = IMatDataHandler.getInstance();
        prevOrders = new ArrayList<>();
        List<Order> prevOrderList = handler.getOrders();
        for (Order order : prevOrderList) {
            TidigareKopItem item = new TidigareKopItem(order, this);
            prevOrders.add(item);
        }

        updateOrderList();

    }

    private void updateOrderList() {
        centerStage.getChildren().clear();
        for (TidigareKopItem order : prevOrders) {
            centerStage.getChildren().add(order);
        }
    }
    public void onBack(){
        backButton.goBack();
    }
}
