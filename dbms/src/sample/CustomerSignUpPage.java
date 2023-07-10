package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CustomerSignUpPage {
    @FXML
    private TextField customer_password;

    @FXML
    private Label username_na_label;

    @FXML
    private TextField customer_state;

    @FXML
    private TextField customer_phone;

    @FXML
    private Label username_ok_label;
    @FXML
    private TextField customer_username;

    @FXML
    private TextField customer_street;

    @FXML
    private TextField customer_name;

    @FXML
    private TextField customer_pin;

    @FXML
    private TextField customer_city;


    @FXML
    private void BackButton(ActionEvent event) throws IOException {
        Main.back();
    }

    @FXML
    private void CustomerUserNameCheckButton(ActionEvent event) throws IOException {
        if(CustomerSql.checkCustomer(customer_username.getText())){
            username_ok_label.setVisible(false);
            username_na_label.setVisible(true);
        }
        else{
            username_ok_label.setVisible(true);
            username_na_label.setVisible(false);
        }

    }
    @FXML
    private void SignUpButton(ActionEvent event) throws IOException {
        if(CustomerSql.checkCustomer(customer_username.getText())){
            username_ok_label.setVisible(false);
            username_na_label.setVisible(true);
        }
        else{
            AdminSql.addCustomer(customer_username.getText(),customer_name.getText(),customer_password.getText(),customer_phone.getText(),customer_street.getText(),customer_city.getText(),customer_state.getText(),customer_pin.getText());
            Main.back();
        }
    }
}
