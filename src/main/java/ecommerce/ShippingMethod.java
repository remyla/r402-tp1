package ecommerce;

import java.math.BigDecimal;

public abstract class ShippingMethod {
    private final Address recipient;

    private final Address sender;

    protected ShippingMethod(Address recipient, Address sender) {
        if (recipient == null || sender == null) {
            throw new IllegalArgumentException("address cannot be null");
        }
        this.recipient = recipient;
        this.sender = sender;
    }

    public Address getRecipient() {
        return recipient;
    }

    public Address getSender() {
        return sender;
    }

    public abstract BigDecimal getCost();

}
