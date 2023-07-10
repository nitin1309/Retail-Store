package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductDetails implements Initializable {

    public ObservableList<Product> pro_items;

    @FXML
    private TableView<Product> PRODUCT_TABLE;

    @FXML
    private TextField PRODUCT_RATING;

    @FXML
    private Button PRODUCT_UPDATE;

    @FXML
    private Button PRODUCT_INSERT;

    @FXML
    private TextField PRODUCT_CATEGORY;

    @FXML
    private Button back;

    @FXML
    private Button PRODUCT_DELETE;

    @FXML
    private TextField PRODUCT_ID;

    @FXML
    private TextField PRODUCT_NAME;

    @FXML
    private TextField PRODUCT_COST;

    @FXML
    void handleTable() {
        int x=PRODUCT_TABLE.getSelectionModel().getFocusedIndex();
        Product row=pro_items.get(x);
        PRODUCT_ID.setText(row.id);
        PRODUCT_NAME.setText(row.name);
        PRODUCT_CATEGORY.setText(row.category);
        PRODUCT_COST.setText(String.valueOf(row.cost));
        PRODUCT_RATING.setText(String.valueOf(row.rating));
    }

    @FXML
    private void BackButton(ActionEvent event) throws IOException {
        Main.back();
    }



    @FXML
    private void UpdateButtonProducts(ActionEvent event) throws IOException, SQLException {
        AdminSql.updateProduct(PRODUCT_ID.getText(),PRODUCT_NAME.getText(),PRODUCT_CATEGORY.getText(),Integer.parseInt(PRODUCT_COST.getText()),Float.parseFloat(PRODUCT_RATING.getText()));
        createObsP();
    }

    @FXML
    private void InsertButtonProducts(ActionEvent event) throws IOException, SQLException {
        AdminSql.addProduct(PRODUCT_ID.getText(),PRODUCT_NAME.getText(),PRODUCT_CATEGORY.getText(),Integer.parseInt(PRODUCT_COST.getText()),Float.parseFloat(PRODUCT_RATING.getText()));
        createObsP();
    }


    public void createTableP(){

        TableColumn<Product, String> iCol=new TableColumn<>("ID");
        iCol.setMinWidth(100);
        iCol.setCellValueFactory(new PropertyValueFactory<>("id"));

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

        PRODUCT_TABLE.getColumns().addAll(iCol,streetCol,cityCol,stateCol,pin_codeCol);
        PRODUCT_TABLE.setLayoutX(20);
        PRODUCT_TABLE.setLayoutY(300);
        PRODUCT_TABLE.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public void createObsP() throws SQLException {
        ResultSet rs=AdminSql.showProductTable();
        pro_items = FXCollections.observableArrayList();
        while (rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            String category=rs.getString(3);
            int cost=rs.getInt(4);
            float rating=rs.getFloat(5);
            pro_items.add(new Product(id,name,category,cost,rating));
        }
        PRODUCT_TABLE.setItems(pro_items);
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
