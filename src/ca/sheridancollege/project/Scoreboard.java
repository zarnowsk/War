package ca.sheridancollege.project;

/**
 * Class representing a scoreboard for the game. Used to keep track of 
 * user's scores if multiple games are played.
 * @author Vinayak Pavate
 */
public class Scoreboard {

    private String userName;
    private String opponentName;
    private int userScore;
    private int opponentScore;

    /**
     * Default constructor
     */
    public Scoreboard() {
        userName = "";
        opponentName = "";
        userScore = 0;
        opponentScore = 0;
    }

    //Getters and setters for the class
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

    /**
     * Method used to increment player's score by 1
     */
    public void incrementUserScore() {
        userScore++;
    }
    
    /**
     * Method used to increment machine's score by 1
     */
    public void incrementOpponentScore() {
        opponentScore++;
    }
    
    @Override
    public String toString(){
         String format = "%s's score: %d \n%s's score: %d";
        return String.format(format, userName,userScore,opponentName,opponentScore);
        
    }

}
