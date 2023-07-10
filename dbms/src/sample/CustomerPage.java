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

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerPage implements Initializable {
    public ObservableList<Product> pro_items;

    @FXML
    private Button shopping_cart;

    @FXML
    private TableView<Product> home_page_products;

    @FXML
    private Button search_button;

    @FXML
    private ComboBox<String> category;

    @FXML
    private Button menu;

    @FXML
    private TextField enter_search;

    @FXML
    void handleCategory(ActionEvent event) throws SQLException {
        String str=category.getValue();
        ResultSet rs;
        if(str.equals("All")){
            rs = AdminSql.showProductTable();
        }
        else {
            rs = AdminSql.executeQuery("Select * from Products Where Category='" + str + "'");
        }
        createObsP(rs);
    }

    @FXML
    void ShoppingCartButton(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("ShopingCart.fxml"));
        Main.nextScene(new Scene(root),"Menu");
    }

    @FXML
    void MenuButton(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Main.nextScene(new Scene(root),"Menu");
    }

    @FXML
    void SearchButton(ActionEvent event) throws SQLException {
        String str=enter_search.getText();
        int l=str.length();
        int x=pro_items.size();
        for(int i=x-1;i>-1;i--){
            String pro=pro_items.get(i).name;
            if(pro.length()>=l) {
                if (!(str.equals(pro.substring(0, l)))) {
                    pro_items.remove(i);
                }
            }
            else{
                pro_items.remove(i);
            }
        }
    }

    @FXML
    void BackButton(ActionEvent event) {
        Main.back();
    }

    @FXML
    void openButton(ActionEvent event) throws IOException {
        int x=home_page_products.getSelectionModel().getFocusedIndex();
        Product row=pro_items.get(x);
        Main.product_ID=row.id;
        Parent root= FXMLLoader.load(getClass().getResource("ItemDetails.fxml"));
        Main.nextScene(new Scene(root),"Product Details");
    }

    public void createTableP(){

        TableColumn<Product, String> streetCol=new TableColumn<>("Name");
        streetCol.setMinWidth(100);
        streetCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, String> cityCol=new TableColumn<>("Category");
        cityCol.setMinWidth(100);
        cityCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Product, Integer> stateCol=new TableColumn<>("Cost");
        stateCol.setMinWidth(100);
        stateCol.setCellValueFactory(new PropertyValueFactory<>("cost"));

        TableColumn<Product, Float> pin_codeCol=new TableColumn<>("Rating");
        pin_codeCol.setMinWidth(100);
        pin_codeCol.setCellValueFactory(new PropertyValueFactory<>("rating"));

        home_page_products.getColumns().addAll(streetCol,cityCol,stateCol,pin_codeCol);
        home_page_products.setLayoutX(20);
        home_page_products.setLayoutY(300);
        home_page_products.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public void createObsP(ResultSet rs) throws SQLException {
        pro_items = FXCollections.observableArrayList();
        while (rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            String category=rs.getString(3);
            int cost=rs.getInt(4);
            float rating=rs.getFloat(5);
            pro_items.add(new Product(id,name,category,cost,rating));
        }
        home_page_products.setItems(pro_items);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createTableP();
        try {
            ResultSet rs=AdminSql.showProductTable();
            createObsP(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ObservableList<String> clist=FXCollections.observableArrayList();
        clist.add("All");
        clist.add("Toys");
        clist.add("Books");
        clist.add("Cosmetics");
        clist.add("Electronics");
        clist.add("Grocery");
        category.setItems(clist);
        category.setValue("All");

    }

}
