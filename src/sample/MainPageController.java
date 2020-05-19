package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.*;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;


public class MainPageController implements Initializable, ShoppingCartListener {
    private final Model model = Model.getInstance();

    @FXML Pane paneIndicatorErbjudanden;
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

    @FXML ImageView imageViewArrowErbjudanden;
    @FXML ImageView imageViewArrowFavoriter;
    @FXML ImageView imageViewArrowBaljvaxter;
    @FXML ImageView imageViewArrowSotsaker;
    @FXML ImageView imageViewArrowDrycker;
    @FXML ImageView imageViewArrowFisk;
    @FXML ImageView imageViewArrowFrukt;
    @FXML ImageView imageViewArrowKott;
    @FXML ImageView imageViewArrowMejeri;
    @FXML ImageView imageViewArrowSkafferi;

    @FXML AnchorPane anchorPaneMainPage;
    @FXML AnchorPane anchorPaneMainLightbox;

    // detailed view @FXML
    @FXML ImageView imageViewMainLightboxImage;
    @FXML Label labelMainLightboxVara;
    @FXML Label labelMainLightboxPrisPaket;
    @FXML Label labelMainLightboxBeskrivning;
    @FXML ImageView imageViewMainLightboxClose;
    @FXML ImageView imageViewMainLightboxFavourite;
    @FXML Label labelMainLightboxPrice;
    @FXML TextField lightboxQuantityTextField;
    @FXML Pane lightboxPlusMinusPane;
    @FXML Pane lightboxAddPane;

    ListItem currentLightboxItem;

    ArrayList<Pane> menuIndicators = new ArrayList<Pane>();
    ArrayList<javafx.scene.control.Button> menuButtons= new ArrayList<javafx.scene.control.Button>();
    ArrayList<ImageView> menuArrows = new ArrayList<ImageView>();

    @FXML
    Label labelVarusida;
    @FXML
    Label labelPreviousPage;
    @FXML
    Label labenNextPage;

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

    private List<ListItem> currentListWithItems = new ArrayList<>();
    private List<ListItem> list8Items = new ArrayList<>();
    int currentPage;
    int lastPage;

    public void setStage(Stage stage, Parent betalsida, Parent konto, Parent kundservice) {
        this.stage = stage;
        this.betalsida = betalsida;
        this.konto = konto;
        this.kundservice = kundservice;


    }


    //Används för att sätta denna till kontroller för mainpage.fxml
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMenuItems();


        displayListItemByCategory("Frukt & Grönt");
        model.setImageViewOnHoverEvent(imageViewMainLightboxClose, null);
        model.setImageViewOnHoverEvent(imageViewMainLightboxFavourite, null);

