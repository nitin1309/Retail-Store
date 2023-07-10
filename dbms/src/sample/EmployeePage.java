package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeePage implements Initializable {

    @FXML
    private TextField employee_state;

    @FXML
    private TextField employee_store_id;

    @FXML
    private Label username_na_label;

    @FXML
    private TextField employee_pin;

    @FXML
    private TextField employee_dob;

    @FXML
    private Label username_ok_label;

    @FXML
    private Button back;

    @FXML
    private TextField employee_name;

    @FXML
    private TextField employee_phone_number;

    @FXML
    private TextField employee_salary;

    @FXML
    private TextField employee_city;

    @FXML
    private Button employee_update;

    @FXML
    private Button employee_see_order_reports;

    @FXML
    private TextField employee_street;

    @FXML
    private TextField EmployeeCurrentPassword;
    @FXML
    private TextField EmployeeNewPassword;

    private String addressID;

    @FXML
    void BackButton(ActionEvent event) {
        Main.back();
    }

    @FXML
    void EmployeeUpdateDetailButton(ActionEvent event) {
        AddressSql.updateAddress(addressID,employee_street.getText(),employee_city.getText(),employee_state.getText(),employee_pin.getText());
        AdminSql.updateEmployee(Main.employee_ID,employee_name.getText(),employee_phone_number.getText(),Float.parseFloat(employee_salary.getText()),employee_store_id.getText(),addressID,employee_dob.getText());
        Main.popup("Details Updated Successfully");
    }

    @FXML
    void EmployeeUpdatePasswordButton(ActionEvent event) throws SQLException {
        String pass=EmployeeSql.getPassword(Main.employee_ID);
        String password=EmployeeCurrentPassword.getText();
        if(pass.equals(password)){
            String npass=EmployeeNewPassword.getText();
            EmployeeSql.changePassword(Main.employee_ID,npass);
            Main.popup("Password Successfully Changed");
        }
        else {
            Main.popup("Wrong Password");
        }
    }


    @FXML
    void ReportButtonA(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("Report.fxml"));
        Main.nextScene(new Scene(root),"Reports");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet rs=EmployeeSql.getEmployeeDetails(Main.employee_ID);
        try {
            rs.next();
            addressID=rs.getString(7);
            AddressSql addressSql=new AddressSql();
            ResultSet rs1=addressSql.getAddress(addressID);
            rs1.next();
            employee_name.setText(rs.getString(2));
            employee_phone_number.setText(rs.getString(4));
            employee_store_id.setText(rs.getString(6));
            employee_dob.setText(rs.getString(8));
            employee_salary.setText(rs.getString(5));
            employee_street.setText(rs1.getString(1));
            employee_city.setText(rs1.getString(2));
            employee_state.setText(rs1.getString(3));
            employee_pin.setText(rs1.getString(4));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
