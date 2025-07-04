package shipping;

import java.util.List;

public interface ShippableService {
    void ship(List<ShippableItem> items , String address);
}
