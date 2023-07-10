package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class PaymentMode {

    @FXML
    private Button Back;

    @FXML
    private Button Cash;

    @FXML
    private AnchorPane PaymentMode;

    @FXML
    private Button Card;

    @FXML
    void ButtonCash(ActionEvent event) {
        CustomerSql.updateOrders(Main.customer_ID,"Cash");
        Main.back();
        Main.back();
        Main.popup("Order Placed");
    }

    @FXML
    void ButtonCard(ActionEvent event) {
        CustomerSql.updateOrders(Main.customer_ID,"Card");
        Main.back();
        Main.back();
        Main.popup("Order Placed");
    }

    @FXML
    void BackButton(ActionEvent event) {
        Main.back();
    }

}
