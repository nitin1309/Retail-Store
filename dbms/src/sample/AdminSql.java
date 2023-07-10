package sample;

import sample.AddressSql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminSql {

    static String url = "jdbc:mysql://localhost:3306/retail_store";
    static String username = "root";
    static String password = "Ashuvst@123";

    static Connection con;

    public AdminSql(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            con = DriverManager.getConnection(url, username, password);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String query){
        try{
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);
            if(!result.isBeforeFirst()){
                return null;
            }
            return result;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void updateQuery(String query){
        try{
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void addEmployee(String Employee_ID, String Employee_name, String Employee_password, String phone, float salary, String store_ID, String address_ID, String DOB){
        Employee_name = "'" + Employee_name + "'";
        Employee_password = "'" + Employee_password + "'";
        DOB = "'" + DOB + "'";
        Employee_ID = "'" + Employee_ID + "'";
        phone = "'" + phone + "'";
        store_ID = "'" + store_ID + "'";
        address_ID = "'" + address_ID + "'";
        String query = "INSERT INTO Employees VALUES (" + Employee_ID + ", " + Employee_name + ", " + Employee_password + ", " + phone + ", " + salary + ", " + store_ID + ", " + address_ID + ", " + DOB + ")";
        System.out.println(query);
        updateQuery(query);
    }

    public static void updateEmployee(String Employee_ID, String Employee_name, String phone, float salary, String store_ID, String address_ID, String DOB){
        Employee_ID = "'" + Employee_ID + "'";
        Employee_name = "'" + Employee_name + "'";
        DOB = "'" + DOB + "'";
        phone = "'" + phone + "'";
        store_ID = "'" + store_ID + "'";
        address_ID = "'" + address_ID + "'";
        String query = "UPDATE Employees SET Employee_name = " + Employee_name + ", Employee_phone = " + phone + ", Employee_salary = " + salary + ", Store_ID = " + store_ID + ", Address_ID = " + address_ID + ", DOB = " + DOB + " WHERE Employee_ID = " + Employee_ID;
        updateQuery(query);
    }

    public static void updateEmployee(String Employee_ID, String Employee_name, String Employee_password, String phone, float salary, String store_ID, String address_ID, String DOB){
        Employee_ID = "'" + Employee_ID + "'";
        Employee_name = "'" + Employee_name + "'";
        Employee_password = "'" + Employee_password + "'";
        DOB = "'" + DOB + "'";
        phone = "'" + phone + "'";
        store_ID = "'" + store_ID + "'";
        address_ID = "'" + address_ID + "'";
        String query = "UPDATE Employees SET Employee_name = " + Employee_name + ", Employee_password = " + Employee_password + ", Employee_phone = " + phone + ", Employee_salary = " + salary + ", Store_ID = " + store_ID + ", Address_ID = " + address_ID + ", DOB = " + DOB + " WHERE Employee_ID = " + Employee_ID;
        updateQuery(query);
    }

    public static ResultSet showEmployeeTable(){
        // Function for accessing employee details.
        String query = "SELECT * FROM Employees";
        ResultSet result = executeQuery(query);
        return result;
    }

    public static void deleteEmployee(String Employee_ID){
        Employee_ID = "'" + Employee_ID + "'";
        String query = "DELETE FROM Employees WHERE Employee_ID = " + Employee_ID;
        String query_address = "SELECT Address_ID FROM Employees WHERE Employee_ID = " + Employee_ID + ";";
        ResultSet result = executeQuery(query_address);
        String Address_ID = "";
        try{
            while(result.next()){
                Address_ID = result.getString(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        updateQuery(query);
        AddressSql add = new AddressSql();
        add.deleteAddress(Address_ID);
    }

    public static void addCustomer(String Customer_ID, String name, String password, String phone, String street, String city, String state, String pincode){
        Customer_ID = "'" + Customer_ID + "'";
        name = "'" + name + "'";
        password = "'" + password + "'";
        phone = "'" + phone + "'";

        AddressSql add = new AddressSql();
        String Address_ID = add.createNewAddress(street, city, state, pincode);
        Address_ID = "'" + Address_ID + "'";
        String query = "INSERT INTO Customer VALUES (" + Customer_ID + ", " + name + ", " + password + ", " + phone + ", " + Address_ID + ")";
        updateQuery(query);
    }

    public static void updateCustomer(String Customer_ID, String name, String phone, String Address_ID){
        Customer_ID = "'" + Customer_ID + "'";
        name = "'" + name + "'";
        phone = "'" + phone + "'";
        Address_ID = "'" + Address_ID + "'";
        String query = "UPDATE Customer SET Customer_name = " + name + ", Phone_number = " + phone + ", Address_ID = " + Address_ID + " WHERE Customer_ID = " + Customer_ID;
        updateQuery(query);
    }
    public static void updateCustomer(String Customer_ID, String name, String password, String phone, String Address_ID){
        Customer_ID = "'" + Customer_ID + "'";
        name = "'" + name + "'";
        password = "'" + password + "'";
        phone = "'" + phone + "'";
        Address_ID = "'" + Address_ID + "'";
        String query = "UPDATE Customer SET Customer_name = " + name + ", Customer_password = " + password + ", Phone_number = " + phone + ", Address_ID = " + Address_ID + " WHERE Customer_ID = " + Customer_ID;
        updateQuery(query);
    }

    // public static void deleteCustomer(String Customer_ID){
    //     String query = "DELETE FROM Customer WHERE Customer_ID = " + Customer_ID + ";";
    //     String query_address = "SELECT Address_ID FROM Customer WHERE Customer_ID = " + Customer_ID + ";";
    //     ResultSet result = executeQuery(query_address);
    //     String Address_ID = "";
    //     try{
    //         while(result.next()){
    //             Address_ID = result.getString(1);
    //         }
    //     }catch(SQLException e){
    //         e.printStackTrace();
    //     }
    //     updateQuery(query);
    //     Address add = new Address();
    //     add.deleteAddress(Address_ID);
    // }

    public static ResultSet showCustomerTable(){
        // Function for accessing customer details.
        String query = "SELECT * FROM Customer";
        ResultSet result = executeQuery(query);
        return result;
    }

    public static void addCharity(String Charity_ID, String name, String street, String city, String state, String pincode, String account_no){
        Charity_ID = "'" + Charity_ID + "'";
        name = "'" + name + "'";
        AddressSql add = new AddressSql();
        String Address_ID = add.createNewAddress(street, city, state, pincode);
        Address_ID = "'" + Address_ID + "'";
        account_no = "'" + account_no + "'";
        String query = "INSERT INTO Charity VALUES (" + Charity_ID + ", " + name + ", " + Address_ID + ", " + account_no + ")";
        updateQuery(query);
    }

    public static void updateCharity(String Charity_ID, String name, String street, String city, String state, String pincode, String account_no){
        name = "'" + name + "'";
        Charity_ID = "'" + Charity_ID + "'";
        AddressSql add = new AddressSql();
        String Address_ID = add.createNewAddress(street, city, state, pincode);
        Address_ID = "'" + Address_ID + "'";
        account_no = "'" + account_no + "'";
        String query = "UPDATE Charity SET Charity_name = " + name + ", Address_ID = " + Address_ID + ", Account_no = " + account_no + " WHERE Charity_ID = " + Charity_ID;
        updateQuery(query);
    }

    public static ResultSet showCharityTable(){
        // Function for accessing charity details.
        String query = "SELECT * FROM Charity";
        ResultSet result = executeQuery(query);
        return result;
    }

    public static void deleteCharity(String Charity_ID){
        Charity_ID = "'" + Charity_ID + "'";
        String query = "DELETE FROM Charity WHERE Charity_ID = " + Charity_ID;
        updateQuery(query);
    }

    public static void addSupplier(String Supplier_ID, String name, String phone, String street, String city, String state, String pincode){
        name = "'" + name + "'";
        Supplier_ID = "'" + Supplier_ID + "'";
        phone = "'" + phone + "'";
        AddressSql add = new AddressSql();
        String Address_ID = add.createNewAddress(street, city, state, pincode);
        Address_ID = "'" + Address_ID + "'";
        String query = "INSERT INTO Supplier VALUES (" + Supplier_ID + ", " + name + ", " + phone + ", " + Address_ID + ")";
        updateQuery(query);
    }

    public static void updateSupplier(String Supplier_ID, String name, String phone, String Address_ID){
        name = "'" + name + "'";
        Supplier_ID = "'" + Supplier_ID + "'";
        Address_ID = "'" + Address_ID + "'";
        phone = "'" + phone + "'";
        String query = "UPDATE Supplier SET Supplier_name = " + name + ", Address_ID = " + Address_ID + ", Phone = " + phone + " WHERE Supplier_ID = " + Supplier_ID;
        updateQuery(query);
    }

    public static ResultSet showSupplierTable(){
        // Function for accessing supplier details.
        String query = "SELECT * FROM Supplier";
        ResultSet result = executeQuery(query);
        return result;
    }

    public static void deleteSupplier(String Supplier_ID){
        Supplier_ID = "'" + Supplier_ID + "'";
        String query = "DELETE FROM Supplier WHERE Supplier_ID = " + Supplier_ID;
        updateQuery(query);
    }

    public static void addStore(String Store_ID, String Phone, String street, String city, String state, String pincode){
        Store_ID = "'" + Store_ID + "'";
        Phone = "'" + Phone + "'";
        AddressSql add = new AddressSql();
        String Address_ID = add.createNewAddress(street, city, state, pincode);
        Address_ID = "'" + Address_ID + "'";
        String query = "INSERT INTO Store VALUES (" + Store_ID + ", " + Phone + ", " + Address_ID + ")";
        updateQuery(query);
    }

    public static void updateStore(String Store_ID, String Phone, String Address_ID){
        Store_ID = "'" + Store_ID + "'";
        Phone = "'" + Phone + "'";
        Address_ID = "'" + Address_ID + "'";
        String query = "UPDATE Store SET Phone = " + Phone + ", Address_ID = " + Address_ID + " WHERE Store_ID = " + Store_ID;
        updateQuery(query);
    }

    public static ResultSet showStoreTable(){
        // Function for accessing store details.
        String query = "SELECT * FROM Store";
        ResultSet result = executeQuery(query);
        return result;
    }

    public static void deleteStore(String Store_ID){
        Store_ID = "'" + Store_ID + "'";
        String query = "DELETE FROM Store WHERE Store_ID = " + Store_ID;
        updateQuery(query);
    }

    public static ResultSet getOrderDetails(){
        // Function for accessing order details.
        String query = "SELECT * FROM Orders";
        ResultSet result = executeQuery(query);
        return result;
    }

    public static void addProduct(String Product_ID, String name, String category, int cost, float rating){
        Product_ID = "'" + Product_ID + "'";
        name = "'" + name + "'";
        category = "'" + category + "'";

        String query = "INSERT INTO Products VALUES (" + Product_ID + ", " + name + ", " + category + ", " + cost + ", " + rating + ")";
        updateQuery(query);
    }

    public static void updateProduct(String Product_ID, String name, String category, int cost, float rating){
        Product_ID = "'" + Product_ID + "'";
        name = "'" + name + "'";
        category = "'" + category + "'";
        String query = "UPDATE Products SET Product_name = " + name + ", Category = " + category + ", Cost = " + cost + ", Average_rating = " + rating + " WHERE Product_ID = " + Product_ID;
        updateQuery(query);
    }

    public static ResultSet showProductTable(){
        // Function for accessing product details.
        String query = "SELECT * FROM Products";
        ResultSet result = executeQuery(query);
        return result;
    }

    public static ResultSet showProductsSuppliedTable(){
        // Function for accessing products supplied details.
        String query = "SELECT * FROM products_supplied";
        ResultSet result = executeQuery(query);
        return result;
    }

    public static void deleteProduct(String Product_ID){
        Product_ID = "'" + Product_ID + "'";
        String query = "DELETE FROM Products WHERE Product_ID = " + Product_ID;
        updateQuery(query);
    }

    public static void addProductsSupplied(String Product_ID, String Store_ID, String Supplier_ID, int Quantity, String EXP){
        String query = "INSERT INTO products_supplied VALUES ('" + Product_ID + "', '" + Store_ID + "', '" + Supplier_ID + "', '" + Quantity + "', '" + EXP + "')";
        System.out.println(query);
        updateQuery(query);
    }

    public static void updateProductsSupplied(String Product_ID, String Store_ID, String Supplier_ID, int Quantity, String EXP){
        Product_ID = "'" + Product_ID + "'";
        Store_ID = "'" + Store_ID + "'";
        Supplier_ID = "'" + Supplier_ID + "'";
        EXP = "'" + EXP + "'";
        String query = "UPDATE products_supplied SET Product_ID = " + Product_ID + ", Store_ID = " + Store_ID + ", Supplier_ID = " + Supplier_ID + ", Availability = " + Quantity + ", Expiry_date = '" + EXP + "' WHERE Product_ID = " + Product_ID + " AND Store_ID = " + Store_ID + " AND Supplier_ID = " + Supplier_ID;
        updateQuery(query);
    }

    public static void main(String args[]){
        AdminSql ad = new AdminSql();
        // addProductsSupplied("2216553353", "5567382134", "5567483324", 300, "NULL");
        // updateProductsSupplied("2216553353", "5567382134", "5567483324", 500, "NULL");
        // deleteEmployee("7743562348");
        // deleteCustomer("3345671281");
        // addStore("5567382137", "9988774456", "Andheri Street", "Alighar", "UP", "987867");
        // deleteCustomer("3345671292");
        // Admin.addCustomer("3345671291", "Apoorv Singh", "apoorv07", "9868903130", "5678439906");
        // Admin.addEmployee("7743562351", "Apoorv Singh", "apoorv", "9868903130", 80000, "5567382135", "5678439895", "1995-9-12");
        // Admin.updateEmployee("7743562351", "Apoorv Singh", "apoorv", "9868903130", 80000, "5567382135", "5678439895", "2000-12-7");
        // updateCustomer("3345671291", "Apoorv Singh", "apoorv19151" , "9976547777" , "5678439880");
        // addCharity("6898578646", "UNICEF India", "5678439904", "1874563245678");
        // updateCharity("6898578646", "UNICEF India", "5678439904", "1874563249999");
        // String orders[][] = getOrderDetails();
        // for(int i = 0 ; orders != null && i<orders.length ; i++){
        //     for(int j = 0 ; j<orders[0].length ; j++){
        //         System.out.print(orders[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        // addSupplier("5567483326", "Aggarwal Wholesalers", "9944553322", "5678439901");
        // updateSupplier("5567483326", "Aggarwal Wholesalers", "9944550000", "5678439901");
        // Admin.addStore("5567382136", "9932324555", "5678439881");
        // Admin.updateStore("5567382136", "9932324999", "5678439881");
        // Admin.addProduct("2216553364", "Pixel 6", "Electronics", 80000, 5);
        // Admin.updateProduct("2216553364", "Pixel 6", "Electronics", 90000, 5);
        // String employees[][] = Admin.showEmployeeTable();
        // for(int i = 0 ; employees != null && i<employees.length ; i++){
        //     for(int j = 0 ; j<employees[0].length ; j++){
        //         System.out.print(employees[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();
        // String orders[][] = Admin.getOrderDetails();
        // for(int i = 0 ; orders != null && i<orders.length ; i++){
        //     for(int j = 0 ; j<orders[0].length ; j++){
        //         System.out.print(orders[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();
        // String customerDetails[][] = Admin.showCustomerTable();
        // for(int i = 0 ; customerDetails != null && i<customerDetails.length ; i++){
        //     for(int j = 0 ; j<customerDetails[0].length ; j++){
        //         System.out.print(customerDetails[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();
        // String charityDetails[][] = Admin.showCharityTable();
        // for(int i = 0 ; charityDetails != null && i<charityDetails.length ; i++){
        //     for(int j = 0 ; j<charityDetails[0].length ; j++){
        //         System.out.print(charityDetails[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();
        // String supplierDetails[][] = Admin.showSupplierTable();
        // for(int i = 0 ; supplierDetails != null && i<supplierDetails.length ; i++){
        //     for(int j = 0 ; j<supplierDetails[0].length ; j++){
        //         System.out.print(supplierDetails[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();
        // String storeDetails[][] = Admin.showStoreTable();
        // for(int i = 0 ; storeDetails != null && i<storeDetails.length ; i++){
        //     for(int j = 0 ; j<storeDetails[0].length ; j++){
        //         System.out.print(storeDetails[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();
        // String productDetails[][] = Admin.showProductTable();
        // for(int i = 0 ; productDetails != null && i<productDetails.length ; i++){
        //     for(int j = 0 ; j<productDetails[0].length ; j++){
        //         System.out.print(productDetails[i][j]+" ");
        //     }
        //     System.out.println();
        // }

    }
}