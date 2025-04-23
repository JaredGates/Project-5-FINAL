import java.io.File;
import java.io.FileNotFoundException;
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
            int PokeRandomNum1=126;//rn.nextInt(1, database.size()+1);
            int PokeRandomNum2=133;//rn.nextInt(1, database.size()+1);

        //Create the two pokemon based on the numbers gotten from the random number
            Pokemon Poke1=database.get(PokeRandomNum1);
            Pokemon Poke2=database.get(PokeRandomNum2);

            System.out.println("Player 1 has gotten the pokemon: "+Poke1.getName()+" (DEX# " +PokeRandomNum1+")");

            if(Poke1.getType2() == null){
                System.out.println("Its type(s) are"+Poke1.getType1());
            } else {
                System.out.println("Its type(s) are "+Poke1.getType1()+" "+Poke1.getType2());
            }


            System.out.println("Player 2 has gotten the pokemon: "+Poke2.getName()+" (DEX# " +PokeRandomNum2+")");

            if(Poke2.getType2() == null){
                System.out.println("Its type(s) are"+Poke2.getType1());
            } else {
                System.out.println("Its type(s) are "+Poke2.getType1()+" "+Poke2.getType2());
            }

        //Call the game loop method and run the game
            Pokemon winningPlayer;
            winningPlayer=gameLoop(Poke1, Poke2);

            System.out.println(winningPlayer.getName()+" has won!");
    }

    /**
     * This method will go through the CSV file provided (or potentially one made) and put all pokemon from it into a hashmap
     * @return a hashmap with a integer key and Pokemon value
     * @throws FileNotFoundException if file not found
     */
    public static HashMap<Integer, Pokemon> importCSVFile() throws FileNotFoundException {
        //Scanner to read in the file
            Scanner fileScan = new Scanner(new File("COMP 220 Final Project Excel.csv"));

        //Read in the blank line at the beginning
            fileScan.nextLine();

        //HashMap to get all the pokemon into a hashmap to be returned, keys are the pokedex entries
            HashMap<Integer, Pokemon> newDatabaseEntry = new HashMap<>();


        //While loop to read in each line of the CSV file and get each of the pokemon's stats
            while (fileScan.hasNext()) {

                //Create another scanner that reads each line using a delimiter of a comma due to the CSV format
                    String newLine=fileScan.nextLine();
                    Scanner lineScan = new Scanner(newLine);
                    lineScan.useDelimiter(",");

                //Reads in the first value which is its pokedex entry, this will be the key
                    int pokeNum = parseInt(lineScan.next());
                    Pokemon newPoke;

                //Read in each of the stats of the pokemon
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
//                    String move1=lineScan.next();
//                    String move2=lineScan.next();
//                    String move3=lineScan.next();
//                    String move4=lineScan.next();

                    String move1="Attack";
                    String move2="Special";
                    String move3="Status";
                    String move4="Protection";

                //Create a pokemon object, depending on if type2 was null there will be a different object created
                    if (type2 == null) {
                        newPoke = new Pokemon(name, type1, healthStat, attackStat, defenceStat, sAttackStat, sDefenceStat, speedStat, move1, move2, move3, move4);
                    } else {
                        newPoke = new Pokemon(name, type1, type2, healthStat, attackStat, defenceStat, sAttackStat, sDefenceStat, speedStat, move1, move2, move3, move4);
                    }

                //Create a new key and object value for the hashmap
                    newDatabaseEntry.put(pokeNum, newPoke);
            }

        //Return
            return newDatabaseEntry;
    }

    /**
     * This method will go through all the moves any pokemon can know
     * @return a hashmap containing all moves
     * @throws FileNotFoundException if file not found
     */
    public static HashMap<String, Move> importMoves() throws FileNotFoundException {
        //Scanner for file
            Scanner fileScan = new Scanner(new File("Something"));
            fileScan.nextLine();

        //Hashmap containing all moves used for pokemon in the dex
            HashMap<String, Move> newMovesEntry = new HashMap<>();

        //While loop to get all data from the moves file
            while (fileScan.hasNext()) {

                //Make a new scanner for each line in the file to scan individually with a comma delimiter
                    String newLine=fileScan.nextLine();
                    Scanner lineScan = new Scanner(newLine);
                    lineScan.useDelimiter(",");

                //Get the name of the move and the type the move is
                    String moveName=lineScan.next();
                    String typeOfMove=lineScan.next();

                //Empty Move object
                    Move tempMove;

                //Decision statement for each type of move making a different kind of Move object
                    if(typeOfMove.equalsIgnoreCase("Attack")) {
                        //Get data for an attack move
                            String type = lineScan.next();
                            double accuracy=lineScan.nextDouble();
                            int power = lineScan.nextInt();

                        //Make the Move object
                            tempMove=new AttackMove(moveName, accuracy, type, power);

                    } else if(typeOfMove.equalsIgnoreCase("Status")){
                        //Get data for a status move
                            String type = lineScan.next();
                            double accuracy=lineScan.nextDouble();
                            String status=lineScan.next();
                            double statusChance=lineScan.nextDouble();

                        //Create a new Move
                            tempMove=new StatusMove(moveName, accuracy, type, status, statusChance);

                    } else {
                        //Get data for a special
                            String type = lineScan.next();
                            double accuracy=lineScan.nextDouble();
                            int power = lineScan.nextInt();
                            String status=lineScan.next();
                            double statusChance=lineScan.nextDouble();

                        //Create a new Move
                            tempMove=new SpecialMove(moveName, accuracy, type, status, statusChance, power);
                    }

                //Add entry into the hashmap
                    newMovesEntry.put(moveName, tempMove);
            }

        //Return
            return newMovesEntry;
    }

    /**
     * This method will check to see if a player has won the game, if they have not then it will return false,
     * otherwise return true.
     * @return boolean
     */
    public static boolean playerWon(Pokemon player1, Pokemon player2){

        //Decision statement to see if a player's pokemon has gone below its health stat
            if(player1.getCurrentHealth()<0){
                return true;
            } else if(player2.getCurrentHealth()<0){
                return true;
            } else {
                return false;
            }
    }

    /**
     * This method will house the main game loop and will run for each player's turn.
     */
    public static Pokemon gameLoop(Pokemon player1, Pokemon player2) throws FileNotFoundException {
        //Scanner for user input
            Scanner sc=new Scanner(System.in);

        //Random
            Random rn=new Random();

        //Damage variable
            int damage = 0;
            int threshold=0;

        //Read in all the moves
//            HashMap<String, Move> movesList=importMoves();


        //Main loop for the game
            while(!playerWon(player1, player2)) {
                System.out.println("\n" + player1.getName() + "'s health is " + player1.getCurrentHealth());
                System.out.println("What move does player 1 want to use? ");
                System.out.println("Your moves are " + player1.getMove1() + ", " + player1.getMove2() + ", " + player1.getMove3() + ", " + player1.getMove4());
                String moveNameP1 = sc.nextLine();

                //Check for incorrect input
                    while(!(moveNameP1.equalsIgnoreCase("attack")||moveNameP1.equalsIgnoreCase("special")||moveNameP1.equalsIgnoreCase("status")||moveNameP1.equalsIgnoreCase("Protection"))){
                        System.out.println("ERROR: incorrect input retry, moves are:");
                        System.out.println(player1.getMove1() + ", " + player1.getMove2() + ", " + player1.getMove3() + ", " + player1.getMove4());
                        moveNameP1=sc.nextLine();
                    }

                System.out.println("\n" + player2.getName() + "'s health is " + player2.getCurrentHealth());
                System.out.println("What move does player 2 want to use? ");
                System.out.println("Your moves are " + player2.getMove1() + ", " + player2.getMove2() + ", " + player2.getMove3() + ", " + player2.getMove4());
                String moveNameP2 = sc.nextLine();

                //Check for incorrect input
                    while(!(moveNameP2.equalsIgnoreCase("attack")||moveNameP2.equalsIgnoreCase("special")||moveNameP2.equalsIgnoreCase("status")||moveNameP2.equalsIgnoreCase("Protection"))){
                        System.out.println("ERROR: incorrect input retry, moves are:");
                        System.out.println(player2.getMove1() + ", " + player2.getMove2() + ", " + player2.getMove3() + ", " + player2.getMove4());
                        moveNameP2=sc.nextLine();
                    }

                    Move move1=null;

                    if(moveNameP1.equalsIgnoreCase("Attack")){
                        move1=new AttackMove(moveNameP1,90,player1.getType1(),100);
                    } else if(moveNameP1.equalsIgnoreCase("Special")){

                        String status="none";
                        int statusNum=rn.nextInt(5);
                        if(statusNum==0){
                            status="Poisoned";
                        } else if(statusNum==1){
                            status="Burned";
                        } else if(statusNum==2){
                            status="Sleeped";
                        } else if(statusNum==3){
                            status="Freezed";
                        } else if(statusNum==4){
                            status="Paralysised";
                        }

                        move1=new SpecialMove(moveNameP1,70,player1.getType1(),status,25, 70);
                    } else if(moveNameP1.equalsIgnoreCase("Status")){
                        String status="none";
                        int statusNum=rn.nextInt(5);
                        if(statusNum==0){
                            status="Poisoned";
                        } else if(statusNum==1){
                            status="Burned";
                        } else if(statusNum==2){
                            status="Sleeped";
                        } else if(statusNum==3){
                            status="Freezed";
                        } else if(statusNum==4){
                            status="Paralysised";
                        }

                        move1=new StatusMove(moveNameP1, 80, player1.getType1(), status, 75);
                    }

                    Move move2=null;

                if(moveNameP2.equalsIgnoreCase("Attack")){
                    move2=new AttackMove(moveNameP2,90,player1.getType1(),100);
                } else if(moveNameP2.equalsIgnoreCase("Special")){

                    String status="none";
                    int statusNum=rn.nextInt(5);
                    if(statusNum==0){
                        status="Poisoned";
                    } else if(statusNum==1){
                        status="Burned";
                    } else if(statusNum==2){
                        status="Sleeped";
                    } else if(statusNum==3){
                        status="Freezed";
                    } else if(statusNum==4){
                        status="Paralysised";
                    }

                    move1=new SpecialMove(moveNameP1,70,player1.getType1(),status,25, 70);
                } else if(moveNameP1.equalsIgnoreCase("Status")){
                    String status="none";
                    int statusNum=rn.nextInt(5);
                    if(statusNum==0){
                        status="Poisoned";
                    } else if(statusNum==1){
                        status="Burned";
                    } else if(statusNum==2){
                        status="Sleeped";
                    } else if(statusNum==3){
                        status="Freezed";
                    } else if(statusNum==4){
                        status="Paralysised";
                    }

                    move1=new StatusMove(moveNameP1, 80, player1.getType1(), status, 75);
                }



                //Status effects do damage to each other
                player1.calcStatusDamage();
                player2.calcStatusDamage();

                //Empty print
                System.out.println();

                //Decision statement to see which player goes first
                if (player1.getSpeedStat() > player2.getSpeedStat() && !playerWon(player1, player2)) {

                    threshold = rn.nextInt(100);

                    //Player 1 has gone first so player 2 is dealt damage by player 1 first
                    if (threshold < move1.getAccuracy()) {
                        damage = (player2.calcDamage(move1, player1));
                        player2.dealDamage(damage);

                        //Print statement to recognise that a player has taken damage
                        System.out.println("\t" + player2.getName() + " took " + damage + " damage\n");
                    } else {
                        System.out.println("\tThe move doesn't work...\n");
                    }

                    //Checking to make sure the damage done did not kill the other player, otherwise a dead pokemon would be able to do damage
                    if (!playerWon(player1, player2)) {

                        threshold = rn.nextInt(100);

                        if (threshold < move2.getAccuracy()) {

                            //Player 1 now deals damage
                            damage = (player1.calcDamage(move2, player2));
                            player1.dealDamage(damage);

                            //Print statement to recognise that a player has taken damage
                            System.out.println("\t" + player1.getName() + " took " + damage + " damage\n");

                        } else {
                            System.out.println("\tThe move doesn't work...\n");
                        }

                    }
                } else if (player2.getSpeedStat() > player1.getSpeedStat() && !playerWon(player1, player2)) {

                    threshold=rn.nextInt(100);

                        if (threshold < move2.getAccuracy()) {

                            //Player 2 has gone first so player 1 is dealt damage by player 2 first
                                damage = (player1.calcDamage(move2, player2));
                                player1.dealDamage(damage);

                            //Print statement to recognise that a player has taken damage
                                System.out.println("\t" + player1.getName() + " took " + damage + " damage\n");
                        } else {
                                System.out.println("\tThe move doesn't work...\n");
                        }


                    if (!playerWon(player1, player2)) {

                        threshold=rn.nextInt(100);

                        if(threshold<move1.getAccuracy()) {
                            //Player 1 now deals damage
                                damage = (player2.calcDamage(move1, player1));
                                player2.dealDamage(damage);

                            //Print statement to recognise that a player has taken damage
                                System.out.println("\t" + player2.getName() + " took " + damage + " damage\n");
                        } else {
                            System.out.println("\tThe move doesn't work...\n");
                        }

                    }
                } else {
                    //FAIL STAT INCASE A PLAYER CANNOT BE PICKED, PLAYER 1 WILL GO FIRST
                    if (player1.getSpeedStat() > player2.getSpeedStat() && !playerWon(player1, player2)) {


                        threshold = rn.nextInt(100);

                        //Player 1 has gone first so player 2 is dealt damage by player 1 first
                        if (threshold < move1.getAccuracy()) {
                            damage = (player2.calcDamage(move1, player1));
                            player2.dealDamage(damage);

                            //Print statement to recognise that a player has taken damage
                            System.out.println("\t" + player2.getName() + " took " + damage + " damage\n");
                        } else {
                            System.out.println("\tThe move doesn't work...\n");
                        }

                        //Checking to make sure the damage done did not kill the other player, otherwise a dead pokemon would be able to do damage
                        if (!playerWon(player1, player2)) {

                            threshold = rn.nextInt(100);

                            if (threshold < move2.getAccuracy()) {

                                //Player 1 now deals damage
                                damage = (player1.calcDamage(move2, player2));
                                player1.dealDamage(damage);

                                //Print statement to recognise that a player has taken damage
                                System.out.println("\t" + player1.getName() + " took " + damage + " damage\n");

                            } else {
                                System.out.println("\tThe move doesn't work...\n");
                            }

                        }
                    }

                    //TODO: use conditionCheck method to make it so that the pokemon can have a status effect.

                    //Reset stats as to not have stacking effects of stats
                    player1.statusReset();
                    player2.statusReset();
                }
            }

            if(player1.getCurrentHealth()<0){
                return player2;
            } else {
                return player1;
            }
    }


    /**
     * This method will see if a pokemon will get effected my a status effect
     * @param mon pokemon potentially being effected
     * @param moveUsed move that was used to deal and effect
     */
    public static void conditionCheck(Pokemon mon, StatusMove moveUsed) {
        //Decision statement to see if the pokemon even needs to have an effect done to him
            if (!mon.getStatus().equals("none")) {
                mon.setStatus("none");
            } else {
                //See if the effect triggers
                    Random rn = new Random();
                    int trigger = rn.nextInt(100);

                //Decision statement to see if an effect it triggers
                    if (trigger > moveUsed.getStatusChance()) {
                        mon.setStatus(moveUsed.getStatus());
                    } else {
                        mon.setStatus("none");
                    }
            }
    }
}
