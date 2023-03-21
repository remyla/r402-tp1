package ecommerce;

import java.math.BigDecimal;

public class ExpressShipping extends ShippingMethod {

    private final static BigDecimal RATE_PER_KILOMETER = new BigDecimal(7).divide(new BigDecimal(100));

    public ExpressShipping(Address recipient, Address sender) {
        super(recipient, sender);
    }

    @Override
    public BigDecimal getCost() {
        return RATE_PER_KILOMETER.multiply(BigDecimal.valueOf(getSender().distance(getRecipient())));
    }
}
