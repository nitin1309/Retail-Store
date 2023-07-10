package sample;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeSql {

    public static String getPassword(String c_id) throws SQLException {
        String password ;
        ResultSet rs=AdminSql.executeQuery("Select Employee_password from Employees Where Employee_ID='"+c_id+"'");
        rs.next();
        password=rs.getString(1);
        return password;
    }

    public static void changePassword(String cid,String pass){
        AdminSql.updateQuery("Update Employees Set Employee_password='"+pass+"' Where Employee_ID='"+cid+"'");
    }

    public static boolean checkEmployee(String Employee_ID, String Employee_password) {
        // Function for checking whether an employee with given ID and password exist in the database or not.
        Employee_password = "'" + Employee_password + "'";
        Employee_ID = "'" + Employee_ID + "'";
        String query = "SELECT * FROM Employees WHERE Employee_ID = " + Employee_ID + " AND Employee_password = " + Employee_password;
        if (AdminSql.executeQuery(query) != null) {
            return true;
        }
        return false;
    }

    public static ResultSet getEmployeeDetails(String Employee_ID) {
        // Function for accessing the details of a particular employee.
        Employee_ID = "'" + Employee_ID + "'";
        String query = "SELECT * FROM Employees WHERE Employee_ID = " + Employee_ID ;
        ResultSet result = AdminSql.executeQuery(query);
        return result;
    }

    public static void updateEmployeePhone(String Employee_ID, String new_phone) {
        // For updating employee's phone number.
        Employee_ID = "'" + Employee_ID + "'";
        new_phone = "'" + new_phone + "'";
        String query = "UPDATE Employees SET Employee_phone = " + new_phone + " WHERE Employee_ID = " + Employee_ID;
        AdminSql.updateQuery(query);
    }

    public static void updateAddress(String Address_ID, String street, String city, String state, String pincode) {
        // For updating employee's address.
        street = "'" + street + "'";
        city = "'" + city + "'";
        state = "'" + state + "'";
        pincode = "'" + pincode + "'";
        Address_ID = "'" + Address_ID + "'";
        String query = "UPDATE Address SET Street = " + street + ", City = " + city + ", State = " + state + ", Pincode = " + pincode + " WHERE Address_ID = " + Address_ID;
        AdminSql.updateQuery(query);
    }
}