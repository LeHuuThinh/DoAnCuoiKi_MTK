package decorator;

public class MilkDecorator extends DrinkDecorator {

    public MilkDecorator(Drink drink) {
        super(drink);
    }

    @Override
    public String getDescription() {
        return drink.getDescription() + ", Sua";
    }

    @Override
    public double cost() {
        return drink.cost() + 5000.0;
    }
}
