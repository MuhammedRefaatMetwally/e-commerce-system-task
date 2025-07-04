package shipping;

import model.CartItem;

public class ShippableCartItem  implements ShippableItem{
    private final CartItem cartItem;

    public ShippableCartItem (CartItem cartItem){
        this.cartItem = cartItem;
    }

    @Override
    public String getName() {
     return cartItem.getProduct().getName();
    }

    @Override
    public double getWeight() {
        return cartItem.getProduct().getWeight();
    }
}
