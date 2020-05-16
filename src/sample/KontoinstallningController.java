package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Customer;

import java.net.URL;
import java.util.Objects;
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
    @FXML TextField cardNumberTextField_1;
    @FXML TextField cardNumberTextField_2;
    @FXML TextField cardNumberTextField_3;
    @FXML TextField cardNumberTextField_4;
    @FXML TextField cardExpiryMonthTextField;
    @FXML TextField cardExpiryYearTextField;
    @FXML TextField CVVTextField;
    @FXML ImageView cardTypeImageView;

    private TextField nextTextfield = firstNameTextField;

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


        //--------------------------------------------------------------------------------------------
        firstNameTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                nextTextfield = lastNameTextField;
            } else {
                System.out.println("Textfield out focus");
            }
        });
        lastNameTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                nextTextfield = adressTextField;
            } else {
                System.out.println("Textfield out focus");
            }
        });
        adressTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                nextTextfield = cityTextField;
            } else {
                System.out.println("Textfield out focus");
            }
        });
        cityTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                nextTextfield = postNumberTextField;
            } else {
                System.out.println("Textfield out focus");
            }
        });
        postNumberTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                nextTextfield = eMailTextField;
            } else {
                System.out.println("Textfield out focus");
            }
        });
        eMailTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                nextTextfield = phoneTextField;
            } else {
                System.out.println("Textfield out focus");
            }
        });
        phoneTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                nextTextfield = cardNumberTextField_1;
            } else {
                System.out.println("Textfield out focus");
            }
        });
        cardNumberTextField_1.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                System.out.println("Textfield on focus");
            } else {
                System.out.println("Textfield out focus");
            }
        });
        cardNumberTextField_2.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                System.out.println("Textfield on focus");
            } else {
                System.out.println("Textfield out focus");
            }
        });
        cardNumberTextField_3.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                System.out.println("Textfield on focus");
            } else {
                System.out.println("Textfield out focus");
            }
        });
        cardNumberTextField_4.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
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


        //---------------------------------------------------------------------------------------------
        cardNumberTextField_1.textProperty().addListener((observable, oldValue, newValue) -> {
            numberOnlyTextField(cardNumberTextField_1, oldValue, newValue, 4);

            String cardType = checkCardType(newValue);
            cardTypeImageView.setImage(getCardTypeImage(cardType));

            if (newValue.length() == 4 && oldValue.length() != 5) {
                cardNumberTextField_2.requestFocus();
                cardNumberTextField_2.positionCaret(4);
            }
        });
        cardNumberTextField_2.textProperty().addListener((observable, oldValue, newValue) -> {
            numberOnlyTextField(cardNumberTextField_2, oldValue, newValue, 4);

            if (newValue.length() == 4 && oldValue.length() != 5) {
                cardNumberTextField_3.requestFocus();
                cardNumberTextField_3.positionCaret(4);
            }
        });
        cardNumberTextField_3.textProperty().addListener((observable, oldValue, newValue) -> {
            numberOnlyTextField(cardNumberTextField_3, oldValue, newValue, 4);

            if (newValue.length() == 4 && oldValue.length() != 5) {
                cardNumberTextField_4.requestFocus();
                cardNumberTextField_4.positionCaret(4);
            }
        });
        cardNumberTextField_4.textProperty().addListener((observable, oldValue, newValue) -> {
            numberOnlyTextField(cardNumberTextField_4, oldValue, newValue, 4);

            if (newValue.length() == 4 && oldValue.length() != 5) {
                cardExpiryMonthTextField.requestFocus();
                cardExpiryMonthTextField.positionCaret(2);
            }
        });
        cardExpiryMonthTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            numberOnlyTextField(cardExpiryMonthTextField, oldValue, newValue, 2);

            if (isInteger(newValue)) {
                int month = Integer.parseInt(newValue);
                if (month <= 12 && newValue.length() == 2 && oldValue.length() != 3) {
                    cardExpiryYearTextField.requestFocus();
                    cardExpiryYearTextField.positionCaret(2);
                }
            }
        });
        cardExpiryYearTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            numberOnlyTextField(cardExpiryYearTextField, oldValue, newValue, 2);

            if (isInteger(newValue)) {
                int year = Integer.parseInt(newValue);
                // hardcoded year value lmao
                if (year >= 20 && newValue.length() == 2 && oldValue.length() != 3) {
                    CVVTextField.requestFocus();
                    CVVTextField.positionCaret(3);
                }
            }
        });
        CVVTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            numberOnlyTextField(CVVTextField, oldValue, newValue, 3);
        });

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
        card.setCardNumber(getCardNumber());
        card.setValidMonth(Integer.parseInt(cardExpiryMonthTextField.getText()));
        card.setValidYear(Integer.parseInt(cardExpiryYearTextField.getText()));
        card.setVerificationCode(Integer.parseInt(CVVTextField.getText()));

        System.out.println("Account settings saved! I hope.");
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

    @FXML
    public void goToNextTextField() {
        nextTextfield.requestFocus();
    }

    private void updateInformation() {
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
        cardNumberTextField_1.setText(getCardNumberBySection(1));
        cardNumberTextField_2.setText(getCardNumberBySection(2));
        cardNumberTextField_3.setText(getCardNumberBySection(3));
        cardNumberTextField_4.setText(getCardNumberBySection(4));
        cardExpiryYearTextField.setText(getYear(card.getValidYear()));
        cardExpiryMonthTextField.setText(getMonth(card.getValidMonth()));
        CVVTextField.setText((card.getVerificationCode() > 99) ? String.valueOf(card.getVerificationCode()) : "");
    }

    /*
     * checks if the given cardNumber is a Visa or Mastercard,
     * returns empty string if neither
     */
    private String checkCardType(String cardNumber) {
        if (cardNumber.length() > 0) {
            int IIN; // issuer identification number --> IIN
            int length = cardNumber.length();
            try {
                IIN = Integer.parseInt(cardNumber.substring(0, (length > 1) ? 2 : 1));
            } catch (NumberFormatException e) {
                // meaning string is not a valid integer
                return "";
            }

            if ((IIN >= 40 && IIN < 50) || IIN == 4) {
                // if first digit is 4 then it's Visa
                return "Visa";
            } else if (IIN >= 51 && IIN <= 55) {
                // if first two digits are in between 51-55 then it's Mastercard
                return "Mastercard";
            }
        }
        return "";
    }

    private Image getCardTypeImage(String cardType) {
        String iconPath;
        switch (cardType) {
            case "Visa":
                iconPath = "sample/resources/Icons/visa.png";
                return new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(iconPath)));
            case "Mastercard":
                iconPath = "sample/resources/Icons/mastercard.png";
                return new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(iconPath)));
            default:
                iconPath = "sample/resources/Icons/unknown_card.png";
                return new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(iconPath)));
        }
    }

    private String getCardNumber() {
        return cardNumberTextField_1.getText()+cardNumberTextField_2.getText()
                +cardNumberTextField_3.getText()+cardNumberTextField_4.getText();
    }

    /*
     *   assuming a card number can only be 16 characters long
     *   returns a string with length 4
     */
    private String getCardNumberBySection(int section) {
        String fullLengthCardNumber = getCardNumber();
        if (fullLengthCardNumber.length() != 16) {
            section = 0;
        }

        return switch (section) {
            case 1 -> fullLengthCardNumber.substring(0, 4);
            case 2 -> fullLengthCardNumber.substring(3, 8);
            case 3 -> fullLengthCardNumber.substring(7, 12);
            case 4 -> fullLengthCardNumber.substring(11, 16);
            default -> "";
        };
    }

    private boolean isInteger(String validMonth) {
        try {
            Integer.parseInt(validMonth);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
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

    /*
     *  by adding this as a textfield's listener will make the textfield numeric,
     *  the textfield will therefor only accept numbers
     */
    private void numberOnlyTextField(TextField tf, String oldValue, String newValue, int digitCount) {
        if (!newValue.matches("\\d*")) {
            tf.setText(newValue.replaceAll("[^\\d]", ""));
        }

        if (newValue.length() > digitCount) {
            tf.setText(oldValue);
        }
    }
}
