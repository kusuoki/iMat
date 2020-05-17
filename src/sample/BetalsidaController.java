package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import javax.swing.*;
import java.awt.*;
import java.awt.TextField;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BetalsidaController implements Initializable, ShoppingCartListener {

        private final Model model = Model.getInstance();
        @FXML TextField searchField;
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
        private ShoppingCart shoppingCart = model.getShoppingCart();
        private Parent mainPage;
        private Stage stage;


        //Används för att sätta denna till kontroller för mainpage.fxml
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
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

                getShoppingCart();
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

        }
        //När man trycker på ikonen på hemknappen... enklare att lösa så här eftersom metoden ovanför kräver ett event och klickar man på ikonen fås inget event...
        @FXML void onHomeButtonClickIcon(){
                stage.getScene().setRoot(mainPage);
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
                        System.out.println(shoppingCart.getItems().size());
                        for (int i = 1; i < shoppingCart.getItems().size(); i++) {

                                flowPaneBekrafta.getChildren().add(new BetalsidaItem(model.getInstance().getProduct(i), model));
                                model.getShoppingCart();
                        }
                }
        }

        @Override
        public void shoppingCartChanged(CartEvent cartEvent) {
                getShoppingCart();
                System.out.println("hej");
        }
}

