package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

import java.net.URL;
import java.util.*;

public class BetalsidaController implements Initializable, ShoppingCartListener {

        private final Model model = Model.getInstance();
        @FXML private AnchorPane anchorPaneBekraftaKundvagn;
        @FXML private AnchorPane anchorPaneKontakt;
        @FXML private AnchorPane anchorPaneBetalningsuppgifter;
        @FXML private AnchorPane anchorPaneSlutfor;
        @FXML private AnchorPane anchorPaneTack;
        @FXML private Pane buttonTidigareKop;
        @FXML private RadioButton radioHemLeverans;
        @FXML private RadioButton radioAffarLeverans;
        @FXML private ToggleGroup leveransToggleGroup = new ToggleGroup();
        @FXML private Spinner spinnerTid;
        @FXML private ComboBox comboHem;
        @FXML private ComboBox comboAffar;
        @FXML private FlowPane flowPaneBekrafta;
        @FXML private FlowPane slutforFlowPane;
        @FXML private Label labelEmail;
        @FXML private Label labelTelefonnummer;
        @FXML private Label labelFirstname;
        @FXML private Label labelLastname;
        @FXML private Label labelAdress;
        @FXML private Label labelStad;
        @FXML private Label labelPostnummer;
        @FXML private Label labelLeverans;
        @FXML private Label labelSlutforSlutpris;
        @FXML private ImageView cardTypeImageView;
        @FXML private ImageView cardTypeImageViewAgain;
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
        @FXML private CheckBox spara;
        @FXML private Label labelErrorMessage;
        @FXML private Label labelTotalPrice;
        @FXML private Label labelAmountOfProduct;
        @FXML private Pane paneEmptyCart;
        @FXML private Button buttonNext1;
        @FXML private Button buttonNext2;
        @FXML private Button buttonNext3;
        private ShoppingCart shoppingCart = model.getShoppingCart();
        private Map<Integer, BetalsidaItem> betalsidaItemMap = new HashMap<>();
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
        private KontoinstallningController kontoinstallningController;
        private TextField currentErrorTextField;

        @FXML
        Button buttonBack1;
        @FXML
        Button buttonBack2;
        @FXML
        Button buttonBack3;
        @FXML
        Button buttonBack4;
        @FXML
        Button buttonClear;
        @FXML
        Button buttonBuy;


