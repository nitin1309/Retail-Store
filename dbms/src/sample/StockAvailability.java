package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class StockAvailability implements Initializable {

    public ObservableList<Stocks> items;

    @FXML
    private ComboBox<String> EnterProductID;
    @FXML
    private ComboBox<String> EnterSupplierID;
    @FXML
    private ComboBox<String> EnterStoreID;
    @FXML
    private DatePicker EnterExpiryDate;

    @FXML
    private TextField Quantity;

    @FXML
    private TextField Supplier_ID;

    @FXML
    private TextField Expiry_Date;

    @FXML
    private TextField Product_ID;

    @FXML
    private Button Stock_Delete;

    @FXML
    private TableView<Stocks> Stock_Table;

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
    void BackButton(ActionEvent event) {
        Main.back();
    }

    @FXML
    void AddSupplyButton(ActionEvent event) throws SQLException {
        AdminSql.addProductsSupplied(EnterProductID.getValue(),EnterStoreID.getValue(),EnterSupplierID.getValue(),Integer.parseInt(Quantity.getText()),String.valueOf(EnterExpiryDate.getValue()));
        createObs();
    }

    public void createTableE(){

        TableColumn<Stocks, String> iCol=new TableColumn<>("Product ID");
        iCol.setMinWidth(100);
        iCol.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));

        TableColumn<Stocks, String> nCol=new TableColumn<>("Store ID");
        nCol.setMinWidth(100);
        nCol.setCellValueFactory(new PropertyValueFactory<>("store_ID"));

        TableColumn<Stocks, String> pCol=new TableColumn<>("Supplier ID");
        pCol.setMinWidth(100);
        pCol.setCellValueFactory(new PropertyValueFactory<>("supplier_ID"));

        TableColumn<Stocks, Integer> phCol=new TableColumn<>("Quantity");
        phCol.setMinWidth(100);
        phCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Stocks, Date> sCol=new TableColumn<>("Expire Date");
        sCol.setMinWidth(100);
        sCol.setCellValueFactory(new PropertyValueFactory<>("expire"));



        Stock_Table.getColumns().addAll(iCol,nCol,pCol,phCol,sCol);
        Stock_Table.setLayoutX(20);
        Stock_Table.setLayoutY(300);
        Stock_Table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public void createObs() throws SQLException {
        ResultSet rs=AdminSql.showProductsSuppliedTable();
        items = FXCollections.observableArrayList();
        while (rs.next()){
            String customer_ID=rs.getString(1);
            String store_ID=rs.getString(2);
            String supplier_ID=rs.getString(3);
            int quantity=rs.getInt(4);
            Date expire=rs.getDate(5);
            items.add(new Stocks(customer_ID,store_ID,supplier_ID,quantity,expire));
        }
        Stock_Table.setItems(items);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createTableE();
        try {
            createObs();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ResultSet rs=AdminSql.executeQuery("Select Product_ID from Products");
        ObservableList<String> plist=FXCollections.observableArrayList();

        while (true){
            try {
                if (!rs.next()) break;
                plist.add(rs.getString(1));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        EnterProductID.setItems(plist);

        rs=AdminSql.executeQuery("Select Supplier_ID from Supplier");
        ObservableList<String> splist=FXCollections.observableArrayList();

        while (true){
            try {
                if (!rs.next()) break;
                splist.add(rs.getString(1));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        EnterSupplierID.setItems(splist);

        rs=AdminSql.executeQuery("Select Store_ID from Store");
        ObservableList<String> stlist=FXCollections.observableArrayList();

        while (true){
            try {
                if (!rs.next()) break;
                stlist.add(rs.getString(1));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        EnterStoreID.setItems(stlist);

    }
}
