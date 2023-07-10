package sample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CustomerSql {

    public static String getPassword(String c_id) throws SQLException {
        String password ;
        ResultSet rs=AdminSql.executeQuery("Select Customer_password from Customer Where Customer_ID='"+c_id+"'");
        rs.next();
        password=rs.getString(1);
        return password;
    }

    public static void changePassword(String cid,String pass){
        AdminSql.updateQuery("Update Customer Set Customer_password='"+pass+"' Where Customer_ID='"+cid+"'");
    }

    public static boolean checkCustomer(String Customer_ID, String Customer_password){
        // Function for checking whether a cutomer with given ID and password exist in the database or not.
        Customer_password = "'" + Customer_password + "'";
        Customer_ID = "'" + Customer_ID + "'";
        String query = "SELECT * FROM Customer WHERE Customer_ID = " + Customer_ID + " AND Customer_password = " + Customer_password;
        if(AdminSql.executeQuery(query) != null){
            return true;
        }
        return false;
    }

    public static boolean checkCustomer(String Customer_ID){
        // Function for checking whether a cutomer with given ID and password exist in the database or not.
        Customer_ID = "'" + Customer_ID + "'";
        String query = "SELECT * FROM Customer WHERE Customer_ID = " + Customer_ID ;
        if(AdminSql.executeQuery(query) != null){
            return true;
        }
        return false;
    }

    public static void createCustomerProfile(String name, String phone, String street, String city, String state, String pin, String password){
        String query = "SELECT Customer_ID FROM Customer WHERE Customer_ID = (SELECT max(Customer_ID) FROM Customer);";
        ResultSet result = AdminSql.executeQuery(query);
        long id = 0;
        try{
            while(result.next()){
                id = Long.parseLong(result.getString(1));
                break;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        id += 1;
        String Customer_ID = id + "";
        AdminSql.addCustomer(Customer_ID, name, password, phone, street, city, state, pin);
    }

    public static ResultSet getCustomerDetails(String Customer_ID){
        // Function for accessing the details of a particular employee.
        Customer_ID = "'" + Customer_ID + "'";
        String query = "SELECT * FROM Customer WHERE Customer_ID = " + Customer_ID ;
        ResultSet result = AdminSql.executeQuery(query);
        return result;
    }

    public static ResultSet getCustomerOrders(String Customer_ID){
        Customer_ID = "'" + Customer_ID + "'";
        String query = "SELECT * FROM Orders WHERE Customer_ID = " + Customer_ID;
        ResultSet result = AdminSql.executeQuery(query);
        return result;
    }

    public static void addToShoppingCart(String Customer_ID, String Product_ID, int Quantity){
        Customer_ID = "'" + Customer_ID + "'";
        Product_ID = "'" + Product_ID + "'";
        String query = "INSERT INTO Shopping_Cart VALUES (" + Customer_ID + ", " + Product_ID + ", " + Quantity + ")";
        AdminSql.updateQuery(query);
    }

    public static ResultSet getProduct(String id){
        return AdminSql.executeQuery("Select * from Products Where Product_ID='"+id+"'");
    }

    public static void deleteFromShoppingCart(String Customer_ID, String Product_ID){
        Customer_ID = "'" + Customer_ID + "'";
        Product_ID = "'" + Product_ID + "'";
        String query = "DELETE FROM Shopping_Cart WHERE Customer_ID = " + Customer_ID + " AND Product_ID = " + Product_ID + ";";
        AdminSql.updateQuery(query);
    }

    public static ResultSet showShoppingCart(String Customer_ID){
        Customer_ID = "'" + Customer_ID + "'";
        String query = "SELECT * FROM Shopping_Cart WHERE Customer_ID = " + Customer_ID;
        ResultSet result = AdminSql.executeQuery(query);
        return result;
    }

    public static ArrayList<String> getStoresList(){
        String query = "SELECT Store_ID FROM Store";
        ResultSet result = AdminSql.executeQuery(query);
        ArrayList<String> list = new ArrayList<>();
        try{
            while(result.next()){
                String id = result.getString(1);
                list.add(id);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public static boolean checkAvailability(String Product_ID, int quantity){
        System.out.println(quantity);
        ArrayList<String> list = getStoresList();
        for(int i = 0 ; i<list.size() ; i++){
            String curStoreID = list.get(i);
            String query = "SELECT SUM(Availability) FROM (SELECT * FROM products_supplied WHERE Product_ID = " + Product_ID + " AND Store_ID = " + curStoreID + ") AS T;";
            ResultSet result = AdminSql.executeQuery(query);
            try{
                result.next();
                System.out.println(result.getString(1));
                if(result != null && result.getInt(1)>=quantity){
                    return true;
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void updateOrders(String Customer_ID, String payment_mode){
        payment_mode = "'" + payment_mode + "'";
        String query_order = "SELECT Order_ID FROM Orders WHERE Order_ID = (SELECT max(Order_ID) FROM Orders);";
        ResultSet result_order = AdminSql.executeQuery(query_order);
        long id = 0;
        try{
            while(result_order.next()){
                id = Long.parseLong(result_order.getString(1));
                break;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        id += 1;
        String order_id = id + "";
        ResultSet cart = showShoppingCart(Customer_ID);
        String Product_ID = "";
        int quantity = 0;
        try{
            while(cart != null && cart.next()){
                Product_ID = cart.getString(2);
                // System.out.println(Product_ID);
                quantity = Integer.parseInt(cart.getString(3));
                int cost = 0;
                LocalDate today = LocalDate.now();
                LocalDate nextDate = today.plusDays(7);
                String Booking_Date = "'" + today + "'";
                String Delivery_Date = "'" + nextDate + "'";
                ArrayList<String> StoreIDList = new ArrayList<>();
                StoreIDList = getStoresList();
                for(int i = 0 ; i<StoreIDList.size() ; i++){
                    int totalQuantity = quantity;
                    String curStoreID = StoreIDList.get(i);
                    String query_sum = "SELECT SUM(Availability) FROM (SELECT * FROM products_supplied WHERE Product_ID = '" + Product_ID + "' AND Store_ID = '" + curStoreID + "') AS T;";
                    ResultSet result_sum = AdminSql.executeQuery(query_sum);
                    result_sum.next();
                    if(result_sum.getString(1)==null || result_sum.getInt(1)<quantity){
                        continue;
                    }
                    String query_products = "SELECT * FROM products_supplied WHERE Product_ID = '" + Product_ID + "' AND Store_ID = '" + curStoreID+"'";
                    ResultSet result_products = AdminSql.executeQuery(query_products);
                    while(totalQuantity>0 && result_products.next()){
                        int curAvailability = result_products.getInt(4);
                        String supplierID = result_products.getString(3);
                        if(curAvailability>=totalQuantity){
                            curAvailability -= totalQuantity;
                            totalQuantity = 0;
                            // System.out.println(curAvailability);
                            String query_update = "UPDATE products_supplied SET Availability = " + curAvailability + " WHERE Product_ID = '" + Product_ID + "' AND Store_ID = '" + curStoreID + "' AND Supplier_ID = '" + supplierID+"'";
                            // System.out.println(query_update);
                            AdminSql.updateQuery(query_update);
                            String query_insert = "INSERT INTO Orders VALUES ('" + Customer_ID + "', '" + order_id  + "', '" + Product_ID + "', " + quantity + ", " + cost + ", " + Booking_Date + ", " + Delivery_Date + ", " + payment_mode + ", '" + curStoreID + "', 0)";
                            // System.out.println(query_insert);
                            AdminSql.updateQuery(query_insert);
                            // System.out.println(Product_ID + " " + quantity);
                        }else{
                            totalQuantity -= curAvailability;
                            curAvailability = 0;
                            String query_update = "UPDATE products_supplied SET Availability = " + curAvailability + " WHERE Product_ID = '" + Product_ID + "' AND Store_ID = '" + curStoreID + "' AND Supplier_ID = '" + supplierID+"'";
                            // System.out.println(query_update);
                            AdminSql.updateQuery(query_update);
                        }
                    }
                    if(totalQuantity==0) {
                        break;
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        String query = "DELETE FROM Shopping_Cart WHERE Customer_ID = '" + Customer_ID+"'";
        AdminSql.updateQuery(query);
    }

    public static void updateReviews(String Customer_ID, String Product_ID, int rating){
        Customer_ID = "'" + Customer_ID + "'";
        Product_ID = "'" + Product_ID + "'";

        String query = "INSERT INTO Reviews VALUES (" + Product_ID + ", " + Customer_ID + ", " + rating + ")";
        AdminSql.updateQuery(query);
        double avg_rating = 0;
        query = "SELECT SUM(Rating) FROM Reviews WHERE Product_ID = " + Product_ID;
        ResultSet result = AdminSql.executeQuery(query);
        int sum = 0;
        try{
            while(result.next()){
                sum = Integer.parseInt(result.getString(1));
                break;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        query = "SELECT COUNT(Product_ID) FROM Reviews WHERE Product_ID = " + Product_ID;
        result = AdminSql.executeQuery(query);
        int count = 0;
        try{
            while(result.next()){
                count = Integer.parseInt(result.getString(1));
                break;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println(sum);
        System.out.println(count);
        avg_rating = (double)sum/count;
        avg_rating = change(avg_rating, 2);
        query = "UPDATE Products SET Average_rating = " + avg_rating + " WHERE Product_ID = " + Product_ID;
        AdminSql.updateQuery(query);
    }

    public static double change(double value, int decimalpoint)
    {
        value = value * Math.pow(10, decimalpoint);
        value = Math.floor(value);
        value = value / Math.pow(10, decimalpoint);
        return value;
    }

    public static void main(String args[]){
        AdminSql ad = new AdminSql();
        // System.out.println(c.checkCustomer("3345671287", "arun1907"));
        // c.createCustomerProfile("Apoorv Singh III", "9868903999", "Surya Nagar", "Ghaziabad", "UP", "201011", "Apoorv0712");
        // CustomerSql.updatebs("3345671286", "Cash");
        updateReviews("3345671281", "2216553360", 4);
        // String details[] = c.getCustomerDetails("3345671289", "roy1909");
        // for(int i = 0 ; i<details.length ; i++){
        //     System.out.print(details[i] + " ");
        // }
        // System.out.println();
        // c.addToShoppingCart("3345671293", "2216553344", 6);
        // c.deleteFromShoppingCart("3345671293", "2216553344");
    }
}