package ecommerce;

import java.time.LocalDate;

public class Client extends Person {

    private final LocalDate accountCreation;

    private final Multiset<Product> shoppingCart;

    private boolean premiumAccount;

    private Address preferredDeliveryAddress;

    public Client(String firstName, String lastName) {
        super(firstName, lastName);
        this.accountCreation = LocalDate.now();
        this.shoppingCart = new Multiset<>();
        this.premiumAccount = false;
        this.preferredDeliveryAddress = null;
    }

    public LocalDate getAccountCreation() {
        return accountCreation;
    }

    public Multiset<Product> getShoppingCart() {
        return shoppingCart;
    }

    public void activatePremiumAccount() {
        premiumAccount = true;
    }

    public void revokePremiumAccount() {
        premiumAccount = false;
    }

    public boolean hasPremiumAccount() {
        return premiumAccount;
    }

    public Address getPreferredDeliveryAddress() {
        return preferredDeliveryAddress;
    }

    public void setPreferredDeliveryAddress(Address preferredDeliveryAddress) {
        this.preferredDeliveryAddress = preferredDeliveryAddress;
    }

    public Order generateOrder(Address deliveryAddress) {
        ShippingMethod s = premiumAccount ?
                new ExpressShipping(deliveryAddress, Address.SHIPPING_ADDRESS)
                : new FlatRateShipping(deliveryAddress, Address.SHIPPING_ADDRESS);
        Order o = new Order(s, shoppingCart);
        shoppingCart.clear();
        return o;
    }


}
