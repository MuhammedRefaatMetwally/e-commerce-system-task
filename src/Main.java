import dto.OrderSummary;
import model.Customer;
import model.Product;
import service.Cart;
import service.OrderService;
import shipping.ShippableItem;
import shipping.ShippableService;
import shipping.ShippingCalculator;
import shipping.StandardShippingCalculator;

public class Main {
    public static void main(String[] args) {
        Product laptop = new Product("Laptop", 950.50, 2.5, false, true, 10);
        Product book = new Product("Java Spring", 49.80, 0.5, false, true, 5);
        Product software = new Product("Angular Best Practices", 199.99, 3.0, false, false, 100);

        Customer customer = new Customer("Muhammed Refaat", "muhammedrefaat@example.com", 1500.0);


        Cart cart = new Cart();
        cart.addItem(laptop, 1);
        cart.addItem(book, 2);
        cart.addItem(software, 1);


        ShippingCalculator shippingCalculator = new StandardShippingCalculator();

        ShippableService shippingService = (items, address) -> {
            System.out.println("Shipping to: " + address);
            for (ShippableItem item : items) {
                System.out.println("- " + item.getName() + " (Weight: " + item.getWeight() + "kg)");
            }
        };

        OrderService orderService = new OrderService(shippingCalculator, shippingService);

        try {
            OrderSummary summary = orderService.processOrder(cart, customer, "Smart Village");

            System.out.println(summary);
            System.out.println("model.Customer balance after order: $" + customer.getBalance());

        } catch (Exception e) {
            System.err.println("Order failed: " + e.getMessage());
        }
    }
}