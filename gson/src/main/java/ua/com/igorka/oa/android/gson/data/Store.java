package ua.com.igorka.oa.android.gson.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Igor Kuzmenko on 17.03.2015.
 *
 */
public class Store {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @SerializedName("product_categories")
    private List<ProductCategory> productCategories;

    public Store() {
    }

    public Store(List<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }

    public List<ProductCategory> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(List<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", productCategories=" + productCategories +
                '}';
    }
}
