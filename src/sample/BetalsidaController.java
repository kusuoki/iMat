package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.*;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.*;

public class BetalsidaController implements Initializable, ShoppingCartListener {

        private final Model model = Model.getInstance();
        @FXML private AnchorPane anchorPaneBekraftaKundvagn;
        @FXML private AnchorPane anchorPaneKontakt;
        @FXML private AnchorPane anchorPaneBetalningsuppgifter;
        @FXML private AnchorPane anchorPaneSlutfor;
        @FXML private AnchorPane anchorPaneTack;
        @FXML private ProgressBar progressBar;
        @FXML private Pane buttonTidigareKop;
        @FXML private RadioButton radioHemLeverans;
        @FXML private RadioButton radioAffarLeverans;
        @FXML private ToggleGroup leveransToggleGroup = new ToggleGroup();
        @FXML private RadioButton radioTid10;
        @FXML private RadioButton radioTid12;
        @FXML private RadioButton radioTid20;
        @FXML private ToggleGroup tidToggleGroup = new ToggleGroup();
        @FXML private ComboBox comboHem;
        @FXML private ComboBox comboAffar;
        @FXML private FlowPane flowPaneBekrafta;
        @FXML private Label labelEmail;
        @FXML private Label labelTelefonnummer;
        @FXML private Label labelFirstname;
        @FXML private Label labelLastname;
        @FXML private Label labelAdress;
        @FXML private Label labelStad;
        @FXML private Label labelPostnummer;
        @FXML private Label labelLeverans;
        @FXML private ImageView cardTypeImageView;
        @FXML private Label labelKortnummer;
        @FXML private Label labelExpiring;
        @FXML private Label labelCVC;
        @FXML private TextField textfieldFirstname;
        @FXML private TextField textfieldLastname;
        @FXML private TextField textfieldAdress;
        @FXML private TextField textfieldStad;
        @FXML private TextField textfieldPostnummer;
        @FXML private TextField textfieldEmail;
        @FXML private TextField textfieldTelefonnummer;
        @FXML private TextField textfieldKortnummer1;
        @FXML private TextField textfieldKortnummer2;
        @FXML private TextField textfieldKortnummer3;
        @FXML private TextField textfieldKortnummer4;
        @FXML private TextField textfieldExpiring1;
        @FXML private TextField textfieldExpiring2;
        @FXML private TextField textfieldCVC;
        private ShoppingCart shoppingCart = model.getShoppingCart();
        private Map<String, BetalsidaItem> betalsidaItemMap = new HashMap<String, BetalsidaItem>();
        private Parent mainPage;
        private Stage stage;
        private TextField nextTextfield = textfieldFirstname;
        private Model handler = Model.getInstance();
        private CreditCard card;
        private Customer customer;
        private Parent listPage;
        private Parent earlierPurchases;
        private Parent customerServicePage;
        private Parent paymentPage;
        private BackButton backButton = BackButton.getBackButton();

        //Används för att sätta denna till kontroller för mainpage.fxml
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                card = handler.getCreditCard();
                customer = handler.getCustomer();

                comboHem.getItems().removeAll(comboHem.getItems());
                comboHem.getItems().addAll("Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag");
                comboHem.getSelectionModel().select("Måndag");
                shoppingCart.addShoppingCartListener(this::shoppingCartChanged);

