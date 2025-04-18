import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * This main class houses the majority of the code as it runs through the game loop until a player has lost.
 * The class will take in a cvs file and generate two random pokemon from that list to put into the game.
 * Then it will ask the player if they would like to start a game, doing so will put them into the main game
 * loop. The players will go back and forth until one pokemon faints.
 */

public class MainGame {
    public static void main(String[] args) throws FileNotFoundException {

        //Get data into the hashmap
            HashMap<Integer, Pokemon> database;
            database=importCSVFile();

        //Get two random numbers for the two Pokémon that will be used
            Random rn=new Random();
            int PokeRandomNum1=rn.nextInt(1, database.size()+1);
            int PokeRandomNum2=rn.nextInt(1, database.size()+1);

        //Create the two pokemon based on the numbers gotten from the random number
            Pokemon Poke1=database.get(PokeRandomNum1);
            Pokemon Poke2=database.get(PokeRandomNum2);

            System.out.println(Poke1.getName());
            System.out.println(Poke2.getName());

        //Call the game loop method and run the game
            gameLoop(Poke1, Poke2);
    }

    /**
     * This method will go and import the entirety of the cvs file into the program via a hashmap.
     */
    public static HashMap<Integer, Pokemon> importCSVFile() throws FileNotFoundException {
        Scanner fileScan = new Scanner(new File("COMP 220 Final Project Excel.csv"));
        fileScan.nextLine();

        HashMap<Integer, Pokemon> newDatabaseEntry = new HashMap<>();


        while (fileScan.hasNext()) {
            String newLine=fileScan.nextLine();
            Scanner lineScan = new Scanner(newLine);
            lineScan.useDelimiter(",");
            int pokeNum = parseInt(lineScan.next());
            Pokemon newPoke;

            String name = lineScan.next();
            String type1 = lineScan.next();
            String type2 = lineScan.next();
            int currentHealth = parseInt(lineScan.next());
            int healthStat = currentHealth;
            int attackStat = parseInt(lineScan.next());
            int defenceStat = parseInt(lineScan.next());
            int sAttackStat = parseInt(lineScan.next());
            int sDefenceStat = parseInt(lineScan.next());
            int speedStat = parseInt(lineScan.next());

            if (type2 == null) {
                newPoke = new Pokemon(name, type1, currentHealth, healthStat, attackStat, defenceStat, sAttackStat, sDefenceStat, speedStat);
            } else {
                newPoke = new Pokemon(name, type1, type2, currentHealth, healthStat, attackStat, defenceStat, sAttackStat, sDefenceStat, speedStat);
            }

            newDatabaseEntry.put(pokeNum, newPoke);
        }

        return newDatabaseEntry;
    }

    /**
     * This method will check to see if a player has won the game, if they have not then it will return false,
     * otherwise return true.
     * @return boolean
     */
    public static boolean playerWon(Pokemon player1, Pokemon player2){
        if(player1.getCurrentHealth()<player1.getHealthStat()){
            return true;
        } else if(player2.getCurrentHealth()<player2.getHealthStat()){
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method will house the main game loop and will run for each player's turn.
     */
    public static void gameLoop(Pokemon player1, Pokemon player2) throws FileNotFoundException {
        //Scanner for user input
            Scanner sc=new Scanner(System.in);


        //Main loop for the game
            while(!playerWon(player1, player2)){
                System.out.print("What move does player 1 want to use? ");
                String moveNameP1=sc.nextLine();

                //TODO: make a system for the moves based on the names.
                AttackMove move1=null;

                System.out.print("What move does player 2 want to use? ");
                String moveNameP2=sc.nextLine();

                //TODO: make a system for the moves based on the names.
                AttackMove move2=null;


                //Do damage to each other
                    if(player1.getSpeedStat()>player2.getSpeedStat()){
                        player2.setCurrentHealth(player2.calcDamage(move1, player1));


                        if(!playerWon(player1,player2)) {
                            player1.setCurrentHealth(player1.calcDamage(move2, player2));
                        }
                    } else {
                        player1.setCurrentHealth(player1.calcDamage(move2, player2));

                        if(!playerWon(player1,player2)) {
                            player2.setCurrentHealth(player2.calcDamage(move1, player1));
                        }
                    }


            }
    }

    public static int conditionDamage(Pokemon mon, StatusMove moveUsed) {
        if (!mon.getStatus().equals("none")) {
            return 1;
        } else {
            //See if the effect triggers
            Random rn = new Random();
            int trigger = rn.nextInt(100);

            //Decision statement to see if it triggers
            if (trigger > moveUsed.getStatusChance()) {
                mon.setStatus(moveUsed.getStatus());
                return 1;
            } else {
                return 0;
            }
        }
    }
}
