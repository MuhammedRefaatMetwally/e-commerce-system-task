package dto;

import model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class OrderSummary {
    private final double subtotal;
    private final double shippingFee;
    private final double total;
    private final List<CartItem> items;

    public OrderSummary(double subtotal, double shippingFee, List<CartItem> items) {
        this.subtotal = subtotal;
        this.shippingFee = shippingFee;
        this.total = subtotal + shippingFee;
        this.items = new ArrayList<>(items);
    }

    public double getSubtotal() { return subtotal; }
    public double getShippingFee() { return shippingFee; }
    public double getTotal() { return total; }
    public List<CartItem> getItems() { return new ArrayList<>(items); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ORDER SUMMARY ===\n");
        for (CartItem item : items) {
            sb.append(item.toString()).append("\n");
        }
        sb.append(String.format("Subtotal: $%.2f\n", subtotal));
        sb.append(String.format("Shipping: $%.2f\n", shippingFee));
        sb.append(String.format("Total: $%.2f\n", total));
        return sb.toString();
    }
}