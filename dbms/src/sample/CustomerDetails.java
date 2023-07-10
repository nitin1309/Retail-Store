package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerDetails implements Initializable {
    private String addId;

    public ObservableList<Customer> cus_items;

    @FXML
    private TableView<Customer> Customer_Table;
    @FXML
    private TextField Customer_Name;

    @FXML
    private TextField Customer_Phone;

    @FXML
    private Button Customer_Update;

    @FXML
    private Button Customer_Insert;

    @FXML
    private TextField Customer_Street;

    @FXML
    private TextField Customer_Password;

    @FXML
    private TextField Customer_State;

    @FXML
    private Button back;

    @FXML
    private TextField Customer_City;

    @FXML
    private TextField Customer_ID;

    @FXML
    private Button Customer_Delete;

    @FXML
    private TextField Customer_Pincode;

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


    public void createTableC(){

        TableColumn<Customer, String> iCol=new TableColumn<>("ID");
        iCol.setMinWidth(100);
        iCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Customer, String> nCol=new TableColumn<>("Name");
        nCol.setMinWidth(100);
        nCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Customer, String> phCol=new TableColumn<>("Phone No.");
        phCol.setMinWidth(100);
        phCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Customer, String> streetCol=new TableColumn<>("Street");
        streetCol.setMinWidth(100);
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));

        TableColumn<Customer, String> cityCol=new TableColumn<>("City");
        cityCol.setMinWidth(100);
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<Customer, String> stateCol=new TableColumn<>("State");
        stateCol.setMinWidth(100);
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));

        TableColumn<Customer, String> pin_codeCol=new TableColumn<>("Pin Code");
        pin_codeCol.setMinWidth(100);
        pin_codeCol.setCellValueFactory(new PropertyValueFactory<>("pincode"));



        Customer_Table.getColumns().addAll(iCol,nCol,phCol,streetCol,cityCol,stateCol,pin_codeCol);
        Customer_Table.setLayoutX(20);
        Customer_Table.setLayoutY(300);
        Customer_Table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public void createObsC() throws SQLException {
        ResultSet rs=AdminSql.showCustomerTable();
        cus_items = FXCollections.observableArrayList();
        while (rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            String password=rs.getString(3);
            String phone=rs.getString(4);
            String address_ID=rs.getString(5);
            String[] addr=returnAddr(address_ID);
            cus_items.add(new Customer(id,name,password,phone,addr[0],addr[1],addr[2],addr[3],address_ID));
        }
        Customer_Table.setItems(cus_items);
    }

    @FXML
    private void UpdateCustomerButton(ActionEvent event) throws IOException, SQLException {
        AddressSql addressSql=new AddressSql();
        addressSql.updateAddress(addId,Customer_Street.getText(),Customer_City.getText(),Customer_State.getText(),Customer_Pincode.getText());
        AdminSql.updateCustomer(Customer_ID.getText(),Customer_Name.getText(),Customer_Password.getText(),Customer_Phone.getText(),addId);
        createObsC();
    }

    @FXML
    private void DeleteCustomerButton(ActionEvent event) throws IOException {

    }

    @FXML
    private void InsertCustomerButton(ActionEvent event) throws IOException, SQLException {
        AdminSql.addCustomer(Customer_ID.getText(),Customer_Name.getText(),Customer_Password.getText(),Customer_Phone.getText(),Customer_Street.getText(),Customer_City.getText(), Customer_State.getText(),Customer_Pincode.getText());
        createObsC();
    }

    @FXML
    void handleTable() {
        int x=Customer_Table.getSelectionModel().getFocusedIndex();
        Customer row=cus_items.get(x);
        Customer_ID.setText(row.id);
        Customer_Street.setText(row.street);
        Customer_City.setText(row.city);
        Customer_Name.setText(row.name);
        Customer_State.setText(row.state);
        Customer_Pincode.setText(row.pincode);
        Customer_Password.setText(row.password);
        Customer_Phone.setText(row.phone);
        addId=row.address;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createTableC();
        try {
            createObsC();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
