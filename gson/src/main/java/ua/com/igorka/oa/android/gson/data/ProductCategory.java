package ua.com.igorka.oa.android.gson.data;

import java.util.List;

/**
 * Created by Igor Kuzmenko on 17.03.2015.
 *
 */
public class ProductCategory {

    private String name;
    private List<Product> products;

    public ProductCategory() {
    }

    public ProductCategory(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
