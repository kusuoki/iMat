package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Pane;
import javafx.scene.layout.FlowPane;

import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.*;

import java.awt.*;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
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

    Parent betalsida;
    Parent konto;
    Parent kundservice;
    Parent tidigarekop;
    Parent listor;
    Stage stage;

    public void setStage(Stage stage, Parent betalsida, Parent konto, Parent kundservice, Parent tidigarekop, Parent listor) {
        this.stage = stage;
        this.betalsida = betalsida;
        this.konto = konto;
        this.kundservice = kundservice;
        this.tidigarekop = tidigarekop;
        this.listor = listor;


    }

    //Används för att sätta denna till kontroller för mainpage.fxml
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
        flowPaneMainPage.getChildren().add(new ListItem(model.getInstance().getProduct(9), model));
        model.getShoppingCart().addShoppingCartListener(this);
    }

    //När man klickar på tidigare köp
    @FXML
    public void onEarlierPurchases(ActionEvent event) {
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
    public void onMenyClick() {
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

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        ShoppingCart shoppingCart = model.getShoppingCart();
        List<ShoppingItem> shoppingItems = shoppingCart.getItems();
        //CartFlowPane.getChildren().clear();


        for (ShoppingItem shoppingItem : shoppingItems) {

            //  CartFlowPane.getChildren().add(new CartPanel(ShoppingItem)); */
        }

        //Lyssnar på om kundvagnen ändras och visar sedan ipp de nya varorna


    }
}