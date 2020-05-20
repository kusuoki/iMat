package sample;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import se.chalmers.cse.dat216.project.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {

    //Den enda instansen av modelklassen
    private static Model model;
    //referens till DataHandlerns som lagrar all information om programmet
    private IMatDataHandler iMatDataHandler;
    private ProductHandler productHandler;

    private Map<Integer, ShoppingItem> shoppingItemMap = new HashMap<>();

    //privat default konstruktor för singleton pattern
    private Model(){}

    //metod för att se till att det endast finns en instans av modell
    public static Model getInstance (){
        if (model==null){
            model=new Model();
            model.init();
        }
        return model;

    }
    //skapar referens till IMatDataHandler
    private void init() {

        iMatDataHandler = IMatDataHandler.getInstance();
        productHandler = ProductHandler.getInstance();

        for (ShoppingItem item : iMatDataHandler.getShoppingCart().getItems()) {
            shoppingItemMap.put(item.getProduct().getProductId(), item);
        }
    }
    //Alla dessa under är i princip setters och getters
    public List<ProductA> getProducts() {
        return productHandler.getProducts();
    }

    public ProductA getProduct(int idNbr) {
        return productHandler.getProduct(idNbr);
    }

    public List<ProductA> getProducts(String category) {
        return productHandler.getProducts(category);
    }

    public List<ProductA> findProducts(String s) {
        return productHandler.getProductAList(iMatDataHandler.findProducts(s));
    }

    // dont need ProductA here because ProductA extends Product
    public Image getImage(Product p) {
        return iMatDataHandler.getFXImage(p);
    }

    public Image getImage(Product p, double width, double height) {
        return iMatDataHandler.getFXImage(p, width, height);
    }

    public void addToShoppingCart(Product p) {
        ShoppingItem item = new ShoppingItem(p);
        shoppingItemMap.put(p.getProductId(), item);
        getShoppingCart().addItem(item);
    }

    public void removeFromShoppingCart(Product p) {
        ShoppingItem item = getShoppingItemMap().get(p.getProductId());
        item.setAmount(0);
        getShoppingCart().removeItem(item);   //Tar bort varan från shoppingcarten
        shoppingItemMap.remove(p.getProductId());       //Tar bort varan från HashMap:en också
    }

    public int getAmountOfThisProductInShoppinCart(Product p){
        ShoppingItem item = getShoppingItemMap().get(p.getProductId());
        if (item == null) {
            return 0;
        }
        return (int) item.getAmount();
    }

    public boolean updateShoppingCart(Product p, int amount) {
        ShoppingItem item = shoppingItemMap.get(p.getProductId());
        if (item != null) {
            item.setAmount(item.getAmount() + amount);
            getShoppingCart().fireShoppingCartChanged(item, true);
            return true;
        }
        return false;
    }

    public boolean setShoppingCartItem(Product p, int amount) {
        ShoppingItem item = shoppingItemMap.get(p.getProductId());
        if (item != null) {
            item.setAmount(amount);
            getShoppingCart().fireShoppingCartChanged(item, true);
            return true;
        }
        return false;
    }

    public Map<Integer, ShoppingItem> getShoppingItemMap() {
        return shoppingItemMap;
    }



    public CreditCard getCreditCard() {
        return iMatDataHandler.getCreditCard();
    }

    public Customer getCustomer() {
        return iMatDataHandler.getCustomer();
    }

    public ShoppingCart getShoppingCart() {
        return iMatDataHandler.getShoppingCart();
    }

    public void clearShoppingCart() {

        iMatDataHandler.getShoppingCart().clear();

    }

    public void placeOrder() {

        iMatDataHandler.placeOrder();

    }


    public int getNumberOfOrders() {

        return iMatDataHandler.getOrders().size();


    }
    public List <Order> getOrders(){
        return iMatDataHandler.getOrders();


    }

    public void shutDown() {
        iMatDataHandler.shutDown();
    }

    public void addFavorite(Product p){
        iMatDataHandler.addFavorite(p);

    }

    public void removeFavorite(Product p){
        iMatDataHandler.removeFavorite(p);
    }

    public boolean isFavorite(Product p) {
        return iMatDataHandler.isFavorite(p);
    }

    public List<ProductA> getFavorites() {
        return productHandler.getProductAList(iMatDataHandler.favorites());
    }

    // changes the cursor when hovered on the given imageview,
    // set afterHover null if don't want to change the image
    public void setImageViewOnHoverEvent(ImageView iv, Image afterHover) {
        Image beforeHover = iv.getImage();
        iv.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                iv.setCursor(Cursor.HAND);
                if (afterHover != null) {
                    iv.setImage(afterHover);
                }
            }
        });

        iv.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                iv.setCursor(Cursor.DEFAULT);
                if (afterHover != null) {
                    iv.setImage(beforeHover);
                }
            }
        });
    }

}
