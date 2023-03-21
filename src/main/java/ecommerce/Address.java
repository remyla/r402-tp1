package ecommerce;

public class Address {

    private String street;

    private String city;

    private int zipCode;

    public static final Address SHIPPING_ADDRESS = new Address("Avenue d'Occitanie", "Montpellier", 34000);

    public Address(String street, String city, int zipCode) {
        if (zipCode < 0 || zipCode > 999999) {
            throw new IllegalArgumentException("incorrect zip code");
        }
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }
    public int distance(Address a) {
        int deptA = zipCode / 1000;
        int deptB = a.zipCode / 1000;
        if (deptA == deptB) {
            return 50; // les deux adresses sont dans le même département
        } else if (deptA < 100 && deptB < 100) {
            return 500; // les deux adresses sont en France métropolitaine
        } else {
            return 5000; // au moins une des deux adresses est en Outre-mer
        }
    }

}
