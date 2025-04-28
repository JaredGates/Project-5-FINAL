import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * This main class houses the majority of the code as it runs through the game loop until a player has lost.
 * The class will take in a cvs file and generate two random Pokémon from that list to put into the game.
 * Then it will ask the player if they would like to start a game, doing so will put them into the main game
 * loop. The players will go back and forth until one Pokémon faints.
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

//            int PokeRandomNum1=13;
//            int PokeRandomNum2=81;

        //Create the two Pokémon based on the numbers gotten from the random number
            Pokemon Poke1=database.get(PokeRandomNum1);
            Pokemon Poke2=database.get(PokeRandomNum2);

        //Output for Pokemon generation for player 1
            System.out.println("Player 1 has gotten the Pokémon: "+Poke1.getName()+" (DEX# " +PokeRandomNum1+")");

        //Output for types of player 1s Pokemon
            if(Poke1.getType2() == null){
                    System.out.println("Its type(s) are"+Poke1.getType1());
                } else {
                    System.out.println("Its type(s) are "+Poke1.getType1()+" "+Poke1.getType2());
                }

        //Output for Pokemon generation for player 2
            System.out.println("Player 2 has gotten the Pokémon: "+Poke2.getName()+" (DEX# " +PokeRandomNum2+")");

        //Output for types of player 2s Pokemon
            if(Poke2.getType2() == null){
                    System.out.println("Its type(s) are"+Poke2.getType1());
                } else {
                    System.out.println("Its type(s) are "+Poke2.getType1()+" "+Poke2.getType2());
                }

        //Call the game loop method and run the game
            Pokemon winningPlayer;
            winningPlayer=gameLoop(Poke1, Poke2);

        //Output for the player who won.
            System.out.println(winningPlayer.getName()+" has won!");
    }

    /**
     * This method will go through the CSV file provided (or potentially one made) and put all Pokémon from it into a hashmap
     * @return a hashmap with an integer key and Pokémon value
     * @throws FileNotFoundException if file not found
     */
    public static HashMap<Integer, Pokemon> importCSVFile() throws FileNotFoundException {
        //Scanner to read in the file
            Scanner fileScan = new Scanner(new File("COMP 220 Final Project Excel.csv"));

        //Read in the blank line at the beginning
            fileScan.nextLine();

        //HashMap to get all the Pokémon into a hashmap to be returned, keys are the Pokédex entries
            HashMap<Integer, Pokemon> newDatabaseEntry = new HashMap<>();


        //While loop to read in each line of the CSV file and get each of the Pokémon's stats
            while (fileScan.hasNext()) {

                //Create another scanner that reads each line using a delimiter of a comma due to the CSV format
                    String newLine=fileScan.nextLine();
                    Scanner lineScan = new Scanner(newLine);
                    lineScan.useDelimiter(",");

                //Reads in the first value which is its Pokédex entry, this will be the key
                    int pokeNum = parseInt(lineScan.next());
                    Pokemon newPoke;

                //Read in each of the stats of the Pokémon
                    String name = lineScan.next();
                    String type1 = lineScan.next();
                    String type2 = lineScan.next();
                    int healthStat = parseInt(lineScan.next());
                    int attackStat = parseInt(lineScan.next());
                    int defenceStat = parseInt(lineScan.next());
                    int sAttackStat = parseInt(lineScan.next());
                    int sDefenceStat = parseInt(lineScan.next());
                    int speedStat = parseInt(lineScan.next());

                //Moves for each Pokemon, prior versions had theses as read in line however
                //due to development changes each Pokemon will have the same moves.
                    String move1="Attack";
                    String move2="Special";
                    String move3="Status";
                    String move4="Protection";

                //Create a Pokémon object, depending on if type2 was null there will be a different object created
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
     * This method will check to see if a player has won the game, if they have not then it will return false,
     * otherwise return true.
     * @return boolean
     */
    public static boolean playerWon(Pokemon player1, Pokemon player2){

        //Decision statement to see if a player's Pokémon has gone below its health stat
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

        //Damage variable
            int damage = 0;
            int threshold=0;

        //Main loop for the game
            while(!playerWon(player1, player2)) {

                //Get the move player 1s Pokemon will use
                    String moveNameP1=playerChoice(player1, sc, "Player 1");

                //Get the move player 2s Pokemon will use
                    String moveNameP2=playerChoice(player2, sc, "Player 2");

                //Make the move described in the playerChoice method for player 1
                    Move move1=makeMove(moveNameP1, player1);

                //Make the move described in the playerChoice method for player 2
                    Move move2=makeMove(moveNameP2, player2);

                //Status effects do damage to each other
                    player1.calcStatusDamage();
                    player2.calcStatusDamage();

                //Empty print
                    System.out.println();

                //Decision statement to see which player goes first
                    if (player1.getSpeedStat() > player2.getSpeedStat() && !playerWon(player1, player2)) {

                        //Player 1 deals damage and causes status effects I.E. uses their move
                            moveChoice(player1, player2, move1);

                        //Checking to make sure the damage done did not kill the other player, otherwise a dead Pokémon would be able to do damage
                            if (!playerWon(player1, player2)) {

                                //Player 2 deals damage and causes status effects
                                moveChoice(player2, player1, move2);

                            }
                   } else if (player2.getSpeedStat() > player1.getSpeedStat() && !playerWon(player1, player2)) {

                        moveChoice(player2, player1, move2);

                        if (!playerWon(player1, player2)) {

                            moveChoice(player1, player2, move1);
                        }

                    } else {
                        //FAIL STATE INCASE A PLAYER CANNOT BE PICKED, PLAYER 1 WILL GO FIRST
                        moveChoice(player1, player2, move1);

                        //Checking to make sure the damage done did not kill the other player, otherwise a dead Pokémon would be able to do damage
                        if (!playerWon(player1, player2)) {

                            moveChoice(player2, player1, move2);

                        }
                    }

                    //Reset stats as to not have stacking effects of stats
                        player1.statusReset();
                        player2.statusReset();
            }

            if(player1.getCurrentHealth()<0){
                return player2;
            } else {
                return player1;
            }
    }

    public static String playerChoice(Pokemon player, Scanner sc, String playerName){
        System.out.println("\n" + player.getName() + "'s health is " + player.getCurrentHealth());
        System.out.println("What move does " + playerName + " want to use? ");
        System.out.println("Your moves are " + player.getMove1() + ", " + player.getMove2() + ", " + player.getMove3() + ", " + player.getMove4());
        System.out.print(playerName + ", make your move: ");
        String moveNameP1 = sc.nextLine();

        //Check for incorrect input
        while(!(moveNameP1.equalsIgnoreCase("attack")||moveNameP1.equalsIgnoreCase("special")||moveNameP1.equalsIgnoreCase("status")||moveNameP1.equalsIgnoreCase("Protection"))){
            System.out.println("ERROR: incorrect input retry, moves are:");
            System.out.println(player.getMove1() + ", " + player.getMove2() + ", " + player.getMove3() + ", " + player.getMove4());
            moveNameP1=sc.nextLine();
        }

        return moveNameP1;
    }

    public static Move makeMove(String moveName, Pokemon player){
        Random rn=new Random();

        Move move;

        if(moveName.equalsIgnoreCase("Attack")){
            move=new AttackMove(moveName,90,player.getType1(),100);
        } else if(moveName.equalsIgnoreCase("Special")){

            String status="none";
            int statusNum=rn.nextInt(5);

            if(statusNum==0){
                status="Poisoned";
            } else if(statusNum==1){
                status="Burned";
            } else if(statusNum==2){
                status="Asleep";
            } else if(statusNum==3){
                status="Frozen";
            } else {
                status="Paralyzed";
            }

            move=new SpecialMove(moveName,70,player.getType1(),status,25, 70);

        } else if(moveName.equalsIgnoreCase("Status")){
            String status="none";
            int statusNum=rn.nextInt(5);

            if(statusNum==0){
                status="Poisoned";
            } else if(statusNum==1){
                status="Burned";
            } else if(statusNum==2){
                status="Asleep";
            } else if(statusNum==3){
                status="Frozen";
            } else {
                status="Paralyzed";
            }

            move=new StatusMove(moveName, 80, player.getType1(), status, 75);
        } else {
            move=new ProtectionMove(moveName, 100, player.getType1());
        }

        return move;
    }

    public static void moveChoice(Pokemon player1, Pokemon player2, Move move) throws FileNotFoundException {
        if(move.getTypeOfMove().equalsIgnoreCase("Attack")) {
            makeAttack(player1, player2, (AttackMove)move);
        }else if(move.getTypeOfMove().equalsIgnoreCase("Special")){
            makeSpecial(player1, player2, (SpecialMove)move);
        }else if(move.getTypeOfMove().equalsIgnoreCase("Status")){
            makeStatus(player1, player2, (StatusMove)move);
        }else if(move.getTypeOfMove().equalsIgnoreCase("Protection")){
            makeProtection(player1);
        }
    }

    public static void makeAttack(Pokemon player1, Pokemon player2, AttackMove move) throws FileNotFoundException {
        Random rn=new Random();

        int damage=0;
        int threshold = rn.nextInt(100);

        System.out.println("\t"+ player1.getName()+" used "+move.getNameOfMove());

        //Player 1 has gone first so player 2 is dealt damage by player 1 first
        if (threshold < move.getAccuracy()) {
            damage = (player1.calcDamage(move, player2));
            player2.dealDamage(damage);

            //Print statement to recognise that a player has taken damage
            System.out.println("\t" + player2.getName() + " took " + damage + " damage\n");
        } else {
            System.out.println("\tThe move doesn't work...\n");
        }
    }

    public static void makeSpecial(Pokemon player1, Pokemon player2, SpecialMove move) throws FileNotFoundException {
        Random rn=new Random();

        int damage=0;
        int threshold = rn.nextInt(100);

        System.out.println("\t"+ player1.getName()+" used "+move.getNameOfMove());

        //Player 1 has gone first so player 2 is dealt damage by player 1 first
        if (threshold < move.getAccuracy()) {
            damage = (player1.calcDamage(move, player2));
            player2.dealDamage(damage);

            //Print statement to recognise that a player has taken damage
            System.out.println("\t" + player2.getName() + " took " + damage + " damage");


            threshold=rn.nextInt(100);

            if(threshold<move.getStatusChance()){
                player2.setStatus(move.getStatus());
                System.out.println("\t"+player2.getName()+" has been "+move.getStatus()+"\n");
            } else {
                System.out.println("\tNo status effect dealt...\n");
            }

        } else {
            System.out.println("\tThe move doesn't work...");
        }


    }

    public static void makeStatus(Pokemon player1, Pokemon player2, StatusMove move){
        Random rn=new Random();

        int threshold=rn.nextInt(100);

        System.out.println("\t"+ player1.getName()+" used "+move.getNameOfMove());
        if(threshold<move.getStatusChance()){
            player2.setStatus(move.getStatus());
            System.out.println("\t"+player2.getName()+" has been "+move.getStatus()+"\n");
        } else {
            System.out.println("\tNo status effect dealt...\n");
        }
    }

    public static void makeProtection(Pokemon player1){
        System.out.println("\t"+ player1.getName()+" used Protection");
        player1.setCurrentDefence(500);
        player1.setCurrentSpecialDefence(500);
        player1.setCurrentSpeed(500);
    }
}
