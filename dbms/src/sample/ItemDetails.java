package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemDetails implements Initializable {

    @FXML
    private Label Item_category;

    @FXML
    private Label Item_rating;

    @FXML
    private Label Item_cost;

    @FXML
    private Label Item_Name;

    @FXML
    private TextField Item_quantity;

    @FXML
    void BackButton(ActionEvent event) {
        Main.back();
    }

    @FXML
    void AddToCartButton(ActionEvent event) {
        CustomerSql.addToShoppingCart(Main.customer_ID,Main.product_ID,Integer.parseInt(Item_quantity.getText()));
        Main.back();
        Main.popup("Item added to cart");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet rs=CustomerSql.getProduct(Main.product_ID);
        try {
            rs.next();
            Item_Name.setText(rs.getString(2));
            Item_cost.setText(String.valueOf(rs.getInt(4)));
            Item_category.setText(rs.getString(3));
            Item_rating.setText(String.valueOf(rs.getFloat(5)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
