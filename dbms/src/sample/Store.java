package sample;

public class Store {
    String store_ID;
    String phone;
    String street;
    String city;
    String state;
    String pincode;
    String address;

    public Store(String store_ID, String phone, String street, String city, String state, String pincode, String address) {
        this.store_ID = store_ID;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.address = address;
    }

    public String getStore_ID() {
        return store_ID;
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

    public String getAddress() {
        return address;
    }
}
