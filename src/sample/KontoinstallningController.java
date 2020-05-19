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


        //--------------------------------no error handling on these-----------------------------------
        firstNameTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                nextTextfield = lastNameTextField;
            }
        });
        lastNameTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                nextTextfield = adressTextField;
            }
        });
        adressTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                nextTextfield = cityTextField;
            }
        });
        cityTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                nextTextfield = postNumberTextField;
            }
        });
        //--------------------------------------------------------------------------------------------
        postNumberTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                nextTextfield = eMailTextField;
            } else if (postNumberTextField.getText().length() != 0) {
                postNumberTextField.getStyleClass().remove("error");
                if (!isValidValue(postNumberTextField.getText(), "PostNumber")) {
                    postNumberTextField.getStyleClass().add("error");
                }
            }
        });
        eMailTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                nextTextfield = phoneTextField;
            } else if (eMailTextField.getText().length() != 0) {
                eMailTextField.getStyleClass().remove("error");
                if (!isValidValue(eMailTextField.getText(), "Email")) {
                    eMailTextField.getStyleClass().add("error");
                }
            }
        });
        phoneTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (isFocus) {
                nextTextfield = cardNumberTextField_1;
            } else if (phoneTextField.getText().length() != 0) {
                phoneTextField.getStyleClass().remove("error");
                if (isValidValue(phoneTextField.getText(), "Phone")) {
                    phoneTextField.setText(getFormattedPhoneNumber(phoneTextField.getText()));
                } else {
                    phoneTextField.getStyleClass().add("error");
                }
            }
        });
        cardNumberTextField_1.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (cardNumberTextField_1.getText().length() != 0 && !isFocus) {
                removeCardNumberError();
                if (!isValidValue(cardNumberTextField_1.getText(), "CardType") || cardNumberTextField_1.getText().length() != 4) {
                    setCardNumberError();
                }
            }
        });
        cardNumberTextField_2.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (cardNumberTextField_2.getText().length() != 0 && !isFocus) {
                removeCardNumberError();
                if (!isValidValue(cardNumberTextField_1.getText(), "CardType") || cardNumberTextField_2.getText().length() != 4) {
                    setCardNumberError();
                }
            }
        });
        cardNumberTextField_3.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (cardNumberTextField_3.getText().length() != 0 && !isFocus) {
                removeCardNumberError();
                if (!isValidValue(cardNumberTextField_1.getText(), "CardType") || cardNumberTextField_3.getText().length() != 4) {
                    setCardNumberError();
                }
            }
        });
        cardNumberTextField_4.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (cardNumberTextField_4.getText().length() != 0 && !isFocus) {
                removeCardNumberError();
                if (!isValidValue(cardNumberTextField_1.getText(), "CardType") || cardNumberTextField_4.getText().length() != 4) {
                    setCardNumberError();
                }
            }
        });
        cardExpiryMonthTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (cardExpiryMonthTextField.getText().length() != 0 && !isFocus) {
                cardExpiryMonthTextField.getStyleClass().remove("error");
                if (isValidValue(cardExpiryMonthTextField.getText(), "Month")) {
                    cardExpiryMonthTextField.setText(getMonth(Integer.parseInt(cardExpiryMonthTextField.getText())));
                } else {
                    cardExpiryMonthTextField.getStyleClass().add("error");
                }
            }
        });
        cardExpiryYearTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (cardExpiryYearTextField.getText().length() != 0 && !isFocus) {
                cardExpiryYearTextField.getStyleClass().remove("error");
                if (!isValidValue(cardExpiryYearTextField.getText(), "Year")) {
                    cardExpiryYearTextField.getStyleClass().add("error");
                }
            }
        });
        CVVTextField.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
            if (CVVTextField.getText().length() != 0 && !isFocus) {
                CVVTextField.getStyleClass().remove("error");
                if (isValidValue(CVVTextField.getText(), "CVV")) {
                    CVVTextField.setText(getCVV(Integer.parseInt(CVVTextField.getText())));
                } else {
                    CVVTextField.getStyleClass().add("error");
                }
            }
        });
        //---------------------------------------------------------------------------------------------
        postNumberTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            numberOnlyTextField(postNumberTextField, oldValue, newValue, 5);
            if (isValidValue(newValue, "PostNumber") || newValue.length() == 0) {
                postNumberTextField.getStyleClass().remove("error");
            }
        });
        eMailTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isValidValue(newValue, "Email") || newValue.length() == 0) {
                eMailTextField.getStyleClass().remove("error");
            }
        });
        phoneTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isValidValue(newValue, "Phone") || newValue.length() == 0) {
                phoneTextField.getStyleClass().remove("error");
            }
        });
        cardNumberTextField_1.textProperty().addListener((observable, oldValue, newValue) -> {
            numberOnlyTextField(cardNumberTextField_1, oldValue, newValue, 4);

            String cardType = checkCardType(newValue);
            cardTypeImageView.setImage(getCardTypeImage(cardType));

            if (getCardNumber().length() == 0) {
                removeCardNumberError();
            } else if (getCardNumber().length() == 16 && isValidValue(newValue, "CardType")) {
                removeCardNumberError();
            }

            if (newValue.length() == 4 && oldValue.length() != 5 && isInteger(newValue)) {
                cardNumberTextField_2.requestFocus();
                cardNumberTextField_2.positionCaret(4);
            }
        });
        cardNumberTextField_2.textProperty().addListener((observable, oldValue, newValue) -> {
            numberOnlyTextField(cardNumberTextField_2, oldValue, newValue, 4);

            if (getCardNumber().length() == 0){
                removeCardNumberError();
            }

            if (newValue.length() == 4 && oldValue.length() != 5 && isInteger(newValue)) {
                cardNumberTextField_3.requestFocus();
                cardNumberTextField_3.positionCaret(4);
            }
        });
        cardNumberTextField_3.textProperty().addListener((observable, oldValue, newValue) -> {
            numberOnlyTextField(cardNumberTextField_3, oldValue, newValue, 4);

            if (getCardNumber().length() == 0){
                removeCardNumberError();
            }
            if (newValue.length() == 4 && oldValue.length() != 5 && isInteger(newValue)) {
                cardNumberTextField_4.requestFocus();
                cardNumberTextField_4.positionCaret(4);
            }
        });
        cardNumberTextField_4.textProperty().addListener((observable, oldValue, newValue) -> {
            numberOnlyTextField(cardNumberTextField_4, oldValue, newValue, 4);

            if (getCardNumber().length() == 0){
                removeCardNumberError();
            }

            if (newValue.length() == 4 && oldValue.length() != 5 && isInteger(newValue)) {
                cardExpiryMonthTextField.requestFocus();
                cardExpiryMonthTextField.positionCaret(2);
            }
        });
        cardExpiryMonthTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            numberOnlyTextField(cardExpiryMonthTextField, oldValue, newValue, 2);

            if (isValidValue(newValue, "Month") || newValue.length() == 0) {
                cardExpiryMonthTextField.getStyleClass().remove("error");
            }

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

            if (isValidValue(newValue, "Year") || newValue.length() == 0) {
                cardExpiryYearTextField.getStyleClass().remove("error");
            }

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

            if (isValidValue(newValue, "CVV") || newValue.length() == 0) {
                CVVTextField.getStyleClass().remove("error");
            }

        });
        //---------------------------------------------------------------------------------------------
    }

    @FXML
    public void save() {
        if (isReadyToSave()) {
            customer.setFirstName(firstNameTextField.getText());
            customer.setLastName(lastNameTextField.getText());
            customer.setAddress(adressTextField.getText());
            customer.setPhoneNumber(getStrippedPhoneNumber(phoneTextField.getText()));
            customer.setEmail(eMailTextField.getText());
            customer.setPostAddress(cityTextField.getText());
            customer.setPostCode(postNumberTextField.getText());
            card.setCardNumber(getCardNumber());
            card.setValidMonth((cardExpiryMonthTextField.getText().length() == 0) ? 0 : Integer.parseInt(cardExpiryMonthTextField.getText()));
            card.setValidYear((cardExpiryYearTextField.getText().length() == 0) ? 0 : Integer.parseInt(cardExpiryYearTextField.getText()));
            card.setVerificationCode((CVVTextField.getText().length() == 0) ? 0 : Integer.parseInt(CVVTextField.getText()));
            System.out.println("Account settings saved! Probably.");
        }
    }

    public void setStage(Stage stage, Parent mainPage, Parent customerServicePage, Parent paymentPage){
        this.stage=stage;
        this.mainPage=mainPage;
        this.customerServicePage=customerServicePage;
        this.paymentPage=paymentPage;
    }

    @FXML
    public void onHomeButtonClick(ActionEvent actionEvent){
        backButton.addToBackList(stage.getScene().getRoot());
        stage.getScene().setRoot(mainPage);
    }
    @FXML void onHomeButtonClickIcon(){
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

    public void updateInformation() {
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
        phoneTextField.setText(getFormattedPhoneNumber(customer.getPhoneNumber()));
        cardNumberTextField_1.setText(getCardNumberBySection(1));
        cardNumberTextField_2.setText(getCardNumberBySection(2));
        cardNumberTextField_3.setText(getCardNumberBySection(3));
        cardNumberTextField_4.setText(getCardNumberBySection(4));
        cardExpiryYearTextField.setText(getYear(card.getValidYear()));
        cardExpiryMonthTextField.setText(getMonth(card.getValidMonth()));
        CVVTextField.setText(getCVV(card.getVerificationCode()));
    }

    /*
     * validate all input, ignores empty text field,
     * and returns true if all text field are ready
     *
     * please don't look at this method
     */
    private boolean isReadyToSave() {
        boolean isReady = true;
        int cardNumberLength = getCardNumber().length();
        if ((cardNumberLength != 0 && !isValidValue(cardNumberTextField_1.getText(), "CardType")) ||
                (cardNumberLength != 0 && cardNumberLength != 16)) {
            removeCardNumberError();
            setCardNumberError();
            isReady = false;
        }

        if (cardExpiryMonthTextField.getText().length() != 0 && !isValidValue(cardExpiryMonthTextField.getText(), "Month")) {
            cardExpiryMonthTextField.getStyleClass().remove("error");
            cardExpiryMonthTextField.getStyleClass().add("error");
            isReady = false;
        }
        if (cardExpiryYearTextField.getText().length() != 0 && !isValidValue(cardExpiryYearTextField.getText(), "Year")) {
            cardExpiryYearTextField.getStyleClass().remove("error");
            cardExpiryYearTextField.getStyleClass().add("error");
            isReady = false;
        }
        if (CVVTextField.getText().length() != 0 && !isValidValue(CVVTextField.getText(), "CVV")) {
            CVVTextField.getStyleClass().remove("error");
            CVVTextField.getStyleClass().add("error");
            isReady = false;
        }
        if (phoneTextField.getText().length() != 0 && !isValidValue(phoneTextField.getText(), "Phone")) {
            phoneTextField.getStyleClass().remove("error");
            phoneTextField.getStyleClass().add("error");
            isReady = false;
        }
        if (eMailTextField.getText().length() != 0 && !isValidValue(eMailTextField.getText(), "Email")) {
            eMailTextField.getStyleClass().remove("error");
            eMailTextField.getStyleClass().add("error");
            isReady = false;
        }
        if (postNumberTextField.getText().length() != 0 && !isValidValue(postNumberTextField.getText(), "PostNumber")) {
            postNumberTextField.getStyleClass().remove("error");
            postNumberTextField.getStyleClass().add("error");
            isReady = false;
        }
        return isReady;
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

    private void removeCardNumberError() {
        cardNumberTextField_1.getStyleClass().remove("error");
        cardNumberTextField_2.getStyleClass().remove("error");
        cardNumberTextField_3.getStyleClass().remove("error");
        cardNumberTextField_4.getStyleClass().remove("error");
    }

    private void setCardNumberError() {
        removeCardNumberError();
        cardNumberTextField_1.getStyleClass().add("error");
        cardNumberTextField_2.getStyleClass().add("error");
        cardNumberTextField_3.getStyleClass().add("error");
        cardNumberTextField_4.getStyleClass().add("error");
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
            case 2 -> fullLengthCardNumber.substring(4, 8);
            case 3 -> fullLengthCardNumber.substring(8, 12);
            case 4 -> fullLengthCardNumber.substring(12, 16);
            default -> "";
        };
    }

    private boolean isInteger(String str) {
        try {
            Long.parseLong(str);
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

    private String getCVV(int CVV) {
        if (CVV == 0) {
            return "";
        } else if (CVV < 10) {
            return "00"+CVV;
        } else if (CVV < 100) {
            return "0"+CVV;
        }
        return Integer.toString(CVV);
    }

    // assuming only whitespace, + and - are used in a phone number
    private String getStrippedPhoneNumber(String phone) {
        return phone.replace("-", "").replace("+", "").replaceAll("\\s","");
    }

    // format phone number into 072-222 33 44
    private String getFormattedPhoneNumber(String phone) {
        phone = getStrippedPhoneNumber(phone);
        StringBuilder sb = new StringBuilder();

        switch (phone.length()) {
            case 9:
                sb.append("0").append(phone, 0, 2).append("-")
                        .append(phone, 2, 5).append(" ").append(phone, 5, 7)
                        .append(" ").append(phone, 7, 9);
                break;
            case 10:
                sb.append(phone, 0, 3).append("-").append(phone,3, 6)
                        .append(" ").append(phone, 6, 8).append(" ").append(phone, 8, 10);
                break;
            case 11:
                sb.append("0").append(phone, 2, 4).append("-").append(phone, 4, 7)
                        .append(" ").append(phone, 7, 9).append(" ").append(phone, 9, 11);
        }
        return sb.toString();
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

    // should probably divide this into different methods
    private boolean isValidValue(String value, String textField) {
        switch (textField) {
            case "PostNumber": {
                if (isInteger(value)) {
                    int postNumber = Integer.parseInt(value);
                    return postNumber > 9999;
                }
                return false;
            }
            case "Email": {
                String[] strArr1 = value.split("@");
                if (strArr1.length == 2) {
                    String[] strArr2 = strArr1[1].split("\\.");
                    return strArr2.length == 2 && strArr2[1].length() > 1;
                }
                return false;
            }
            case "Phone": { // mobile phone number format
                String strippedValue = getStrippedPhoneNumber(value);
                if (isInteger(strippedValue)) {
                    switch (strippedValue.length()) {
                        case 11: return strippedValue.startsWith("467");
                        case 10: return strippedValue.startsWith("07");
                        case 9: return strippedValue.startsWith("7");
                    }
                }
                return false;
            }
            case "CardType": {
                return checkCardType(value).length() != 0;
            }
            case "Month": {
                if (isInteger(value)) {
                    int month = Integer.parseInt(value);
                    return month > 0 && month <= 12;
                }
                return false;
            }
            case "Year": {
                if (isInteger(value)) {
                    int year = Integer.parseInt(value);
                    return year >= 20;
                }
                return false;
            }
            case "CVV": {
                if (isInteger(value)) {
                    int CVV = Integer.parseInt(value);
                    return CVV != 0;
                }
                return false;
            }
            default: return false;
        }
    }
}
