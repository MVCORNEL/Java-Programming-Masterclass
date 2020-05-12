import java.util.ArrayList;

//MODIFY THE CLASS DECLARATION
//TYPE PARAMETER
//The TYPE PARAMETER EXTEND PLAYER CLASS BECAUSE THEN from the T reference we can call the method without casting the objects as player
//We could use T but we need to be more specific... to assure that only Player of that type can be added to the class
//<T> TYPE PARAMETER
//<T extends ClassName> bounded type parameter
//<T extends ClassName & Interface & Interface> -> first is the class the following ones are the interfaces in this case
public class Team<T extends Player> implements Comparable<Team<T>> {
    //Comparable will take the generic type of Team<T> .< exactly ass the matchMaking method

    private String name;
    int played = 0;
    int won = 0;
    int lost = 0;
    int tied = 0;

    private ArrayList<T> members = new ArrayList<>();


    public Team(String name) {
        this.name = name;
    }

    //PLAYERS IT OF THE TYPE PARAMETER
    public boolean addPlayer(T player) {
        if (members.contains(player)) {
            System.out.println("Player already into the list");
        }
        {
            members.add(player);
            System.out.println(player.getName() + " picked for the team " + this.name);
            return true;
        }
    }

    public String getName() {
        return name;
    }

    public int numPlayer() {
        return this.members.size();
    }

    //TEAM WILL BE OF THIS GENERIC TYPE
    public void matchResult(Team<T> opponent, int ourScore, int theirScore) {
        if (ourScore > theirScore) {
            won++;
        } else if (ourScore == theirScore) {
            tied++;
        } else {
            lost++;
        }
        played++;
        //opponent is null because if will not be null this method will execute indefinitely
        if (opponent != null) {
            opponent.matchResult(null, theirScore, ourScore);

        }
    }

    public int ranking() {
        int rank = (won * 2) + tied;
        return rank;

    }

    //COMPARE TO
    @Override
    public int compareTo(Team<T> t) {
        //return a negative number if this  is less than the object
        //that why we implement it opposite we want it to be descendant
        //ascendant will be  -> i(this.ranking() < t.ranking()) -> the collection will be sorted ascendant
        if(this.ranking() > t.ranking()){
            return -1;
        }
        else if(this.ranking()==t.ranking()){
            return 0;
        }
        else{
            return 1;
        }
    }
}


