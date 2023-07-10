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
import java.util.Date;
import java.util.ResourceBundle;

public class Report implements Initializable {

    public ObservableList<Order> items;

    @FXML
    private TextField enter_order_id;

    @FXML
    private CheckBox order_id_check;
    @FXML
    private CheckBox customer_id_check;
    @FXML
    private TextField product_name_field;

    @FXML
    private TextField enter_customer_id;

    @FXML
    private DatePicker select_to_date;

    @FXML
    private DatePicker select_from_date;

    @FXML
    private TableView<Order> Stock_Table;

    @FXML
    private ComboBox<String> select_store_id;

    @FXML
    private ComboBox<String> select_product_id;

    @FXML
    private void BackButton(ActionEvent event) throws IOException {
        Main.back();
    }

    @FXML
    void FindOrdersButton(ActionEvent event) throws SQLException {
        String[] str=new String[4];
        str[0]="Order_ID='"+enter_order_id+"'";
        str[1]="Customer_ID='"+enter_customer_id.getText()+"'";
        str[2]="Product_ID='"+select_product_id.getValue()+"'";
        str[3]="Store_ID='"+select_store_id.getValue()+"'";
        Boolean[] bool=new Boolean[4];
        bool[0]=order_id_check.isSelected();
        bool[1]=customer_id_check.isSelected();
        String from=String.valueOf(select_from_date.getValue());
        String to=String.valueOf(select_to_date.getValue());
        if(select_product_id.getValue()=="All")
            bool[2]=false;
        else
            bool[2]=true;
        if(select_store_id.getValue()=="All")
            bool[3]=false;
        else
            bool[3]=true;
        String query="Select * from Orders Where Booking_date between '"+from+"' and '"+to+"'";
        for(int i=0;i<4;i++){
            if(bool[i])
                query=query+" And "+str[i];
        }
        ResultSet rs=AdminSql.executeQuery(query);
        if(rs!=null) {
            createObsC(rs);
        }
        else{
            items = FXCollections.observableArrayList();
            Stock_Table.setItems(items);
        }
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

        TableColumn<Order, String> cdCol=new TableColumn<>("Charity Donation");
        cdCol.setMinWidth(100);
        cdCol.setCellValueFactory(new PropertyValueFactory<>("charity_donation"));



        Stock_Table.getColumns().addAll(iCol,nCol,pCol,qCol,tCol,phCol,ddCol,pmCol,streetCol,cityCol,stateCol,pin_codeCol,siCol,cdCol);
        Stock_Table.setLayoutX(20);
        Stock_Table.setLayoutY(300);
        Stock_Table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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
            float donation=rs.getFloat(10);
            ResultSet resultSet=AdminSql.executeQuery("Select Address_ID from Customer Where Customer_ID='"+customer_ID+"'");
            resultSet.next();
            String address_ID=resultSet.getString(1);
            ResultSet addr=address.getAddress(address_ID);
            addr.next();
            items.add(new Order(customer_ID,order_ID,product_ID,quantity,total_cost,booking,delivery,payment_mode,addr.getString(1),addr.getString(2),addr.getString(3),addr.getString(4),store_ID,donation));
        }
        Stock_Table.setItems(items);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createTableC();
        ResultSet rs=AdminSql.executeQuery("Select Store_ID from Store");
        ObservableList<String> slist=FXCollections.observableArrayList();
        slist.add("All");
        while (true){
            try {
                if (!rs.next()) break;
                slist.add(rs.getString(1));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        select_store_id.setItems(slist);
        rs=AdminSql.executeQuery("Select Product_ID from Products");
        ObservableList<String> plist=FXCollections.observableArrayList();
        plist.add("All");
        while (true){
            try {
                if (!rs.next()) break;
                plist.add(rs.getString(1));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        select_product_id.setItems(plist);
        select_product_id.setValue(plist.get(0));
        select_store_id.setValue(slist.get(0));
    }


    public void SelectProductIdButton(ActionEvent actionEvent) {
        String code=select_product_id.getValue();
        if(code!=null && !code.equals("All")){
            ResultSet rs = null;
            try {
                rs = AdminSql.executeQuery("Select Product_name from Products where Product_ID='" + code + "'");

                rs.next();
                product_name_field.setText(rs.getString(1));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(code.equals("All")){
            product_name_field.setText("");
        }
    }
}
