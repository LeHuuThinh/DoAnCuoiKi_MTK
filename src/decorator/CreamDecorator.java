package decorator;

public class CreamDecorator extends DrinkDecorator {

    public CreamDecorator(Drink drink) {
        super(drink);
    }

    @Override
    public String getDescription() {
        return drink.getDescription() + ", Cream";
    }

    @Override
    public double cost() {
        return drink.cost() + 7000.0;
    }
}
