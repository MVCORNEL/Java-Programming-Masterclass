import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    //************************************************* CONNECTION **************************************************//
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:G:\\Java Course Tim\\07_Databases\\07_SQLInjectionAttack_PreparedStatements\\src\\" + DB_NAME;

    Connection conn;

    //OPEN CONNECTION -- DRIVER MANAGER USED TO GET A CONNECTION
    public boolean openConnection() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't connect to database " + e.getMessage());
            return false;
        }
    }

    //*************************************************CLOSE CONNECTION **************************************************//
    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't close connection " + e.getMessage());
        }
    }

    //*************************************************TABLES AND COLUMNS**************************************************//
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

    //*************************************************CREATE VIEW IF NOT EXISTS**************************************************//
    public static final String TABLE_ARTIST_SONG_VIEW = "artists_list";
    public static final String COLUMN_ARTIST_SONG_ALBUM = "album";
    public static final String CREATE_ARTIST_FOR_SONG_VIEW =
            "CREATE VIEW IF NOT EXISTS " + TABLE_ARTIST_SONG_VIEW + " AS SELECT " +
                    TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " AS " + COLUMN_ARTIST_SONG_ALBUM + ", " +
                    TABLE_SONGS + "." + COLUMN_SONG_TRACK + ", " +
                    TABLE_SONGS + "." + COLUMN_SONG_TITLE + " FROM " + TABLE_ARTISTS +
                    " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + "=" + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
                    " INNER JOIN " + TABLE_SONGS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID + "=" + TABLE_SONGS + "." + COLUMN_SONG_ID +
                    " ORDER BY " +
                    TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
                    TABLE_SONGS + "." + COLUMN_SONG_TITLE;

    public boolean createViewForSongArtists() {
        System.out.println(CREATE_ARTIST_FOR_SONG_VIEW);
        try (Statement statement = conn.createStatement()) {
            statement.execute(CREATE_ARTIST_FOR_SONG_VIEW);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Create view failed : " + e.getMessage());
            return false;
        }
    }

    //************************************************* QUERYING BY USING THE PREPARED STATEMENT **************************************************//
    public static final String QUERY_VIEW_SONG_INFO_PREPARED_STATEMENT =
            "SELECT " +
                    COLUMN_ARTIST_NAME + ", " +
                    COLUMN_SONG_ALBUM + ", " +
                    COLUMN_SONG_TRACK + ", " +
                    COLUMN_SONG_TITLE +
                    " FROM " + TABLE_ARTIST_SONG_VIEW +
                    " WHERE " + COLUMN_SONG_TITLE + " LIKE " +
                    "? ESCAPE '!'"
                    + " ORDER BY " + COLUMN_ARTIST_NAME + ", " +
                    COLUMN_SONG_ALBUM + ", " +
                    COLUMN_SONG_TRACK +
                    " COLLATE NOCASE ASC "
            ;

    public static final int VIEW_INDEX_ARTIST_NAME = 1;
    public static final int VIEW_INDEX_ALBUM_NAME = 2;
    public static final int VIEW_INDEX_SONG_TRACK = 3;
    public static final int VIEW_INDEX_SONG_TITLE = 4;

    public List<SongArtist> querySongsInfoView(String title) {

        //EVERY TIME WILL CLOSE THE STATEMENT ANY ASSOCIATES RESULT SETS ARE ALSO CLOSED
        try (PreparedStatement statement = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREPARED_STATEMENT)) {
            //PREPARED STATEMENT TO AVOID SQL INJECTION ATTACKS
            //FIRST PARAMETER IS THE the place holder(?) occurrence order in our case, having only one ? will be 1
           //ESCAPE CHARACTERS more in the link
            //https://stackoverflow.com/questions/8247970/using-like-wildcard-in-prepared-statement
            title = title
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_")
                    .replace("[", "![");
            statement.setString(1, "%"+title+"%");
            ResultSet results = statement.executeQuery();

            List<SongArtist> songs = new ArrayList<>();
            while (results.next()) {
                SongArtist songArtist = new SongArtist();

                songArtist.setArtistName(results.getString(VIEW_INDEX_ARTIST_NAME));
                songArtist.setAlbumName(results.getString(VIEW_INDEX_ALBUM_NAME));
                songArtist.setTrack(results.getInt(VIEW_INDEX_SONG_TRACK));
                songArtist.setSongTitle(results.getString(VIEW_INDEX_SONG_TITLE));
                songs.add(songArtist);
            }
            return songs;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Query failed " + e.getMessage());
            return null;
        }
    }


}
