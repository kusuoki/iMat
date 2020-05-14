package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.*;

import java.awt.*;
import java.awt.event.ActionEvent;
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

    //Används för att sätta denna till kontroller för mainpage.fxml
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    //När man klickar på tidigare köp
    @FXML
    public void onEarlierPurchases(ActionEvent event) {
    }

    //När man klickar på listor
    @FXML
    public void onListsClick(ActionEvent event) throws IOException {
        AnchorPane list = FXMLLoader.load(getClass().getResource("listor.fxml"));
        mainAnchor.getChildren().setAll(list);


    }

    //När man klickar på kundservice och hjälp
    @FXML
    public void onCustomerServiceAndHelpClick(ActionEvent event) throws  IOException {
        AnchorPane service = FXMLLoader.load(getClass().getResource("kundservice.fxml"));
        mainAnchor.getChildren().setAll(service);
    }

    //När man trycker på kontoinställningar
    @FXML
    public void onAccountSettingsClick(ActionEvent event) throws IOException {
        AnchorPane account = FXMLLoader.load(getClass().getResource("kontoinställningar.fxml"));
        mainAnchor.getChildren().setAll(account);
    }
    //När man trycker på betala
    @FXML
    public void onPaymentButton(ActionEvent event) throws IOException {
        AnchorPane payment = FXMLLoader.load(getClass().getResource("betalsida.fxml"));
        mainAnchor.getChildren().setAll(payment);
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

    //Lyssnar på om kundvagnen ändras och visar sedan ipp de nya varorna
    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        ShoppingCart shoppingCart = model.getShoppingCart();
        List<ShoppingItem> shoppingItems = shoppingCart.getItems();
        //CartFlowPane.getChildren().clear();


        for (ShoppingItem shoppingItem : shoppingItems) {

            //  CartFlowPane.getChildren().add(new CartPanel(ShoppingItem)); */

        }
    }
}