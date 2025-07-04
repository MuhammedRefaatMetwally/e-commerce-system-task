package shipping;

import model.CartItem;

import java.util.List;

//Strategy Pattern for different shipping calculations
public interface ShippingCalculator  {
    double calculateShipping(double totalWeight , List<CartItem> shippableItems);
}
