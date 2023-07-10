package sample;

import java.util.Date;

public class Order {
    String customer_ID;
    String order_ID;
    String product_ID;
    int quantity;
    int total_cost;
    Date booking;
    Date delivery;
    String payment_mode;
    String street;
    String city;
    String state;
    String pincode;
    String store_ID;
    float charity_donation;

    public Order(String customer_ID, String order_ID, String product_ID, int quantity, int total_cost, Date booking, Date delivery, String payment_mode, String street, String city, String state, String pincode, String store_ID, float charity_donation) {
        this.customer_ID = customer_ID;
        this.order_ID = order_ID;
        this.product_ID = product_ID;
        this.quantity = quantity;
        this.total_cost = total_cost;
        this.booking = booking;
        this.delivery = delivery;
        this.payment_mode = payment_mode;
        this.street = street;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.store_ID = store_ID;
        this.charity_donation = charity_donation;
    }

    public float getCharity_donation() {
        return charity_donation;
    }

    public String getCustomer_ID() {
        return customer_ID;
    }

    public String getOrder_ID() {
        return order_ID;
    }

    public String getProduct_ID() {
        return product_ID;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotal_cost() {
        return total_cost;
    }

    public Date getBooking() {
        return booking;
    }

    public Date getDelivery() {
        return delivery;
    }

    public String getPayment_mode() {
        return payment_mode;
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

    public String getStore_ID() {
        return store_ID;
    }
}
