package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    //JDBC CONNECTION STRING
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:G:\\Java Course Tim\\07_Databases\\06_Music_SQLite_Database_EXAMPLE\\src\\model\\" + DB_NAME;
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

    //************************************************* CONNECTION **************************************************//
    private Connection conn;

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

    //*************************************************QUERY WITHOUT TRY CATCH WITH RESOURCES **************************************************//
    //QUERY THE ARTISTS WITHOUT TRY CATCH WITH RESOURCES
//    public List<Artist> queryArtists() {
//        Statement statement = null;
//        ResultSet resultSet = null;
//        try {
//            statement = conn.createStatement();
//            resultSet = statement.executeQuery("SELECT * FROM " + TABLE_ARTISTS);
//
//            List<Artist> artists = new ArrayList<>();
//            while (resultSet.next()) {
//                Artist artist = new Artist();
//                artist.setId(resultSet.getInt(COLUMN_ARTIST_ID));
//                artist.setName(resultSet.getString(COLUMN_ARTIST_NAME));
//            }
//            return artists;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Query failed" + e.getMessage());
//            return null;
//        }
//
//        //CLOSING THE RESOURCES
//        //BETTER TO KEEP THEM INDIVIDUALLY IN SEPARATE TRY CATCH BLOCK... BECAUSE IF WE GROUPT THEM TOGETHER AND THE FIRST ONE THROWS AN EXCEPTION SE SECOND ONE WON'T BE CHECKED
//        //THAT IS THE REASON OF HAVING THEM IN DIFFERENT BLOCKS
//        finally {
//            //1 CLOSING THE RESOURCE SET
    //statement.close() will automatically close also the result resource
