import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * This main class houses the majority of the code as it runs through the game loop until a player has lost.
 * The class will take in a cvs file and generate two random Pokémon from that list to put into the game.
 * Then it will ask the player if they would like to start a game, doing so will put them into the main game
 * loop. The players will go back and forth until one Pokémon faints.
 */

public class MainGame {
    public static void main(String[] args) throws FileNotFoundException {

        try {
            //Get data into the hashmap
            HashMap<Integer, Pokemon> database;
            database = importCSVFile();

            // Scanner to get input
            Scanner scanner = new Scanner(System.in);

            //Random number generator to generate random Pokemon between bounds
            Random rn = new Random();

            // Get a list of all available keys in database
            List<Integer> allKeys = new ArrayList<>(database.keySet());

            // Start of program dialogue
            System.out.println("Welcome to the Pokemon 1v1 Battle Simulator");
            System.out.println("Please select one of the following options to begin: ");

        // Get player 1's pokemon
        int PokeNum1 = getPokemon(1, scanner, rn, allKeys);

        // Get player 2's pokemon + check if they are the same
        int PokeNum2;
        while (true){
            PokeNum2 = getPokemon(2, scanner, rn, allKeys);
            if (PokeNum2 == PokeNum1){
                System.out.println("ERROR: Both players cannot have the same Pokemon. Player 2, please select a new one.\n");
            } else {
                break;
            }
        }


        // Create Pokemon objects
        Pokemon Poke1 = database.get(PokeNum1);
        Pokemon Poke2 = database.get(PokeNum2);

        //Output for Pokemon generation for player 1
            System.out.println("Player 1 has gotten the Pokémon: "+Poke1.getName()+" (DEX# " + PokeNum1 +")");

            //Output for types of player 1s Pokemon
            if (Poke1.getType2() == null) {
                System.out.println("Its type(s) are" + Poke1.getType1());
            } else {
                System.out.println("Its type(s) are " + Poke1.getType1() + " " + Poke1.getType2());
            }
            System.out.println();

        //Output for Pokemon generation for player 2
            System.out.println("Player 2 has gotten the Pokémon: "+Poke2.getName()+" (DEX# " + PokeNum2 +")");

            //Output for types of player 2s Pokemon
            if (Poke2.getType2() == null) {
                System.out.println("Its type(s) are" + Poke2.getType1());
            } else {
                System.out.println("Its type(s) are " + Poke2.getType1() + " " + Poke2.getType2());
            }
            System.out.println();

            //Call the game loop method and run the game
            Pokemon winningPlayer;
            winningPlayer = gameLoop(Poke1, Poke2);

            //Output for the player who won.
            System.out.println(winningPlayer.getName() + " has won!");
        } catch (FileNotFoundException e){
            System.out.println("ERROR: File was not found, ending program.");
        }
    }

    public static int getPokemon(int playerNum, Scanner scanner, Random rn, List<Integer> allKeys) throws FileNotFoundException {
        int selectedPokeNum = 0;
        while (true) {
            System.out.println("Player " + playerNum + ", do you want to: ");
            System.out.println("   1. Randomize your Pokemon");
            System.out.println("   2. Select your Pokemon");
            System.out.print("Your choice: ");
            String player1Input = scanner.next();
            System.out.println();

            if (player1Input.equals("1")){
                int randomIndex = rn.nextInt(allKeys.size());
                selectedPokeNum = allKeys.get(randomIndex);
                break;
            }
            else if (player1Input.equals("2")){
                selectedPokeNum = getPokemonNumber();
                break;
            }
            else{
                System.out.println("ERROR: Invalid input. Please enter either 1 or 2 for your selection.\n");
            }
        }
        return selectedPokeNum;
    }

