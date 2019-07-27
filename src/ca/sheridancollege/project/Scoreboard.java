package ca.sheridancollege.project;

/**
 *
 * @author acer
 */
public class Scoreboard {

    private String userName;
    private String opponentName;
    private int userScore;
    private int opponentScore;

    public Scoreboard() {
        userName = "";
        opponentName = "";
        userScore = 0;
        opponentScore = 0;
    }

    public String getUserName() {
        return userName;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
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
         String format = "%s's score: %d \n%s's score: %d";
        return String.format(format, userName,userScore,opponentName,opponentScore);
        
    }

}
