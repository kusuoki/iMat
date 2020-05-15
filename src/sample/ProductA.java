package sample;

import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

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

    // delegations
    @Override
    public ProductCategory getCategory() {
        return oldProduct.getCategory();
    }

    @Override
    public void setCategory(ProductCategory category) {
        oldProduct.setCategory(category);
    }

    @Override
    public String getName() {
        return oldProduct.getName();
    }

    @Override
    public void setName(String name) {
        oldProduct.setName(name);
    }

    @Override
    public Boolean isEcological() {
        return oldProduct.isEcological();
    }

    @Override
    public void setIsEcological(Boolean isEco) {
        oldProduct.setIsEcological(isEco);
    }

    @Override
    public double getPrice() {
        return oldProduct.getPrice();
    }

    @Override
    public void setPrice(double price) {
        oldProduct.setPrice(price);
    }

    @Override
    public int getProductId() {
        return oldProduct.getProductId();
    }

    @Override
    public void setProductId(int productId) {
        oldProduct.setProductId(productId);
    }

    @Override
    public String getUnit() {
        return oldProduct.getUnit();
    }

    @Override
    public String getUnitSuffix() {
        return oldProduct.getUnitSuffix();
    }

    @Override
    public void setUnit(String unit) {
        oldProduct.setUnit(unit);
    }

    @Override
    public String getImageName() {
        return oldProduct.getImageName();
    }

    @Override
    public void setImageName(String imageName) {
        oldProduct.setImageName(imageName);
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