    public static int getPokemonNumber() throws FileNotFoundException {
        // Scanner to get user input
        Scanner pokeScan = new Scanner(System.in);

        //Get data into the hashmap
        HashMap<Integer, Pokemon> database;
        database=importCSVFile();

        // Variable for pokedex number to be returned to the main method
        int dexNum = 0;

        // Get user input for Pokemon selection
        while (true){
            System.out.println("Select one of the following options: ");
            System.out.println("   1. I know my Pokemon's Pokedex number");
            System.out.println("   2. I don't know my Pokemon's Pokedex number");
            String userChoice = pokeScan.next();

            if (userChoice.equals("1")){
                while (true){
                    System.out.print("Enter Pokemon's Pokedex number: ");
                    if (pokeScan.hasNextInt()){
                        dexNum = pokeScan.nextInt();
                        if (database.containsKey(dexNum)){
                            return dexNum;
                        } else{
                            System.out.println("ERROR: Invalid Pokemon.\n");
                        }
                    } else {
                        System.out.println("ERROR: Invalid input. Please enter a valid integer.\n");
                        pokeScan.next();
                    }
                }
            } else if (userChoice.equals("2")){
                System.out.println("Valid Pokedex:");
                for (Map.Entry<Integer, Pokemon> entry: database.entrySet()){
                    System.out.println(entry.getKey() + ": " + entry.getValue().getName());
                }

                while (true) {
                    System.out.print("Enter Pokemon's Pokedex number: ");
                    if (pokeScan.hasNextInt()){
                        dexNum = pokeScan.nextInt();
                        if (database.containsKey(dexNum)){
                            return dexNum;
                        } else {
                            System.out.println("ERROR: Invalid Pokemon.");
                        }
                    } else {
                        System.out.println("ERROR: Invalid input. Please enter a valid integer.\n");
                        pokeScan.next();
                    }
                }
            } else{
                System.out.println("ERROR: Invalid choice. Please enter either 1 or 2 to proceed.\n");
            }
        }
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

                //Effect speed if a player chooses the Protection move
                    if(move1.getTypeOfMove().equalsIgnoreCase("Protection")){
                        player1.setCurrentSpeed(500);
                    }

                    if(move2.getTypeOfMove().equalsIgnoreCase("Protection")){
                        player2.setCurrentSpeed(500);
                    }


                //Decision statement to see which player goes first
                    if (player1.getSpeedStat() > player2.getSpeedStat() && !playerWon(player1, player2)) {

                        //Player 1 deals damage and causes status effects I.E. uses their move
                            moveChoice(player1, player2, move1);

                        //Checking to make sure the damage done did not kill the other player, otherwise a dead Pokémon would be able to do damage
                            if (!playerWon(player1, player2)) {

                                //Player 2 deals damage and causes status effects I.E. uses their move
                                moveChoice(player2, player1, move2);

                            }
                   } else if (player2.getSpeedStat() > player1.getSpeedStat() && !playerWon(player1, player2)) {

                        //Player 2 deals damage and causes status effects I.E. uses their move
                            moveChoice(player2, player1, move2);

                        //Checking to make sure the damage done did not kill the other player, otherwise a dead Pokémon would be able to do damage
                            if (!playerWon(player1, player2)) {

                                //Player 1 deals damage and causes status effects I.E. uses their move
                                    moveChoice(player1, player2, move1);
                            }

                    } else {
                        //FAIL STATE INCASE A PLAYER CANNOT BE PICKED (I.E. both Pokemon have the same speed, PLAYER 1 WILL GO FIRST

                        //Player 1 deals damage and causes status effects I.E. uses their move
                            moveChoice(player1, player2, move1);

                        //Checking to make sure the damage done did not kill the other player, otherwise a dead Pokémon would be able to do damage
                            if (!playerWon(player1, player2)) {

                                //Player 2 deals damage and causes status effects I.E. uses their move
                                    moveChoice(player2, player1, move2);
                            }
                    }

                    //Reset stats as to not have stacking effects of stats
                        player1.statusReset();
                        player2.statusReset();
            }

