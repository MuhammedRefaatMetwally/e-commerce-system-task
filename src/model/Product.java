package model;

public class Product {
    private final String name;
    private final double price;
    private final double weight;
    private final boolean expireAble;
    private final boolean requiresShipping;
    private int stockQuantity;

    public Product(String name, double price, double weight, boolean expireAble, boolean requiresShipping, int stockQuantity) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("model.Product name cannot be null or empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("model.Product price cannot be negative");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("model.Product weight cannot be negative");
        }
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }

        this.name = name;
        this.price = price;
        this.weight = weight;
        this.expireAble = expireAble;
        this.requiresShipping = requiresShipping;
        this.stockQuantity = stockQuantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isExpireAble() {
        return expireAble;
    }

    public boolean isRequiresShipping() {
        return requiresShipping;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void reduceStock(int quantity){
        if (quantity <= 0){
            throw new IllegalArgumentException("Quantity must be positive!");
        }
        if (quantity > stockQuantity){
            throw  new IllegalStateException("Insufficient stock");
        }
        this.stockQuantity -= quantity;
    }

    public void addStock(int quantity){
        if (quantity <= 0){
            throw new IllegalArgumentException("Quantity must be positive!");
        }
        this.stockQuantity += quantity;
    }

    public boolean isInStock(int quantity){
        return  stockQuantity >= quantity;
    }

    @Override
    public String toString() {
        return String.format("model.Product{name='%s' , price=%.2f , weight=%.2f , stock=%d}",name,price,weight,stockQuantity);
    }
}
