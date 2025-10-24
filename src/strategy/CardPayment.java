package strategy;

public class CardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Da tra " + amount + " bang Card.");
    }
}
