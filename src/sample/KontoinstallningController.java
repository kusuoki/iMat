package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class KontoinstallningController implements Initializable {

    @FXML Label labelSparadeUppgifter;
    @FXML TextField firstNameTextField;
    @FXML TextField lastNameTextField;
    @FXML TextField adressTextField;
    @FXML TextField cityTextField;
    @FXML TextField postNumberTextField;
    @FXML TextField eMailTextField;
    @FXML TextField phoneTextField;
    @FXML TextField cardNumberTextField;
    @FXML TextField cardExpiryDateTextField;
    @FXML TextField CVVTextField;

    private Model handler = Model.getInstance();
    private CreditCard card;
    private Customer customer;

    private Stage stage;
    private Parent mainPage;
    private Parent listPage;
    private Parent earlierPurchases;
    private Parent customerServicePage;
    private Parent paymentPage;
    private BackButton backButton = BackButton.getBackButton();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        card = handler.getCreditCard();
        customer = handler.getCustomer();

        updateInformation();
    }

    private void updateInformation(){
        StringBuilder sb = new StringBuilder();

        sb.append(card.getCardNumber()).append(card.getValidMonth()).append(card.getValidYear())
                .append(card.getVerificationCode()).append(customer.getAddress()).append(customer.getPostAddress())
                .append(customer.getEmail()).append(customer.getFirstName()).append(customer.getLastName())
                .append(customer.getPhoneNumber()).append(customer.getPostCode());

        if (sb.toString().length() == 0) {
            labelSparadeUppgifter.setText("Du har inga sparade uppgifter.");
        } else {
            labelSparadeUppgifter.setText("Nedan är dina sparade uppgifter.");
        }

        firstNameTextField.setText(customer.getFirstName());
        lastNameTextField.setText(customer.getLastName());
       // adressTextField.setText(getStreetAddress(customer.getAddress()));
        //cityTextField.setText(getCity(customer.getAddress()));
        postNumberTextField.setText(customer.getPostCode());
        eMailTextField.setText(customer.getEmail());
        phoneTextField.setText(customer.getPhoneNumber());
        cardNumberTextField.setText(card.getCardNumber());
        cardExpiryDateTextField.setText(card.getValidMonth()+"/"+card.getValidYear());
        CVVTextField.setText(String.valueOf(card.getVerificationCode()));
    }

    // customer.getAddress() combines streetAddress and city
    private String getStreetAddress(String address) {
       return address.split(";", 0)[0];
    }

    private String getCity(String address){
        return address.split(";", 0)[1];
    }

    public void setStage(Stage stage, Parent mainPage, Parent listPage, Parent earlierPurchases, Parent customerServicePage, Parent paymentPage){
        this.stage=stage;
        this.mainPage=mainPage;
        this.listPage=listPage;
        this.earlierPurchases=earlierPurchases;
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
    public void onEarlierPurchasesPageClick(ActionEvent actionEvent){
        backButton.addToBackList(stage.getScene().getRoot());
        stage.getScene().setRoot(earlierPurchases);
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

    @FXML
    public void onBackButtonClick(ActionEvent actionEvent){
        backButton.goBack();
    }

}
