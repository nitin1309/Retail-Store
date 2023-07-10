package sample;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressSql {

    public static String createNewAddress(String street, String city, String state, String pincode){
        street = "'" + street + "'";
        city = "'" + city + "'";
        state = "'" + state + "'";
        pincode = "'" + pincode + "'";

        String query = "SELECT Address_ID FROM Address WHERE Address_ID = (SELECT max(Address_ID) FROM Address);";
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
        query = "INSERT INTO Address VALUES (" + id + ", " + street + ", " + city + ", " + state + ", " + pincode + ")";
        AdminSql.updateQuery(query);
        return id + "";
    }

    public static ResultSet getAddress(String Address_ID){
        Address_ID = "'" + Address_ID + "'";
        String query = "SELECT * FROM Address WHERE Address_ID = " + Address_ID;
        ResultSet result = AdminSql.executeQuery(query);
        return result;
    }

    public static void updateAddress(String Address_ID, String street, String city, String state, String pincode){
        street = "'" + street + "'";
        city = "'" + city + "'";
        state = "'" + state + "'";
        pincode = "'" + pincode + "'";
        Address_ID = "'" + Address_ID + "'";
        String query = "UPDATE Address SET Street = " + street + ", City = " + city + ", State = " + state + ", Pincode = " + pincode + " WHERE Address_ID = " + Address_ID;
        AdminSql.updateQuery(query);
    }

    public static void deleteAddress(String Address_ID){
        Address_ID = "'" + Address_ID + "'";
        String query = "DELETE FROM Address WHERE Address_ID = " + Address_ID;
        AdminSql.updateQuery(query);
    }

    public static void main(String args[]){
        AdminSql ad = new AdminSql();
        AddressSql a = new AddressSql();
        // a.createNewAddress("C-26 Surya Nagar", "Ghaziabad", "UP", "201011");
    }
}