package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    @FXML AnchorPane anchorUndermenyBaljvaxter;
    @FXML AnchorPane anchorUndermenyDrycker;
    @FXML AnchorPane anchorUndermenyFisk;
    @FXML AnchorPane anchorUndermenyFrukt;
    @FXML AnchorPane anchorUndermenyKott;
    @FXML AnchorPane anchorUndermenyMejeri;
    @FXML AnchorPane anchorUndermenySkafferi;
    @FXML AnchorPane anchorUndermenySotsaker;

    @FXML javafx.scene.control.Button buttonAllaBaljvaxter;
    @FXML javafx.scene.control.Button buttonBonor;
    @FXML javafx.scene.control.Button buttonLinser;
    @FXML javafx.scene.control.Button buttonArtor;
    @FXML javafx.scene.control.Button buttonAllaDrycker;
    @FXML javafx.scene.control.Button buttonKallaDrycker;
    @FXML javafx.scene.control.Button buttonVarmaDrycker;
    @FXML javafx.scene.control.Button buttonAllaFrukt;
    @FXML javafx.scene.control.Button buttonBar;
    @FXML javafx.scene.control.Button buttonCitrusfrukter;
    @FXML javafx.scene.control.Button buttonExotiskaFrukter;
    @FXML javafx.scene.control.Button buttonFarskaOrtkryddor;
    @FXML javafx.scene.control.Button buttonGronsaker;
    @FXML javafx.scene.control.Button buttonKal;
    @FXML javafx.scene.control.Button buttonMelon;
    @FXML javafx.scene.control.Button buttonPotatisOchRotfrukter;
    @FXML javafx.scene.control.Button buttonSotaStenfrukter;
    @FXML javafx.scene.control.Button buttonAllaFisk;
    @FXML javafx.scene.control.Button buttonFarskLax;
    @FXML javafx.scene.control.Button buttonSill;
    @FXML javafx.scene.control.Button buttonSkaldjur;
    @FXML javafx.scene.control.Button buttonOvrigFisk;
    @FXML javafx.scene.control.Button buttonAllaKott;
    @FXML javafx.scene.control.Button buttonKyckling;
    @FXML javafx.scene.control.Button buttonNotkott;
    @FXML javafx.scene.control.Button buttonAllaMejeri;
    @FXML javafx.scene.control.Button buttonMjolk;
    @FXML javafx.scene.control.Button buttonOst;
    @FXML javafx.scene.control.Button buttonYoghurt;
    @FXML javafx.scene.control.Button buttonAgg;
    @FXML javafx.scene.control.Button buttonAllaSkafferi;
    @FXML javafx.scene.control.Button buttonBrod;
    @FXML javafx.scene.control.Button buttonFron;
    @FXML javafx.scene.control.Button buttonKakao;
    @FXML javafx.scene.control.Button buttonKnackebrod;
    @FXML javafx.scene.control.Button buttonMjol;
    @FXML javafx.scene.control.Button buttonNotter;
    @FXML javafx.scene.control.Button buttonPasta;
    @FXML javafx.scene.control.Button buttonRis;
    @FXML javafx.scene.control.Button buttonSockerOchSalt;
    @FXML javafx.scene.control.Button buttonAllaSotsaker;
    @FXML javafx.scene.control.Button buttonBakverk;
    @FXML javafx.scene.control.Button buttonGlass;
    @FXML javafx.scene.control.Button buttonGodis;
    @FXML javafx.scene.control.Button buttonSnacks;

    @FXML AnchorPane anchorPaneMainPage;

    /*ArrayList<Pane> menuIndicators = new ArrayList<Pane>();
    ArrayList<javafx.scene.control.Button> menuButtons= new ArrayList<javafx.scene.control.Button>();
    ArrayList<ImageView> menuArrows = new ArrayList<ImageView>();*/
    ArrayList<menuItem> menuItems = new ArrayList<menuItem>();

    @FXML TextField searchField;

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

    private List<ListItem> listItems = new ArrayList<>();


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
        initMenuItems();


        saveListItemByCategory("Varma drycker");

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
            m.button.setOnAction((event)->{
                menuOnClick(m);
            });

            //Sätter en listener på imageview med pilen så att den byter bild och gör en kant på knappen om man hoverar över den
            m.arrow.hoverProperty().addListener((event) -> {

                menuOnHover(m);

            });

            m.arrow.setOnMouseClicked((event) -> menuOnClick(m));
            m.button.hoverProperty().addListener((event) -> menuOnHover(m));

            if (m.sMenu != null){
                for (javafx.scene.control.Button btn : m.sMenu){
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

    @FXML
    public void mainPageToFront(){
        anchorPaneMainPage.toFront();
    }

    @FXML
    public void mouseTrap(Event event){
        event.consume();
    }


    //När man klickar på menyn
   @FXML
    public void menuOnClick(menuItem m) {

       javafx.scene.control.Button b = m.button;

        for(menuItem mItem : menuItems) //Clears any button that may've been clicked before
        {
            if( mItem.button != b) {
                if (mItem.button.getStyleClass().toString().equals("menuButtonClicked")) {
                    resetButtonStyle(mItem);
                }
                if(m.anchorPane != null)
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
                if (m.anchorPane != null){
                    m.anchorPane.toFront();
                }
            }


    }

    //När man hoovrar över menyn
    public void menuOnHover(menuItem m) {

        for (menuItem item : menuItems)
        {
            if ((item != m) && !(item.button.getStyleClass().toString().equals("menuButtonClicked"))){
                resetButtonStyle(item);
            }
        }

        if (m.button.getStyleClass().toString().equals("menuButtonClicked"))
        {}
        else {
            m.button.getStyleClass().add("menuButtonHover");
            setLightgreenArrow(m.arrow);
        }
        //byter färg på arrow
        //ändrar styleclass på knappen
    }


    public void subMenuOnHover(ArrayList<javafx.scene.control.Button> sMenu, javafx.scene.control.Button btn){

        for (javafx.scene.control.Button b : sMenu)
             {
            if ((b != btn) && !(b.getStyleClass().toString().equals("menuButtonClicked"))){
                b.getStyleClass().clear();
                b.getStyleClass().add("menuButton");
            }
        }

        if (btn.getStyleClass().toString().equals("menuButtonClicked"))
        {}
        else {
            btn.getStyleClass().add("menuButtonHover");
        }
    }

    public void subMenuOnClick(ArrayList<javafx.scene.control.Button> sMenu, javafx.scene.control.Button btn){
        for(javafx.scene.control.Button b : sMenu) //Clears any button that may've been clicked before
        {
            if((b != btn) && b.getStyleClass().toString().equals("menuButtonClicked")) {
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
        }else {
            System.out.println("HJELLO");
            btn.getStyleClass().clear();
            btn.getStyleClass().add("menuButtonClicked");
        }
    }

    public void resetButtonStyle(menuItem m){
        //Återställer stilen på menyknappar
            m.button.getStyleClass().clear();
            m.button.getStyleClass().add("menuButton");
            setDarkgreenArrow(m.arrow);
            m.button.toFront();
            m.arrow.toFront();

            //Återställer stilen på undermenyknappar (under den knappen)
        if(m.sMenu != null){
        for (javafx.scene.control.Button btn : m.sMenu)
        {
            btn.getStyleClass().clear();
            btn.getStyleClass().add("menuButton");
        }
        }

    }

    public void setLightgreenArrow(ImageView arrow){ //Sets the arrow in a menuItem to lightgreen
        arrow.setImage(new Image("file:/C:/Users/hanna/Documents/Programmering/iMat/iMat/out/production/iMat/sample/resources/Icons/ic_arrow_lightgreen_64dpcentered.png"));
    }

    public void setDarkgreenArrow (ImageView arrow){ //Sets the arrow in a menuItem to darkgreen
        arrow.setImage(new Image("file:/C:/Users/hanna/Documents/Programmering/iMat/iMat/out/production/iMat/sample/resources/Icons/ic_arrow_darkgreen_64dp.png"));
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

        if (!cartEvent.isAddEvent()) {
            displayListItems();
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

    private void saveListItemByCategory(String category){
        listItems.clear();

        List<ProductA> productList = model.getProducts(category);

        for (ProductA p : productList){
            ListItem item = new ListItem(p, model);
            listItems.add(item);
        }

        displayListItems();
    }

    private void displayListItems() {
        flowPaneMainPage.getChildren().clear();
        for (ListItem item : listItems){
            flowPaneMainPage.getChildren().add(item);
            item.switchButtons();
        }
    }
}