package ca.sheridancollege.project;

/**
 *
 * @author acer
 */
public class Scoreboard {

    private final String userName;
    private final String opponentName;
    public int userScore;
    public int opponentScore;

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

    public void setUserScore(int score) {
        userScore = score;
    }
    
    public void setOpponentScore(int score) {
        opponentScore = score;
    }
    
    @Override
    public String toString(){
         String format = "%s's score: %d \n %s's score: %d";
        return String.format(format, userName,userScore,opponentName,opponentScore);
        
    }

}
