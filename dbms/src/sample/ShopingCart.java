package sample;

import com.mysql.cj.x.protobuf.MysqlxCrud;
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
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShopingCart implements Initializable {

    private Cart row;
    public ObservableList<Cart> items;

    @FXML
    private TableView<Cart> PRODUCT_TABLE;

    @FXML
    private AnchorPane ShoopingCart;

    @FXML
    private Button PlaceOrder;

    @FXML
    private Button Back;

    @FXML
    private Button save;

    @FXML
    private TextField Quantity;

    @FXML
    private Button remove;

    @FXML
    void handleTable() {
        int x=PRODUCT_TABLE.getSelectionModel().getFocusedIndex();
        row=items.get(x);
        Quantity.setText(String.valueOf(row.quantity));
    }

    @FXML
    void PlaceOrderButton(ActionEvent event) throws IOException {
        boolean flag=true;
        int x=items.size();
        for(int i=0;i<x;i++){
            if(!(CustomerSql.checkAvailability(items.get(i).id,items.get(i).quantity))){
                flag=false;
                Main.popup(items.get(i).name+" not available");
            }
        }
        if(flag){
            Parent root= FXMLLoader.load(getClass().getResource("PaymentMode.fxml"));
            Main.nextScene(new Scene(root),"Payment Mode");
        }
    }

    @FXML
    void BackButton(ActionEvent event) {
        Main.back();
    }

    @FXML
    void RemoveButton(ActionEvent event) throws SQLException {
        CustomerSql.deleteFromShoppingCart(Main.customer_ID,row.id);
        createObsP();
    }

    @FXML
    void SaveButton(ActionEvent event) throws SQLException {
        int x=Integer.parseInt(Quantity.getText());
        AdminSql.updateQuery("Update Shopping_Cart Set Quantity= "+x+" Where Customer_ID = '"+Main.customer_ID+"' And Product_ID= '"+row.id+"'");
        createObsP();
    }

    public void createTableP(){

        TableColumn<Cart, String> iCol=new TableColumn<>("Name");
        iCol.setMinWidth(100);
        iCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Cart, Integer> streetCol=new TableColumn<>("Cost");
        streetCol.setMinWidth(100);
        streetCol.setCellValueFactory(new PropertyValueFactory<>("cost"));

        TableColumn<Cart, Integer> cityCol=new TableColumn<>("Quantity");
        cityCol.setMinWidth(100);
        cityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Cart, Integer> stateCol=new TableColumn<>("Total Cost");
        stateCol.setMinWidth(100);
        stateCol.setCellValueFactory(new PropertyValueFactory<>("total"));

        PRODUCT_TABLE.getColumns().addAll(iCol,streetCol,cityCol,stateCol);
        PRODUCT_TABLE.setLayoutX(20);
        PRODUCT_TABLE.setLayoutY(300);
        PRODUCT_TABLE.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public void createObsP() throws SQLException {
        ResultSet rs=CustomerSql.showShoppingCart(Main.customer_ID);
        items = FXCollections.observableArrayList();
        if(rs!=null) {
            while (rs.next()) {
                String id = rs.getString(2);
                int quantity = rs.getInt(3);
                ResultSet rs1 = CustomerSql.getProduct(id);
                rs1.next();
                String name = rs1.getString(2);
                int cost = rs1.getInt(4);
                int total = cost * quantity;
                items.add(new Cart(id,name, cost, quantity, total));
            }
        }
        PRODUCT_TABLE.setItems(items);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createTableP();
        try {
            createObsP();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
