import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDataBase {
    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:G:\\Java Course Tim\\07_Databases\\03_Insert_Update_Delete\\src\\testjava.db");
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS contacts(name TEXT, phone INTEGER ,email TEXT)");
            //1 INSERT VALUES
            statement.execute("INSERT INTO  contacts(name,phone,email) VALUES ('Bobo',2233311,'bobo@gmail.com')");
            statement.execute("INSERT INTO  contacts(name,phone,email) VALUES ('Alexandra',45433,'alexandra@gmail.com')");
            statement.execute("INSERT INTO  contacts(name,phone,email) VALUES ('Mircea',214222,'mircea@gmail.com')");
            statement.execute("INSERT INTO  contacts(name,phone,email) VALUES ('Radu',124222,'radu@gmail.com')");
            //2 UPDATE a record
            statement.execute("UPDATE contacts SET phone=1111 WHERE name='Bobo'");
            //3 DELETE RECORD(ROW)
            statement.execute("DELETE FROM contacts WHERE name='Mircea'");
            //4 Crucial to close the connection and the connection statement
            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
    }
}
