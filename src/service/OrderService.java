package service;
import dto.OrderSummary;
import model.CartItem;
import model.Customer;
import shipping.ShippableCartItem;
import shipping.ShippableItem;
import shipping.ShippableService;
import shipping.ShippingCalculator;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private final ShippingCalculator shippingCalculator;
    private final ShippableService shippableService;

    public OrderService(ShippingCalculator shippingCalculator, ShippableService shippingService) {
        this.shippingCalculator = shippingCalculator;
        this.shippableService = shippingService;
    }

    public OrderSummary processOrder(Cart cart, Customer customer, String shippingAddress) {
        validateOrder(cart, customer);
        List<CartItem> items = cart.getItems();
        double subtotal = cart.getSubtotal();
        double shippingFee = calculateShippingFee(cart);
        double total = subtotal + shippingFee;

        if (!customer.hasBalance(total)) {
            throw new IllegalStateException("Insufficient customer balance");
        }

        reserveStock(items);

        try {
            customer.deductBalance(total);
            OrderSummary summary = new OrderSummary(subtotal, shippingFee, items);

            //ship items
            List<CartItem> shippableItems = cart.getShippableItems();
            if (!shippableItems.isEmpty()) {
                List<ShippableItem> shipItems = shippableItems.stream().map(ShippableCartItem::new).collect(Collectors.toList());
                shippableService.ship(shipItems, shippingAddress);
            }
            cart.clear();
            return summary;

        } catch (Exception e) {
            restoreStock(items);
            throw e;
        }
    }

    private void validateOrder(Cart cart, Customer customer) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("service.Cart is empty");
        }
        if (customer == null) {
            throw new IllegalArgumentException("model.Customer cannot  be null");
        }

        for (CartItem item : cart.getItems()) {
            if (!item.getProduct().isInStock(item.getQuantity())) {
                throw new IllegalStateException("model.Product " + item.getProduct().getName() + " is out of stock");
            }
        }
    }

    private double calculateShippingFee(Cart cart) {
        List<CartItem> shippableItems = cart.getShippableItems();
        if (shippableItems.isEmpty()) {
            return 0.0;
        }
        double totalWeight = cart.getTotalWeight();
        return shippingCalculator.calculateShipping(totalWeight, shippableItems);
    }

    private void reserveStock(List<CartItem> items) {
        for (CartItem item : items) {
            item.getProduct().reduceStock(item.getQuantity());
        }
    }

    private void restoreStock(List<CartItem> items) {
        for (CartItem item : items) {
            item.getProduct().addStock(item.getQuantity());
        }
    }
}
