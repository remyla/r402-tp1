package ecommerce;

import java.math.BigDecimal;

public class FlatRateShipping extends ShippingMethod {

    private final static BigDecimal FLAT_RATE = new BigDecimal(15);

    protected FlatRateShipping(Address recipient, Address sender) {
        super(recipient, sender);
    }

    @Override
    public BigDecimal getCost() {
        return FLAT_RATE;
    }
}
