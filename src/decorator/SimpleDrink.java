package decorator;

import decorator.Drink;
import model.Product;

public class SimpleDrink implements Drink {
    private Product product;

    public SimpleDrink(Product product) {
        this.product = product;
    }

    @Override
    public String getDescription() {
        return product.getName();
    }

    @Override
    public double cost() {
        return product.getPrice();
    }
}

