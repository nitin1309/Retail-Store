package sample;

import java.util.Date;

public class Stocks {
    String customer_ID;
    String store_ID;
    String supplier_ID;
    int quantity;
    Date expire;

    public Stocks(String customer_ID, String store_ID, String supplier_ID, int quantity, Date expire) {
        this.customer_ID = customer_ID;
        this.store_ID = store_ID;
        this.supplier_ID = supplier_ID;
        this.quantity = quantity;
        this.expire = expire;
    }

    public String getCustomer_ID() {
        return customer_ID;
    }

    public String getStore_ID() {
        return store_ID;
    }

    public String getSupplier_ID() {
        return supplier_ID;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getExpire() {
        return expire;
    }
}
