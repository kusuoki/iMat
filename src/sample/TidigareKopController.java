package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
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
    private Stage stage;
    private Parent mainPage;
    private Parent listPage;
    private Parent accountPage;
    private Parent customerServicePage;
    private Parent paymentPage;
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
    public void setStage(Stage stage, Parent mainPage, Parent listPage, Parent accountPage, Parent customerServicePage, Parent paymentPage){
        this.stage=stage;
        this.mainPage=mainPage;
        this.listPage=listPage;
        this.accountPage=accountPage;
        this.customerServicePage=customerServicePage;
        this.paymentPage=paymentPage;
    }
    @FXML
    public void onHomeButtonClick(ActionEvent actionEvent){
        backButton.addToBackList(stage.getScene().getRoot());
        stage.getScene().setRoot(mainPage);
    }
    @FXML
    public void onListPageClick(ActionEvent actionEvent){
        backButton.addToBackList(stage.getScene().getRoot());
        stage.getScene().setRoot(listPage);
    }
    @FXML
    public void onAccountPageClick(ActionEvent actionEvent){
        backButton.addToBackList(stage.getScene().getRoot());
        stage.getScene().setRoot(accountPage);
    }@FXML
    public void onCustomerServicePageClick(ActionEvent actionEvent){
        backButton.addToBackList(stage.getScene().getRoot());
        stage.getScene().setRoot(customerServicePage);
    }
    @FXML
    public void onPaymentPageClick(ActionEvent actionEvent){
        backButton.addToBackList(stage.getScene().getRoot());
        stage.getScene().setRoot(paymentPage);
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
