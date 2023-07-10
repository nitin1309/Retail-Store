package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StoreDetails implements Initializable {
    private String addId;
    public static ObservableList<Store> str_items;
    @FXML
    private TableView<Store> table_STORE;
    @FXML
    private TextField E_storeid_STORES;
    @FXML
    private TextField E_phone_STORES;
    @FXML
    private TextField E_street_STORES;
    @FXML
    private TextField E_city_STORES;
    @FXML
    private TextField E_state_STORES;
    @FXML
    private TextField E_pin_STORES;


    @FXML
    private void BackButton(ActionEvent event) throws IOException {
        Main.back();
    }
    @FXML
    private void handleStoreTable(ActionEvent event) throws IOException {

    }

    @FXML
    private void UpdateButtonStores(ActionEvent event) throws IOException, SQLException {
        AddressSql addressSql=new AddressSql();
        addressSql.updateAddress(addId,E_street_STORES.getText(),E_city_STORES.getText(),E_state_STORES.getText(),E_pin_STORES.getText());
        AdminSql.updateStore(E_storeid_STORES.getText(),E_phone_STORES.getText(),addId);
        createObsS();
    }

    @FXML
    private void DeleteButtonStores(ActionEvent event) throws IOException {

    }

    @FXML
    private void handleTable() throws IOException {
        int x=table_STORE.getSelectionModel().getFocusedIndex();
        Store row= str_items.get(x);
        E_storeid_STORES.setText(row.store_ID);
        E_phone_STORES.setText(row.phone);
        E_street_STORES.setText(row.street);
        E_city_STORES.setText(row.city);
        E_state_STORES.setText(row.state);
        E_pin_STORES.setText(row.pincode);
        addId=row.address;
    }

    @FXML
    private void InsertButtonStores(ActionEvent event) throws IOException, SQLException {
        AdminSql.addStore(E_storeid_STORES.getText(),E_phone_STORES.getText(),E_street_STORES.getText(),E_city_STORES.getText(),E_state_STORES.getText(),E_pin_STORES.getText());
        createObsS();
    }

    public void createTableS(){

        TableColumn<Store, String> iCol=new TableColumn<>("ID");
        iCol.setMinWidth(100);
        iCol.setCellValueFactory(new PropertyValueFactory<>("store_ID"));

        TableColumn<Store, String> phCol=new TableColumn<>("Phone No.");
        phCol.setMinWidth(100);
        phCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Store, String> streetCol=new TableColumn<>("Street");
        streetCol.setMinWidth(100);
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));

        TableColumn<Store, String> cityCol=new TableColumn<>("City");
        cityCol.setMinWidth(100);
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<Store, String> stateCol=new TableColumn<>("State");
        stateCol.setMinWidth(100);
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));

        TableColumn<Store, String> pin_codeCol=new TableColumn<>("Pin Code");
        pin_codeCol.setMinWidth(100);
        pin_codeCol.setCellValueFactory(new PropertyValueFactory<>("pincode"));

        table_STORE.getColumns().addAll(iCol,phCol,streetCol,cityCol,stateCol,pin_codeCol);
        table_STORE.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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

    public void createObsS() throws SQLException {
        ResultSet rs=AdminSql.showStoreTable();
        str_items = FXCollections.observableArrayList();
        while (rs.next()){
            String id=rs.getString(1);
            String phone=rs.getString(2);
            String address_ID=rs.getString(3);
            String[] addr=returnAddr(address_ID);
            str_items.add(new Store(id,phone,addr[0],addr[1],addr[2],addr[3],address_ID));
        }
        table_STORE.setItems(str_items);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createTableS();
        try {
            createObsS();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
