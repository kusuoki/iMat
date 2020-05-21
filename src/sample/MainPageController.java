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

    @FXML
    Pane paneIndicatorErbjudanden;
    @FXML
    Pane paneIndicatorFavoriter;
    @FXML
    Pane paneIndicatorBaljvaxter;
    @FXML
    Pane paneIndicatorSotsaker;
    @FXML
    Pane paneIndicatorDrycker;
    @FXML
    Pane paneIndicatorFisk;
    @FXML
    Pane paneIndicatorFrukt;
    @FXML
    Pane paneIndicatorKott;
    @FXML
    Pane paneIndicatorMejeri;
    @FXML
    Pane paneIndicatorSkafferi;

    @FXML
    javafx.scene.control.Button buttonErbjudanden;
    @FXML
    javafx.scene.control.Button buttonFavoriter;
    @FXML
    javafx.scene.control.Button buttonBaljvaxter;
    @FXML
    javafx.scene.control.Button buttonSotsaker;
    @FXML
    javafx.scene.control.Button buttonDrycker;
    @FXML
    javafx.scene.control.Button buttonFisk;
    @FXML
    javafx.scene.control.Button buttonFrukt;
    @FXML
    javafx.scene.control.Button buttonKott;
    @FXML
    javafx.scene.control.Button buttonMejeri;
    @FXML
    javafx.scene.control.Button buttonSkafferi;

    @FXML
    ImageView imageViewArrowErbjudanden;
    @FXML
    ImageView imageViewArrowFavoriter;
    @FXML
    ImageView imageViewArrowBaljvaxter;
    @FXML
    ImageView imageViewArrowSotsaker;
    @FXML
    ImageView imageViewArrowDrycker;
    @FXML
    ImageView imageViewArrowFisk;
    @FXML
    ImageView imageViewArrowFrukt;
    @FXML
    ImageView imageViewArrowKott;
    @FXML
    ImageView imageViewArrowMejeri;
    @FXML
    ImageView imageViewArrowSkafferi;

    @FXML
    AnchorPane anchorUndermenyBaljvaxter;
    @FXML
    AnchorPane anchorUndermenyDrycker;
    @FXML
    AnchorPane anchorUndermenyFisk;
    @FXML
    AnchorPane anchorUndermenyFrukt;
    @FXML
    AnchorPane anchorUndermenyKott;
    @FXML
    AnchorPane anchorUndermenyMejeri;
    @FXML
    AnchorPane anchorUndermenySkafferi;
    @FXML
    AnchorPane anchorUndermenySotsaker;

    @FXML
    javafx.scene.control.Button buttonAllaBaljvaxter;
    @FXML
    javafx.scene.control.Button buttonBonor;
    @FXML
    javafx.scene.control.Button buttonLinser;
    @FXML
    javafx.scene.control.Button buttonArtor;
    @FXML
    javafx.scene.control.Button buttonAllaDrycker;
    @FXML
    javafx.scene.control.Button buttonKallaDrycker;
    @FXML
    javafx.scene.control.Button buttonVarmaDrycker;
    @FXML
    javafx.scene.control.Button buttonAllaFrukt;
    @FXML
    javafx.scene.control.Button buttonBar;
    @FXML
    javafx.scene.control.Button buttonCitrusfrukter;
    @FXML
    javafx.scene.control.Button buttonExotiskaFrukter;
    @FXML
    javafx.scene.control.Button buttonFarskaOrtkryddor;
    @FXML
    javafx.scene.control.Button buttonGronsaker;
    @FXML
    javafx.scene.control.Button buttonKal;
    @FXML
    javafx.scene.control.Button buttonMelon;
    @FXML
    javafx.scene.control.Button buttonPotatisOchRotfrukter;
    @FXML
    javafx.scene.control.Button buttonSotaStenfrukter;
    @FXML
    javafx.scene.control.Button buttonAllaFisk;
    @FXML
    javafx.scene.control.Button buttonFarskLax;
    @FXML
    javafx.scene.control.Button buttonSill;
    @FXML
    javafx.scene.control.Button buttonSkaldjur;
    @FXML
    javafx.scene.control.Button buttonOvrigFisk;
    @FXML
    javafx.scene.control.Button buttonAllaKott;
    @FXML
    javafx.scene.control.Button buttonKyckling;
    @FXML
    javafx.scene.control.Button buttonNotkott;
    @FXML
    javafx.scene.control.Button buttonAllaMejeri;
    @FXML
    javafx.scene.control.Button buttonMjolk;
    @FXML
    javafx.scene.control.Button buttonOst;
    @FXML
    javafx.scene.control.Button buttonYoghurt;
    @FXML
    javafx.scene.control.Button buttonAgg;
    @FXML
    javafx.scene.control.Button buttonAllaSkafferi;
    @FXML
    javafx.scene.control.Button buttonBrod;
    @FXML
    javafx.scene.control.Button buttonFron;
    @FXML
    javafx.scene.control.Button buttonKakao;
    @FXML
    javafx.scene.control.Button buttonKnackebrod;
    @FXML
    javafx.scene.control.Button buttonMjol;
    @FXML
    javafx.scene.control.Button buttonNotter;
    @FXML
    javafx.scene.control.Button buttonPasta;
    @FXML
    javafx.scene.control.Button buttonRis;
    @FXML
    javafx.scene.control.Button buttonSockerOchSalt;
    @FXML
    javafx.scene.control.Button buttonAllaSotsaker;
    @FXML
    javafx.scene.control.Button buttonBakverk;
    @FXML
    javafx.scene.control.Button buttonGlass;
    @FXML
    javafx.scene.control.Button buttonGodis;
    @FXML
    javafx.scene.control.Button buttonSnacks;

    @FXML
    AnchorPane anchorPaneMainPage;

    @FXML
    AnchorPane anchorPaneMainLightbox;

    // detailed view @FXML
    @FXML
    ImageView imageViewMainLightboxImage;
    @FXML
    Label labelMainLightboxVara;
    @FXML
    Label labelMainLightboxPrisPaket;
    @FXML
    Label labelMainLightboxBeskrivning;
    @FXML
    ImageView imageViewMainLightboxClose;
    @FXML
    ImageView imageViewMainLightboxFavourite;
    @FXML
    Label labelMainLightboxPrice;
    @FXML
    TextField lightboxQuantityTextField;
    @FXML
    Pane lightboxPlusMinusPane;
    @FXML
    Pane lightboxAddPane;

    ListItem currentLightboxItem;

    ArrayList<menuItem> menuItems = new ArrayList<menuItem>();

    //Labels för nästa sida, förra sidan och nuvarande sidan för Affärsfönstret
    @FXML
    Label labelVarusida;
    @FXML
    Label labelPreviousPage;
    @FXML
    Label labelNextPage;

    //Labels för nästa sida, förra sidan och nuvarande sidan för Tidigare köp
    @FXML
    Label labelOrder;
    @FXML
    Label labelPreviousOrderPage;
    @FXML
    Label labelNextOrderPage;

    @FXML
    TextField searchField;

    //AnchorPane som ligger som grund till allt i MainPage
    @FXML
    AnchorPane mainAnchor;
    @FXML
    FlowPane flowPaneMainPage;
    @FXML
    FlowPane flowPaneVarukorg;


    //Grejer till tidigare köp m.m.
    @FXML
    Pane paneVaruDisplay;
    @FXML
    Pane paneTidigareKop;
    @FXML
    FlowPane flowPaneTidigareKop;

    //Grejer till tidigare köp LIGHTBOX
    @FXML
    Label orderLightboxDate;
    @FXML
    Label orderLightboxPrice;
    @FXML
    Label orderLightboxQuantity;
    @FXML
    Label orderLightboxOrdernumber;
    @FXML
    AnchorPane orderLightbox;

    @FXML
    Label totalQuantityLabel;
    @FXML
    Label totalPriceLabel;

    @FXML
    javafx.scene.control.Button buttonBetala;

    Parent betalsida;
    Parent konto;
    Parent kundservice;
    Parent tidigarekop;
    Parent listor;
    Stage stage;

    BackButton backButton = BackButton.getBackButton();

    private List<ListItem> currentListWithItems = new ArrayList<>();
    private List<ListItem> list8Items = new ArrayList<>();
    int currentPage;
    int lastPage;
    String category;
    boolean cat = false;
    boolean search = false;
    private List<ProductA> tempSearch = new ArrayList<>();

    private List<TidigareKopItem> allOrders = new ArrayList<>();
    private List<TidigareKopItem> ordersCurrentlyDisplayed = new ArrayList<>();
    int currentOrderPage;
    int lastOrderPage;

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
        initCategoryMenu();

        // force update the shopping cart to render out the products
        if (model.getShoppingCart().getItems().size() > 0) {
            model.updateShoppingCart(model.getShoppingCart().getItems().get(0).getProduct(), 0);
        }
        model.setImageViewOnHoverEvent(imageViewMainLightboxClose, null);
        model.setImageViewOnHoverEvent(imageViewMainLightboxFavourite, null);
        model.getOrders().clear();
        for (int i = 1; i < 20; i++) {
            Order o = generateTestOrder(new Date(), i, i);
            model.getOrders().add(o);
        }
        onSearch();

        if (model.getShoppingCart().getItems().size() == 0) {
            buttonBetala.setStyle("-fx-background-color: #A0A0A0; -fx-text-fill:white;");
        } else {
            buttonBetala.setStyle("-fx-background-color: #FFB422; -fx-text-fill:black;");
        }
    }

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
    //TODO TEMP METHOD FOR TESTING

    private Order generateTestOrder(Date date, int productID, int orderID) {
        Order order = new Order();
        order.setDate(date);
        List<ShoppingItem> testList = new ArrayList<>();
        testList.add(new ShoppingItem(model.getProducts().get(productID)));
        order.setItems(testList);
        order.setOrderNumber(orderID);
        return order;
    }
    //Fram och tillbaka knappar för TidigareKöp

    @FXML
    private void nextOrderPageButton() {
        if (lastOrderPage > currentOrderPage + 1) {
            currentOrderPage++;
            displayOrdersOnPage();
        }
    }
    @FXML
    private void previousOrderPageButton() {
        if (currentOrderPage > 0) {
            currentOrderPage--;
            displayOrdersOnPage();
        }
    }

    //Lightboxfunktionalitet

    @FXML
    public void exitOrderLightbox() {
        orderLightbox.toBack();
    }
    @FXML
    public void enterOrderLightbox(TidigareKopItem item) {
        orderLightboxDate.setText(item.getOrder().getDate().toString());
        orderLightboxPrice.setText(item.getOrderTotalCost(item.getOrder()) + "kr");
        orderLightboxQuantity.setText(item.getOrder().getItems().size() + "varor");
        orderLightbox.toFront();
    }

    void initMenuItems() {

        menuItems.add(new menuItem(buttonErbjudanden, paneIndicatorErbjudanden, imageViewArrowErbjudanden, null, null));
        menuItems.add(new menuItem(buttonFavoriter, paneIndicatorFavoriter, imageViewArrowFavoriter, null, null));
        menuItems.add(new menuItem(buttonBaljvaxter, paneIndicatorBaljvaxter, imageViewArrowBaljvaxter, anchorUndermenyBaljvaxter, new ArrayList<javafx.scene.control.Button>(Arrays.asList(buttonAllaBaljvaxter, buttonBonor, buttonLinser, buttonArtor))));
        menuItems.add(new menuItem(buttonDrycker, paneIndicatorDrycker, imageViewArrowDrycker, anchorUndermenyDrycker, new ArrayList<javafx.scene.control.Button>(Arrays.asList(buttonAllaDrycker, buttonKallaDrycker, buttonVarmaDrycker))));
        menuItems.add(new menuItem(buttonFisk, paneIndicatorFisk, imageViewArrowFisk, anchorUndermenyFisk, new ArrayList<javafx.scene.control.Button>(Arrays.asList(buttonAllaFisk, buttonFarskLax, buttonSill, buttonSkaldjur, buttonOvrigFisk))));
        menuItems.add(new menuItem(buttonFrukt, paneIndicatorFrukt, imageViewArrowFrukt, anchorUndermenyFrukt, new ArrayList<javafx.scene.control.Button>(Arrays.asList(buttonAllaFrukt, buttonBar, buttonCitrusfrukter, buttonExotiskaFrukter, buttonFarskaOrtkryddor, buttonGronsaker, buttonKal, buttonMelon, buttonPotatisOchRotfrukter, buttonSotaStenfrukter))));
        menuItems.add(new menuItem(buttonKott, paneIndicatorKott, imageViewArrowKott, anchorUndermenyKott, new ArrayList<javafx.scene.control.Button>(Arrays.asList(buttonAllaKott, buttonKyckling, buttonNotkott))));
        menuItems.add(new menuItem(buttonMejeri, paneIndicatorMejeri, imageViewArrowMejeri, anchorUndermenyMejeri, new ArrayList<javafx.scene.control.Button>(Arrays.asList(buttonAllaMejeri, buttonMjolk, buttonOst, buttonYoghurt, buttonAgg))));
        menuItems.add(new menuItem(buttonSkafferi, paneIndicatorSkafferi, imageViewArrowSkafferi, anchorUndermenySkafferi, new ArrayList<javafx.scene.control.Button>(Arrays.asList(buttonAllaSkafferi, buttonBrod, buttonFron, buttonKakao, buttonKnackebrod, buttonMjol, buttonNotter, buttonPasta, buttonRis, buttonSockerOchSalt))));
        menuItems.add(new menuItem(buttonSotsaker, paneIndicatorSotsaker, imageViewArrowSotsaker, anchorUndermenySotsaker, new ArrayList<javafx.scene.control.Button>(Arrays.asList(buttonAllaSotsaker, buttonBakverk, buttonGlass, buttonGodis, buttonSnacks))));


        for (menuItem m : menuItems) {
            m.pane.toBack();
            m.button.setOnAction((event) -> {
                menuOnClick(m);
            });

            //Sätter en listener på imageview med pilen så att den byter bild och gör en kant på knappen om man hoverar över den
            m.arrow.hoverProperty().addListener((event) -> {

                menuOnHover(m);

            });

            m.arrow.setOnMouseClicked((event) -> menuOnClick(m));
            m.button.hoverProperty().addListener((event) -> menuOnHover(m));

            if (m.sMenu != null) {
                for (javafx.scene.control.Button btn : m.sMenu) {
                    btn.hoverProperty().addListener((event) -> subMenuOnHover(m.sMenu, btn));
                    btn.setOnMouseClicked(((event) -> subMenuOnClick(m.sMenu, btn)));
                }
            }


        }


        model.getShoppingCart().addShoppingCartListener(this);

       /* model.addToShoppingCart(model.getProduct(10));
        flowPaneVarukorg.getChildren().add(new VarukorgItem(model.getShoppingItemMap().get(10), model));*/

    }

    //När man klickar på tidigare köp

    @FXML
    public void onEarlierPurchases(ActionEvent event) {
        flowPaneTidigareKop.getChildren().clear();
        ordersCurrentlyDisplayed.clear();
        allOrders.clear();
        currentOrderPage = 0;
        paneTidigareKop.toFront();

        for (Order orders : model.getOrders()) {
            TidigareKopItem item = new TidigareKopItem(orders, this);
            allOrders.add(item);
        }

        lastOrderPage = allOrders.size() / 9;
        if (allOrders.size() % 9 != 0) {
            lastOrderPage++;
        }

        displayOrdersOnPage();
    }
    public void displayOrdersOnPage() {
        flowPaneTidigareKop.getChildren().clear();
        updateOrdersBeingDisplayedWith9orders();
        labelOrder.setText("Sida " + (currentOrderPage + 1) + " av " + lastOrderPage);

        if (lastOrderPage == 0){
            labelOrder.setText("Sida 0 av 0");
        }

        for (TidigareKopItem item : ordersCurrentlyDisplayed) {
            flowPaneTidigareKop.getChildren().add(item);
        }
    }

    private void updateOrdersBeingDisplayedWith9orders() {
        ordersCurrentlyDisplayed.clear();
        for (int i = currentOrderPage * 9; i < currentOrderPage * 9 + 9; i++) {
            if (i < allOrders.size()) {
                ordersCurrentlyDisplayed.add(allOrders.get(i));
            }
        }
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
        if (model.getShoppingCart().getItems().size() > 0) {
            stage.getScene().setRoot(betalsida);
        }
    }
    //När man trycker på sökknappen

    @FXML
    public void onSearch() {
        List<ProductA> searchList = model.findProducts(searchField.getText());
        /*updateProductList(searchList);
    }

    @FXML
    public void mainPageToFront(){
        anchorPaneMainPage.toFront();
    }

    @FXML
    public void mouseTrap(Event event){
        event.consume();
    }*/
        this.tempSearch = searchList;

        //TODO: Används inte längre men vågar inte ta bort lol (updateProductList() det vill säga)
        //updateProductList(searchList);

        paneVaruDisplay.toFront();
        displayListItemFromList(searchList);
    }
    //När man klickar på menyn

    @FXML
    public void menuOnClick(menuItem m) {

        javafx.scene.control.Button b = m.button;

        for (menuItem mItem : menuItems) //Clears any button that may've been clicked before
        {
            if (mItem.button != b) {
                if (mItem.button.getStyleClass().toString().equals("menuButtonClicked")) {
                    resetButtonStyle(mItem);
                }
                if (m.anchorPane != null)
                    m.anchorPane.toBack();
            }
        }
        //Sets the new styleclass for the clicked button
        if (b.getStyleClass().toString().equals("menuButtonClicked")) {
            resetButtonStyle(m);
        } else {
            b.getStyleClass().clear();
            b.getStyleClass().add("menuButtonClicked"); //Här ska den lägga till den nya undermenyn
            setLightgreenArrow(m.arrow);
            m.pane.toFront();
            if (m.anchorPane != null) {
                m.anchorPane.toFront();
            }
        }
    }


    //När man hoovrar över menyn

    public void menuOnHover(menuItem m) {

        for (menuItem item : menuItems) {
            if ((item != m) && !(item.button.getStyleClass().toString().equals("menuButtonClicked"))) {
                resetButtonStyle(item);
            }
        }

        if (m.button.getStyleClass().toString().equals("menuButtonClicked")) {
        } else {
            m.button.getStyleClass().add("menuButtonHover");
            setLightgreenArrow(m.arrow);
        }
        //byter färg på arrow
        //ändrar styleclass på knappen

    }
    @FXML
        public void favorite () {
            if (model.isFavorite(currentLightboxItem.product)) {
                currentLightboxItem.unFavorite();
            } else {
                currentLightboxItem.favorite();
            }
            imageViewMainLightboxFavourite.setImage(getFavoriteImage(model.isFavorite(currentLightboxItem.product)));
        }
        @FXML
        public void lightBoxToFront () {
            anchorPaneMainLightbox.toFront();
        }

        @FXML
        public void mainPageToFront () {
            anchorPaneMainPage.toFront();
        }

        @FXML
        public void mouseTrap (Event event){
            event.consume();
        }

        public void openLightBox (ListItem item){
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
        public void lightBoxAddFirstItem () {
            currentLightboxItem.addFirstProduct();
            lightboxQuantityTextField.setText(String.valueOf(model.getAmountOfThisProductInShoppinCart(currentLightboxItem.product)));
            lightboxPlusMinusPane.toFront();
        }

        @FXML
        public void lightBoxRemoveOneItem () {
            currentLightboxItem.removeOneOfProduct();

            int amount = model.getAmountOfThisProductInShoppinCart(currentLightboxItem.product);
            if (amount < 1) {
                lightboxAddPane.toFront();
            } else {
                lightboxQuantityTextField.setText(String.valueOf(amount));
            }
        }

        @FXML
        public void lightBoxAddOneItem () {
            currentLightboxItem.addOneOfProduct();
            lightboxQuantityTextField.setText(String.valueOf(model.getAmountOfThisProductInShoppinCart(currentLightboxItem.product)));
        }

        public Image getFavoriteImage ( boolean isFavorite){
            if (!isFavorite) {
                return new Image("sample/resources/Icons/ic_favorite_border_red_48d.png");
            }
            return new Image("sample/resources/Icons/ic_favorite_red_48dp.png");
        }


        public void subMenuOnHover (ArrayList < javafx.scene.control.Button > sMenu, javafx.scene.control.Button btn){

            for (javafx.scene.control.Button b : sMenu) {
                if ((b != btn) && !(b.getStyleClass().toString().equals("menuButtonClicked"))) {
                    b.getStyleClass().clear();
                    b.getStyleClass().add("menuButton");
                }
            }

            if (btn.getStyleClass().toString().equals("menuButtonClicked")) {
            } else {
                btn.getStyleClass().add("menuButtonHover");
            }
        }

        public void subMenuOnClick (ArrayList < javafx.scene.control.Button > sMenu, javafx.scene.control.Button btn){
            for (javafx.scene.control.Button b : sMenu) //Clears any button that may've been clicked before
            {
                if ((b != btn) && b.getStyleClass().toString().equals("menuButtonClicked")) {
                    b.getStyleClass().clear();
                    b.getStyleClass().add("menuButton");
                    System.out.println("LEOELEL");
                }

            }
            //Sets the new styleclass for the clicked button
            if (btn.getStyleClass().toString().equals("menuButtonClicked")) {
                System.out.println("HELLOOO");
                //lägg in att man kommer tillbaka till alla varor inom den kategorin eller liknande.
                btn.getStyleClass().clear();
                btn.getStyleClass().add("menuButton");
            } else {
                System.out.println("HJELLO");
                btn.getStyleClass().clear();
                btn.getStyleClass().add("menuButtonClicked");
            }
        }

        public void resetButtonStyle (menuItem m){
            //Återställer stilen på menyknappar
            m.button.getStyleClass().clear();
            m.button.getStyleClass().add("menuButton");
            setDarkgreenArrow(m.arrow);
            m.button.toFront();
            m.arrow.toFront();

            //Återställer stilen på undermenyknappar (under den knappen)
            if (m.sMenu != null) {
                for (javafx.scene.control.Button btn : m.sMenu) {
                    btn.getStyleClass().clear();
                    btn.getStyleClass().add("menuButton");
                }
            }

        }

        public void setLightgreenArrow (ImageView arrow){ //Sets the arrow in a menuItem to lightgreen
            arrow.setImage(new Image("file:/C:/Users/hanna/Documents/Programmering/iMat/iMat/out/production/iMat/sample/resources/Icons/ic_arrow_lightgreen_64dpcentered.png"));
        }

        public void setDarkgreenArrow (ImageView arrow){ //Sets the arrow in a menuItem to darkgreen
            arrow.setImage(new Image("file:/C:/Users/hanna/Documents/Programmering/iMat/iMat/out/production/iMat/sample/resources/Icons/ic_arrow_darkgreen_64dp.png"));
        }



    //TODO: ANVÄNDS INTE LÄNGRE, använder istället displayListItemFromList(searchList)
        //Denna kallas när efter man söker/filtrerar (inte implementerat) efter varor för att sedan uppdatera flowplanen där de ligger

    private void updateProductList (List < ProductA > searchList) {
/*productsFlowPane.getChildren().clear();

        for (Product product : products) {

            productsFlowPane.getChildren().add(new ProductPanel(product)); */

        }
        //Lyssnar på om kundvagnen ändras och visar sedan upp de nya varorna

    @Override
        public void shoppingCartChanged (CartEvent cartEvent){
            List<ShoppingItem> shoppingItems = model.getShoppingCart().getItems();
            List<VarukorgItem> varukorgItems = new ArrayList<>();

            for (ShoppingItem shoppingItem : shoppingItems) {
                VarukorgItem item = new VarukorgItem(shoppingItem, model);
                varukorgItems.add(item);
            }

            flowPaneVarukorg.getChildren().clear();
            for (VarukorgItem item : varukorgItems) {
                item.updateThisItem();
                flowPaneVarukorg.getChildren().add(item);
            }

            if (model.getShoppingCart().getItems().size() == 0) {
                buttonBetala.setStyle("-fx-background-color: #A0A0A0; -fx-text-fill:white;");
            } else {
                buttonBetala.setStyle("-fx-background-color: #FFB422; -fx-text-fill:black;");
            }

            displayListItems();
            updateShoppingCartPriceAndQuantity();
        }
        private void updateShoppingCartPriceAndQuantity () {
            double total = model.getShoppingCart().getTotal();
            double roundedTotal = Math.round(total * 100.0) / 100.0;
            totalPriceLabel.setText(roundedTotal + "kr");

            int quantity = 0;
            for (ShoppingItem item : model.getShoppingCart().getItems()) {
                quantity += item.getAmount();
            }


            totalQuantityLabel.setText(quantity + " varor");
        }

        private void displayListItemByCategory (String category){
            this.category = category;
            if (!cat) {
                currentPage = 0;
            }
            cat = true;
            search = false;
            list8Items.clear();
            currentListWithItems.clear();


            List<ProductA> productList = model.getProducts(category);

            for (int i = currentPage * 8; i < currentPage * 8 + 8; i++) {
                if (i > productList.size() - 1) {
                    break;
                }
                ListItem item = new ListItem(productList.get(i), model, this);
                currentListWithItems.add(item);

            }


            lastPage = productList.size() / 8;
            if (productList.size() % 8 > 0) {
                lastPage++;
            } else if (currentListWithItems.size() % 8 != 0) {
                lastPage++;
            }

            paneVaruDisplay.toFront();

            displayListItems();
        }

        private void displayListItemFromList (List < ProductA > productList) {
            if (!search) {
                currentPage = 0;
            }
            search = true;
            cat = false;
            list8Items.clear();
            currentListWithItems.clear();


            for (int i = currentPage * 8; i < currentPage * 8 + 8; i++) {
                if (i > productList.size() - 1) {
                    break;
                }
                ListItem item = new ListItem(productList.get(i), model, this);
                currentListWithItems.add(item);

            }

            lastPage = productList.size() / 8;
            if (productList.size() % 8 > 0) {
                lastPage++;
            } else if (currentListWithItems.size() % 8 != 0) {
                lastPage++;
            }

            paneVaruDisplay.toFront();

            displayListItems();
        }

        private void displayListItems () {
            flowPaneMainPage.getChildren().clear();
            updateListWith8ItemsFromCurrent();

            labelVarusida.setText("Sida " + (currentPage + 1) + " av " + lastPage);
            if (lastPage == 0){
                labelVarusida.setText("Sida 0 av 0");
            }

            for (ListItem item : list8Items) {
                flowPaneMainPage.getChildren().add(item);
                item.switchButtons();
                item.updateTextfieldWithAmountOfProduct();
            }
        }

        private void updateListWith8ItemsFromCurrent () {
            list8Items.clear();
            for (int i = 0; i < 8; i++) {
                if (i < currentListWithItems.size()) {
                    list8Items.add(currentListWithItems.get(i));
                }
            }
        }

        @FXML
        public void nextPageButton () {
            if (lastPage > currentPage + 1) {
                currentPage++;
                if (cat) {
                    displayListItemByCategory(category);
                }
                if (search) {
                    displayListItemFromList(tempSearch);
                }
                displayListItems();
            }
        }

        @FXML
        public void previousPageButton () {
            if (currentPage > 0) {
                currentPage--;
                if (cat) {
                    displayListItemByCategory(category);
                }
                if (search) {
                    displayListItemFromList(tempSearch);
                }
                displayListItems();
            }
        }

        @FXML
        public void onSecondBreadcrumb(){

        }

        @FXML
        public void onFirstBreadcrumb(){


        }

    private void initCategoryMenu() {
        buttonFavoriter.setOnAction(e -> displayListItemFromList(model.getFavorites()));
        buttonAllaBaljvaxter.setOnAction(e -> displayListItemByCategory("Baljväxter"));
        buttonBonor.setOnAction(e -> displayListItemByCategory("Bönor"));
        buttonLinser.setOnAction(e -> displayListItemByCategory("Linser"));
        buttonArtor.setOnAction(e -> displayListItemByCategory("Ärtor"));
        buttonAllaDrycker.setOnAction(e -> displayListItemByCategory("Drycker"));
        buttonKallaDrycker.setOnAction(e -> displayListItemByCategory("Kalla drycker"));
        buttonVarmaDrycker.setOnAction(e -> displayListItemByCategory("Varma drycker"));
        buttonAllaFrukt.setOnAction(e -> displayListItemByCategory("Frukt & Grönt"));
        buttonBar.setOnAction(e -> displayListItemByCategory("Bär"));
        buttonCitrusfrukter.setOnAction(e -> displayListItemByCategory("Citrusfrukter"));
        buttonExotiskaFrukter.setOnAction(e -> displayListItemByCategory("Exotiska frukter"));
        buttonFarskaOrtkryddor.setOnAction(e -> displayListItemByCategory("Färska örtkryddor"));
        buttonGronsaker.setOnAction(e -> displayListItemByCategory("Grönsaker"));
        buttonKal.setOnAction(e -> displayListItemByCategory("Kål"));
        buttonMelon.setOnAction(e -> displayListItemByCategory("Melon"));
        buttonPotatisOchRotfrukter.setOnAction(e -> displayListItemByCategory("Potatis & Rotfrukt"));
        buttonSotaStenfrukter.setOnAction(e -> displayListItemByCategory("Söta stenfrukter"));
        buttonAllaFisk.setOnAction(e -> displayListItemByCategory("Fisk & Skaldjur"));
        buttonFarskLax.setOnAction(e -> displayListItemByCategory("Färsk fisk"));
        buttonSill.setOnAction(e -> displayListItemByCategory("Sill"));
        buttonSkaldjur.setOnAction(e -> displayListItemByCategory("Skaldjur"));
        buttonOvrigFisk.setOnAction(e -> displayListItemByCategory("Övrig fisk"));
        buttonAllaKott.setOnAction(e -> displayListItemByCategory("Kött & Fågel"));
        buttonKyckling.setOnAction(e -> displayListItemByCategory("Kyckling"));
        buttonNotkott.setOnAction(e -> displayListItemByCategory("Nötkött"));
        buttonAllaMejeri.setOnAction(e -> displayListItemByCategory("Mejeriprodukter & Ägg"));
        buttonMjolk.setOnAction(e -> displayListItemByCategory("Mjölk"));
        buttonOst.setOnAction(e -> displayListItemByCategory("Ost"));
        buttonYoghurt.setOnAction(e -> displayListItemByCategory("Yoghurt & Filmjölk"));
        buttonAgg.setOnAction(e -> displayListItemByCategory("Ägg"));
        buttonAllaSkafferi.setOnAction(e -> displayListItemByCategory("Skafferi"));
        buttonBrod.setOnAction(e -> displayListItemByCategory("Bröd"));
        buttonFron.setOnAction(e -> displayListItemByCategory("Frön"));
        buttonKakao.setOnAction(e -> displayListItemByCategory("Kakao"));
        buttonKnackebrod.setOnAction(e -> displayListItemByCategory("KnäckeBröd"));
        buttonMjol.setOnAction(e -> displayListItemByCategory("Mjöl"));
        buttonNotter.setOnAction(e -> displayListItemByCategory("Nötter"));
        buttonPasta.setOnAction(e -> displayListItemByCategory("Pasta"));
        buttonRis.setOnAction(e -> displayListItemByCategory("Ris"));
        //TODO: SOCKER OCH SALT ÄR OLIKA KATEGORIER, Kanske har fixat?
        buttonSockerOchSalt.setOnAction(e -> displayListItemByCategory("Socker & Salt"));
        buttonAllaSotsaker.setOnAction(e -> displayListItemByCategory("Sötsaker"));
        buttonBakverk.setOnAction(e -> displayListItemByCategory("Bakverk & Kakor"));
        buttonGlass.setOnAction(e -> displayListItemByCategory("Glass"));
        buttonGodis.setOnAction(e -> displayListItemByCategory("Godis"));
        buttonSnacks.setOnAction(e -> displayListItemByCategory("Snacks"));
    }



}


