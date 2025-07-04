# 🛒 E-commerce System

A simple Java e-commerce system with clean code and good design patterns.

## What it does
- Add products to cart
- Calculate shipping costs
- Process orders
- Manage customer balance

## How to run
```bash
javac *.java
java Main
```

## Project structure
```
src/
├── Main.java           # Run this
├── model/              # Basic stuff (Product, Customer, CartItem)
├── service/            # Business logic (Cart, OrderService)  
├── shipping/           # Shipping calculations
└── dto/                # Order summary
```

## Quick example
```java
// Create stuff
Product laptop = new Product("Laptop", 999.99, 2.5, false, true, 10);
Customer john = new Customer("John", "john@email.com", 1500.0);
Cart cart = new Cart();

// Add to cart
cart.addItem(laptop, 1);

// Process order
OrderService service = new OrderService(new StandardShippingCalculator(), shippingService);
OrderSummary summary = service.processOrder(cart, john, "123 Main St");
```

## Key features
- ✅ Clean code with SOLID principles
- ✅ Design patterns (Strategy, Adapter)
- ✅ Error handling

That's it! Simple e-commerce system that actually works.
