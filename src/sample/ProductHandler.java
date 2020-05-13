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
            case 28:
            case 29:
            case 30:
            case 31:
                p.setMainCategory("Drycker");
                p.setSubCategory("Varma drycker");
                break;
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
                p.setMainCategory("Drycker");
                p.setSubCategory("Kalla drycker");
                break;
            case 71:
            case 72:
            case 76:
            case 149:
            case 150:
                p.setMainCategory("Kött & Fågel");
                p.setSubCategory("Nötkött");
                break;
            case 73:
            case 74:
            case 75:
                p.setMainCategory("Kött & Fågel");
                p.setSubCategory("Kyckling");
                break;
            case 50:
            case 52:
                p.setMainCategory("Fisk & Skaldjur");
                p.setSubCategory("Färsk fisk");
                break;
            case 48:
            case 54:
                p.setMainCategory("Fisk & Skaldjur");
                p.setSubCategory("Övrig fisk");
                break;
            case 49:
            case 51:
                p.setMainCategory("Fisk & Skaldjur");
                p.setSubCategory("Skaldjur");
                break;
            case 53:
                p.setMainCategory("Fisk & Skaldjur");
                p.setSubCategory("Sill");
                break;
            case 80:
                p.setMainCategory("Mejeriprodukter & Ägg");
                p.setSubCategory("Mjölk");
                break;
            case 77:
            case 79:
            case 81:
            case 82:
                p.setMainCategory("Mejeriprodukter & Ägg");
                p.setSubCategory("Ost");
                break;
            case 85:
            case 154:
                p.setMainCategory("Mejeriprodukter & Ägg");
                p.setSubCategory("Ägg");
                break;
            case 78:
            case 83:
            case 84:
                p.setMainCategory("Mejeriprodukter & Ägg");
                p.setSubCategory("Yoghurt & Filmjölk");
                break;
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 64:
            case 126:
                p.setMainCategory("Frukt & Grönt");
                p.setSubCategory("Grönsaker");
                break;
            case 63:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69: // nice
            case 70:
            case 152:
            case 153:
                p.setMainCategory("Frukt & Grönt");
                p.setSubCategory("Kål");
                break;
            case 116:
            case 117:
            case 118:
            case 119:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 127:
            case 128:
                p.setMainCategory("Frukt & Grönt");
                p.setSubCategory("Potatis & Rotfrukt");
                break;
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
                p.setMainCategory("Frukt & Grönt");
                p.setSubCategory("Melon");
                break;
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                p.setMainCategory("Frukt & Grönt");
                p.setSubCategory("Bär");
                break;
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
                p.setMainCategory("Frukt & Grönt");
                p.setSubCategory("Citrusfrukter");
                break;
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
                p.setMainCategory("Frukt & Grönt");
                p.setSubCategory("Exotiska frukter");
                break;
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
                p.setMainCategory("Frukt & Grönt");
                p.setSubCategory("Söta stenfrukter");
                break;
            case 141:
            case 142:
            case 143:
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
                p.setMainCategory("Frukt & Grönt");
                p.setSubCategory("Färska örtkryddor");
                break;
            case 6:
            case 7:
                p.setMainCategory("Baljväxter");
                p.setSubCategory("Bönor");
                break;
            case 3:
            case 4:
            case 5:
                p.setMainCategory("Baljväxter");
                p.setSubCategory("Linser");
                break;
            case 1:
            case 2:
                p.setMainCategory("Baljväxter");
                p.setSubCategory("Ärtor");
                break;
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
                p.setMainCategory("Skafferi");
                p.setSubCategory("Bröd");
                break;
            case 11:
            case 13:
                p.setMainCategory("Skafferi");
                p.setSubCategory("KnäckeBröd");
                break;
            case 92:
            case 93:
            case 96:
                p.setMainCategory("Skafferi");
                p.setSubCategory("Mjöl");
                break;
            case 27:
                p.setMainCategory("Skafferi");
                p.setSubCategory("Kakao");
                break;
            case 95:
            case 151:
                p.setMainCategory("Skafferi");
                p.setSubCategory("Socker");
                break;
            case 94:
                p.setMainCategory("Skafferi");
                p.setSubCategory("Salt");
                break;
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
                p.setMainCategory("Skafferi");
                p.setSubCategory("Nötter");
                break;
            case 104:
            case 105:
                p.setMainCategory("Skafferi");
                p.setSubCategory("Frön");
                break;
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
                p.setMainCategory("Skafferi");
                p.setSubCategory("Pasta");
                break;
            case 113:
            case 114:
            case 115:
            case 120:
                p.setMainCategory("Skafferi");
                p.setSubCategory("Ris");
                break;
            case 136:
                p.setMainCategory("Bakverk, glass, godis & snacks");
                p.setSubCategory("Glass");
                break;
            case 135:
            case 140:
                p.setMainCategory("Bakverk, glass, godis & snacks");
                p.setSubCategory("Godis");
                break;
            case 137:
            case 138:
            case 139:
                p.setMainCategory("Bakverk, glass, godis & snacks");
                p.setSubCategory("Bakverk & Kakor");
                break;
            case 134:
                p.setMainCategory("Bakverk, glass, godis & snacks");
                p.setSubCategory("Snacks");
                break;
        }
    }

    private ProductHandler() {}
}

