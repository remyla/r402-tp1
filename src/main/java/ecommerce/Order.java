package ecommerce;

import java.math.BigDecimal;
import java.util.Collection;
import java.time.LocalDate;
import java.util.Objects;

public class Order extends Multiset<Product> {

    private final LocalDate creation;

    private ShippingMethod shipping;

    private boolean paid;

    private boolean shipped;

    public Order(ShippingMethod shipping, Collection<Product> products) {
        super(products);
        if (shipping == null) { throw new IllegalArgumentException("shipping method cannot be null"); }
        this.creation = LocalDate.now();
        this.shipping = shipping;
        this.paid = false;
        this.shipped = false;
    }

    public LocalDate getCreation() {
        return creation;
    }

    public ShippingMethod getShippingMethod() {
        return shipping;
    }

    public void setShippingMethod(ShippingMethod shipping) {
        if (shipping == null) { throw new IllegalArgumentException("shipping method cannot be null"); }
        this.shipping = shipping;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isShipped() {
        return shipped;
    }

    public void setShipped(boolean shipped) {
        this.shipped = shipped;
    }

    public BigDecimal getTotalCost() {
        BigDecimal total = shipping.getCost();
        for (Product p: this) {
            total = total.add(p.getPrice());
        }
        return total;
    }
}
