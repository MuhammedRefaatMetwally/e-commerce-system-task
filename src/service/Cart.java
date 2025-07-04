package service;

import model.CartItem;
import model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cart {
    private final Map<Product, CartItem> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    public void addItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("model.Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (!product.isInStock(quantity)) {
            throw new IllegalStateException("model.Product is out of stock");
        }
        CartItem existingItem = items.get(product);
        if (existingItem != null) {
            int newQuantity = existingItem.getQuantity() + quantity;
            if (!product.isInStock(newQuantity)) {
                throw new IllegalStateException("Insufficient stock for the new quantity");
            }
            existingItem.setQuantity(newQuantity);
        } else {
            items.put(product, new CartItem(product, quantity));
        }
    }

    public void removeItem(Product product) {
        items.remove(product);
    }

    public void updateQuantity(Product product, int quantity) {
        if (quantity <= 0) {
            removeItem(product);
            return;
        }
        CartItem item = items.get(product);
        if (item != null) {
            if (!product.isInStock(quantity)) {
                throw new IllegalStateException("Insufficient stock for the new quantity");
            }
            item.setQuantity(quantity);
        }
    }



    public List<CartItem> getItems() {
        return new ArrayList<>(items.values());
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }
    public double getSubtotal() {
        return items.values().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public double getTotalWeight() {
        return items.values().stream()
                .filter(item -> item.getProduct().isRequiresShipping())
                .mapToDouble(CartItem::getTotalWeight)
                .sum();
    }

    public List<CartItem> getShippableItems() {
        return items.values().stream()
                .filter(item -> item.getProduct().isRequiresShipping())
                .collect(Collectors.toList());
    }

}
