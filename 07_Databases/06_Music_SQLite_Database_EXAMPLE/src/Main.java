import model.DataSource;
import model.SongArtist;

import java.util.List;

public class Main {
    //MOST IMPORTANT IS TO ADD THE JDBC DRIVER to the project
    //IMPORTANT-> INT THIS APPLICATION WE DON'T WANT THE METHODS OF OUR DataSource CLASS TO RETURN A RESULT SET, BECAUSE WE DON'T WANT CLASSES THAT USED THE MODEL PACKAGE TO HAVE TO
    //UNDERSTAND THE IMPLEMENTATION DETAILS OF THE MODEL. IF THERE WILL FOLLOWING CHANGES THE ONLY CHANGES WHICH WELL NEED TO DO WILL BE IN THE MODEL PACKAGE
    //SO INSTEAD OF RETURNING A RESULT SET WILL RETURN A LIST OF OBJECTS(ARTIST,ALBUMS,SONGS)
    //*************************************************CREATE CONNECTION AND STATEMENT **************************************************//
    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        if (!dataSource.openConnection()) {
            System.out.println("Couldn't open data source");
            return;
        }
        //*************************************************QUERY ONE **************************************************//
//        List<Artist> artists = dataSource.queryArtists(DataSource.ORDER_BY_DESC);
//        if(artists.isEmpty()){
//            System.out.println("No artists");
//        }
//        else {
//            for (Artist artist : artists) {
//                System.out.println(String.format(("ID:%06d + %-30s"), artist.getId(), artist.getName()));
//            }
//        }
        //*************************************************QUERY TWO **************************************************//
//        List<String> albums = dataSource.queryAlbumsForArtists("Pink Floyd", DataSource.ORDER_BY_ASC);
//        if (albums.isEmpty()){
//            System.out.println("No albums with the name Pink");
//        } else {
//            //nice way of prinit
//            albums.forEach(album -> System.out.println(album));
//        }
        //*************************************************QUERY THREE **************************************************//
//        List<SongArtist> songArtistList = dataSource.queryArtistsFromSong("Love", DataSource.ORDER_BY_DESC);
//        songArtistList.forEach(songArtist -> {
//            System.out.println(String.format(("Artist: %s - Album %s - TrackID %d"),songArtist.getArtistName(),songArtist.getAlbumName(),songArtist.getTrack()));
//        });

        //*************************************************QUERY FOUR METADATA **************************************************//
    //dataSource.querySongsMetadata();

        //*************************************************QUERY FUNCTION **************************************************//
//        dataSource.getCountAndMinimumId(DataSource.TABLE_SONGS);

        //*************************************************CREATE VIEW IF NOT EXISTS**************************************************//
        System.out.println(dataSource.createViewForSongArtists());
        //**************************************************QUERY THE PREVIOUS CREATED VIEW ***************************************************//
        List<SongArtist> songs= dataSource.querySongsInfoView("Love");
        if(songs.isEmpty()){
            System.out.println("No song with that name");
        }
        else{
            songs.forEach(song -> System.out.format("Artist: %30s, Album: %30s TrackId: %5d, Title %45s \n",song.getArtistName(),song.getAlbumName(),song.getTrack(),song.getSongTitle() ));
            }
//        //*************************************************CLOSE CONNECTION ********************************************//
        dataSource.closeConnection();
    }
}
