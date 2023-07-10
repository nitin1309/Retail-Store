package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Editprofilecustomer implements Initializable {

    @FXML
    private Label username_na_label;

    @FXML
    private TextField EditProfile__username;

    @FXML
    private Button EditProfileUpdatePassword;

    @FXML
    private Label username_ok_label;

    @FXML
    private Button back;

    @FXML
    private Button check;

    @FXML
    private Button EditProfileSavebutton;

    @FXML
    private TextField EditProfile_name;

    @FXML
    private TextField EditProfile_newpassword;

    @FXML
    private TextField EditProfile_street;

    @FXML
    private TextField EditProfile_phone;

    @FXML
    private TextField EditProfile_city;

    @FXML
    private TextField EditProfile_state;

    @FXML
    private TextField EditProfile_pin;

    @FXML
    private TextField EditProfile_password;

    private String addr;

    @FXML
    void BackButton(ActionEvent event) {
        Main.back();
    }

    @FXML
    void EditProfileSaveButton(ActionEvent event) {
        AddressSql.updateAddress(addr,EditProfile_street.getText(),EditProfile_city.getText(),EditProfile_state.getText(),EditProfile_pin.getText());
        AdminSql.updateCustomer(Main.customer_ID,EditProfile_name.getText(),EditProfile_phone.getText(),addr);
        Main.popup("Details Updated Successfully");
    }

    @FXML
    void EditProfilePasswordUpdateButton(ActionEvent event) throws SQLException {
        String pass=CustomerSql.getPassword(Main.customer_ID);
        String password=EditProfile_password.getText();
        if(pass.equals(password)){
            String npass=EditProfile_newpassword.getText();
            CustomerSql.changePassword(Main.customer_ID,npass);
            Main.popup("Password Successfully Changed");
        }
        else {
            Main.popup("Wrong Password");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet rs=CustomerSql.getCustomerDetails(Main.customer_ID);
        try {
            rs.next();
            EditProfile_phone.setText(rs.getString(4));
            EditProfile__username.setText(rs.getString(1));
            EditProfile_name.setText(rs.getString(2));
            addr=rs.getString(5);
            rs=AddressSql.getAddress(addr);
            rs.next();
            EditProfile_street.setText(rs.getString(2));
            EditProfile_city.setText(rs.getString(3));
            EditProfile_state.setText(rs.getString(4));
            EditProfile_pin.setText(rs.getString(5));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        EditProfile__username.setEditable(false);
    }
}
