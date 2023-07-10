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

public class CharityDetails implements Initializable {
    private String addId;

    @FXML
    private TableView<Charity> Charity_Table;

    @FXML
    private TextField CHARITY_STREET;

    @FXML
    private TextField CHARITY_NAME;

    @FXML
    private TextField CHARITY_STATE;

    @FXML
    private Button delete_CHARITY;

    @FXML
    private TextField CHARITY_ACC;

    @FXML
    private Button back;

    @FXML
    private Button update_CHARITY;

    @FXML
    private TextField CHARITY_ID;

    @FXML
    private TextField CHARITY_PIN;

    @FXML
    private TextField CHARITY_CITY;

    @FXML
    private void BackButton(ActionEvent event) throws IOException {
        Main.back();
    }

    public static ObservableList<Charity> cha_items;

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
    private void UpdateCharityButton(ActionEvent event) throws IOException, SQLException {
        AdminSql.updateCharity(CHARITY_ID.getText(),CHARITY_NAME.getText(),CHARITY_STREET.getText(),CHARITY_CITY.getText(), CHARITY_STATE.getText(),CHARITY_PIN.getText(),CHARITY_ACC.getText());
        createObsCh();
    }

    @FXML
    private void DeleteCharityButton(ActionEvent event) throws IOException {

    }

    @FXML
    private void InsertCharityButton(ActionEvent event) throws IOException, SQLException {
        AdminSql.addCharity(CHARITY_ID.getText(),CHARITY_NAME.getText(),CHARITY_STREET.getText(),CHARITY_CITY.getText(), CHARITY_STATE.getText(),CHARITY_PIN.getText(),CHARITY_ACC.getText());
        createObsCh();
    }

    @FXML
    void handleTable() {
        int x=Charity_Table.getSelectionModel().getFocusedIndex();
        Charity row=cha_items.get(x);
        CHARITY_ID.setText(row.id);
        CHARITY_NAME.setText(row.name);
        CHARITY_STREET.setText(row.street);
        CHARITY_CITY.setText(row.city);
        CHARITY_STATE.setText(row.state);
        CHARITY_PIN.setText(row.pincode);
        CHARITY_ACC.setText(row.account);
        addId=row.address;
    }

    public void createObsCh() throws SQLException {
        ResultSet rs=AdminSql.showCharityTable();
        cha_items = FXCollections.observableArrayList();
        while (rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            String address_ID=rs.getString(3);
            String account=rs.getString(4);
            String[] addr=returnAddr(address_ID);
            cha_items.add(new Charity(id,name,addr[0],addr[1],addr[2],addr[3],account,address_ID));
        }
        Charity_Table.setItems(cha_items);
    }

    public void createTableCh(){

        TableColumn<Charity, String> iCol=new TableColumn<>("ID");
        iCol.setMinWidth(100);
        iCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Charity, String> nCol=new TableColumn<>("Name");
        nCol.setMinWidth(100);
        nCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Charity, String> streetCol=new TableColumn<>("Street");
        streetCol.setMinWidth(100);
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));

        TableColumn<Charity, String> cityCol=new TableColumn<>("City");
        cityCol.setMinWidth(100);
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<Charity, String> stateCol=new TableColumn<>("State");
        stateCol.setMinWidth(100);
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));

        TableColumn<Charity, String> pin_codeCol=new TableColumn<>("Pin Code");
        pin_codeCol.setMinWidth(100);
        pin_codeCol.setCellValueFactory(new PropertyValueFactory<>("pincode"));

        TableColumn<Charity, String> account_noCol=new TableColumn<>("Account No");
        account_noCol.setMinWidth(100);
        account_noCol.setCellValueFactory(new PropertyValueFactory<>("account"));


        Charity_Table.getColumns().addAll(iCol,nCol,streetCol,cityCol,stateCol,pin_codeCol,account_noCol);
        Charity_Table.setLayoutX(20);
        Charity_Table.setLayoutY(300);
        Charity_Table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createTableCh();
        try {
            createObsCh();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