        //Används för att sätta denna till kontroller för mainpage.fxml
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                card = handler.getCreditCard();
                customer = handler.getCustomer();
                //<editor-fold desc="???? region, ignore pls">
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
                                        spinnerTid.setDisable(false);
                                }
                                else if (radioAffarLeverans.isSelected())
                                {
                                        comboHem.setDisable(true);
                                        comboAffar.setDisable(false);
                                        spinnerTid.setDisable(true);
                                }
                        }
                });

            SpinnerValueFactory<Integer> maxPrices = new SpinnerValueFactory.IntegerSpinnerValueFactory(6, 22, 12, 1);
            spinnerTid.setValueFactory(maxPrices);
            spinnerTid.setEditable(true);

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
                                        labelErrorMessage.setText("Ogiltigt postnummer!");
                                        labelErrorMessage.toFront();
                                        currentErrorTextField = textfieldPostnummer;
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
                                        labelErrorMessage.setText("Ogiltigt e-mail adress!");
                                        labelErrorMessage.toFront();
                                        currentErrorTextField = textfieldEmail;
                                }
                        }
                });
                textfieldTelefonnummer.setOnAction(null);
                textfieldTelefonnummer.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (textfieldTelefonnummer.getText().length() != 0 && !isFocus) {
                                textfieldTelefonnummer.getStyleClass().remove("error");
                                if (isValidValue(textfieldTelefonnummer.getText(), "Phone")) {
                                        textfieldTelefonnummer.setText(getFormattedPhoneNumber(textfieldTelefonnummer.getText()));
                                } else {
                                        textfieldTelefonnummer.getStyleClass().add("error");
                                        labelErrorMessage.setText("Ogiltigt telefonnummer!");
                                        labelErrorMessage.toFront();
                                        currentErrorTextField = textfieldTelefonnummer;
                                }
                        }
                });
                textfieldKortnummer1.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (textfieldKortnummer1.getText().length() != 0 && !isFocus) {
                                removeCardNumberError();
                                if (!isValidValue(textfieldKortnummer1.getText(), "CardType") || textfieldKortnummer1.getText().length() != 4) {
                                        setCardNumberError();
                                        labelErrorMessage.setText("Ogiltigt kortnummmer! Vi accepterar bara Visa eller Mastercard.");
                                        labelErrorMessage.toFront();
                                        currentErrorTextField = textfieldKortnummer1;
                                }
                        }
                });
                textfieldKortnummer2.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (textfieldKortnummer2.getText().length() != 0 && !isFocus) {
                                removeCardNumberError();
                                if (!isValidValue(textfieldKortnummer1.getText(), "CardType") || textfieldKortnummer2.getText().length() != 4) {
                                        setCardNumberError();
                                        labelErrorMessage.setText("Ogiltigt kortnummmer! Vi accepterar bara Visa eller Mastercard.");
                                        labelErrorMessage.toFront();
                                        currentErrorTextField = textfieldKortnummer1;
                                }
                        }
                });
                textfieldKortnummer3.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (textfieldKortnummer3.getText().length() != 0 && !isFocus) {
                                removeCardNumberError();
                                if (!isValidValue(textfieldKortnummer1.getText(), "CardType") || textfieldKortnummer3.getText().length() != 4) {
                                        setCardNumberError();
                                        labelErrorMessage.setText("Ogiltigt kortnummmer! Vi accepterar bara Visa eller Mastercard.");
                                        labelErrorMessage.toFront();
                                        currentErrorTextField = textfieldKortnummer1;
                                }
                        }
                });
                textfieldKortnummer4.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (textfieldKortnummer4.getText().length() != 0 && !isFocus) {
                                removeCardNumberError();
                                if (!isValidValue(textfieldKortnummer1.getText(), "CardType") || textfieldKortnummer4.getText().length() != 4) {
                                        setCardNumberError();
                                        labelErrorMessage.setText("Ogiltigt kortnummmer! Vi accepterar bara Visa eller Mastercard.");
                                        labelErrorMessage.toFront();
                                        currentErrorTextField = textfieldKortnummer1;
                                }
                        } else if (isFocus) {
                                nextTextfield = textfieldExpiring1;
                        }
                });
                textfieldExpiring1.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (textfieldExpiring1.getText().length() != 0 && !isFocus) {
                                textfieldExpiring1.getStyleClass().remove("error");
                                if (isValidValue(textfieldExpiring1.getText(), "Month")) {
                                        textfieldExpiring1.setText(getMonth(Integer.parseInt(textfieldExpiring1.getText())));
                                } else {
                                        textfieldExpiring1.getStyleClass().add("error");
                                        labelErrorMessage.setText("Ogiltig månad!");
                                        labelErrorMessage.toFront();
                                        currentErrorTextField = textfieldExpiring1;
                                }
                        } else if (isFocus) {
                                nextTextfield = textfieldExpiring2;
                        }
                });
                textfieldExpiring2.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (textfieldExpiring2.getText().length() != 0 && !isFocus) {
                                textfieldExpiring2.getStyleClass().remove("error");
                                if (!isValidValue(textfieldExpiring2.getText(), "Year")) {
                                        textfieldExpiring2.getStyleClass().add("error");
                                        labelErrorMessage.setText("Ogiltigt årtal!");
                                        labelErrorMessage.toFront();
                                        currentErrorTextField = textfieldExpiring2;
                                }
                        }  else if (isFocus) {
                                nextTextfield = textfieldCVC;
                        }
                });
                textfieldCVC.focusedProperty().addListener((arg0, oldPropertyValue, isFocus) -> {
                        if (textfieldCVC.getText().length() != 0 && !isFocus) {
                                textfieldCVC.getStyleClass().remove("error");
                                if (isValidValue(textfieldCVC.getText(), "CVV")) {
                                        textfieldCVC.setText(getCVV(Integer.parseInt(textfieldCVC.getText())));
                                } else {
                                        textfieldCVC.getStyleClass().add("error");
                                        labelErrorMessage.setText("CVV nummer kan inte vara tomt!");
                                        labelErrorMessage.toFront();
                                        currentErrorTextField = textfieldCVC;
                                }
                        }
                });
                //---------------------------------------------------------------------------------------------
                textfieldFirstname.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (textfieldFirstname.getText().length() > 0) {
                                textfieldFirstname.getStyleClass().remove("error");
                        }
                        if (currentErrorTextField == textfieldFirstname && isTextFieldNotEmpty(textfieldFirstname, "Förnamn kan inte vara tomt!")) {
                               labelErrorMessage.toBack();
                        }
                });
                textfieldLastname.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (textfieldLastname.getText().length() > 0) {
                                textfieldLastname.getStyleClass().remove("error");
                        }
                        if (currentErrorTextField == textfieldLastname && isTextFieldNotEmpty(textfieldLastname, "Efternamn kan inte vara tomt!")) {
                                labelErrorMessage.toBack();
                        }
                });
                textfieldAdress.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (textfieldAdress.getText().length() > 0) {
                                textfieldAdress.getStyleClass().remove("error");
                        }
                        if (currentErrorTextField == textfieldAdress && isTextFieldNotEmpty(textfieldAdress, "Gatuadress kan inte vara tom!")) {
                                labelErrorMessage.toBack();
                        }
                });
                textfieldStad.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (textfieldStad.getText().length() > 0) {
                                textfieldStad.getStyleClass().remove("error");
                        }
                        if (currentErrorTextField == textfieldStad && isTextFieldNotEmpty(textfieldStad, "Stad kan inte vara tom!")) {
                                labelErrorMessage.toBack();
                        }
                });
                textfieldPostnummer.textProperty().addListener((observable, oldValue, newValue) -> {
                        numberOnlyTextField(textfieldPostnummer, oldValue, newValue, 5);
                        if (isValidValue(newValue, "PostNumber") || newValue.length() == 0) {
                                textfieldPostnummer.getStyleClass().remove("error");
                        }
                        if (currentErrorTextField == textfieldPostnummer && checkValidValue(textfieldPostnummer, "PostNumber", "Ogiltigt postnummer!")) {
                                labelErrorMessage.toBack();
                        }
                });
                textfieldEmail.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (isValidValue(newValue, "Email") || newValue.length() == 0) {
                                textfieldEmail.getStyleClass().remove("error");
                        }
                        if (currentErrorTextField == textfieldEmail && checkValidValue(textfieldEmail, "Email", "Ogiltigt e-mail adress!")) {
                                labelErrorMessage.toBack();
                        }
                });
                textfieldTelefonnummer.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (isValidValue(newValue, "Phone") || newValue.length() == 0) {
                                textfieldTelefonnummer.getStyleClass().remove("error");
                        }
                        if (currentErrorTextField == textfieldTelefonnummer && checkValidValue(textfieldTelefonnummer, "Phone", "Ogiltigt telefonnummer!")) {
                                labelErrorMessage.toBack();
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

                        if (currentErrorTextField == textfieldKortnummer1 && isCardNumberValid()) {
                                removeCardNumberError();
                                labelErrorMessage.toBack();
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

                        if (currentErrorTextField == textfieldKortnummer1 && isCardNumberValid()) {
                                removeCardNumberError();
                                labelErrorMessage.toBack();
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

                        if (currentErrorTextField == textfieldKortnummer1 && isCardNumberValid()) {
                                removeCardNumberError();
                                labelErrorMessage.toBack();
                        }
                });
                textfieldKortnummer4.textProperty().addListener((observable, oldValue, newValue) -> {
                        numberOnlyTextField(textfieldKortnummer4, oldValue, newValue, 4);

                        if (getCurrentCardNumber().length() == 0){
                                removeCardNumberError();
                        }

                        /*
                        if (newValue.length() == 4 && oldValue.length() != 5 && isInteger(newValue)) {
                                textfieldExpiring1.requestFocus();
                                textfieldExpiring1.positionCaret(2);
                        }*/

                        if (currentErrorTextField == textfieldKortnummer1 && isCardNumberValid()) {
                                removeCardNumberError();
                                labelErrorMessage.toBack();
                        }
                });

                textfieldExpiring1.textProperty().addListener((observable, oldValue, newValue) -> {
                        numberOnlyTextField(textfieldExpiring1, oldValue, newValue, 2);

                        if (isValidValue(newValue, "Month") || newValue.length() == 0) {
                                textfieldExpiring1.getStyleClass().remove("error");
                        }

                        /*
                        if (isInteger(newValue)) {
                                int month = Integer.parseInt(newValue);
                                if (month <= 12 && newValue.length() == 2 && oldValue.length() != 3) {
                                        textfieldExpiring2.requestFocus();
                                        textfieldExpiring2.positionCaret(2);
                                }
                        }*/

                        if (currentErrorTextField == textfieldExpiring1 && checkValidValue(textfieldExpiring1, "Month", "Ogiltig månad!")) {
                                labelErrorMessage.toBack();
                        }
                });
                textfieldExpiring2.textProperty().addListener((observable, oldValue, newValue) -> {
                        numberOnlyTextField(textfieldExpiring2, oldValue, newValue, 2);

                        if (isValidValue(newValue, "Year") || newValue.length() == 0) {
                                textfieldExpiring2.getStyleClass().remove("error");
                        }

                        /*
                        if (isInteger(newValue)) {
                                int year = Integer.parseInt(newValue);

                                // hardcoded year value lmao
                                if (year >= 20 && newValue.length() == 2 && oldValue.length() != 3) {
                                        textfieldCVC.requestFocus();
                                        textfieldCVC.positionCaret(3);
                                }
                        }*/

                        if (currentErrorTextField == textfieldExpiring2 && checkValidValue(textfieldExpiring2, "Year", "Ogiltigt årtal!")) {
                                labelErrorMessage.toBack();
                        }
                });
                textfieldCVC.textProperty().addListener((observable, oldValue, newValue) -> {
                        numberOnlyTextField(textfieldCVC, oldValue, newValue, 3);

                        if (isValidValue(newValue, "CVV") || newValue.length() == 0) {
                                textfieldCVC.getStyleClass().remove("error");
                        }

                        if (currentErrorTextField == textfieldCVC && checkValidValue(textfieldCVC, "CVV", "CVV nummer kan inte vara tomt!")) {
                                labelErrorMessage.toBack();
                        }
                });
                //</editor-fold>

                anchorPaneBekraftaKundvagn.toFront();
                buttonTidigareKop.toFront();
                updateShoppingCart();
                updateInformation();

                initButtons();
        }

        //Init för alla ikoner på knappar
        private void initButtons() {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("Icons/ic_remove_shopping_cart_white_24dp.png"));
        buttonClear.setGraphic(new ImageView(image));
        buttonClear.setGraphicTextGap(5);

        image = new Image(getClass().getClassLoader().getResourceAsStream("Icons/ic_arrow_back_white_24dp.png"));
        buttonBack1.setGraphic(new ImageView(image));
        buttonBack1.setGraphicTextGap(5);
        buttonBack2.setGraphic(new ImageView(image));
        buttonBack2.setGraphicTextGap(5);
        buttonBack3.setGraphic(new ImageView(image));
        buttonBack3.setGraphicTextGap(5);
        buttonBack4.setGraphic(new ImageView(image));
        buttonBack4.setGraphicTextGap(5);

        image = new Image(getClass().getClassLoader().getResourceAsStream("Icons/ic_arrow_forward_white_24dp.png"));
        buttonNext1.setGraphic(new ImageView(image));
        buttonNext1.setContentDisplay(ContentDisplay.RIGHT);
        buttonNext1.setGraphicTextGap(5);
        buttonNext2.setGraphic(new ImageView(image));
        buttonNext2.setContentDisplay(ContentDisplay.RIGHT);
        buttonNext2.setGraphicTextGap(5);
        buttonNext3.setGraphic(new ImageView(image));
        buttonNext3.setContentDisplay(ContentDisplay.RIGHT);
        buttonNext3.setGraphicTextGap(5);

        image = new Image(getClass().getClassLoader().getResourceAsStream("Icons/ic_check_circle_black_24dp.png"));
        buttonBuy.setGraphic(new ImageView(image));
        buttonBuy.setGraphicTextGap(5);


        }

        //setter för stage och mainpage root
        public void setStage(Stage stage,Parent mainPage, KontoinstallningController kontoinstallningController){
             this.stage=stage;
             this.mainPage=mainPage;
             this.kontoinstallningController=kontoinstallningController;

        }

        @FXML
        public void onHomeButtonClickIcon(){
                backButton.addToBackList(stage.getScene().getRoot());
                stage.getScene().setRoot(mainPage);
                onBackClick2();
        }

        //När man trycker på hemknappen
        @FXML
        public void onHomeClick(){
                anchorPaneBekraftaKundvagn.toFront();
                buttonTidigareKop.toFront();
                stage.getScene().setRoot(mainPage);
        }

        //När man klickar på första nästa
        @FXML
        public void onNextClick1() {
                if (shoppingCart.getItems().size() != 0) {
                        anchorPaneKontakt.toFront();
                        buttonTidigareKop.toFront();
                }
                updateInformation();
        }

        //När man klickar på andra nästa
        @FXML
        public void onNextClick2() {
                if (isContactInfoCorrect()) {
                        anchorPaneBetalningsuppgifter.toFront();
                        buttonTidigareKop.toFront();
                }
        }

        //När man klickar på andra tillbaka
        @FXML
        public void onBackClick2() {
                anchorPaneBekraftaKundvagn.toFront();
                buttonTidigareKop.toFront();
        }

        //När man klickar på tredje nästa
        @FXML
        public void onNextClick3() {
                if (isPaymentInfoCorrect()) {
                        anchorPaneSlutfor.toFront();
                        buttonTidigareKop.toFront();
                        setData();
                        updateSlutFor();
                }
        }

        //När man klickar på tredje tillbaka
        @FXML
        public void onBackClick3() {
                anchorPaneKontakt.toFront();
                buttonTidigareKop.toFront();
        }

        //När man klickar på fjärde tillbaka
        @FXML
        public void onBackClick4() {
                anchorPaneBetalningsuppgifter.toFront();
                buttonTidigareKop.toFront();
        }

        //När man klickar på köp
        @FXML
        public void onBuyClick() {
                anchorPaneTack.toFront();
                betalsidaItemMap.clear();
                model.placeOrder();
                model.clearShoppingCart();
                if(spara.isSelected())
                {
                        save();
                }
        }

        @FXML
        public void emptyShoppingCart() {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.setHeaderText("Är du säker på att du vill rensa varukorgen?");
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                        model.clearShoppingCart();
                        betalsidaItemMap.clear();
                }
        }

        public void updateSlutFor() {
                List<SlutforItem> items = new ArrayList<>();

                slutforFlowPane.getChildren().clear();
                for (ShoppingItem item : shoppingCart.getItems()) {
                        slutforFlowPane.getChildren().add(0, new SlutforItem(item));
                }

                labelSlutforSlutpris.setText(model.doubleToString(shoppingCart.getTotal()) + " kr");
        }

        public void updateShoppingCart() {
                int amountOfProduct = 0;

                flowPaneBekrafta.getChildren().clear();
                BetalsidaItem betalsidaItem;
                for (ShoppingItem shoppingItem : shoppingCart.getItems()) {
                        betalsidaItem = betalsidaItemMap.get(shoppingItem.getProduct().getProductId());
                        if (betalsidaItem == null) {
                                betalsidaItem = new BetalsidaItem(shoppingItem, this);
                                betalsidaItemMap.put(shoppingItem.getProduct().getProductId(), betalsidaItem);
                        }
                        flowPaneBekrafta.getChildren().add(0, betalsidaItem);
                        amountOfProduct += betalsidaItem.getAmount();
                        betalsidaItem.updateTextFields();
                }
                labelAmountOfProduct.setText(amountOfProduct + " st");
                labelTotalPrice.setText(model.doubleToString(model.getShoppingCart().getTotal()) + " kr");

                if (amountOfProduct != 0) {
                        buttonNext1.getStyleClass().remove("backToStoreButton");
                        buttonNext1.getStyleClass().remove("disabled-button");
                        buttonNext1.getStyleClass().add("backToStoreButton");
                        paneEmptyCart.toBack();
                } else {
                        buttonNext1.getStyleClass().remove("backToStoreButton");
                        buttonNext1.getStyleClass().remove("disabled-button");
                        buttonNext1.getStyleClass().add("disabled-button");
                        paneEmptyCart.toFront();
                }
        }


        public void removeFromMap(BetalsidaItem item) {
                betalsidaItemMap.remove(item.getProduct().getProductId());
        }

        public void removeFromMap(int id) {
                betalsidaItemMap.remove(id);
        }

        @Override
        public void shoppingCartChanged(CartEvent cartEvent) {
                if (!cartEvent.isAddEvent() && !betalsidaItemMap.isEmpty()) {
                        removeFromMap(cartEvent.getShoppingItem().getProduct().getProductId());
                }
                updateShoppingCart();
        }


        //<editor-fold desc="textfield stuff">
        public void setData() {
                labelEmail.setText(textfieldEmail.getText());
                labelTelefonnummer.setText(textfieldTelefonnummer.getText());
                labelFirstname.setText(textfieldFirstname.getText());
                labelLastname.setText(textfieldLastname.getText());
                labelAdress.setText(textfieldAdress.getText());
                labelStad.setText(textfieldStad.getText());
                labelPostnummer.setText(textfieldPostnummer.getText());
                if(radioHemLeverans.isSelected())
                {
                    labelLeverans.setText("Levereras " + comboHem.getValue().toString().toLowerCase() +
                            " vid kl. " + spinnerTid.getValue());

                }
                else if(radioAffarLeverans.isSelected())
                {
                    labelLeverans.setText("Finns redo att hämta imorgon i: " + comboAffar.getValue());
                }
                cardTypeImageView.setImage(getCardTypeImage(checkCardType(textfieldKortnummer1.getText())));
                cardTypeImageViewAgain.setImage(getCardTypeImage(checkCardType(textfieldKortnummer1.getText())));
                labelKortnummer.setText(textfieldKortnummer1.getText() + "-" + textfieldKortnummer2.getText() +
                        "-" + textfieldKortnummer3.getText() + "-" + textfieldKortnummer4.getText());
                labelExpiring.setText(textfieldExpiring1.getText() + "/" + textfieldExpiring2.getText());
                labelCVC.setText(textfieldCVC.getText());
        }

        @FXML
        public void save() {
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
                kontoinstallningController.updateInformation();
        }

        public boolean isPaymentInfoCorrect() {
                if (!isCardNumberValid()) return false;
                if (!checkValidValue(textfieldExpiring1, "Month", "Ogiltig månad!")) return false;
                if (!checkValidValue(textfieldExpiring2, "Year", "Ogiltigt årtal!")) return false;
                if (!checkValidValue(textfieldCVC, "CVV", "CVV nummer kan inte vara tomt!")) return false;

                labelErrorMessage.toBack();
                return true;
        }

        public boolean isContactInfoCorrect() {
                if (!isTextFieldNotEmpty(textfieldFirstname, "Förnamn kan inte vara tomt!")) return false;
                if (!isTextFieldNotEmpty(textfieldLastname, "Efternamn kan inte vara tomt!")) return false;
                if (!isTextFieldNotEmpty(textfieldAdress, "Gatuadress kan inte vara tom!")) return false;
                if (!isTextFieldNotEmpty(textfieldStad, "Stad kan inte vara tom!")) return false;

                if (!checkValidValue(textfieldPostnummer, "PostNumber", "Ogiltigt postnummer!")) return false;
                if (!checkValidValue(textfieldEmail, "Email", "Ogiltigt e-mail adress!")) return false;
                if (!checkValidValue(textfieldTelefonnummer, "Phone", "Ogiltigt telefonnummer!")) return false;

                labelErrorMessage.toBack();
                return true;
        }

        public boolean isCardNumberValid() {
                if (getCurrentCardNumber().length() != 16 || !isValidValue(getCurrentCardNumber(), "CardType")) {
                        currentErrorTextField = textfieldKortnummer1;
                        setCardNumberError();
                        labelErrorMessage.setText("Ogiltigt kortnummmer! Vi accepterar bara Visa eller Mastercard.");
                        labelErrorMessage.toFront();
                        return false;
                }

                if (currentErrorTextField == textfieldKortnummer1) {
                        currentErrorTextField = null;
                }
                return true;
        }

        public boolean checkValidValue(TextField tf, String type, String message) {
                if (!isValidValue(tf.getText(), type)) {
                        currentErrorTextField = tf;
                        tf.getStyleClass().remove("error");
                        tf.getStyleClass().add("error");
                        labelErrorMessage.setText(message);
                        labelErrorMessage.toFront();
                        return false;
                }

                if (currentErrorTextField == tf) {
                        currentErrorTextField = null;
                }
                labelErrorMessage.toBack();
                return true;
        }

        public boolean isTextFieldNotEmpty(TextField tf, String message) {
                if (tf.getText().length() == 0) {
                        currentErrorTextField = tf;
                        tf.getStyleClass().remove("error");
                        tf.getStyleClass().add("error");
                        labelErrorMessage.setText(message);
                        labelErrorMessage.toFront();
                        return false;
                }

                if (currentErrorTextField == tf) {
                        currentErrorTextField = null;
                }
                labelErrorMessage.toBack();
                return true;
        }

        public void setStage(Stage stage, Parent mainPage, Parent customerServicePage, Parent paymentPage){
                this.stage=stage;
                this.mainPage=mainPage;
                this.customerServicePage=customerServicePage;
                this.paymentPage=paymentPage;
        }

        public void goToNextTextField() {
                        nextTextfield.requestFocus();
                }

        public void updateInformation() {
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
        //</editor-fold>
}

