package strategy;

public class CashPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Da tra " + amount + " bang Tien Mat.");
    }
}
