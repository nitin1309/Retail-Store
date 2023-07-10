package sample;

public class Customer {
    String id;
    String name;
    String password;
    String phone;
    String street;
    String city;
    String state;
    String pincode;
    String address;

    public Customer(String id, String name, String password, String phone, String street, String city, String state, String pincode, String address) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
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
