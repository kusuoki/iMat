package sample;

import se.chalmers.cse.dat216.project.Product;

// a modified version of class Product to fit our needs
public class ProductA extends Product {
    private String mainCategory;
    private String subCategory;

    // saves a copy in case of spaghetti code, can be removed later
    private final Product oldProduct;

    // can only be initiated with an existing copy of Product
    public ProductA(Product p) {
        oldProduct = p;
    }

    public Product getOldVersion() {
        return oldProduct;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }
}
