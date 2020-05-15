package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.ScrollPane;
import se.chalmers.cse.dat216.project.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainPageController implements Initializable, ShoppingCartListener {
    private final Model model = Model.getInstance();
    @FXML
    TextField searchField;
    @FXML
    FlowPane flowPaneMainPage;
    @FXML
    FlowPane flowPaneVarukorg;

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

        model.addToShoppingCart(model.getProduct(10));
        flowPaneVarukorg.getChildren().add(new VarukorgItem(model.getShoppingItemMap().get(10), model));
    }

    //När man klickar på tidigare köp
    @FXML
    public void onEarlierPurchases(ActionEvent event) {
    }

    //När man klickar på listor
    @FXML
    public void onListsClick() {
    }

    //När man klickar på kundservice och hjälp
    @FXML
    public void onCustomerServiceAndHelp() {
    }

    //När man trycker på kontoinställningar
    @FXML
    public void onAccountSettings() {
    }
    //När man trycker på betala
    @FXML
    public void onPaymentButton() {
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