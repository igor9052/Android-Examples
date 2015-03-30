package ua.com.igorka.oa.android.gson.data;

/**
 * Created by Igor Kuzmenko on 17.03.2015.
 */
public class Product {

    private String name;
    private int amount;

    public Product() {
    }

    public Product(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
