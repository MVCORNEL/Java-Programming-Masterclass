import java.util.List;

public class Main {
    //THE WAY WE BUILD OUR STRING MAKE THE DATABASE VULNERABLE TO HACKING ATTEMPTS
    //SQL INJECTION ATTACK
    public static void main(String[] args) {
        //************************************************* CONNECTION **************************************************//
        DataSource dataSource=new DataSource();
        dataSource.openConnection();
        //************************************************* SCANNER **************************************************//

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter a song title: ");
//        String title=scanner.nextLine();
        //*************************************************CREATE VIEW IF NOT EXISTS**************************************************//
//        System.out.println(dataSource.createViewForSongArtists());
        //**************************************************QUERY VIEW ***************************************************//

        String title="love";
        List<SongArtist> songs= dataSource.querySongsInfoView(title);
        System.out.println(dataSource.QUERY_VIEW_SONG_INFO_PREPARED_STATEMENT);
        if(songs.isEmpty()){
            System.out.println("No song with that name");
        }
        else{
            songs.forEach(song -> System.out.format("Artist: %30s, Album: %30s TrackId: %5d, Title %45s \n",song.getArtistName(),song.getAlbumName(),song.getTrack(),song.getSongTitle() ));

        }








        //************************************************* CLOSE CONNECTION **************************************************//
        dataSource.closeConnection();
    }
}