        /*flowPaneMainPage.getChildren().add(new ListItem(model.getInstance().getProduct(10), model));
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
        flowPaneMainPage.getChildren().add(new ListItem(model.getInstance().getProduct(9), model));*/

    }

    void initMenuItems(){
        menuIndicators.add(paneIndicatorErbjudanden);
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

        menuArrows.add(imageViewArrowErbjudanden);
        menuArrows.add(imageViewArrowFavoriter);
        menuArrows.add(imageViewArrowBaljvaxter);
        menuArrows.add(imageViewArrowSotsaker);
        menuArrows.add(imageViewArrowErbjudanden);



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
        
        //TODO: Används inte längre men vågar inte ta bort lol (updateProductList() det vill säga)
        //updateProductList(searchList);

        /*
        //TODO: VISAR ENDAST DE FÖRSTA 8 VARORNA NU ANNARS LAGGAR DET
        List<ProductA> first8ItemsInList;
        if (searchList.size() > 8) {
            first8ItemsInList = searchList.subList(0, 8);
        } else {
            first8ItemsInList = searchList;
        }

         */

        displayListItemFromList(searchList);
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
                    //Här ska man också ta bort den gamla undermenyn. Kolla på att göra en ihopsättning av indicators, knappar, bilder på pilar och hela nya undermenyvyn
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

    @FXML
    public void favorite() {
        if (model.isFavorite(currentLightboxItem.product)) {
            currentLightboxItem.unFavorite();
        } else {
            currentLightboxItem.favorite();
        }
        imageViewMainLightboxFavourite.setImage(getFavoriteImage(model.isFavorite(currentLightboxItem.product)));
    }

    @FXML
    public void lightBoxToFront() {
        anchorPaneMainLightbox.toFront();
    }

    @FXML
    public void mainPageToFront() {
        anchorPaneMainPage.toFront();
    }

    @FXML
    public void mouseTrap(Event event) {
        event.consume();
    }

    public void openLightBox(ListItem item) {
        currentLightboxItem = item;
        imageViewMainLightboxImage.setImage(model.getImage(item.product));
        imageViewMainLightboxFavourite.setImage(getFavoriteImage(model.isFavorite(item.product)));
        labelMainLightboxVara.setText(item.product.getName());
        labelMainLightboxPrisPaket.setText(item.product.getPrice() + " " + item.product.getUnit());
        labelMainLightboxPrice.setText(String.valueOf(item.product.getPrice()));
        labelMainLightboxBeskrivning.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        int amount = model.getAmountOfThisProductInShoppinCart(currentLightboxItem.product);
        if (amount > 0) {
            lightboxPlusMinusPane.toFront();
            lightboxQuantityTextField.setText(String.valueOf(amount));
        } else {
            lightboxAddPane.toFront();
        }

        lightBoxToFront();
    }

    @FXML
    public void lightBoxAddFirstItem() {
        currentLightboxItem.addFirstProduct();
        lightboxQuantityTextField.setText(String.valueOf(model.getAmountOfThisProductInShoppinCart(currentLightboxItem.product)));
        lightboxPlusMinusPane.toFront();
    }

    @FXML
    public void lightBoxRemoveOneItem() {
        currentLightboxItem.removeOneOfProduct();

        int amount = model.getAmountOfThisProductInShoppinCart(currentLightboxItem.product);
        if (amount < 1) {
            lightboxAddPane.toFront();
        } else {
            lightboxQuantityTextField.setText(String.valueOf(amount));
        }
    }

    @FXML
    public void lightBoxAddOneItem() {
        currentLightboxItem.addOneOfProduct();
        lightboxQuantityTextField.setText(String.valueOf(model.getAmountOfThisProductInShoppinCart(currentLightboxItem.product)));
    }

    public Image getFavoriteImage(boolean isFavorite) {
        if (!isFavorite) {
            return new Image("sample/resources/Icons/ic_favorite_border_red_48d.png");
        }
        return new Image("sample/resources/Icons/ic_favorite_red_48dp.png");
    }

    //När man hoovrar över menyn
    @FXML
    public void onMenyHoover() {
    }

    //TODO: ANVÄNDS INTE LÄNGRE, använder istället displayListItemFromList(searchList)
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

        displayListItems();
        updateShoppingCartPriceAndQuantity();
    }

    private void updateShoppingCartPriceAndQuantity() {
        double total = model.getShoppingCart().getTotal();
        double roundedTotal = Math.round(total*100.0)/100.0;
        totalPriceLabel.setText(roundedTotal + "kr");

        int quantity = 0;
        for (ShoppingItem item : model.getShoppingCart().getItems()){
            quantity += item.getAmount();
        }
        totalQuantityLabel.setText(quantity + " varor");
    }

    private void displayListItemByCategory(String category){
        list8Items.clear();
        currentListWithItems.clear();
        currentPage = 0;

        List<ProductA> productList = model.getProducts(category);

        for (ProductA p : productList){
            ListItem item = new ListItem(p, model, this);
            currentListWithItems.add(item);
        }

        lastPage = currentListWithItems.size() / 8;
        if (currentListWithItems.size() % 8 != 0 ) {
            lastPage++;
        }

        displayListItems();
    }

    private void displayListItemFromList(List<ProductA> productList){
        list8Items.clear();
        currentListWithItems.clear();
        currentPage = 0;

        for (ProductA p : productList){
            ListItem item = new ListItem(p, model, this);
            currentListWithItems.add(item);
        }

        lastPage = currentListWithItems.size() / 8;
        if (currentListWithItems.size() % 8 != 0 ) {
            lastPage++;
        }

        displayListItems();
    }

    private void displayListItems() {
        flowPaneMainPage.getChildren().clear();
        updateListWith8ItemsFromCurrent();
        labelVarusida.setText("Sida " + (currentPage + 1) + " av " + lastPage);

        for (ListItem item : list8Items){
            flowPaneMainPage.getChildren().add(item);
            item.switchButtons();
            item.updateTextfieldWithAmountOfProduct();
        }
    }

    private void updateListWith8ItemsFromCurrent(){
        list8Items.clear();
        for (int i = currentPage * 8; i < currentPage * 8 + 8; i++ ){
            if (i < currentListWithItems.size() ){
                list8Items.add(currentListWithItems.get(i));
            }
        }
    }

    @FXML
    public void nextPageButton(){
        if (lastPage > currentPage + 1) {
            currentPage++;
            displayListItems();
        }
    }

    @FXML
    public void previousPageButton(){
        if (currentPage > 0) {
            currentPage--;
            displayListItems();
        }
    }

}