package model;

public class Customer {
    private final String name;
    private final String email;
    private double balance;

    public Customer(String name, String email, double balance) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("model.Customer name cannot be null or empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("model.Customer email cannot be null or empty");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("model.Customer balance cannot be negative");
        }

        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public void deductBalance(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient balance");
        }
        this.balance -= amount;
    }


    public boolean hasBalance(double amount) {
        return balance >= amount;
    }
}