//            try {
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//            } catch (SQLException e) {
//                System.out.println("Couldn't close the resultSet resource" + e.getMessage());
//            }
//            //2 CLOSING THE CONNECTION STATEMENT
//            try {
//                if (statement != null) {
//                    statement.close();
//                }
//            } catch (SQLException e) {
//                System.out.println("Couldn't close the statement resource" + e.getMessage());
//            }
//        }
//    }

    //*************************************************QUERY ONE **************************************************//

    //QUERYING THE ARTISTS USING TRY CATCH WITH RESOURCES WHICH WILL AUTOMATICALLY CLOSE ALL THE RESOURCES
    public List<Artist> queryArtists(int sortOrder) {
        //STRING USED FOR SORTING THE ORDER
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS);
        //SORTING THE ORDER BY NAME
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTIST_NAME);
            sb.append(" COLLATE NOCASE ");
            //DESCENDING ORDER
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            }
            //ASCENDING
            //IN CASE THE USER WILL PASS AN  INAPPROPRIATE sort order argument the ASC WILL BE USED TO REALIZE THAT IS THE DEFAULT BEHAVIOUR
            else {
                sb.append("ASC");
            }
        }
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sb.toString())) {
            List<Artist> artists = new ArrayList<>();
            while (resultSet.next()) {
                Artist artist = new Artist();
                // 1
                // BY USING THE USING THE COLUMN NAME IS NOT EFFICIENT FOR VERY BIG VALUE DATA SETS
                //artist.setId(resultSet.getInt(COLUMN_ARTIST_ID));
                //artist.setName(resultSet.getString(COLUMN_ARTIST_NAME));
                // 2
                //SAME THING AS PREVIOUSLY BY USING AN OVERLOADED METHOD OF RESULT SET WHICH TAKE INDEXES INSTEAD OF COLUMN NAMES
                //WHICH IS MORE EFFICIENT BECAUSE THE GETTER METHODS WILL NOW EXACTLY WHERE TO GO TO GET THE VALUE IN THE RESULT SET
                artist.setId(resultSet.getInt(INDEX_ARTIST_ID));
                artist.setName(resultSet.getString(INDEX_ARTIST_NAME));
                artists.add(artist);
            }
            return artists;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Query failed" + e.getMessage());
            return null;
        }
    }

    //*************************************************QUERY TWO **************************************************//
    public List<String> queryAlbumsForArtists(String artistName, int sortOrder) {
        //BUILDING THE QUERY
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(TABLE_ALBUMS);
        sb.append(".");
        sb.append(COLUMN_ALBUM_NAME);
        sb.append(" FROM ");
        sb.append(TABLE_ALBUMS);
        sb.append(" INNER JOIN ");
        sb.append(TABLE_ARTISTS);
        sb.append(" ON ");
        sb.append(TABLE_ALBUMS);
        sb.append(".");
        sb.append(COLUMN_ALBUM_ARTIST);
        sb.append("=");
        sb.append(TABLE_ARTISTS);
        sb.append(".");
        sb.append(COLUMN_ARTIST_ID);
        sb.append(" WHERE ");
        sb.append(TABLE_ARTISTS);
        sb.append(".");
        sb.append(COLUMN_ARTIST_NAME);
        sb.append(" LIKE \"");
        sb.append(artistName);
        sb.append("\"");

        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(TABLE_ALBUMS);
            sb.append(".");
            sb.append(COLUMN_ALBUM_NAME);
            sb.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {
            List<String> albums = new ArrayList<>();
            while (results.next()) {
                //IMPORTANT
                //Very important is the indexes is the index of the QUERY NOT THE INDEX OF THE TABLE
                //In this case we returning only the names, will be represented on only 1 columns
                String album = results.getString(1);
                albums.add(album);
            }
            return albums;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Query failed" + e.getMessage());
            return null;
        }
    }

    //*************************************************QUERY THREE **************************************************//
    //in order to to append as long queries
    public static final String QUERY_ARTIST_FOR_SONG_START = "SELECT "
            + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", "
            + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", "
            + TABLE_SONGS + "." + COLUMN_SONG_TRACK + " FROM " + TABLE_ALBUMS + " INNER JOIN " + TABLE_ARTISTS +
            " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + "=" + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID + " INNER JOIN " + TABLE_SONGS +
            " ON " + TABLE_SONGS + "." + COLUMN_SONG_ALBUM + "=" + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID +
            " WHERE " + TABLE_SONGS + "." + COLUMN_SONG_TITLE + " LIKE ";
    public static final String QUERY_ARTIST_FOR_SONG_ORDER = " ORDER BY " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
            TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
            TABLE_SONGS + "." + COLUMN_SONG_TRACK + " COLLATE NOCASE ";

    public List<SongArtist> queryArtistsFromSong(String songName, int sortOrder) {
        StringBuilder sb = new StringBuilder(QUERY_ARTIST_FOR_SONG_START);
        sb.append("\"%");
        sb.append(songName);
        sb.append("%\"");

        if (sortOrder != DataSource.ORDER_BY_NONE) {
            sb.append(QUERY_ARTIST_FOR_SONG_ORDER);
            if (sortOrder == DataSource.ORDER_BY_DESC) {
                sb.append(" DESC ");
            } else {
                sb.append(" ASC ");
            }
        }

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {
            List<SongArtist> songArtistList = new ArrayList<>();
            while (results.next()) {
                //IMPORTANT
                //Very important is the indexes is the index of the QUERY NOT THE INDEX OF THE TABLE
                //In this case we returning only the names, will be represented on only 1 columns
                SongArtist songArtist = new SongArtist();

                songArtist.setArtistName(results.getString(1));
                songArtist.setAlbumName(results.getString(2));
                songArtist.setTrack(results.getInt(3));

                songArtistList.add(songArtist);
            }
            return songArtistList;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Query failed : " + e.getMessage());
            return null;
        }
    }

    //*************************************************QUERY METADATA **************************************************//
    //USING THE result.getMetaData we can get information such as the column names and types. get schema information about the table
    public void querySongsMetadata() {
        String sql = "SELECT * FROM " + TABLE_SONGS;
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numColumns = metaData.getColumnCount();

            for (int i = 1; i <= numColumns; i++) {
                System.out.format("Column % d in the songs table is named %s\n",
                        i, metaData.getColumnName(i));
            }
        } catch (Exception e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    //*************************************************FUNCTION QUERY**************************************************//
    public void getCountAndMinimumId(String table) {
        String sql = "SELECT COUNT(*) AS count,MIN(_id) AS min_id FROM " + table;

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            //RETRIEVING THE FUNCTIONS BY COLUMN ID
            //int count = resultSet.getInt(1);
            //int min=resultSet.getInt(2);
            int count = resultSet.getInt("count");
            int min = resultSet.getInt("min_id");
            System.out.format("Count is %d and the minimum id is %d", count, min);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Query failed : " + e.getMessage());
        }
    }

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

    //*************************************************QUERY THE VIEW**************************************************//
    private static final String QUERY_VIEW_SONG_INFO_START = "SELECT " +
            COLUMN_ARTIST_NAME + ", " +
            COLUMN_SONG_ALBUM + ", " +
            COLUMN_SONG_TRACK + ", " +
            COLUMN_SONG_TITLE +
            " FROM " + TABLE_ARTIST_SONG_VIEW +
            " WHERE " + COLUMN_SONG_TITLE + " LIKE " + "'%";
    private static final String QUERY_VIEW_SONG_INFO_END = "%' ORDER BY " + COLUMN_ARTIST_NAME + ", " +
            COLUMN_SONG_ALBUM + ", " +
            COLUMN_SONG_TRACK  +
            " COLLATE NOCASE ASC";

    public static final int VIEW_INDEX_ARTIST_NAME = 1;
    public static final int VIEW_INDEX_ALBUM_NAME = 2;
    public static final int VIEW_INDEX_SONG_TRACK = 3;
    public static final int VIEW_INDEX_SONG_TITLE = 4;

    public List<SongArtist> querySongsInfoView(String title) {
        StringBuilder sv = new StringBuilder(QUERY_VIEW_SONG_INFO_START);
        sv.append(title);
        sv.append(QUERY_VIEW_SONG_INFO_END);
        System.out.println(sv.toString());
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sv.toString())) {
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
