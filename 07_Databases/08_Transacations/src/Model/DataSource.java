package Model;

import java.sql.*;

public class DataSource {
    //*************************************************TABLES AND COLUMNS CONSTANTS**************************************************//
    //ALBUMS'
    //ALBUMS' TABLE
    public static final String TABLE_ALBUMS = "albums";
    //ALBUMS' COLUMNS
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    //ARTISTS'
    //ARTISTS' TABLE
    public static final String TABLE_ARTISTS = "artists";
    //ARTISTS' COLUMNS
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";
    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;

    //SONGS'
    //SONGS' TABLE
    public static final String TABLE_SONGS = "songs";
    //SONGS' COLUMNS
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";
    public static final int INDEX_SONG_ID = 1;
    public static final int INDEX_SONG_TRACK = 2;
    public static final int INDEX_SONG_TITLE = 3;
    public static final int INDEX_SONG_ALBUM = 4;
    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    private Connection conn;

    //IN THE CASE THE STATEMENT WILL BE USED MULTIPLE TIMES IT WILL BE MORE EFFICIENT TO DECLARE THEM WHEN WE OPEN THE CONNECTION, WILL RESULT INTO A BETTER PERFORMANCE
    private PreparedStatement querySongInfo;

    private PreparedStatement insertIntoArtistsStatement;
    private PreparedStatement insertIntoAlbumsStatement;
    private PreparedStatement insertIntoSongsStatement;

    private PreparedStatement queryArtist;
    private PreparedStatement queryAlbum;


