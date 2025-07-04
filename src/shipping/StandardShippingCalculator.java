package shipping;

import model.CartItem;

import java.util.List;

public class StandardShippingCalculator implements ShippingCalculator {
    private static final double RATE_PER_KM = 5.0;
    private static final double MINIMUM_FEE = 10.0;

    @Override
    public double calculateShipping(double totalWeight, List<CartItem> shippableItems) {
        double shippingFee = totalWeight * RATE_PER_KM;
        return Math.max(shippingFee, MINIMUM_FEE);
    }
}
