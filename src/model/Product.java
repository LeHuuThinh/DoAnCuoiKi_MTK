package model;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;

    // Constructor không tham số
    public Product() {}

    // thêm vào class Product
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = ""; // hoặc null tuỳ bạn muốn
    }


    // Constructor không có id (dùng khi thêm mới)
    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