    //*************************************************** Open Connection *************************************************** //
    public boolean openConnection() {
        try {
            //RELATIVE PATH FOR CONNECTION BETTER THAN ABSOLUTE PATH
            conn = DriverManager.getConnection("jdbc:sqlite:src\\Model\\music.db");
            //IN THE CASE THE STATEMENT WILL BE USED MULTIPLE TIMES IT WILL BE MORE EFFICIENT TO DECLARE THEM WHEN WE OPEN THE CONNECTION, WILL RESULT INTO A BETTER PERFORMANCE
            //FIRSTLY YOU DONE NEED TO ALWAYS DEFINE THEM, OPEN AND CLOSE THEM SO OFTEN
            //QUERY STATEMENT
//            querySongInfo = conn.prepareStatement("");
            //INSERT STATEMENTS
            //WE NEED A SECOND PARAMETER, to be able to get the key from the FROM THE PREPARED STATEMENT OBJECT
            insertIntoArtistsStatement = conn.prepareStatement(INSERT_INTO_ARTIST, Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbumsStatement = conn.prepareStatement(INSERT_INTO_ALBUMS, Statement.RETURN_GENERATED_KEYS);
            insertIntoSongsStatement = conn.prepareStatement(INSERT_INTO_SONGS);
            //QUERY TO SEE IF THE ALBUM OR ARTIST ALREADY EXIST
            queryArtist = conn.prepareStatement(QUERY_ARTIST);
            queryAlbum = conn.prepareStatement(QUERY_ALBUM);


            return true;
        } catch (SQLException e) {
            e.getStackTrace();
            System.out.println("Couldn't connect to server" + e.getMessage());
            return false;
        }
    }

    //*************************************************** Close Connection and StatementResources *************************************************** //
    public void closeConnection() {
        try {
            //CLOSE STATEMENTS
            if (querySongInfo != null) {
                querySongInfo.close();
            }
            if (insertIntoArtistsStatement != null) {
                insertIntoArtistsStatement.close();
            }
            if (insertIntoAlbumsStatement != null) {
                insertIntoAlbumsStatement.close();
            }
            if (insertIntoSongsStatement != null) {
                insertIntoSongsStatement.close();
            }
            if (queryArtist != null) {
                queryArtist.close();
            }
            if (queryAlbum != null) {
                queryAlbum.close();
            }
            //CLOSE CONNECTION
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't close connection" + e.getMessage());
        }
    }

    //*************************************************** INSERT CONSTANT  ***************************************************

    public static final String INSERT_INTO_ARTIST = "INSERT INTO " + TABLE_ARTISTS + " (" + COLUMN_ARTIST_NAME + ")" + "VALUES(?)";
    public static final String INSERT_INTO_ALBUMS = "INSERT INTO " + TABLE_ALBUMS + " (" + COLUMN_ALBUM_NAME + ", " + COLUMN_ALBUM_ARTIST + ") VALUES(?,?)";
    public static final String INSERT_INTO_SONGS = "INSERT INTO " + TABLE_SONGS + " (" + COLUMN_SONG_TRACK + ", " + COLUMN_SONG_TITLE + ", " + COLUMN_SONG_ALBUM + ") VALUES(?,?,?)";
    //*************************************************** QUERYING CONSTANT  ************************************************

    public static final String QUERY_ARTIST = "SELECT " + COLUMN_ARTIST_ID + " FROM " + TABLE_ARTISTS + " WHERE " + COLUMN_ARTIST_NAME + "= ?";
    public static final String QUERY_ALBUM = "SELECT " + COLUMN_ALBUM_ID + " FROM " + TABLE_ALBUMS + " WHERE " + COLUMN_ALBUM_NAME + "= ?";


    //*************************************************** INSERT ARTIST  ***************************************************

    //IF THE ARTIST ALREADY EXIST WE RETURN THE ARTIST ID ELSE WE INSERT THE ARTIST AND RETURN THE GENERATE ID
    //WE THROW SQLException here because we want the caller to handle the exceptions
    private int insertArtist(String artistName) throws SQLException {
        queryArtist.setString(1, artistName);
        ResultSet resultSet = queryArtist.executeQuery();
        //IF WE GET A NUMBER BACK FROM OUR FROM OUR FIRST QUERY WE KNOW THAT IN THIS CASE THE ARTIST IS ALREADY ON FILE, SO WE RETURN THE ID OF THE EXISTING RECORD
        //WE DON't NEED TO INSERT THE ARTIST AS IT IS ALREADY ON FILE
        if (resultSet.next()) {
            return resultSet.getInt(INDEX_ARTIST_ID);
        }
        //IF DOESN'T EXIST WE NEED TO INSERT IN THE TABLE THE ARTIST NAME
        else {
            insertIntoArtistsStatement.setString(1, artistName);
            //THAT WILL NOT ONLY SAVE THE RECORD AS A SIMPLE EXECUTE METHOD BUT, WILL TELL US HOW MANY ROWS ARE UPDATED
            //THE EXPECTED ROW TO BE UPDATED IS ONE
            int affectedRows = insertIntoArtistsStatement.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert artist name");
            }
            //RETRIEVE THE ID FOR THE NEWLY INSERTED RECORD
            ResultSet generatedKeys = insertIntoArtistsStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for artist");
            }
        }
    }

    //*************************************************** INSERT ALBUM  ***************************************************

    //IF THE ALBUM ALREADY EXIST WE RETURN THE ALBUM ID ELSE WE INSERT THE ALBUM AND RETURN THE GENERATE ID
    //WE THROW SQLException here because we want the caller to handle the exceptions
    private int insertAlbum(String albumName, int artistId) throws SQLException {
        queryAlbum.setString(1, albumName);
        ResultSet resultSet = queryAlbum.executeQuery();
        //IF WE GET A NUMBER BACK FROM OUR FROM OUR FIRST QUERY WE KNOW THAT IN THIS CASE THE ALBUM IS ALREADY ON FILE, SO WE RETURN THE ID OF THE EXISTING RECORD
        //WE DON't NEED TO INSERT THE ALBUM AS IT IS ALREADY ON FILE
        if (resultSet.next()) {
            return resultSet.getInt(INDEX_ALBUM_ID);
        }
        //IF DOESN'T EXIST WE NEED TO INSERT IN THE TABLE THE ALBUM NAME
        else {
            insertIntoAlbumsStatement.setString(1, albumName);
            //SET THE ID OBTAINED FROM THE insertArtist() method
            insertIntoAlbumsStatement.setInt(2, artistId);
            //THAT WILL NOT ONLY SAVE THE RECORD AS A SIMPLE EXECUTE METHOD BUT, WILL TELL US HOW MANY ROWS ARE UPDATED
            //THE EXPECTED ROW TO BE UPDATED IS ONE
            int affectedRows = insertIntoAlbumsStatement.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert album name");
            }
            //RETRIEVE THE ID FOR THE NEWLY INSERTED RECORD
            ResultSet generatedKeys = insertIntoAlbumsStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for album");
            }
        }
    }
    //*************************************************** INSERT SONG - THE CALLER METHOD  **************************************************

    //WE HANDLE THE SQLException THROWN BY (insertArtist() AND insertAlbum()) methods here
    //THIS METHOD PUBLIC NOT LIKE THE PREVIOUS ONES PRIVATE
    public void insertSong(int songTrack, String songTitle, String albumName, String artistName) {
        //TRY
        try {
            //SET AUTOCOMMIT TO FALSE BECAUSE WE'RE GONNA PERFORM TRANSACTIONS
            conn.setAutoCommit(false);
            //1 CALLING TABLE ARTIST method
            int artistId = insertArtist(artistName);
            //2 CALLING TABLE ALBUM method
            int albumId = insertAlbum(albumName, artistId);
            //3 TABLE SONG
            insertIntoSongsStatement.setInt(1, songTrack);
            insertIntoSongsStatement.setString(2, songTitle);
            insertIntoSongsStatement.setInt(3, albumId);
            //THAT WILL NOT ONLY SAVE THE RECORD AS A SIMPLE EXECUTE METHOD BUT, WILL TELL US HOW MANY ROWS ARE UPDATED
            //THE EXPECTED ROW TO BE UPDATED IS ONE
            int affectedRows = insertIntoSongsStatement.executeUpdate();
            if (affectedRows == 1) {
                conn.commit();
            } else {
                throw new SQLException("The song insert failed");
            }
        }
        //CATCH
        //VERY VERY VERY IMPORTANT IS TO CATCH EXCEPTION HERE INSTEAD OF  SQLEXCEPTION-> BECAUSE AT THE LINE 206 IF THE PASS ASS AN ARGUMENT ANOTHER INTEGER INSTEAD OF 3
        //WILL RESULT INTO ArrayIndexOutOfBoundsException AND THE ERROR WON't BE CAUGHT SO THE TRANSACTION WON't GET ROLLBACK AND WILL BE COMMITTED
        // at line 220 and result on setting the conn.setAutoCommit(true); in finally block so we will update the tables even if we have exceptions
        //IF AN EXCEPTION OCCURRED WE HAVE TO PERFORM THE TRANSACTION ROLLBACK
        catch (Exception e) {

            e.printStackTrace();
            System.out.println("Insert song exception " + e.getMessage());
            //TRANSACTION ROLLBACK
            try {
                System.out.println("Performing rollback ");
                conn.rollback();
            } catch (SQLException e2) {
                e.printStackTrace();
                System.out.println("Performing rollback failed " + e2.getMessage());
            }
        }
        //FINALLY
        finally {
            //RESETTING DEFAULT COMMIT BEHAVIOUR
            try {
                System.out.println("Resetting the default commit behaviour to True");
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Could't reset auto-commit ! " + e.getMessage());
            }
        }
    }
}



