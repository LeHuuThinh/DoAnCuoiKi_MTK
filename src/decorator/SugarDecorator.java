package decorator;

public class SugarDecorator extends DrinkDecorator {

    public SugarDecorator(Drink drink) {
        super(drink);
    }

    @Override
    public String getDescription() {
        return drink.getDescription() + ", Duong";
    }

    @Override
    public double cost() {
        return drink.cost() + 2000.0;
    }
}
