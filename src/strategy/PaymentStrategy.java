package strategy;

public interface PaymentStrategy {
    void pay(double amount);

    // Default trả về tên lớp nếu bạn không override
    default String getName() {
        return this.getClass().getSimpleName();
    }
}
