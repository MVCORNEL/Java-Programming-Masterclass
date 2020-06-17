import java.sql.*;

public class TestJDBC {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:G:\\Java Course Tim\\07_Databases\\04_Select_ResultSet_Cursor\\test.db");
            Statement statement = conn.createStatement();
            //CREATE TABLE
            statement.execute("CREATE TABLE IF NOT EXISTS contacts(name TEXT,phone INTEGER,email TEXT)");
            //INSERT DATA
            statement.execute("INSERT INTO contacts (name,phone,email) VALUES ('Bobo',221142,'bobo@gmail.com')");
            statement.execute("INSERT INTO contacts (name,phone,email) VALUES ('Radu',211222,'radu@gmail.com')");
            statement.execute("INSERT INTO contacts (name,phone,email) VALUES ('Ioana',242211,'ioana@gmail.com')");
            //1 SELECT STATEMENT
            //statement.execute() will return true if it hold an object as ResultSet
            statement.execute("SELECT * FROM contacts");
            //2 RESULTSET
            //contains the query result, it is a resource sow it must be closed
            //An active statement can have only one ResultSet Associated with it once.
            ResultSet results = statement.getResultSet();
            //3 CURSOR
            //Every ResultSet has a cursor, when the result is create the cursor is positioned before the first record
            //If we are willing to work with different queries at the same time is crucial to have a different statement for each query
            while (results.next()) {
                System.out.println(results.getString("name") + " - " +
                        results.getString("phone") + " - " +
                        results.getString("email"));
            }
            //4 CLOSING CURSOR IS CRUCIAL BECAUSE IT IS A RESOURCE
            results.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }


    }
}
