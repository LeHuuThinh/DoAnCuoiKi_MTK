package model;

import decorator.Drink;
import strategy.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Order {
    private List<Drink> drinks = new ArrayList<>();
    private double totalAmount = 0.0;
    private PaymentStrategy paymentStrategy;

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void calculateTotal() {
        totalAmount = 0.0;
        for (Drink drink : drinks) {
            totalAmount += drink.cost();
        }
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public PaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }

    public void processPayment() {
        if (paymentStrategy == null) {
            System.out.println("Hay chon mot phuong thuc thanh toan!");
        } else {
            paymentStrategy.pay(totalAmount);
        }
    }

    public void printOrderDetails() {
        System.out.println("\n===== CHI TIET DON HANG =====");
        for (Drink drink : drinks) {
            System.out.println("- " + drink.getDescription() + ": " + drink.cost());
        }
        System.out.println("Tong: " + totalAmount);
    }

    // --- MỚI: trả về tóm tắt các món ở dạng chuỗi (dùng để lưu vào DB)
    public String getOrderSummary() {
        if (drinks.isEmpty()) return "(no items)";
        // mỗi món: "Name (price)" ; nếu muốn kèm topping, drink.getDescription() đã mô tả chung
        return drinks.stream()
                .map(d -> d.getDescription() + " (" + d.cost() + ")")
                .collect(Collectors.joining(" | "));
    }

    // --- MỚI: trả về tên phương thức thanh toán
    public String getPaymentMethodName() {
        if (paymentStrategy == null) return "Chua chon";
        return paymentStrategy.getName();
    }

    // (tuỳ chọn) reset order sau khi thanh toán
    public void clear() {
        drinks.clear();
        totalAmount = 0.0;
        paymentStrategy = null;
    }
}
