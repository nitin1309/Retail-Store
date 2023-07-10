package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class PreviousOrders implements Initializable {

    @FXML
    private Button ReviewOrder;

    @FXML
    private TableView<Order> prevordertable;

    @FXML
    private Button back;
    public ObservableList<Order> items;

    @FXML
    void BackButton(ActionEvent event) {
        Main.back();
    }

    @FXML
    void ReviewOrderButton(ActionEvent event) throws IOException {
        int x=prevordertable.getSelectionModel().getFocusedIndex();
        Order row=items.get(x);
        Main.product_ID=row.product_ID;
        Parent root= FXMLLoader.load(getClass().getResource("Rating.fxml"));
        Main.nextScene(new Scene(root),"Rating");
    }

    public void createTableC(){

        TableColumn<Order, String> iCol=new TableColumn<>("Customer_ID");
        iCol.setMinWidth(100);
        iCol.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));

        TableColumn<Order, String> nCol=new TableColumn<>("Order_ID");
        nCol.setMinWidth(100);
        nCol.setCellValueFactory(new PropertyValueFactory<>("order_ID"));

        TableColumn<Order, String> pCol=new TableColumn<>("Product_ID");
        pCol.setMinWidth(100);
        pCol.setCellValueFactory(new PropertyValueFactory<>("product_ID"));

        TableColumn<Order, Integer> qCol=new TableColumn<>("Quantity");
        qCol.setMinWidth(100);
        qCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Order, Integer> tCol=new TableColumn<>("total Cost");
        tCol.setMinWidth(100);
        tCol.setCellValueFactory(new PropertyValueFactory<>("total_cost"));

        TableColumn<Order, Date> phCol=new TableColumn<>("Booking Date");
        phCol.setMinWidth(100);
        phCol.setCellValueFactory(new PropertyValueFactory<>("booking"));

        TableColumn<Order, Date> ddCol=new TableColumn<>("Delivery Date");
        ddCol.setMinWidth(100);
        ddCol.setCellValueFactory(new PropertyValueFactory<>("delivery"));

        TableColumn<Order, String> pmCol=new TableColumn<>("Payment Mode");
        pmCol.setMinWidth(100);
        pmCol.setCellValueFactory(new PropertyValueFactory<>("payment_mode"));

        TableColumn<Order, String> streetCol=new TableColumn<>("Street");
        streetCol.setMinWidth(100);
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));

        TableColumn<Order, String> cityCol=new TableColumn<>("City");
        cityCol.setMinWidth(100);
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<Order, String> stateCol=new TableColumn<>("State");
        stateCol.setMinWidth(100);
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));

        TableColumn<Order, String> pin_codeCol=new TableColumn<>("Pin Code");
        pin_codeCol.setMinWidth(100);
        pin_codeCol.setCellValueFactory(new PropertyValueFactory<>("pincode"));

        TableColumn<Order, String> siCol=new TableColumn<>("Store ID");
        siCol.setMinWidth(100);
        siCol.setCellValueFactory(new PropertyValueFactory<>("store_ID"));



        prevordertable.getColumns().addAll(iCol,nCol,pCol,qCol,tCol,phCol,ddCol,pmCol,streetCol,cityCol,stateCol,pin_codeCol,siCol);
        prevordertable.setLayoutX(20);
        prevordertable.setLayoutY(300);
        prevordertable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public void createObsC(ResultSet rs) throws SQLException {
        items = FXCollections.observableArrayList();
        AddressSql address=new AddressSql();
        while (rs.next()){
            String customer_ID=rs.getString(1);
            String order_ID=rs.getString(2);
            String product_ID=rs.getString(3);
            int quantity=rs.getInt(4);
            int total_cost=rs.getInt(5);
            Date booking=rs.getDate(6);
            Date delivery=rs.getDate(7);
            String payment_mode=rs.getString(8);
            String store_ID=rs.getString(9);
            ResultSet resultSet=AdminSql.executeQuery("Select Address_ID from Customer Where Customer_ID='"+customer_ID+"'");
            resultSet.next();
            String address_ID=resultSet.getString(1);
            ResultSet addr=address.getAddress(address_ID);
            addr.next();
            items.add(new Order(customer_ID,order_ID,product_ID,quantity,total_cost,booking,delivery,payment_mode,addr.getString(1),addr.getString(2),addr.getString(3),addr.getString(4),store_ID,0));
        }
        prevordertable.setItems(items);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createTableC();
        ResultSet rs=CustomerSql.getCustomerOrders(Main.customer_ID);
        try {
            createObsC(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
