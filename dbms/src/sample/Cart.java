package sample;

public class Cart {
    String id;
    String name;
    int cost;
    int quantity;
    int total;

    public Cart(String id, String name, int cost, int quantity, int total) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotal() {
        return total;
    }
}
