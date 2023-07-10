package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.mysql.cj.log.Log;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main extends Application {
    public static Stage primaryStage;
    public static Connection con;
    public static Statement st;
    public static Stack<Scene> stack;
    public static Stack<String> tstack;
    public static String customer_ID;
    public static String employee_ID;
    public static String product_ID;

    public static void popup(String str){
        double w=300;
        double h=150;
        Label l=new Label(str);
        l.setLayoutX((w/2)-(w*3/8));
        l.setLayoutY((h/6)-(h/10));
        Button b=new Button("Close");
        b.setLayoutX((w/2)-(w/10));
        b.setLayoutY((h/2)-(h/20));
        Group g=new Group();
        g.getChildren().addAll(b,l);
        Scene scene=new Scene(g,w,h, Color.GRAY);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
        b.setOnMouseClicked(e->{
            stage.close();
        });
    }


    public static String[] returnAddr(String str) throws SQLException {
        ResultSet rs1 = st.executeQuery("Select * from Address Where Address_ID = '"+str+"'");
        String[] s=new String[4];
        s[0]=rs1.getString(2);
        s[1]=rs1.getString(3);
        s[2]=rs1.getString(4);
        s[3]=rs1.getString(5);
        return s;
    }

    public static void back(){
        stack.pop();
        tstack.pop();
        primaryStage.setTitle(tstack.peek());
        primaryStage.setScene(stack.peek());
        return;
    }

    public static void nextScene(Scene scene, String s){
        primaryStage.setTitle(s);
        primaryStage.setScene(scene);
        stack.add(scene);
        tstack.add(s);
        return;
    }

    @Override
    public void start(Stage pStage) throws Exception{
        primaryStage=pStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);

        nextScene(scene,"Main Menu");

        primaryStage.show();
    }

    public static void main(String[] args) {
        AdminSql adminSql=new AdminSql();
        stack=new Stack<>();
        tstack=new Stack<>();
        String url = "jdbc:mysql://localhost:3306/Midsem";
        String username = "root";
        String password = "Ashuvst@123";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            con = DriverManager.getConnection(url, username, password);
            st = con.createStatement();
        }catch(SQLException e){
            e.printStackTrace();
        }



        launch(args);
    }

}

//    public static double h;
//    public static double w;