            //Returns the won player at the end of the game
                if(player1.getCurrentHealth()<0){
                    return player2;
                } else {
                    return player1;
                }
    }

    /**
     * This method will take in user input and then check to make sure that the input is correct with in the correct
     * options displayed to the user.
     * @param player, Pokemon the player is using, used to get the name of the Pokemon and moves of Pokemon
     * @param sc, Scanner
     * @param playerName, Name of the player, either player 1 or player 2
     * @return a String of the move name after it has been checked.
     */
    public static String playerChoice(Pokemon player, Scanner sc, String playerName){

        //Output for user information such as health and moves available
            System.out.println("\n" + player.getName() + "'s health is " + player.getCurrentHealth());
            System.out.println("What move does " + playerName + " want to use? ");
            System.out.println("Your moves are " + player.getMove1() + ", " + player.getMove2() + ", " + player.getMove3() + ", " + player.getMove4());
            System.out.print(playerName + ", make your move: ");

        //User Input for attack name.
            String moveNameP1 = sc.nextLine();

        //Check for incorrect input
            while(!(moveNameP1.equalsIgnoreCase("attack")||moveNameP1.equalsIgnoreCase("special")||moveNameP1.equalsIgnoreCase("status")||moveNameP1.equalsIgnoreCase("Protection"))){

                //Retry statement
                    System.out.println("ERROR: incorrect input retry, moves are:");
                    System.out.println(player.getMove1() + ", " + player.getMove2() + ", " + player.getMove3() + ", " + player.getMove4());

                //User Input for attack name.
                    moveNameP1=sc.nextLine();
            }

        //Return
            return moveNameP1;
    }

    /**
     * This method will make the move with the given information, using casting to change the Move object given
     * to a specific child class
     * @param moveName, name of the move given, can be obtained from player choice
     * @param player, Pokemon that is going to be using the move
     * @return a Move which is specifically done for each type of move
     */
    public static Move makeMove(String moveName, Pokemon player){

        //Random
            Random rn=new Random();

        //Default constructor for Move
            Move move;

        //Decision statement to determine what type of "move" the variable move will be.
            if(moveName.equalsIgnoreCase("Attack")){

                //Make a new Move object with teh AttackMove type
                    move=new AttackMove(moveName,90,player.getType1(),100);

            } else if(moveName.equalsIgnoreCase("Special")){

                //String for the status of the SpecialMove
                    String status="none";

                //Generates a random int from 0 to 4
                    int statusNum=rn.nextInt(5);

                //Decision statement based on the randomly generated number from before, then changes status
                //to the value determined below
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

                //Make a new Move object with the SpecialMove type
                    move=new SpecialMove(moveName,70,player.getType1(),status,25, 70);

            } else if(moveName.equalsIgnoreCase("Status")){

                //String for the status of the StatusMove
                    String status="none";

                //Generates a random int from 0 to 4
                    int statusNum=rn.nextInt(5);

                //Decision statement based on the randomly generated number from before, then changes status
                //to the value determined below
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

                //Make a new Move object with the StatusMove type
                    move=new StatusMove(moveName, 90, player.getType1(), status, 75);

            } else {

                //Make a new Move object with the ProtectionMove type
                    move=new ProtectionMove(moveName, 100, player.getType1());
            }

        //Return
            return move;
    }

    /**
     * This method will take the two players and deal damage or status effects to the other using the move given
     * Note: each of the attacks are separated into their own method for easier readability and for casting of the Move object
     * @param player1, Player making the move
     * @param player2, Player being dealt the move
     * @param move, move used by player 1
     * @throws FileNotFoundException
     */
    public static void moveChoice(Pokemon player1, Pokemon player2, Move move) throws FileNotFoundException {

        //Decision statement to get the specific type fo move and specific method to go along with it
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

    /**
     * Make an Attack move using the two players and the move given.
     * @param player1, Player making the AttackMove
     * @param player2, Player receiving damage
     * @param move, move Player 1 is using
     * @throws FileNotFoundException
     */
    public static void makeAttack(Pokemon player1, Pokemon player2, AttackMove move) throws FileNotFoundException {
        //Random
            Random rn=new Random();

        //Variables to hold damage and threshold value which is used for random chance.
            int damage=0;
            int threshold = rn.nextInt(100);

        //Print out the name of the move used
            System.out.println("\t"+ player1.getName()+" used "+move.getNameOfMove());

        //Player 1 will deal damage to Player 2 (NOTE: Player 1 and Player 2 are not the actual players but the order
        //they were put into the method
            if (threshold < move.getAccuracy()) {

                //Calculate damage
                    damage = (player1.calcDamage(move, player2));

                //Deal damage
                    player2.dealDamage(damage);

                //Print statement to recognise that a player has taken damage
                    System.out.println("\t" + player2.getName() + " took " + damage + " damage\n");

            } else {
                //Print statement to recognise that the move did not work
                    System.out.println("\tThe move doesn't work...\n");
            }
    }

    /**
     * Make a Special move using the two players and the move given, deals less damage but can have a chance to deal a
     * status effect.
     * @param player1, Player making the SpecialMove
     * @param player2, Player receiving damage
     * @param move, move Player 1 is using
     * @throws FileNotFoundException
     */
    public static void makeSpecial(Pokemon player1, Pokemon player2, SpecialMove move) throws FileNotFoundException {
        //Random
            Random rn=new Random();

        //Variables to hold damage and threshold value which is used for random chance.
            int damage=0;
            int threshold = rn.nextInt(100);

        //Print out the name of the move used
            System.out.println("\t"+ player1.getName()+" used "+move.getNameOfMove());

        //Player 1 will deal damage to Player 2 (NOTE: Player 1 and Player 2 are not the actual players but the order
        //they were put into the method.)
            if (threshold < move.getAccuracy()) {

                //Calculate damage
                    damage = (player1.calcDamage(move, player2));

                //Deal damage
                    player2.dealDamage(damage);

                //Print statement to recognise that a player has taken damage
                    System.out.println("\t" + player2.getName() + " took " + damage + " damage\n");


                //Create a random number from 0 to 99 which is used for a random chance
                    threshold=rn.nextInt(100);

                //Decision statement to see if the status effect goes through
                    if(threshold<move.getStatusChance()){

                        //Put a status on Player 2
                            player2.setStatus(move.getStatus());

                        //Print statement to recognise that a player has been afflicted with a status effect
                            System.out.println("\t"+player2.getName()+" has been "+move.getStatus()+"\n");

                    } else {
                        //Print statement to recognise that a player has been afflicted with a status effect
                            System.out.println("\tNo status effect dealt...\n");
                    }

            } else {
                //Print statement to recognise that the move did not work
                    System.out.println("\tThe move doesn't work...\n");
            }


    }

    /**
     * Make a Status move using the two players and the move given, more likely to cause a status effect but deals
     * no damage.
     * @param player1, Player making the AttackMove
     * @param player2, Player receiving damage
     * @param move, move Player 1 is using
     * @throws FileNotFoundException
     */
    public static void makeStatus(Pokemon player1, Pokemon player2, StatusMove move){
        Random rn=new Random();

        //Create a random number from 0 to 99 which is used for a random chance
        int threshold=rn.nextInt(100);

        //Player 1 will deal damage to Player 2 (NOTE: Player 1 and Player 2 are not the actual players but the order
        //they were put into the method.)
            if (threshold < move.getAccuracy()) {

                //Decision statement to see if the status effect goes through
                if (threshold < move.getStatusChance()) {

                    //Put a status on Player 2
                        player2.setStatus(move.getStatus());

                    //Print statement to recognise that a player has been afflicted with a status effect
                        System.out.println("\t" + player2.getName() + " has been " + move.getStatus() + "\n");

                } else {
                    //Print statement to recognise that a player has been afflicted with a status effect
                        System.out.println("\tNo status effect dealt...\n");
                }
            } else {
                //Print statement to recognise that the move did not work
                    System.out.println("\tThe move doesn't work...\n");
            }
    }

    /**
     * Raises the player's defensive stats
     * @param player1, player that stats are being raised.
     */
    public static void makeProtection(Pokemon player1){
        //Print statement to recognise the move
            System.out.println("\t"+ player1.getName()+" used Protection");

        //Sets defence and special defence 500, this lowers damage from any move most likely to 2
            player1.setCurrentDefence(500);
            player1.setCurrentSpecialDefence(500);
    }
}
