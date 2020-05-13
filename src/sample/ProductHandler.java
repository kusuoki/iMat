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
        switch (p.getProductId()) {
            case 28, 29, 30, 31 -> {
                p.setMainCategory("Drycker");
                p.setSubCategory("Varma drycker");
            }
            case 32, 33, 34, 35, 36, 37, 38, 39, 40 -> {
                p.setMainCategory("Drycker");
                p.setSubCategory("Kalla drycker");
            }
            case 71, 72, 76, 149, 150 -> {
                p.setMainCategory("Kött & Fågel");
                p.setSubCategory("Nötkött");
            }
            case 73, 74, 75 -> {
                p.setMainCategory("Kött & Fågel");
                p.setSubCategory("Kyckling");
            }
            case 50, 52 -> {
                p.setMainCategory("Fisk & Skaldjur");
                p.setSubCategory("Färsk fisk");
            }
            case 48, 54 -> {
                p.setMainCategory("Fisk & Skaldjur");
                p.setSubCategory("Övrig fisk");
            }
            case 49, 51 -> {
                p.setMainCategory("Fisk & Skaldjur");
                p.setSubCategory("Skaldjur");
            }
            case 53 -> {
                p.setMainCategory("Fisk & Skaldjur");
                p.setSubCategory("Sill");
            }
            case 80 -> {
                p.setMainCategory("Mejeriprodukter & Ägg");
                p.setSubCategory("Mjölk");
            }
            case 77, 79, 81, 82 -> {
                p.setMainCategory("Mejeriprodukter & Ägg");
                p.setSubCategory("Ost");
            }
            case 85, 154 -> {
                p.setMainCategory("Mejeriprodukter & Ägg");
                p.setSubCategory("Ägg");
            }
            case 78, 83, 84 -> {
                p.setMainCategory("Mejeriprodukter & Ägg");
                p.setSubCategory("Yoghurt & Filmjölk");
            }
            case 55, 56, 57, 58, 59, 60, 61, 62, 64, 126 -> {
                p.setMainCategory("Frukt & Grönt");
                p.setSubCategory("Grönsaker");
            }
            case 63, 65, 66, 67, 68, 69, 70, 152, 153 -> {
                p.setMainCategory("Frukt & Grönt");
                p.setSubCategory("Kål");
            }
            case 116, 117, 118, 119, 121, 122, 123, 124, 125, 127, 128 -> {
                p.setMainCategory("Frukt & Grönt");
                p.setSubCategory("Potatis & Rotfrukt");
            }
            case 86, 87, 88, 89, 90 -> {
                p.setMainCategory("Frukt & Grönt");
                p.setSubCategory("Melon");
            }
            case 15, 16, 17, 18, 19, 20, 21 -> {
                p.setMainCategory("Frukt & Grönt");
                p.setSubCategory("Bär");
            }
            case 22, 23, 24, 25, 26 -> {
                p.setMainCategory("Frukt & Grönt");
                p.setSubCategory("Citrusfrukter");
            }
            case 41, 42, 43, 44, 45, 46, 47 -> {
                p.setMainCategory("Frukt & Grönt");
                p.setSubCategory("Exotiska frukter");
            }
            case 129, 130, 131, 132, 133 -> {
                p.setMainCategory("Frukt & Grönt");
                p.setSubCategory("Söta stenfrukter");
            }
            case 141, 142, 143, 144, 145, 146, 147, 148 -> {
                p.setMainCategory("Frukt & Grönt");
                p.setSubCategory("Färska örtkryddor");
            }
            case 6, 7 -> {
                p.setMainCategory("Baljväxter");
                p.setSubCategory("Bönor");
            }
            case 3, 4, 5 -> {
                p.setMainCategory("Baljväxter");
                p.setSubCategory("Linser");
            }
            case 1, 2 -> {
                p.setMainCategory("Baljväxter");
                p.setSubCategory("Ärtor");
            }
            case 8, 9, 10, 12, 14 -> {
                p.setMainCategory("Skafferi");
                p.setSubCategory("Bröd");
            }
            case 11, 13 -> {
                p.setMainCategory("Skafferi");
                p.setSubCategory("KnäckeBröd");
            }
            case 92, 93, 96 -> {
                p.setMainCategory("Skafferi");
                p.setSubCategory("Mjöl");
            }
            case 27 -> {
                p.setMainCategory("Skafferi");
                p.setSubCategory("Kakao");
            }
            case 95, 151 -> {
                p.setMainCategory("Skafferi");
                p.setSubCategory("Socker");
            }
            case 94 -> {
                p.setMainCategory("Skafferi");
                p.setSubCategory("Salt");
            }
            case 97, 98, 99, 100, 101, 102, 103 -> {
                p.setMainCategory("Skafferi");
                p.setSubCategory("Nötter");
            }
            case 104, 105 -> {
                p.setMainCategory("Skafferi");
                p.setSubCategory("Frön");
            }
            case 106, 107, 108, 109, 110, 111, 112 -> {
                p.setMainCategory("Skafferi");
                p.setSubCategory("Pasta");
            }
            case 113, 114, 115, 120 -> {
                p.setMainCategory("Skafferi");
                p.setSubCategory("Ris");
            }
            case 136 -> {
                p.setMainCategory("Bakverk, glass, godis & snacks");
                p.setSubCategory("Glass");
            }
            case 135, 140 -> {
                p.setMainCategory("Bakverk, glass, godis & snacks");
                p.setSubCategory("Godis");
            }
            case 137, 138, 139 -> {
                p.setMainCategory("Bakverk, glass, godis & snacks");
                p.setSubCategory("Bakverk & Kakor");
            }
            case 134 -> {
                p.setMainCategory("Bakverk, glass, godis & snacks");
                p.setSubCategory("Snacks");
            }
        }
    }

    private ProductHandler() {}
}

