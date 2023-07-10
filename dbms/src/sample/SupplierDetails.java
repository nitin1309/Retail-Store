package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SupplierDetails implements Initializable {
    private String addId;

    public ObservableList<Supplier> sup_items;

    @FXML
    private TableView<Supplier> PRODUCT_TABLE;

    @FXML
    private TextField SUPPLIER_CITY;

    @FXML
    private Button PRODUCT_UPDATE;

    @FXML
    private Button PRODUCT_INSERT;

    @FXML
    private TextField SUPPLIERS_ID;

    @FXML
    private TextField SUPPLIER_STATE;

    @FXML
    private TextField SUPPLIER_PHONE;

    @FXML
    private Button back;

    @FXML
    private Button PRODUCT_DELETE;

    @FXML
    private TextField SUPPLIER_STREET;

    @FXML
    private TextField SUPPLIER_NAME;

    @FXML
    private TextField SUPPLIER_PIN;

    @FXML
    void handleTable() {
        int x=PRODUCT_TABLE.getSelectionModel().getFocusedIndex();
        Supplier row=sup_items.get(x);
        SUPPLIERS_ID.setText(row.supplier_ID);
        SUPPLIER_NAME.setText(row.name);
        SUPPLIER_PHONE.setText(row.phone);
        SUPPLIER_STREET.setText(row.street);
        SUPPLIER_CITY.setText(row.city);
        SUPPLIER_STATE.setText(row.state);
        SUPPLIER_PIN.setText(row.pincode);
        addId=row.address;
    }

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
    private void UpdateButtonProducts(ActionEvent event) throws IOException, SQLException {
        AddressSql addressSql=new AddressSql();
        addressSql.updateAddress(addId,SUPPLIER_STREET.getText(),SUPPLIER_STATE.getText(), SUPPLIER_CITY.getText(),SUPPLIER_PIN.getText());
        AdminSql.updateSupplier(SUPPLIERS_ID.getText(),SUPPLIER_NAME.getText(),SUPPLIER_PHONE.getText(),addId);
        createObsSP();
    }

    @FXML
    private void DeleteButtonProducts(ActionEvent event) throws IOException {

    }

    @FXML
    private void InsertButtonProducts(ActionEvent event) throws IOException, SQLException {
        AdminSql.addSupplier(SUPPLIERS_ID.getText(),SUPPLIER_NAME.getText(),SUPPLIER_PHONE.getText(),SUPPLIER_STREET.getText(),SUPPLIER_STATE.getText(), SUPPLIER_CITY.getText(),SUPPLIER_PIN.getText());
        createObsSP();
    }

    public void createTableSP(){

        TableColumn<Supplier, String> iCol=new TableColumn<>("ID");
        iCol.setMinWidth(100);
        iCol.setCellValueFactory(new PropertyValueFactory<>("supplier_ID"));

        TableColumn<Supplier, String> nameCol=new TableColumn<>("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Supplier, String> phCol=new TableColumn<>("Phone No.");
        phCol.setMinWidth(100);
        phCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Supplier, String> streetCol=new TableColumn<>("Street");
        streetCol.setMinWidth(100);
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));

        TableColumn<Supplier, String> cityCol=new TableColumn<>("City");
        cityCol.setMinWidth(100);
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<Supplier, String> stateCol=new TableColumn<>("State");
        stateCol.setMinWidth(100);
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));

        TableColumn<Supplier, String> pin_codeCol=new TableColumn<>("Pin Code");
        pin_codeCol.setMinWidth(100);
        pin_codeCol.setCellValueFactory(new PropertyValueFactory<>("pincode"));

        PRODUCT_TABLE.getColumns().addAll(iCol,nameCol,phCol,streetCol,cityCol,stateCol,pin_codeCol);
        PRODUCT_TABLE.setLayoutX(20);
        PRODUCT_TABLE.setLayoutY(300);
        PRODUCT_TABLE.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public void createObsSP() throws SQLException {
        ResultSet rs=AdminSql.showSupplierTable();
        sup_items = FXCollections.observableArrayList();
        while (rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            String phone=rs.getString(3);
            String address_ID=rs.getString(4);
            String[] addr=returnAddr(address_ID);
            sup_items.add(new Supplier(id,name,phone,addr[0],addr[1],addr[2],addr[3],address_ID));
        }
        PRODUCT_TABLE.setItems(sup_items);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createTableSP();
        try {
            createObsSP();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
