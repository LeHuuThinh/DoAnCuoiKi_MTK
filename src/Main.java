import dao.DrinkDAO;
import dao.OrderHistoryDAO;
import decorator.*;
import model.Order;
import model.Product;
import strategy.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Order order = new Order();
        Drink currentDrink = null;

        while (true) {
            System.out.println("\n===== COFFEE SHOP =====");
            System.out.println("1. Chon do uong moi");
            System.out.println("2. Them topping vao mon hien tai");
            System.out.println("3. Them mon hien tai vao Order");
            System.out.println("4. Xem don hang");
            System.out.println("5. Chon phuong thuc thanh toan");
            System.out.println("6. Thanh toan");
            System.out.println("7. Xem lich su Order");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // tránh lỗi xuống dòng

            switch (choice) {
                case 1 -> {
                    DrinkDAO drinkDAO = new DrinkDAO();
                    var drinks = drinkDAO.getAllDrinks();

                    if (drinks.isEmpty()) {
                        System.out.println("⚠ Khong co do uong nao trong he thong!");
                        break;
                    }

                    System.out.println("Chon do uong:");
                    for (int i = 0; i < drinks.size(); i++) {
                        Product p = drinks.get(i);
                        System.out.println((i + 1) + ". " + p.getName() + " (Gia: " + p.getPrice() + ")");
                    }

                    System.out.print("Nhap lua chon: ");
                    int select = scanner.nextInt();
                    scanner.nextLine();

                    if (select < 1 || select > drinks.size()) {
                        System.out.println("❌ Lua chon khong hop le!");
                        break;
                    }

                    Product chosen = drinks.get(select - 1);
                    currentDrink = new SimpleDrink(chosen);

                    System.out.println("✔ Da chon: " + currentDrink.getDescription());
                }

                case 2 -> {
                    if (currentDrink == null) {
                        System.out.println("Ban chua chon do uong nao!");
                        break;
                    }
                    System.out.println("Chon topping:");
                    System.out.println("1. Sua (+5000)");
                    System.out.println("2. Duong (+2000)");
                    System.out.println("3. Cream (+7000)");
                    int toppingChoice = scanner.nextInt();

                    switch (toppingChoice) {
                        case 1 -> currentDrink = new MilkDecorator(currentDrink);
                        case 2 -> currentDrink = new SugarDecorator(currentDrink);
                        case 3 -> currentDrink = new CreamDecorator(currentDrink);
                    }
                    System.out.println("Mon hien tai: " + currentDrink.getDescription());
                }
                case 3 -> {
                    if (currentDrink != null) {
                        order.addDrink(currentDrink);
                        System.out.println("Da them vao don: " + currentDrink.getDescription());
                        currentDrink = null;
                    } else {
                        System.out.println("Khong co mon nao de them!");
                    }
                }
                case 4 -> {
                    order.calculateTotal();
                    order.printOrderDetails();
                }
                case 5 -> {
                    System.out.println("Chon phuong thuc thanh toan:");
                    System.out.println("1. Tien Mat");
                    System.out.println("2. MoMo");
                    System.out.println("3. Card");
                    int payChoice = scanner.nextInt();

                    switch (payChoice) {
                        case 1 -> order.setPaymentStrategy(new CashPayment());
                        case 2 -> order.setPaymentStrategy(new MoMoPayment());
                        case 3 -> order.setPaymentStrategy(new CardPayment());
                    }
                    System.out.println("Da chon phuong thuc thanh toan!");
                }
                case 6 -> {
                    order.calculateTotal();
                    order.printOrderDetails();
                    System.out.println("---- Thanh toan ----");
                    order.processPayment();

                    // Lưu lịch sử vào DB
                    OrderHistoryDAO historyDAO = new OrderHistoryDAO();
                    String itemsSummary = order.getOrderSummary();
                    double total = order.getTotalAmount();
                    String paymentName = order.getPaymentMethodName();
                    historyDAO.saveOrder(itemsSummary, total, paymentName);

                    // reset order nếu muốn
                    order.clear();
                }
                case 7 -> {
                    OrderHistoryDAO historyDAO = new OrderHistoryDAO();
                    var history = historyDAO.getOrderHistory();
                    System.out.println("\n===== LICH SU DON HANG =====");
                    if (history.isEmpty()) {
                        System.out.println("Chua co don hang nao!");
                    } else {
                        for (String h : history) {
                            System.out.println(h);
                        }
                    }
                }

                case 0 -> {
                    System.out.println("Cam on ban da dung Coffee Shop!");
                    return;
                }
                default -> System.out.println("Lua chon khong hop le!");
            }
        }
    }
}