//    public static TableView<Store> str_Table;
//    public static TableView<Employee> emp_Table;
//    public static TableView<Customer> cus_Table;
//    public static TableView<Product> pro_Table;
//    public static TableView<Supplier> sup_Table;
//    public static TableView<Charity> cha_Table;
//
//    public static TextField createTextField(String str, double a, double b){
//        TextField textField=new TextField();
//        textField.setPromptText(str);
////        textField.setLayoutX(x);
////        textField.setLayoutY(y);
//        textField.setPrefSize(a,b);
//        return textField;
//    }
//
//    public static Text createText(String str, double x, double y){
//        Text text=new Text(str);
//        text.setLayoutY(y);
//        text.setLayoutX(x);
//        return text;
//    }
//
//    public static Button createButton(String str, double x, double y, double a, double b){
//        Button button=new Button(str);
//        button.setLayoutX(x);
//        button.setLayoutY(y);
//        button.setPrefSize(a,b);
//        return button;
//    }
//
//
//    public static ComboBox createCBox(String str[] , double x, double y){
//        ComboBox cBox=new ComboBox(FXCollections.observableArrayList(str));
//        cBox.setLayoutX(x);
//        cBox.setLayoutY(y);
//        return cBox;
//    }
//
//    public static ComboBox createCBox(ObservableList str , double x, double y){
//        ComboBox cBox=new ComboBox(FXCollections.observableArrayList(str));
//        cBox.setLayoutX(x);
//        cBox.setLayoutY(y);
//        return cBox;
//    }
//
//    public static Label createLabel(Node node, String s, double x, double y){
//        Label label=new Label(s,node);
//        label.setLayoutX(x);
//        label.setLayoutY(y);
//        label.setContentDisplay(ContentDisplay.BOTTOM);
//        return label;
//    }
//
//    public static void confirmQuit(){
//        double w=300;
//        double h=150;
//        Stage stage=new Stage();
//        Button button1 = createButton("Quit",(w/3)-(w/10),(h/2)-(h/20), w/5,h/20);
//        Button button2 = createButton("Cancel",(2*w/3)-(w/10),(h/2)-(h/20),w/5,h/20);
//        Group root=new Group();
//        root.getChildren().addAll(button1,button2);
//
//        stage.setTitle("Quit");
//        Scene scene=new Scene(root,w,h, Color.GRAY);
//        stage.setScene(scene);
//        stage.show();
//
//        button1.setOnAction(e-> System.exit(0));
//        button2.setOnAction(e-> stage.close());
//    }
//
//    public static void back(Stage primaryStage){
//        stack.pop();
//        tstack.pop();
//        primaryStage.setTitle(tstack.peek());
//        primaryStage.setScene(stack.peek());
//        return;
//    }
//
//    public static void nextScene(Scene scene, String s){
//        primaryStage.setTitle(s);
//        primaryStage.setScene(scene);
//        stack.add(scene);
//        tstack.add(s);
//        return;
//    }
//
//
//    @Override
//    public void start(Stage pStage) throws Exception{
//        primaryStage=pStage;
//        Button admin=createButton("Admin",230,10,80,30);
//        Button employee=createButton("Employee",10,10,80,30);
//        Button customer=createButton("Customer",120,10,80,30);
//
////        zero.setGraphic(view);
//        Group root=new Group();
//        root.getChildren().addAll(admin,employee,customer);
//        Scene scene=new Scene(root, w, h,Color.GRAY);
//
//        nextScene(scene,"Main Menu");
//        primaryStage.show();
//
//        admin.setOnMouseClicked(e->{
//            admin_login();
//        });
//
//        employee.setOnMouseClicked(e->{
//            employee_login();
//        });
//
//        customer.setOnMouseClicked(e->{
//            customer_login();
//        });
//        primaryStage.setOnCloseRequest(e-> {
//            e.consume();
//            confirmQuit();
//        });
//    }
//
//    public static void customer_login(){
//        Button Login =createButton("Login",611,210,100,40);
//        Button signup=createButton("Signup",611,260,100,40);
//        TextField tbatch=createTextField("",120,40);
//        Label lname=createLabel(tbatch,"Employee ID",600,70);
//
//        TextField tbatch2=createTextField("",120,40);
//        Label lname2=createLabel(tbatch2,"Password",600,135);
//        Button back=createButton("Back",0,0,60,30);
//
//        Group root=new Group();
//        root.getChildren().addAll(Login,lname,lname2,back,signup);
//        Scene scene=new Scene(root, w, h,Color.GRAY);
//        nextScene(scene,"Login");
//
//        signup.setOnMouseClicked(e->{
//            c_signup();
//        });
//
//        Login.setOnMouseClicked(e->{
//            mainpage();
//        });
//
//        back.setOnMouseClicked(e -> {
//            back(primaryStage);
//            return;
//        });
//    }
//
//    public static void c_signup(){
//        Button back=createButton("Back",0,0,60,30);
//        Button check=createButton("Check",550,145,60,30);
//        TextField name=createTextField("",200,40);
//        TextField phone=createTextField("",200,40);
//        TextField street=createTextField("",200,40);
//        TextField city=createTextField("",200,40);
//        TextField state=createTextField("",200,40);
//        TextField pin=createTextField("",200,40);
//        TextField password=createTextField("",200,40);
//        TextField username=createTextField("",200,40);
//
//        Label lname=createLabel(name,"Name",300,40);
//        Label lusername=createLabel(username,"Username",300,120);
//        Label lphone=createLabel(phone,"Phone No",300,200);
//        Label lstreet=createLabel(street,"Street",300,280);
//        Label lcity=createLabel(city,"City",800,40);
//        Label lstate=createLabel(state,"State",800,120);
//        Label lpin=createLabel(pin,"Pincode",800,200);
//        Label lpassword=createLabel(password,"Password",800,280);
//
//        Button create=createButton("Create",850,400,100,40);
//
//
//        Group root=new Group();
//        root.getChildren().addAll(lname,lusername,check,lphone,lstreet,lcity,lstate,lpin,lpassword,create,back);
//        Scene scene=new Scene(root, w, h,Color.GRAY);
//        nextScene(scene,"SignUp");
//
//        back.setOnMouseClicked(e -> {
//            back(primaryStage);
//            return;
//        });
//    }
//
//    public static void mainpage(){
//        Button searchbutton =createButton("Search",810,60,100,40);
//
//        Button menubutton =createButton("Menu",920,60,100,40);
//
//        TextField search=createTextField("",500,40);
//        Label lsearch=createLabel(search,"Search Bar",300,40);
//
//
//        Button cart =createButton("Shopping cart",1030,60,100,40);
//
//        Group root=new Group();
//        root.getChildren().addAll(searchbutton,lsearch,menubutton,cart);
//        Scene scene=new Scene(root, w, h,Color.GRAY);
//        nextScene(scene,"Main Page");
//    }
//
//    public static void employee_login(){
//        Button Login =createButton("Login",611,205,100,40);
//        TextField tbatch=createTextField("",120,40);
//        Label lname=createLabel(tbatch,"Employee ID",600,70);
//
//        TextField tbatch2=createTextField("",120,40);
//        Label lname2=createLabel(tbatch2,"Password",600,135);
//        Button back=createButton("Back",0,0,60,30);
//
//        Group root=new Group();
//        root.getChildren().addAll(Login,lname,lname2,back);
//        Scene scene=new Scene(root, w, h,Color.GRAY);
//        nextScene(scene,"Employee Login");
//
//        Login.setOnMouseClicked(e->{
//            employee_info();
//        });
//
//        back.setOnMouseClicked(e -> {
//            back(primaryStage);
//            return;
//        });
//    }
//
//    public static void employee_info(){
//        Button Report =createButton("Orders Report",611,450,100,40);
//
//
//        TextField Qname=createTextField("",200,40);
//        Label lQname=createLabel(Qname,"Name",300,40);
//        Qname.setEditable(false);
//
//        TextField storeID=createTextField("",200,40);
//        Label lstoreID=createLabel(storeID,"StoreID",300,120);
//        storeID.setEditable(false);
//
//        TextField salary=createTextField("",200,40);
//        Label lsalary=createLabel(salary,"Salary",300,280);
//        salary.setEditable(false);
//
//        TextField dob=createTextField("",200,40);
//        Label ldob=createLabel(dob,"DOB",300,200);
//        dob.setEditable(false);
//
//        TextField phonenumber=createTextField("",200,40);
//        Label lphonenumber=createLabel(phonenumber,"Phone number",800,370);
//
//        TextField street=createTextField("",200,40);
//        Label lstreet=createLabel(street,"Street",800,40);
//
//        TextField city=createTextField("",200,40);
//        Label lcity=createLabel(city,"City",800,120);
//
//        TextField state=createTextField("",200,40);
//        Label lstate=createLabel(state,"State",800,200);
//
//        TextField pincode=createTextField("",200,40);
//        Label lpincode=createLabel(pincode,"Pincode",800,280);
//
//        Button back=createButton("Back",0,0,60,30);
//
//        Button savepassword =createButton("Save Changes",611,505,100,40);
//
//        Group root=new Group();
//        root.getChildren().addAll(Report,back,ldob,lstoreID,lphonenumber,lQname,savepassword,lsalary,lstreet,lcity,lstate,lpincode);
//        Scene scene=new Scene(root, w, h,Color.GRAY);
//        nextScene(scene,"Employee Info");
//
//        back.setOnMouseClicked(e -> {
//            back(primaryStage);
//            return;
//        });
//    }
//
//    public static void admin_login(){
//        TextField tpassword=createTextField("",100,30);
//        Label lpassword=createLabel(tpassword,"Admin Password",600,50);
//        Button back=createButton("Back",0,0,60,30);
//        Button login=createButton("Login",610,150,80,30);
//        Group root=new Group();
//        root.getChildren().addAll(lpassword,back,login);
//        Scene scene=new Scene(root, w, h,Color.GRAY);
//
//        nextScene(scene,"Admin Login");
//
//        login.setOnMouseClicked(e->{
//            admin_home();
//        });
//
//        back.setOnMouseClicked(e -> {
//            back(primaryStage);
//            return;
//        });
//    }
//
//    public static void admin_home(){
//        Button employee =createButton("Employee",420,100,100,40);
//        Button customer =createButton("Customer",420,160,100,40);
//        Button charityy =createButton("Charity",420,220,100,40);
//        Button change_password =createButton("Change Password",480,300,150,40);
//        Button reportss =createButton("Reports",580,100,100,40);
//        Button supplier =createButton("Supplier",580,160,100,40);
//        Button storee =createButton("Store",580,220,100,40);
//
//        Group root=new Group();
//        root.getChildren().addAll(employee,customer,change_password,charityy,reportss,supplier,storee);
//        Scene scene=new Scene(root, w, h,Color.GRAY);
//        nextScene(scene,"Admin Page");
//    }
//
//

//
//

//
//
//
//



//
//
//

//
//
//    public static void main(String[] args) {
//
//        Screen screen = Screen.getPrimary();
//        Rectangle2D bounds = screen.getVisualBounds();
//        w=bounds.getWidth();
//        h=bounds.getHeight();
////        w-=10;
//        h-=34;
//
//        h=650;
//        w=1250;
//
//        stack=new Stack<>();
//        tstack=new Stack<>();
//        String url = "jdbc:mysql://localhost:3306/Midsem";
//        String username = "root";
//        String password = "Ashuvst@123";
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        }catch(ClassNotFoundException e){
//            e.printStackTrace();
//        }
//        try{
//            con = DriverManager.getConnection(url, username, password);
//            st = con.createStatement();
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//        launch(args);
//    }
