package strategy;

public class MoMoPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Da tra " + amount + " bang MoMo.");
    }
}
