package ExternalJavaFiles;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
private Connection conn; 
private Statement statement;

public Connection openConnection() throws SQLException{
    if(conn==null){
      
        String uri="jdbc:mysql://localhost:3306/";
        String dbName="scmv";
        String driver="com.mysql.jdbc.Driver";
        String userName="root";
        String password="";
        try{
           
            Class.forName(driver);
           this.conn=(Connection)DriverManager.getConnection(uri+dbName,userName,password);
        //this.conn=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/scmv?zeroDateTimeBehavior=convertToNull","root","");
         System.out.println("Connection Successfull!");
        } catch (ClassNotFoundException | SQLException e) {
             System.out.println("Connection Failed Again!"+e.getMessage());
        }         
    }
    return conn;
}

}
