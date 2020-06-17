import java.sql.*;

public class Main {
    //CONSTANTS
    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:G:\\Java Course Tim\\07_Databases\\05_ExecuteQuery_Constants\\" + DB_NAME;
    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
    public static void main(String[] args) {
        try {
            //1 CREATE CONNECTION AND CONNECTION STATEMENT
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            //2 CREATE TABLE
            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                    " ( " + COLUMN_NAME + " TEXT, " +
                    COLUMN_PHONE + " INTEGER, " +
                    COLUMN_EMAIL + " TEXT " + ")");
            //3 INSERT using am method
            insertContact(statement,"Joe",122333,"joe@gmail.com");
            insertContact(statement,"Ioana",3232,"ioana@gmail.com");
            insertContact(statement,"Bobo",4242121,"bobo@gmail.com");
            //4 UPDATE
            statement.execute("UPDATE "+ TABLE_CONTACTS + " SET " + COLUMN_PHONE+"=000000"+ " WHERE "+ COLUMN_NAME+ "='Ioana'");
            //5 DELETE
            statement.execute("DELETE FROM " + TABLE_CONTACTS + " WHERE " + COLUMN_NAME + "='Bobo'");
            //6 SELECT -> RESULT SET
            //execute query return the set of records
            ResultSet results=statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);
            //7 cursor is initially positioned before the first record
            while (results.next()){
                System.out.println(results.getString(COLUMN_NAME)+"|"+
                        results.getString(COLUMN_PHONE) + "|" +
                        results.getString(COLUMN_EMAIL) + "|");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong" + e.getMessage());
        }
    }
    //INSERT METHOD
    private static void insertContact(Statement statement,String name,int phone,String email) throws SQLException{
        statement.execute("INSERT INTO " + TABLE_CONTACTS +
                " (" + COLUMN_NAME + ", " +
                COLUMN_PHONE + ", " +
                COLUMN_EMAIL + ") " +
                " VALUES ('"+name+ "', "+phone+ ", '"+email+"')");
    }
}