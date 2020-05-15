package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.*;
import javax.swing.*;
import java.awt.*;
import javafx.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import se.chalmers.cse.dat216.project.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class MainPageController implements Initializable, ShoppingCartListener {
    private final Model model = Model.getInstance();
    @FXML
    TextField searchField;

    //AnchorPane som ligger som grund till allt i MainPage
    @FXML
    AnchorPane mainAnchor;
    @FXML
    FlowPane flowPaneMainPage;
    @FXML
    FlowPane flowPaneVarukorg;

    @FXML
    Label totalQuantityLabel;
    @FXML
    Label totalPriceLabel;

    Parent betalsida;
    Parent konto;
    Parent kundservice;
    Parent tidigarekop;
    Parent listor;
    Stage stage;
    BackButton backButton=BackButton.getBackButton();
    public void setStage(Stage stage, Parent betalsida, Parent konto, Parent kundservice, Parent tidigarekop, Parent listor) {
        this.stage = stage;
        this.betalsida = betalsida;
        this.konto = konto;
        this.kundservice = kundservice;
        this.tidigarekop = tidigarekop;
        this.listor = listor;


    }

    @FXML Pane paneIndicatorEbjudanden;
    @FXML Pane paneIndicatorFavoriter;
    @FXML Pane paneIndicatorBaljvaxter;
    @FXML Pane paneIndicatorSotsaker;
    @FXML Pane paneIndicatorDrycker;
    @FXML Pane paneIndicatorFisk;
    @FXML Pane paneIndicatorFrukt;
    @FXML Pane paneIndicatorKott;
    @FXML Pane paneIndicatorMejeri;
    @FXML Pane paneIndicatorSkafferi;

    @FXML javafx.scene.control.Button buttonErbjudanden;
    @FXML javafx.scene.control.Button buttonFavoriter;
    @FXML javafx.scene.control.Button buttonBaljvaxter;
    @FXML javafx.scene.control.Button buttonSotsaker;
    @FXML javafx.scene.control.Button buttonDrycker;
    @FXML javafx.scene.control.Button buttonFisk;
    @FXML javafx.scene.control.Button buttonFrukt;
    @FXML javafx.scene.control.Button buttonKott;
    @FXML javafx.scene.control.Button buttonMejeri;
    @FXML javafx.scene.control.Button buttonSkafferi;

    ArrayList<Pane> menuIndicators = new ArrayList<Pane>();
    ArrayList<javafx.scene.control.Button> menuButtons= new ArrayList<javafx.scene.control.Button>();


    //Används för att sätta denna till kontroller för mainpage.fxml
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMenuItems();


        flowPaneMainPage.getChildren().add(new ListItem(model.getInstance().getProduct(10), model));
        flowPaneMainPage.getChildren().add(new ListItem(model.getInstance().getProduct(11), model));
        flowPaneMainPage.getChildren().add(new ListItem(model.getInstance().getProduct(12), model));
        flowPaneMainPage.getChildren().add(new ListItem(model.getInstance().getProduct(13), model));
        flowPaneMainPage.getChildren().add(new ListItem(model.getInstance().getProduct(14), model));
        flowPaneMainPage.getChildren().add(new ListItem(model.getInstance().getProduct(15), model));
        flowPaneMainPage.getChildren().add(new ListItem(model.getInstance().getProduct(16), model));
        flowPaneMainPage.getChildren().add(new ListItem(model.getInstance().getProduct(17), model));
        flowPaneMainPage.getChildren().add(new ListItem(model.getInstance().getProduct(18), model));
        flowPaneMainPage.getChildren().add(new ListItem(model.getInstance().getProduct(19), model));
        flowPaneMainPage.getChildren().add(new ListItem(model.getInstance().getProduct(20), model));
        flowPaneMainPage.getChildren().add(new ListItem(model.getInstance().getProduct(21), model));
        flowPaneMainPage.getChildren().add(new ListItem(model.getInstance().getProduct(22), model));
        flowPaneMainPage.getChildren().add(new ListItem(model.getInstance().getProduct(23), model));
        flowPaneMainPage.getChildren().add(new ListItem(model.getInstance().getProduct(9), model));

    }

    void initMenuItems(){
        menuIndicators.add(paneIndicatorEbjudanden);
        menuIndicators.add(paneIndicatorFavoriter);
        menuIndicators.add(paneIndicatorBaljvaxter);
        menuIndicators.add(paneIndicatorSotsaker);
        menuIndicators.add(paneIndicatorDrycker);
        menuIndicators.add(paneIndicatorFisk);
        menuIndicators.add(paneIndicatorFrukt);
        menuIndicators.add(paneIndicatorKott);
        menuIndicators.add(paneIndicatorMejeri);
        menuIndicators.add(paneIndicatorSkafferi);

        menuButtons.add(buttonErbjudanden);
        menuButtons.add(buttonFavoriter);
        menuButtons.add(buttonBaljvaxter);
        menuButtons.add(buttonSotsaker);
        menuButtons.add(buttonDrycker);
        menuButtons.add(buttonFisk);
        menuButtons.add(buttonFrukt);
        menuButtons.add(buttonKott);
        menuButtons.add(buttonMejeri);
        menuButtons.add(buttonSkafferi);

        for (Pane p : menuIndicators) {
            p.toBack();
        }

        System.out.println(menuButtons);

        for(javafx.scene.control.Button b : menuButtons){
            b.setOnAction((event)->{
                onMenuClick(b);
            });
        }

        model.getShoppingCart().addShoppingCartListener(this);

       /* model.addToShoppingCart(model.getProduct(10));
        flowPaneVarukorg.getChildren().add(new VarukorgItem(model.getShoppingItemMap().get(10), model));*/

    }

    //När man klickar på tidigare köp
    @FXML
    public void onEarlierPurchases(ActionEvent event) {
        backButton.addToBackList(stage.getScene().getRoot());
        stage.getScene().setRoot(tidigarekop);
    }

    //När man klickar på listor
    @FXML
    public void onListsClick(ActionEvent event) throws IOException {
        stage.getScene().setRoot(listor);


    }

    //När man klickar på kundservice och hjälp
    @FXML
    public void onCustomerServiceAndHelpClick(ActionEvent event) throws IOException {
        stage.getScene().setRoot(kundservice);

    }

    //När man trycker på kontoinställningar
    @FXML
    public void onAccountSettingsClick(ActionEvent event) throws IOException {
        stage.getScene().setRoot(konto);
    }

    //När man trycker på betala
    @FXML
    public void onPaymentButton(ActionEvent event) throws IOException {
        stage.getScene().setRoot(betalsida);
    }

    //När man trycker på sökknappen
    @FXML
    public void onSearch() {
        List<ProductA> searchList = model.findProducts(searchField.getText());
        updateProductList(searchList);
    }



    //När man klickar på menyn


    @FXML
    public void onMenuClick(javafx.scene.control.Button b) {
        for(javafx.scene.control.Button button : menuButtons) //Clears any button that may've been clicked before
        {
            if(button != b) {
                if (button.getStyleClass().toString().equals("menuButtonClicked")) {
                    button.getStyleClass().clear();
                    button.getStyleClass().add("menuButton");
                    //Här ska man också ta bort den gamla undermenyn. Kolla på att göra en ihopsättning av indicators, knappar och hela nya menyvyn
                }
            }

        }
            System.out.println(b.getStyleClass());
            if (b.getStyleClass().toString().equals("menuButtonClicked")) {
                b.getStyleClass().clear();
                b.getStyleClass().add("menuButton");
            } else {
                b.getStyleClass().clear();
                b.getStyleClass().add("menuButtonClicked");
            }

    }




    //När man hoovrar över menyn
    @FXML
    public void onMenyHoover() {
    }

    //Denna kallas när efter man söker/filtrerar (inte implementerat) efter varor för att sedan uppdatera flowplanen där de ligger
    private void updateProductList(List<ProductA> searchList) {
/*productsFlowPane.getChildren().clear();

        for (Product product : products) {

            productsFlowPane.getChildren().add(new ProductPanel(product)); */

    }

    //Lyssnar på om kundvagnen ändras och visar sedan upp de nya varorna
    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        List<ShoppingItem> shoppingItems = model.getShoppingCart().getItems();
        List<VarukorgItem> varukorgItems = new ArrayList<>();

        for (ShoppingItem shoppingItem : shoppingItems){
            VarukorgItem item = new VarukorgItem(shoppingItem, model);
            varukorgItems.add(item);
        }

        flowPaneVarukorg.getChildren().clear();
        for (VarukorgItem item : varukorgItems) {
            item.updateThisItem();
            flowPaneVarukorg.getChildren().add(item);
        }

        updateShoppingCartPriceAndQuantity();
    }

    private void updateShoppingCartPriceAndQuantity() {
        totalPriceLabel.setText(model.getShoppingCart().getTotal() + "kr");

        int quantity = 0;
        for (ShoppingItem item : model.getShoppingCart().getItems()){
            quantity += item.getAmount();
        }
        totalQuantityLabel.setText(quantity + " varor");
    }

}