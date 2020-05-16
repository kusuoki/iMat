package sample;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
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
    @FXML TextField cardExpiryMonthTextField;
    @FXML TextField cardExpiryYearTextField;
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

        firstNameTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                System.out.println("Textfield on focus");
            } else {
                System.out.println("Textfield out focus");
            }
        });
        lastNameTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                System.out.println("Textfield on focus");
            } else {
                System.out.println("Textfield out focus");
            }
        });
        adressTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                System.out.println("Textfield on focus");
            } else {
                System.out.println("Textfield out focus");
            }
        });
        cityTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                System.out.println("Textfield on focus");
            } else {
                System.out.println("Textfield out focus");
            }
        });
        postNumberTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                System.out.println("Textfield on focus");
            } else {
                System.out.println("Textfield out focus");
            }
        });
        eMailTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                System.out.println("Textfield on focus");
            } else {
                System.out.println("Textfield out focus");
            }
        });
        phoneTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                System.out.println("Textfield on focus");
            } else {
                System.out.println("Textfield out focus");
            }
        });
        cardNumberTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                System.out.println("Textfield on focus");
            } else {
                System.out.println("Textfield out focus");
            }
        });
        cardExpiryMonthTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                System.out.println("Textfield on focus");
            } else {
                System.out.println("Textfield out focus");
            }
        });
        cardExpiryYearTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                System.out.println("Textfield on focus");
            } else {
                System.out.println("Textfield out focus");
            }
        });
        CVVTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                System.out.println("Textfield on focus");
            } else {
                System.out.println("Textfield out focus");
            }
        });

        // TODO: error handling
        cardNumberTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            numberOnlyTextField(cardNumberTextField, oldValue, newValue, 16);
        });

        cardExpiryMonthTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            numberOnlyTextField(cardExpiryMonthTextField, oldValue, newValue, 2);
        });

        cardExpiryYearTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            numberOnlyTextField(cardExpiryYearTextField, oldValue, newValue, 2);
        });

        CVVTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            numberOnlyTextField(CVVTextField, oldValue, newValue, 3);
        });

    }


    private void numberOnlyTextField(TextField tf, String oldValue, String newValue, int digitCount) {
        if (!newValue.matches("\\d*")) {
            tf.setText(newValue.replaceAll("[^\\d]", ""));
        }

        if (newValue.length() > digitCount) {
            tf.setText(oldValue);
        }
    }

    @FXML
    public void save() {
        customer.setFirstName(firstNameTextField.getText());
        customer.setLastName(lastNameTextField.getText());
        customer.setAddress(adressTextField.getText());
        customer.setPhoneNumber(phoneTextField.getText());
        customer.setEmail(eMailTextField.getText());
        customer.setPostAddress(cityTextField.getText());
        customer.setPostCode(postNumberTextField.getText());
        card.setCardNumber(cardNumberTextField.getText());
        card.setValidMonth(Integer.parseInt(cardExpiryMonthTextField.getText()));
        card.setValidYear(Integer.parseInt(cardExpiryYearTextField.getText()));
        card.setVerificationCode(Integer.parseInt(CVVTextField.getText()));

        System.out.println("Account settings saved! I hope.");
    }


    private void updateInformation(){
        StringBuilder sb = new StringBuilder();

        sb.append(card.getCardNumber()).append(customer.getAddress()).append(customer.getPostAddress())
                .append(customer.getEmail()).append(customer.getFirstName()).append(customer.getLastName())
                .append(customer.getPhoneNumber()).append(customer.getPostCode());

        if (sb.toString().length() == 0 &&
                (card.getVerificationCode() + card.getValidYear() + card.getValidMonth()) == 0) {
            labelSparadeUppgifter.setText("Du har inga sparade uppgifter.");
        } else {
            labelSparadeUppgifter.setText("Nedan är dina sparade uppgifter.");
        }

        firstNameTextField.setText(customer.getFirstName());
        lastNameTextField.setText(customer.getLastName());
        adressTextField.setText(customer.getAddress());
        cityTextField.setText(customer.getPostAddress());
        postNumberTextField.setText(customer.getPostCode());
        eMailTextField.setText(customer.getEmail());
        phoneTextField.setText(customer.getPhoneNumber());
        cardNumberTextField.setText(card.getCardNumber());
        cardExpiryYearTextField.setText(getYear(card.getValidYear()));
        cardExpiryMonthTextField.setText(getMonth(card.getValidMonth()));
        CVVTextField.setText((card.getVerificationCode() > 99) ? String.valueOf(card.getVerificationCode()) : "");
    }

    private String getMonth(int validMonth) {
        if (validMonth < 0 || validMonth > 12){
            return "";
        } else if (validMonth < 10){
            return "0"+validMonth;
        }
        return String.valueOf(validMonth);
    }

    private String getYear(int validYear) {
        if (validYear < 0 || validYear > 99){
            return "";
        }
        return String.valueOf(validYear);
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
