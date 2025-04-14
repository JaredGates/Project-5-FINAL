import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This main class houses the majority of the code as it runs through the game loop until a player has lost.
 * The class will take in a cvs file and generate two random pokemon from that list to put into the game.
 * Then it will ask the player if they would like to start a game, doing so will put them into the main game
 * loop. The players will go back and forth until one pokemon faints.
 */

public class MainGame {
    public static void main(String[] args) throws FileNotFoundException {
        importCSVFile();
    }

    /**
     * This method will go and import the entirety of the cvs file into the program via a hashmap.
     */
    public static void importCSVFile() throws FileNotFoundException {
        String filePath = "COMP 220 Final Project Excel.csv";
        File file = new File(filePath);

        Scanner scan = new Scanner(file);

        // Skip the header
        if (scan.hasNextLine()){
            scan.nextLine();
        }

        while (scan.hasNextLine()){
            String line = scan.nextLine();
            String[] values = line.split(",");

            String dexNum = values[0];
            String name = values[1];
            String type1 = values[2];
            String type2 = values[3];
            String hp = values[4];
            String atk = values[5];
            String def = values[6];
            String SpAtk = values[7];
            String SpDef = values[8];
            String spd = values[9];

            System.out.println("Pokedex Number: " + dexNum);
            System.out.println("Name: " + name);
            System.out.println("Primary type: " + type1);

            if (type2 == null || type2.trim().isEmpty()){
                System.out.println("No Secondary Typing");
            }
            else{
                System.out.println("Secondary Typing: " + type2);
            }

            System.out.println("HP: " + hp);
            System.out.println("Attack: " + atk);
            System.out.println("Defence: " + def);
            System.out.println("Special Attack: " + SpAtk);
            System.out.println("Special Defence: " + SpDef);
            System.out.println("Speed: " + spd);
            System.out.println();


        }
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
