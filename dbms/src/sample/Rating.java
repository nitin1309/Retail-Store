package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Rating implements Initializable {
    @FXML
    private ComboBox<Integer> Item_rate_cbox;

    @FXML
    void BackButton(ActionEvent event) {
        Main.back();
    }

    @FXML
    void RateButton(){
        CustomerSql.updateReviews(Main.customer_ID,Main.product_ID,Item_rate_cbox.getValue());
        Main.back();
        Main.popup("Thanks for reviewing.");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Integer> items= FXCollections.observableArrayList();
        items.add(1);
        items.add(2);
        items.add(3);
        items.add(4);
        items.add(5);
        Item_rate_cbox.setItems(items);
    }
}
