import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//IMPORTANT ADD THE jdbc File->Project Structure->Libraries->+ jdbc location
public class TestDB {
    public static void main(String[] args) {
        //1 CREATING DATABASE
        //When using sqlite connecting to a database that doesn't exist will create the database
        //1.1 Obtaining the connection using the DriverManager
        //Setup an connection string that accepts the connection string and return the connection instance
        //Very import to close the resources when you finish working with databases -> conn.close will be close automatically because the conn implments AutoClosable
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:G:\\Java Course Tim\\07_Databases\\02_CreateDatabase_JDBC\\src\\testjava.db")) {
            //1.2 Creating statement instance by calling the connection method
            //1.3 The jdbc connection's object default behaviour is to auto-commit the changes
            //conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
            //The semicolon at the end of the statement is not needed, because when statement execute is called is interpreted as being a terminate statement
            statement.execute("CREATE TABLE IF NOT EXISTS contacts(name TEXT, phone INTEGER, email TEXT);");
            //Commit in chase the connection is not set to autoCommit True;
            //conn.commit();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
