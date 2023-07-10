package sample;

public class Product {
    String id;
    String name;
    String category;
    int cost;
    float rating;

    public Product(String id, String name, String category, int cost, float rating) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.cost = cost;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getCost() {
        return cost;
    }

    public float getRating() {
        return rating;
    }
}
