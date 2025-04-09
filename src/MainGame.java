/**
 * This main class houses the majority of the code as it runs through the game loop until a player has lost.
 * The class will take in a cvs file and generate two random pokemon from that list to put into the game.
 * Then it will ask the player if they would like to start a game, doing so will put them into the main game
 * loop. The players will go back and forth until one pokemon faints.
 */

public class MainGame {
    public static void main(String[] args){

    }

    /**
     * This method will go and import the entirety of the cvs file into the program via a hashmap.
     */
    public static void importCSVFile(){
        //stub
    }

    /**
     * This method will check to see if a player has won the game, if they have not then it will return false,
     * otherwise return true.
     * @return boolean
     */
    public static boolean playerWon(){
        //stub
        return false;
    }

    /**
     * This method will house the main game loop and will run for each player's turn.
     */
    public static void gameLoop(){
        //stub
    }

    /**
     * This method will check the typing of the move that was used with the pokemon recieving the move
     * if the move is of a right typing then a certain multiplier will be put onto the damage done.
     * @return double that is the multiplier.
     */
    public static double typingCheck(){
        //stub
        return -1;
    }
}
