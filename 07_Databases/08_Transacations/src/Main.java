//A TRANSACTION IS A SEQUENCE OF SQL STATEMENTS THAT ARE TREATED AS A SINGLE LOGICAL UNIT. IF ANY OF THE STATEMENTS FAIL,THE RESULTS OF ANY PREVIOUS STATEMENTS IN THE TRANSACTION CAN BE ROLLED BACK,OR JUST NOT SAVED IT's AS THEY NEVER HAPPENED
//DATABASE TRANSACTIONS MUST BE ACID-compliant.THEY MUST meet the following characteristics:

//*************************************************** ACID-compliant*************************************************** //
//-> ATOMICITY. This relate to what we've discussed.If a series of SQL statements change the database,then either all the changes are committed, or none of then are
//-> CONSISTENCY. Before a transaction begins, the database is in a valid state.When it completes, the database is still in a valid state.
//-> ISOLATION. Until the changes committed by a transaction are completed, they won't be visible to other connections. Transactions can't depend on each other.
//-> DURABILITY Once the changes performed by a transaction are committed to the database, they are permanent.If an application then crashes or the database
//server goes down(in the case of a client/server database like MySQL), the changes made by transaction are still there when the application runs again, or the database comes back up
//We only have to use transactions when we change the data in a database. We don;t need them we're querying the database.

//*************************************************** TRANSACTIONS COMMANDS *************************************************** //
//->BEGIN TRANSACTION - we use this to manually start a transaction
//-> END TRANSACTION - use this to end a transaction. Committing changes automatically ends a transaction.Also, ending a transaction also commits any changes
//In other words, END TRANSACTION and COMMIT are aliases. We only have to use one when we want to end a transaction and commit the changes.
//-> COMMIT - we use this to commit the changes made by a transaction. AS mentioned, this ends a transaction so we don't have to also tun the END TRANSACTION command
//-> ROLLBACK - this rolls back any uncommitted changes and ends the transaction.Note that it can only rollback changes that have occurred since the last COMMIT or ROLLBACK

import Model.DataSource;

//*************************************************** STEPS FOR ADDING A SONG *************************************************** //
//1 GET THE title,album,track number, and artist(we'll just have the main() method pass them as parameters, rather than prompting for them
//2 Check to see if there's a record for the artist in the artists table.If it is,go to step 4.If not go to step 3.
//3 Add the artist to the artist' table
//4 CHeck to see if the album is in the albums table. if yes, go to step 5. Otherwise, do step 5
//5 Add the album to the albums table
//6 Add the song to the song table
public class Main {
    public static void main(String[] args) {
        //*************************************************** Open Connection ***************************************************//
        DataSource dataSource = new DataSource();
        System.out.println(dataSource.openConnection());
        System.out.println(DataSource.INSERT_INTO_ARTIST);
        System.out.println(DataSource.INSERT_INTO_ALBUMS);
        System.out.println(DataSource.INSERT_INTO_SONGS);
        //*************************************************** Insert Song ***************************************************//
        dataSource.insertSong(1,"COCO","COCO","COCO");
        //*************************************************** Close Connection ***************************************************//
        dataSource.closeConnection();
    }

}
