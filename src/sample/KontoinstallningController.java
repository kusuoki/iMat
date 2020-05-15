package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        card = handler.getCreditCard();
        customer = handler.getCustomer();

        checkInformation();
    }

    private void checkInformation(){
        StringBuilder sb = new StringBuilder();

        sb.append(card.getCardNumber()).append(card.getValidMonth()).append(card.getValidYear())
                .append(card.getVerificationCode()).append(customer.getAddress()).append(customer.getPostAddress())
                .append(customer.getEmail()).append(customer.getFirstName()).append(customer.getLastName())
                .append(customer.getPhoneNumber()).append(customer.getPostCode());

        // check the saved information to determine the displayed message
        if (sb.toString().length() == 0) {
            labelSparadeUppgifter.setText("Du har inga sparade uppgifter.");
        } else {
            labelSparadeUppgifter.setText("Nedan är dina sparade uppgifter.");
        }
    }
}
