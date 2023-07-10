package sample;

public class Charity {
    String id;
    String name;
    String street;
    String city;
    String state;
    String pincode;
    String account;
    String address;

    public Charity(String id, String name, String street, String city, String state, String pincode, String account, String address) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.account = account;
        this.address = address;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public String getAccount() {
        return account;
    }

    public String getAddress() {
        return address;
    }
}
