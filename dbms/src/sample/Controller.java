package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private TextField customerid_CL;

    @FXML
    Label AdminWrongPassword;
    @FXML
    private TextField new_password;
    @FXML
    private TextField present_password;


    @FXML
    private TextField password_AL;
    @FXML
    private TextField userid_EL;
    @FXML
    private TextField password_EL;

    @FXML
    private Label pass_label_AL;
    @FXML
    private TextField password_CL;



    @FXML
    private void BackButton(ActionEvent event) throws IOException {
        Main.back();
    }
    @FXML
    private void handleAdminButton(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("AdminLogIn.fxml"));
        Main.nextScene(new Scene(root),"Admin Login");
    }
    @FXML
    private void handleEmployeeButton(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("EmployeeLogin.fxml"));
        Main.nextScene(new Scene(root),"Employee Login");
    }
    @FXML
    private void handleCustomerButton(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("CustomerLogin.fxml"));
        Main.nextScene(new Scene(root),"Customer Login");
    }

    @FXML
    private void AdminLogInButton(ActionEvent event) throws IOException {
        String password=password_AL.getText();
        File file = new File("Admin.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str = br.readLine();
        if(str.equals(password)){
            pass_label_AL.setVisible(false);
            Parent root= FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
            password_AL.setText("");
            Main.nextScene(new Scene(root),"Admin");
        }
        else{
            pass_label_AL.setVisible(true);
            password_AL.setText("");        }
    }

    @FXML
    private void CustomerSignUpButton(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("CustomerSignUpPage.fxml"));
        Main.nextScene(new Scene(root),"Customer SignUp");
    }

    @FXML
    private void CustomerLogInButton(ActionEvent event) throws IOException {
        if(CustomerSql.checkCustomer(customerid_CL.getText(),password_CL.getText())) {
            Parent root = FXMLLoader.load(getClass().getResource("CustomerPage.fxml"));
            Main.nextScene(new Scene(root), "Welcome");
            Main.customer_ID = customerid_CL.getText();
        }
        else
            Main.popup("Wrong Credentials");
    }

    @FXML
    private void EmployeeLogInButton(ActionEvent event) throws IOException {
        if(EmployeeSql.checkEmployee(userid_EL.getText(),password_EL.getText())) {
            Main.employee_ID = userid_EL.getText();
            Parent root = FXMLLoader.load(getClass().getResource("EmployeePage.fxml"));
            Main.nextScene(new Scene(root), "Employee");
        }
        else
        Main.popup("Wrong Credentials");
    }


    @FXML
    private void EmployeeButtonA(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("EmployeeDetails.fxml"));
        Main.nextScene(new Scene(root),"Employee Details");
    }

    @FXML
    private void CustomerButtonA(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("CustomerDetails.fxml"));
        Main.nextScene(new Scene(root),"Customer Details");
    }

    @FXML
    private void CharityButtonA(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("CharityDetails.fxml"));
        Main.nextScene(new Scene(root),"Charity Details");
    }

    @FXML
    private void ChangePasswordButtonA(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("APasswordChange.fxml"));
        Main.nextScene(new Scene(root),"Change Password");
    }

    @FXML
    private void ReportButtonA(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("Report.fxml"));
        Main.nextScene(new Scene(root),"Reports");
    }

    @FXML
    private void StoreButtonA(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("StoreDetails.fxml"));
        Main.nextScene(new Scene(root),"Store Details");
    }

    @FXML
    private void SupplierButtonA(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("SupplierDetails.fxml"));
        Main.nextScene(new Scene(root),"Supplier Details");
    }

    @FXML
    private void StockButtonA(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("StockAvailability.fxml"));
        Main.nextScene(new Scene(root),"Stocks Available");
    }

    @FXML
    private void ProductButtonA(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("ProductDetails.fxml"));
        Main.nextScene(new Scene(root),"Product Details");
    }

    @FXML
    private void UpdateButtonStores(ActionEvent event) throws IOException {
        String password=present_password.getText();
        String npassword=new_password.getText();
        File file = new File("Admin.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str = br.readLine();
        if(str.equals(password)){
            FileWriter myWriter = null;
            try {
                myWriter = new FileWriter("Admin.txt");
                myWriter.write(npassword);
                myWriter.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            AdminWrongPassword.setVisible(false);
            Main.back();
        }
        else
        {
            AdminWrongPassword.setVisible(true);
        }
    }



}
