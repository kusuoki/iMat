package sample;

import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductHandler {

    private IMatDataHandler handler;
    private List<ProductA> products;

    private static ProductHandler productHandler;

    public static ProductHandler getInstance(){
        if (productHandler == null){
            productHandler = new ProductHandler();
            productHandler.init();
        }
        return productHandler;
    }

    public List<ProductA> getProducts() {
        return products;
    }

    // returns a list of ProductA that has the same category as the argument
    public List<ProductA> getProducts(String category) {
        List<ProductA> productByCategory = new ArrayList<>();
        for (ProductA p : products){
            if (p.getMainCategory().equals(category) || p.getSubCategory().equals(category)){
                productByCategory.add(p);
            }
        }
        return productByCategory;
    }

    // returns a ProductA with a give ID
    public ProductA getProduct(int id) {
        for (ProductA p : products){
            if (p.getProductId() == id) {
                return p;
            }
        }
        return null;
    }

    // returns the ProductA that has the Product p
    public ProductA getProductA(Product p) {
        for (ProductA p_A : products) {
            if (p_A.getOldVersion().equals(p)) {
                return p_A;
            }
        }
        return null;
    }

    // same as getProductA but for a list
    public List<ProductA> getProductAList(List<Product> productList) {
        List<ProductA> convertedList = new ArrayList<>();
        for (Product p : productList) {
            convertedList.add(getProductA(p));
        }
        return convertedList;
    }

    private void init() {
        handler = IMatDataHandler.getInstance();
        products = new ArrayList<>();

        loadProducts();
    }

    // simply converts Product into ProductA and saves in the list
    private void loadProducts() {
        List<Product> productList = handler.getProducts();

        for (Product p : productList) {
            ProductA productA = new ProductA(p);
            setProductCategories(productA);
            products.add(productA);
        }
    }

    // all categories should be hardcoded in, good luck
    private void setProductCategories(ProductA p) {
        switch (p.getProductId()){
            case 1: // this needs to be done 100+ more times
                p.setMainCategory("bla bla1");
                p.setSubCategory("bla bla2");
                break;
            case 2:
                break;







        }
    }

    private ProductHandler() {}
}