                comboHem.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                //model.setWeekday(newValue);
                        }
                });

                comboAffar.getItems().removeAll(comboAffar.getItems());
                comboAffar.getItems().addAll("iMat Johanneberg", "iMat Korsvägen", "iMat Gårda", "iMat Frölunda", "iMat Angered");
                comboAffar.getSelectionModel().select("iMat Johanneberg");

                comboAffar.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                //model.setStore(newValue);
                        }
                });

                radioHemLeverans.setSelected(true);
                radioHemLeverans.setToggleGroup(leveransToggleGroup);
                radioAffarLeverans.setToggleGroup(leveransToggleGroup);
                comboAffar.setDisable(true);
                leveransToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

                        @Override
                        public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                                if (leveransToggleGroup.getSelectedToggle() != null) {
                                        RadioButton selected = (RadioButton) leveransToggleGroup.getSelectedToggle();
                                        //model.setLeverans(selected.getText());
                                }
                                if (radioHemLeverans.isSelected())
                                {
                                        comboAffar.setDisable(true);
                                        comboHem.setDisable(false);
                                        radioTid10.setDisable(false);
                                        radioTid12.setDisable(false);
                                        radioTid20.setDisable(false);
                                }
                                else if (radioAffarLeverans.isSelected())
                                {
                                        comboHem.setDisable(true);
                                        comboAffar.setDisable(false);
                                        radioTid10.setDisable(true);
                                        radioTid12.setDisable(true);
                                        radioTid20.setDisable(true);
                                }
                        }
                });


                radioTid10.setSelected(true);
                radioTid10.setToggleGroup(tidToggleGroup);
                radioTid12.setToggleGroup(tidToggleGroup);
                radioTid20.setToggleGroup(tidToggleGroup);
                tidToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

                        @Override
                        public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                                if (tidToggleGroup.getSelectedToggle() != null) {
                                        RadioButton selected = (RadioButton) tidToggleGroup.getSelectedToggle();

                                        //model.setTime(selected.getText());
                                }
                        }
                });

                textfieldFirstname.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (isFocus) {
                                nextTextfield = textfieldLastname;
                        }
                });
                textfieldLastname.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (isFocus) {
                                nextTextfield = textfieldAdress;
                        }
                });
                textfieldAdress.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (isFocus) {
                                nextTextfield = textfieldStad;
                        }
                });
                textfieldStad.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (isFocus) {
                                nextTextfield = textfieldPostnummer;
                        }
                });
                //--------------------------------------------------------------------------------------------
                textfieldPostnummer.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (isFocus) {
                                nextTextfield = textfieldEmail;
                        } else if (textfieldPostnummer.getText().length() != 0) {
                                textfieldPostnummer.getStyleClass().remove("error");
                                if (!isValidValue(textfieldPostnummer.getText(), "PostNumber")) {
                                        textfieldPostnummer.getStyleClass().add("error");
                                }
                        }
                });
                textfieldEmail.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (isFocus) {
                                nextTextfield = textfieldTelefonnummer;
                        } else if (textfieldEmail.getText().length() != 0) {
                                textfieldEmail.getStyleClass().remove("error");
                                if (!isValidValue(textfieldEmail.getText(), "Email")) {
                                        textfieldEmail.getStyleClass().add("error");
                                }
                        }
                });
                textfieldTelefonnummer.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (isFocus) {
                                nextTextfield = textfieldKortnummer1;
                        } else if (textfieldTelefonnummer.getText().length() != 0) {
                                textfieldTelefonnummer.getStyleClass().remove("error");
                                if (isValidValue(textfieldTelefonnummer.getText(), "Phone")) {
                                        textfieldTelefonnummer.setText(getFormattedPhoneNumber(textfieldTelefonnummer.getText()));
                                } else {
                                        textfieldTelefonnummer.getStyleClass().add("error");
                                }
                        }
                });
                textfieldKortnummer1.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (textfieldKortnummer1.getText().length() != 0 && !isFocus) {
                                removeCardNumberError();
                                if (!isValidValue(textfieldKortnummer1.getText(), "CardType") || textfieldKortnummer1.getText().length() != 4) {
                                        setCardNumberError();
                                }
                        }
                });
                textfieldKortnummer2.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (textfieldKortnummer2.getText().length() != 0 && !isFocus) {
                                removeCardNumberError();
                                if (!isValidValue(textfieldKortnummer1.getText(), "CardType") || textfieldKortnummer2.getText().length() != 4) {
                                        setCardNumberError();
                                }
                        }
                });
                textfieldKortnummer3.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (textfieldKortnummer3.getText().length() != 0 && !isFocus) {
                                removeCardNumberError();
                                if (!isValidValue(textfieldKortnummer1.getText(), "CardType") || textfieldKortnummer3.getText().length() != 4) {
                                        setCardNumberError();
                                }
                        }
                });
                textfieldKortnummer4.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (textfieldKortnummer4.getText().length() != 0 && !isFocus) {
                                removeCardNumberError();
                                if (!isValidValue(textfieldKortnummer1.getText(), "CardType") || textfieldKortnummer4.getText().length() != 4) {
                                        setCardNumberError();
                                }
                        }
                });
                textfieldExpiring1.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (textfieldExpiring1.getText().length() != 0 && !isFocus) {
                                textfieldExpiring1.getStyleClass().remove("error");
                                if (isValidValue(textfieldExpiring1.getText(), "Month")) {
                                        textfieldExpiring1.setText(getMonth(Integer.parseInt(textfieldExpiring1.getText())));
                                } else {
                                        textfieldExpiring1.getStyleClass().add("error");
                                }
                        }
                });
                textfieldExpiring2.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (textfieldExpiring2.getText().length() != 0 && !isFocus) {
                                textfieldExpiring2.getStyleClass().remove("error");
                                if (!isValidValue(textfieldExpiring2.getText(), "Year")) {
                                        textfieldExpiring2.getStyleClass().add("error");
                                }
                        }
                });
                textfieldCVC.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (textfieldCVC.getText().length() != 0 && !isFocus) {
                                textfieldCVC.getStyleClass().remove("error");
                                if (isValidValue(textfieldCVC.getText(), "CVV")) {
                                        textfieldCVC.setText(getCVV(Integer.parseInt(textfieldCVC.getText())));
                                } else {
                                        textfieldCVC.getStyleClass().add("error");
                                }
                        }
                });
                //---------------------------------------------------------------------------------------------
                textfieldPostnummer.textProperty().addListener((observable, oldValue, newValue) -> {
                        numberOnlyTextField(textfieldPostnummer, oldValue, newValue, 5);
                        if (isValidValue(newValue, "PostNumber") || newValue.length() == 0) {
                                textfieldPostnummer.getStyleClass().remove("error");
                        }
                });
                textfieldEmail.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (isValidValue(newValue, "Email") || newValue.length() == 0) {
                                textfieldEmail.getStyleClass().remove("error");
                        }
                });
                textfieldTelefonnummer.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (isValidValue(newValue, "Phone") || newValue.length() == 0) {
                                textfieldTelefonnummer.getStyleClass().remove("error");
                        }
                });
                textfieldKortnummer1.textProperty().addListener((observable, oldValue, newValue) -> {
                        numberOnlyTextField(textfieldKortnummer1, oldValue, newValue, 4);

                        String cardType = checkCardType(newValue);
                        cardTypeImageView.setImage(getCardTypeImage(cardType));

                        if (getCurrentCardNumber().length() == 0) {
                                removeCardNumberError();
                        } else if (getCurrentCardNumber().length() == 16 && isValidValue(newValue, "CardType")) {
                                removeCardNumberError();
                        }

                        if (newValue.length() == 4 && oldValue.length() != 5 && isInteger(newValue)) {
                                textfieldKortnummer2.requestFocus();
                                textfieldKortnummer2.positionCaret(4);
                        }
                });
                textfieldKortnummer2.textProperty().addListener((observable, oldValue, newValue) -> {
                        numberOnlyTextField(textfieldKortnummer2, oldValue, newValue, 4);

                        if (getCurrentCardNumber().length() == 0){
                                removeCardNumberError();
                        }

                        if (newValue.length() == 4 && oldValue.length() != 5 && isInteger(newValue)) {
                                textfieldKortnummer3.requestFocus();
                                textfieldKortnummer3.positionCaret(4);
                        }
                });
                textfieldKortnummer3.textProperty().addListener((observable, oldValue, newValue) -> {
                        numberOnlyTextField(textfieldKortnummer3, oldValue, newValue, 4);

                        if (getCurrentCardNumber().length() == 0){
                                removeCardNumberError();
                        }
                        if (newValue.length() == 4 && oldValue.length() != 5 && isInteger(newValue)) {
                                textfieldKortnummer4.requestFocus();
                                textfieldKortnummer4.positionCaret(4);
                        }
                });
                textfieldKortnummer4.textProperty().addListener((observable, oldValue, newValue) -> {
                        numberOnlyTextField(textfieldKortnummer4, oldValue, newValue, 4);

                        if (getCurrentCardNumber().length() == 0){
                                removeCardNumberError();
                        }

                        if (newValue.length() == 4 && oldValue.length() != 5 && isInteger(newValue)) {
                                textfieldExpiring1.requestFocus();
                                textfieldExpiring1.positionCaret(2);
                        }
                });
                textfieldExpiring1.textProperty().addListener((observable, oldValue, newValue) -> {
                        numberOnlyTextField(textfieldExpiring1, oldValue, newValue, 2);

                        if (isValidValue(newValue, "Month") || newValue.length() == 0) {
                                textfieldExpiring1.getStyleClass().remove("error");
                        }

                        if (isInteger(newValue)) {
                                int month = Integer.parseInt(newValue);
                                if (month <= 12 && newValue.length() == 2 && oldValue.length() != 3) {
                                        textfieldExpiring2.requestFocus();
                                        textfieldExpiring2.positionCaret(2);
                                }
                        }
                });
                textfieldExpiring2.textProperty().addListener((observable, oldValue, newValue) -> {
                        numberOnlyTextField(textfieldExpiring2, oldValue, newValue, 2);

                        if (isValidValue(newValue, "Year") || newValue.length() == 0) {
                                textfieldExpiring2.getStyleClass().remove("error");
                        }

                        if (isInteger(newValue)) {
                                int year = Integer.parseInt(newValue);

                                // hardcoded year value lmao
                                if (year >= 20 && newValue.length() == 2 && oldValue.length() != 3) {
                                        textfieldCVC.requestFocus();
                                        textfieldCVC.positionCaret(3);
                                }
                        }
                });
                textfieldCVC.textProperty().addListener((observable, oldValue, newValue) -> {
                        numberOnlyTextField(textfieldCVC, oldValue, newValue, 3);

                        if (isValidValue(newValue, "CVV") || newValue.length() == 0) {
                                textfieldCVC.getStyleClass().remove("error");
                        }

                });
                
                anchorPaneBekraftaKundvagn.toFront();
                progressBar.setProgress(0);
                buttonTidigareKop.toFront();
                progressBar.toFront();
                getShoppingCart();
                updateInformation();
        }
        //setter för stage och mainpage root
        public void setStage(Stage stage,Parent mainPage){
             this.stage=stage;
             this.mainPage=mainPage;

        }
        //När man trycker på hemknappen
        @FXML
        public void onHomeClick(ActionEvent event){
                stage.getScene().setRoot(mainPage);
                model.getShoppingCart().clear();
                anchorPaneBekraftaKundvagn.toFront();

        }

        //När man klickar på första nästa
        @FXML
        public void onNextClick1() {
            anchorPaneKontakt.toFront();
            progressBar.setProgress(0.25);
            buttonTidigareKop.toFront();
            progressBar.toFront();
        }

        //När man klickar på andra nästa
        @FXML
        public void onNextClick2() {
                anchorPaneBetalningsuppgifter.toFront();
                progressBar.setProgress(0.5);
                progressBar.toFront();
                buttonTidigareKop.toFront();
        }

        //När man klickar på andra tillbaka
        @FXML
        public void onBackClick2() {
                anchorPaneBekraftaKundvagn.toFront();
                progressBar.setProgress(0);
                progressBar.toFront();
                buttonTidigareKop.toFront();
        }

        //När man klickar på tredje nästa
        @FXML
        public void onNextClick3() {
                anchorPaneSlutfor.toFront();
                progressBar.setProgress(0.75);
                progressBar.toFront();
                buttonTidigareKop.toFront();
                setData();
        }

        //När man klickar på tredje tillbaka
        @FXML
        public void onBackClick3() {
                anchorPaneKontakt.toFront();
                progressBar.setProgress(0.25);
                progressBar.toFront();
                buttonTidigareKop.toFront();
        }

        //När man klickar på fjärde tillbaka
        @FXML
        public void onBackClick4() {
                anchorPaneBetalningsuppgifter.toFront();
                progressBar.setProgress(0.5);
                progressBar.toFront();
                buttonTidigareKop.toFront();
        }

        //När man klickar på köp
        @FXML
        public void onBuyClick() {
                progressBar.setProgress(1);
                progressBar.toFront();
                anchorPaneTack.toFront();
        }

        public void getShoppingCart()
        {
                if (shoppingCart.getItems().size() != 0)
                {

                        for (ShoppingItem shoppingItem : model.getShoppingCart().getItems()) {
                                BetalsidaItem betalsidaItem = new BetalsidaItem(shoppingItem, model);
                                betalsidaItemMap.put(shoppingItem.getProduct().getName(), betalsidaItem);
                        }
                        flowPaneBekrafta.getChildren().clear();
                        List<ShoppingItem> shoppingItems = model.getShoppingCart().getItems();
                        for (ShoppingItem shoppingItem : shoppingItems)
                        {
                                flowPaneBekrafta.getChildren().add(betalsidaItemMap.get(shoppingItem.getProduct().getName()));
                        }
                }

        }

        public Map<String, BetalsidaItem> getHashMap()
        {
                return betalsidaItemMap;
        }

        @Override
        public void shoppingCartChanged(CartEvent cartEvent) {
                getShoppingCart();
        }


        public void setData() {
                labelEmail.setText(textfieldEmail.getText());
                labelTelefonnummer.setText(textfieldTelefonnummer.getText());
                labelFirstname.setText(textfieldFirstname.getText());
                labelLastname.setText(textfieldLastname.getText());
                labelAdress.setText(textfieldAdress.getText());
                labelStad.setText(textfieldStad.getText());
                labelPostnummer.setText(textfieldPostnummer.getText());
                //labelLeverans.setText(tex);

                cardTypeImageView.setImage(getCardTypeImage(checkCardType(getCardNumberBySection(1))));
                labelKortnummer.setText(textfieldKortnummer1.getText() + "-" + textfieldKortnummer2.getText() +
                        "-" + textfieldKortnummer3.getText() + "-" + textfieldKortnummer4.getText());
                labelExpiring.setText(textfieldExpiring1.getText() + "/" + textfieldExpiring2.getText());
                labelCVC.setText(textfieldCVC.getText());
        }

        //public void getData {

        //}

        @FXML
        public void save() {
                if (isReadyToSave()) {
                        customer.setFirstName(textfieldFirstname.getText());
                        customer.setLastName(textfieldLastname.getText());
                        customer.setAddress(textfieldAdress.getText());
                        customer.setPhoneNumber(getStrippedPhoneNumber(textfieldTelefonnummer.getText()));
                        customer.setEmail(textfieldEmail.getText());
                        customer.setPostAddress(textfieldStad.getText());
                        customer.setPostCode(textfieldPostnummer.getText());
                        card.setCardNumber(getCurrentCardNumber());
                        card.setValidMonth((textfieldExpiring1.getText().length() == 0) ? 0 : Integer.parseInt(textfieldExpiring1.getText()));
                        card.setValidYear((textfieldExpiring2.getText().length() == 0) ? 0 : Integer.parseInt(textfieldExpiring2.getText()));
                        card.setVerificationCode((textfieldCVC.getText().length() == 0) ? 0 : Integer.parseInt(textfieldCVC.getText()));
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

                textfieldFirstname.setText(customer.getFirstName());
                textfieldLastname.setText(customer.getLastName());
                textfieldAdress.setText(customer.getAddress());
                textfieldStad.setText(customer.getPostAddress());
                textfieldPostnummer.setText(customer.getPostCode());
                textfieldEmail.setText(customer.getEmail());
                textfieldTelefonnummer.setText(getFormattedPhoneNumber(customer.getPhoneNumber()));
                textfieldKortnummer1.setText(getCardNumberBySection(1));
                textfieldKortnummer2.setText(getCardNumberBySection(2));
                textfieldKortnummer3.setText(getCardNumberBySection(3));
                textfieldKortnummer4.setText(getCardNumberBySection(4));
                cardTypeImageView.setImage(getCardTypeImage(checkCardType(getCardNumberBySection(1))));
                textfieldExpiring2.setText(getYear(card.getValidYear()));
                textfieldExpiring1.setText(getMonth(card.getValidMonth()));
                textfieldCVC.setText(getCVV(card.getVerificationCode()));
        }

        /*
                 * validate all input, ignores empty text field,
                 * and returns true if all text field are ready
                 *
                 * please don't look at this method
        */
        private boolean isReadyToSave() {
                boolean isReady = true;
                int cardNumberLength = getCurrentCardNumber().length();
                if ((cardNumberLength != 0 && !isValidValue(textfieldKortnummer1.getText(), "CardType")) ||
                        (cardNumberLength != 0 && cardNumberLength != 16)) {
                        removeCardNumberError();
                        setCardNumberError();
                        isReady = false;
                }

                if (textfieldExpiring1.getText().length() != 0 && !isValidValue(textfieldExpiring1.getText(), "Month")) {
                        textfieldExpiring1.getStyleClass().remove("error");
                        textfieldExpiring1.getStyleClass().add("error");
                        isReady = false;
                }
                if (textfieldExpiring2.getText().length() != 0 && !isValidValue(textfieldExpiring2.getText(), "Year")) {
                        textfieldExpiring2.getStyleClass().remove("error");
                        textfieldExpiring2.getStyleClass().add("error");
                        isReady = false;
                }
                if (textfieldCVC.getText().length() != 0 && !isValidValue(textfieldCVC.getText(), "CVV")) {
                        textfieldCVC.getStyleClass().remove("error");
                        textfieldCVC.getStyleClass().add("error");
                        isReady = false;
                }
                if (textfieldTelefonnummer.getText().length() != 0 && !isValidValue(textfieldTelefonnummer.getText(), "Phone")) {
                        textfieldTelefonnummer.getStyleClass().remove("error");
                        textfieldTelefonnummer.getStyleClass().add("error");
                        isReady = false;
                }
                if (textfieldEmail.getText().length() != 0 && !isValidValue(textfieldEmail.getText(), "Email")) {
                        textfieldEmail.getStyleClass().remove("error");
                        textfieldEmail.getStyleClass().add("error");
                        isReady = false;
                }
                if (textfieldPostnummer.getText().length() != 0 && !isValidValue(textfieldPostnummer.getText(), "PostNumber")) {
                        textfieldPostnummer.getStyleClass().remove("error");
                        textfieldPostnummer.getStyleClass().add("error");
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
                textfieldKortnummer1.getStyleClass().remove("error");
                textfieldKortnummer2.getStyleClass().remove("error");
                textfieldKortnummer3.getStyleClass().remove("error");
                textfieldKortnummer4.getStyleClass().remove("error");
        }

        private void setCardNumberError() {
                removeCardNumberError();
                textfieldKortnummer1.getStyleClass().add("error");
                textfieldKortnummer2.getStyleClass().add("error");
                textfieldKortnummer3.getStyleClass().add("error");
                textfieldKortnummer4.getStyleClass().add("error");
        }

        private String getCurrentCardNumber() {
                return textfieldKortnummer1.getText()+textfieldKortnummer2.getText()
                        +textfieldKortnummer3.getText()+textfieldKortnummer4.getText();
        }

        /*
                 *   assuming a card number can only be 16 characters long
                 *   returns a string with length 4
        */
        private String getCardNumberBySection(int section) {
                String fullLengthCardNumber = card.getCardNumber();
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

