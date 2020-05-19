package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ListorController implements Initializable {  private Stage stage;
    private Parent mainPage;
    private Parent customerServicePage;
    private Parent earlierPurchases;
    private Parent accountPage;
    private Parent paymentPage;
    private BackButton backButton = BackButton.getBackButton();


    public void setStage(Stage stage, Parent mainPage, Parent customerServicePage, Parent earlierPurchases, Parent accountPage,Parent paymentPage) {
        this.stage = stage;
        this.mainPage=mainPage;
        this.customerServicePage=customerServicePage;
        this.earlierPurchases=earlierPurchases;
        this.accountPage=accountPage;
        this.paymentPage=paymentPage;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void onHomeButtonClick(ActionEvent actionEvent){
        backButton.addToBackList(stage.getScene().getRoot());
        stage.getScene().setRoot(mainPage);
    }
    @FXML
    public void onCustomerServiceClick(ActionEvent actionEvent){
        backButton.addToBackList(stage.getScene().getRoot());
        stage.getScene().setRoot(customerServicePage);
    }
    @FXML
    public void onEarlierPurchasesPageClick(ActionEvent actionEvent){
        backButton.addToBackList(stage.getScene().getRoot());
        stage.getScene().setRoot(earlierPurchases);
    }@FXML
    public void onAccountPageClick(ActionEvent actionEvent){
        backButton.addToBackList(stage.getScene().getRoot());
        stage.getScene().setRoot(accountPage);
    }
    @FXML
    public void onPaymentPageClick(ActionEvent actionEvent){
        backButton.addToBackList(stage.getScene().getRoot());
        stage.getScene().setRoot(paymentPage);
    }
    @FXML
    public void onBackButtonClick(ActionEvent actionEvent){
        backButton.goBack();
    }

}
