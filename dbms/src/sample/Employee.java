package sample;


import java.sql.Date;

public class Employee {
    String id;
    String name;
    String password;
    String phone;
    float salary;
    String store_ID;
    String street;
    String city;
    String state;
    String pincode;
    Date dob;
    String address;

    public Employee(String id, String name, String password, String phone, float salary, String store_ID, String street, String city, String state, String pincode, Date dob, String address) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.salary = salary;
        this.store_ID = store_ID;
        this.street = street;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.dob = dob;
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

    public float getSalary() {
        return salary;
    }

    public String getStore_ID() {
        return store_ID;
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

    public Date getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }
}
