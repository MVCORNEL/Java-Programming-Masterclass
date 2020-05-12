import java.util.ArrayList;
import java.util.Collections;

//Usage of generics
//--> earlier bug is spotted into the code
//-->more at TEAM CLASS
public class Main {

    public static void main(String[] args) {
        //1 PLAYERS
        FootballPlayer joe = new FootballPlayer("Joe");
        FootballPlayer mihai = new FootballPlayer("Mihai");
        BaseballPlayer pat = new BaseballPlayer("Pat");
        SoccerPlayer beckham = new SoccerPlayer("Beckham");
        //2 TEAMS
        //2.1 FOOTBALL
        //because the Team class is generic the error will be spotted at the compile time
        Team<FootballPlayer> footballTeam = new Team<>("Adelaide Crows");
        footballTeam.addPlayer(joe);
        Team<FootballPlayer> footballTeam2 = new Team<>("Big Wales");
        footballTeam.addPlayer(mihai);
        Team<FootballPlayer> footballTeam3 = new Team<>("Big Wales");
        footballTeam.addPlayer(mihai);
        //2.1.1MATCH!
        footballTeam.matchResult(footballTeam2, 5, 2);
        //2.1.1MATCH!
        footballTeam.matchResult(footballTeam3, 2, 3);

        //adelaideCrows.addPlayer(pat);
        //2.2 BASEBALL
        Team<BaseballPlayer> baseballTeam = new Team<>("Chicago Cubs");
        baseballTeam.addPlayer(pat);

        //That won't work because the Team parameter is generic -> look in the Team class
        //baseballTeam.matchResult(footballTeam,2,2);


        //SORTING USING COMPARABLE -> COMPARABLE IS IMPLEMENTED USING GENERICS
        //MORE IN TEAM CLASS
        ArrayList<Team> footballTeamsList = new ArrayList<>();
        footballTeamsList.add(footballTeam);
        footballTeamsList.add(footballTeam2);
        footballTeamsList.add(footballTeam3);
        Collections.sort(footballTeamsList);
        for (Team team : footballTeamsList) {
            System.out.println(team.getName() + " : " + team.ranking());
        }


    }


}
