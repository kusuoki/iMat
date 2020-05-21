package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class KundserviceController implements Initializable {

    private Stage stage;
    private Parent mainPage;
    private Parent listPage;
    private Parent earlierPurchases;
    private Parent accountPage;
    private Parent paymentPage;
    private MainPageController mainPageController;
    private BackButton backButton = BackButton.getBackButton();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMenuButtons();
    }

    public void setStage(Stage stage, Parent mainPage,  Parent accountPage,Parent paymentPage, MainPageController mainPageController) {
        this.stage = stage;
        this.mainPage=mainPage;
        this.accountPage=accountPage;
        this.paymentPage=paymentPage;
        this.mainPageController=mainPageController;
    }

    //Menyknapparna längst upp
    @FXML
    Button buttonTidigareKop;
    @FXML
    Button buttonKundservice;
    @FXML
    Button buttonKonto;

    private void initMenuButtons(){
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("Icons/ic_receipt_white_24dp.png"));
        buttonTidigareKop.setGraphic(new ImageView(image));
        buttonTidigareKop.setGraphicTextGap(5);

        image = new Image(getClass().getClassLoader().getResourceAsStream("Icons/ic_call_white_24dp.png"));
        buttonKundservice.setGraphic(new ImageView(image));
        buttonKundservice.setGraphicTextGap(5);

        image = new Image(getClass().getClassLoader().getResourceAsStream("Icons/ic_account_circle_white_24dp.png"));
        buttonKonto.setGraphic(new ImageView(image));
        buttonKonto.setGraphicTextGap(5);
    }

    @FXML
    public void onHomeButtonClick(ActionEvent actionEvent){
        backButton.addToBackList(stage.getScene().getRoot());
        stage.getScene().setRoot(mainPage);
    }

    @FXML void onHomeButtonClickIcon(){                         //Ikonen behöver en egen metod, samma funktionalitet som ovan
        backButton.addToBackList(stage.getScene().getRoot());
        stage.getScene().setRoot(mainPage);
    }


    @FXML
    public void onListPageClick(ActionEvent actionEvent){
        backButton.addToBackList(stage.getScene().getRoot());
        stage.getScene().setRoot(listPage);
    }
    @FXML
    public void onEarlierPurchasesPageClick(ActionEvent actionEvent){
        //backButton.addToBackList(stage.getScene().getRoot());
        mainPageController.onEarlierPurchases(actionEvent);
        stage.getScene().setRoot(mainPage);
    }
    @FXML
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
