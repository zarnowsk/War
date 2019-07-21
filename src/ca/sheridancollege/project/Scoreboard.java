package ca.sheridancollege.project;

/**
 *
 * @author acer
 */
public class Scoreboard {

    private final String userName;
    private final String opponentName;
    private int userScore;
    private int opponentScore;

    public Scoreboard(String userName, String opponentName, int userScore, int opponentScore) {
        this.userName = userName;
        this.opponentName = opponentName;
        this.userScore = userScore;
        this.opponentScore = opponentScore;
    }

    public String getUserName() {
        return userName;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public int getUserScore() {
        return userScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }

    public void incrementUserScore() {
        userScore++;
    }
    
    public void incrementOpponentScore() {
        opponentScore++;
    }
    
    @Override
    public String toString(){
         String format = "%s's score: %d \n %s's score: %d";
        return String.format(format, userName,userScore,opponentName,opponentScore);
        
    }

}
