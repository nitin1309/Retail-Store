package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class EmployeeDetails implements Initializable {
    private String addId;

    public ObservableList<Employee> emp_items;

    @FXML
    private TextField Employee_ID;
    @FXML
    private TextField Employee_Street;
    @FXML
    private TextField Employee_City;
    @FXML
    private TextField Employee_Name;
    @FXML
    private TextField Employee_State;
    @FXML
    private TextField Employee_Pincode;
    @FXML
    private TextField Employee_Password;
    @FXML
    private TextField Employee_Phone;
    @FXML
    private TextField Employee_Salary;
    @FXML
    private ComboBox<String> Employee_StoreID;
    @FXML
    private DatePicker Employee_DOB;


    @FXML
    private TableView<Employee> Employee_Table;

    @FXML
    private void BackButton(ActionEvent event) throws IOException {
        Main.back();
    }

    public String[] returnAddr(String str) throws SQLException {
        AddressSql addressSql=new AddressSql();
        ResultSet rs1 = addressSql.getAddress(str);
        rs1.next();
        String[] s=new String[4];
        s[0]=rs1.getString(2);
        s[1]=rs1.getString(3);
        s[2]=rs1.getString(4);
        s[3]=rs1.getString(5);
        return s;
    }

    @FXML
    private void UpdateEmployeeButton(ActionEvent event) throws IOException, SQLException {
        AddressSql addressSql=new AddressSql();
        addressSql.updateAddress(addId,Employee_Street.getText(),Employee_City.getText(),Employee_State.getText(),Employee_Pincode.getText());
        AdminSql.updateEmployee(Employee_ID.getText(),Employee_Name.getText(),Employee_Password.getText(),Employee_Phone.getText(),Float.parseFloat(Employee_Salary.getText()),Employee_StoreID.getValue(),addId, String.valueOf(Employee_DOB.getValue()));
        createObsE();
    }

    @FXML
    private void DeleteEmployeeButton(ActionEvent event) throws IOException {

    }

    @FXML
    private void InsertEmployeeButton(ActionEvent event) throws IOException, SQLException {
        AddressSql addressSql=new AddressSql();
        String addr=addressSql.createNewAddress(Employee_Street.getText(),Employee_City.getText(),Employee_State.getText(),Employee_Pincode.getText());
        AdminSql.addEmployee(Employee_ID.getText(),Employee_Name.getText(),Employee_Password.getText(),Employee_Phone.getText(),Float.parseFloat(Employee_Salary.getText()),Employee_StoreID.getValue(),addr,String.valueOf(Employee_DOB.getValue()));
        createObsE();
    }

    @FXML
    private void handleTable() throws IOException {
        int x=Employee_Table.getSelectionModel().getFocusedIndex();
        Employee row=emp_items.get(x);
        Employee_ID.setText(row.id);
        Employee_Street.setText(row.street);
        Employee_City.setText(row.city);
        Employee_Name.setText(row.name);
        Employee_State.setText(row.state);
        Employee_Pincode.setText(row.pincode);
        Employee_Password.setText(row.password);
        Employee_Phone.setText(row.phone);
        Employee_Salary.setText(String.valueOf(row.salary));
        Employee_StoreID.setValue(row.store_ID);
        addId=row.address;
    }

    public void createTableE(){

        TableColumn<Employee, String> iCol=new TableColumn<>("ID");
        iCol.setMinWidth(100);
        iCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Employee, String> nCol=new TableColumn<>("Name");
        nCol.setMinWidth(100);
        nCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Employee, String> pCol=new TableColumn<>("Password");
        pCol.setMinWidth(100);
        pCol.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<Employee, String> phCol=new TableColumn<>("Phone No.");
        phCol.setMinWidth(100);
        phCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Employee, Float> sCol=new TableColumn<>("Salary");
        sCol.setMinWidth(100);
        sCol.setCellValueFactory(new PropertyValueFactory<>("salary"));

        TableColumn<Employee, String> store_idCol=new TableColumn<>("Store ID");
        store_idCol.setMinWidth(100);
        store_idCol.setCellValueFactory(new PropertyValueFactory<>("store_ID"));

        TableColumn<Employee, String> streetCol=new TableColumn<>("Street");
        streetCol.setMinWidth(100);
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));

        TableColumn<Employee, String> cityCol=new TableColumn<>("City");
        cityCol.setMinWidth(100);
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<Employee, String> stateCol=new TableColumn<>("State");
        stateCol.setMinWidth(100);
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));

        TableColumn<Employee, String> pin_codeCol=new TableColumn<>("Pin Code");
        pin_codeCol.setMinWidth(100);
        pin_codeCol.setCellValueFactory(new PropertyValueFactory<>("pincode"));

        TableColumn<Employee, String> dob_Col=new TableColumn<>("D.O.B.");
        dob_Col.setMinWidth(100);
        dob_Col.setCellValueFactory(new PropertyValueFactory<>("dob"));



        Employee_Table.getColumns().addAll(iCol,nCol,pCol,phCol,sCol,store_idCol,streetCol,cityCol,stateCol,pin_codeCol,dob_Col);
        Employee_Table.setLayoutX(20);
        Employee_Table.setLayoutY(300);
        Employee_Table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public void createObsE() throws SQLException {
        ResultSet rs=AdminSql.showEmployeeTable();
        emp_items = FXCollections.observableArrayList();
        while (rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            String password=rs.getString(3);
            String phone=rs.getString(4);
            float salary=rs.getFloat(5);
            String store_ID=rs.getString(6);
            String address_ID=rs.getString(7);
            String[] addr=returnAddr(address_ID);
            Date dob=rs.getDate(8);
            emp_items.add(new Employee(id,name,password,phone,salary,store_ID,addr[0],addr[1],addr[2],addr[3], (java.sql.Date) dob,address_ID));
        }
        Employee_Table.setItems(emp_items);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createTableE();
        try {
            createObsE();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ResultSet rs=AdminSql.executeQuery("Select Store_ID from Store");
        ObservableList<String> stlist=FXCollections.observableArrayList();

        while (true){
            try {
                if (!rs.next()) break;
                stlist.add(rs.getString(1));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        Employee_StoreID.setItems(stlist);
    }

}
