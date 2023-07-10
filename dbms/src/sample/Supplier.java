package sample;

public class Supplier {
    String supplier_ID;
    String name;
    String phone;
    String street;
    String city;
    String state;
    String pincode;
    String address;

    public Supplier(String supplier_ID, String name, String phone, String street, String city, String state, String pincode, String address) {
        this.supplier_ID = supplier_ID;
        this.name = name;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getSupplier_ID() {
        return supplier_ID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPincode() {
        return pincode;
    }
}
