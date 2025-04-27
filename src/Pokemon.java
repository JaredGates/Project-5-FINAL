import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class contains the information for each Pokémon, which includes typing, health, stats and the calculation for
 * damage.
 *
 * Version 1.0
 * Author Jared Gates and Josh Fair
 */

public class Pokemon {
    //Name, typing and status
        private final String name;
        private ArrayList<String> types=new ArrayList<>();
        private final String type1;
        private final String type2;
        private String status="none";

    //Current stats that may and can change
        private int currentHealth;
        private int currentSpeed;
        private int currentAttack;
        private int currentSpecialAttack;
        private int currentDefence;
        private int currentSpecialDefence;

    //Stats of the Pokémon that will not change
        private final int healthStat;
        private final int attackStat;
        private final int defenceStat;
        private final int specialAttackStat;
        private final int specialDefenceStat;
        private final int speedStat;

    //Moves of the Pokémon
        private final String move1;
        private final String move2;
        private final String move3;
        private final String move4;

    /**
     * Constructor for the class with only one type
     */
    public Pokemon(String name, String type1, int healthStat, int attackStat, int defenceStat, int specialAttackStat, int specialDefenceStat, int speedStat, String move1, String move2, String move3, String move4){
        this.name=name;
        this.type1=type1;
        type2=null;
        this.currentHealth=healthStat;
        this.healthStat=healthStat;
        this.currentAttack=attackStat;
        this.attackStat=attackStat;
        this.currentDefence=defenceStat;
        this.defenceStat=defenceStat;
        this.currentSpecialAttack=specialAttackStat;
        this.specialAttackStat=specialAttackStat;
        this.currentSpecialDefence=specialDefenceStat;
        this.specialDefenceStat=specialDefenceStat;
        this.currentSpeed=speedStat;
        this.speedStat=speedStat;
        this.move1=move1;
        this.move2=move2;
        this.move3=move3;
        this.move4=move4;
    }

    /**
     * constructor for a Pokémon with 2 types
     */
    public Pokemon(String name, String type1, String type2, int healthStat, int attackStat, int defenceStat, int specialAttackStat, int specialDefenceStat, int speedStat, String move1, String move2, String move3, String move4){
        this.name=name;
        this.type1=type1;
        this.type2=type2;
        this.currentHealth=healthStat;
        this.healthStat=healthStat;
        this.currentAttack=attackStat;
        this.attackStat=attackStat;
        this.currentDefence=defenceStat;
        this.defenceStat=defenceStat;
        this.currentSpecialAttack=specialAttackStat;
        this.specialAttackStat=specialAttackStat;
        this.currentSpecialDefence=specialDefenceStat;
        this.specialDefenceStat=specialDefenceStat;
        this.currentSpeed=speedStat;
        this.speedStat=speedStat;
        this.move1=move1;
        this.move2=move2;
        this.move3=move3;
        this.move4=move4;
    }

    /**
     * Gets the name of the Pokémon
     * @return string of name
     */
    public String getName(){
        return name;
    }

    /**
     * returns the typing of the Pokémon
     * @param numTypes which indicates the potential typing of the Pokémon
     * @return a list that contains all the typing of the Pokémon
     */
    public ArrayList<String> getTypes(int numTypes){
        if(numTypes==1){
            types.add(getType1());
        } else {
            types.add(getType1());
            types.add(getType2());
        }

        return types;
    }

    /**
     * gets the first type of the Pokémon
     * @return the first type
     */
    public String getType1(){
        return type1;
    }

    /**
     * gets the second type of the Pokémon
     * @return the second type
     */
    public String getType2(){
        return type2;
    }

    /**
     * returns the status of the Pokémon
     * @return status of pokemon
     */
    public String getStatus(){
        return status;
    }

    /**
     * sets the status to the parameter given
     * @param s string containing the status
     */
    public void setStatus(String s){
        status=s;
    }

    /**
     * Returns the current health of the Pokémon
     * @return integer
     */
    public int getCurrentHealth(){
        return currentHealth;
    }

    /**
     * sets the current health to the parameter
     * @param other int
     */
    public void setCurrentHealth(int other){
        currentHealth=other;
    }

    /**
     * deals damage to the Pokémon by subtracting the current health from the parameter
     * @param damage int
     */
    public void dealDamage(int damage){
        currentHealth=currentHealth-damage;
    }

    /**
     * Returns the Health stat of the Pokémon which is also used as the max health of the Pokémon
     * @return integer
     */
    public int getHealthStat(){
        return healthStat;
    }

    /**
     * returns the current attack of the Pokémon
     * @return the current attack
     */
    public int getCurrentAttack(){
        return currentAttack;
    }

    /**
     * sets the current attack to the parameter
     * @param other int
     */
    public void setCurrentAttack(int other){
        currentAttack=other;
    }

    /**
     * Returns the Damage stat for the Pokémon
     * @return int
     */
    public int getAttackStat(){
        return attackStat;
    }

    public int getCurrentDefence(){
        return currentDefence;
    }

    public void setCurrentDefence(int newStat){
        currentDefence=newStat;
    }

    /**
     * Returns the Defence stat for the Pokémon
     * @return int
     */
    public int getDefenceStat(){
        return defenceStat;
    }

    /**
     * Returns the current special attack value
     * @return currentSpecialAttack
     */
    public int getCurrentSpecialAttack(){
        return currentSpecialAttack;
    }

    /**
     * sets the special attack the parameter
     * @param other int
     */
    public void setCurrentSpecialAttack(int other){
        currentSpecialAttack=other;
    }

    /**
     * Returns the Special Damage stat for the Pokémon
     * @return int
     */
    public int getSAttackStat(){
        return specialAttackStat;
    }

    public int getCurrentSpecialDefence(){
        return currentSpecialDefence;
    }

    public void setCurrentSpecialDefence(int newStat){
        currentSpecialDefence=newStat;
    }

    /**
     * Returns the Special Defence stat for the Pokémon
     * @return int
     */
    public int getSDefenceStat(){
        return specialDefenceStat;
    }

    /**
     * Returns the current speed
     * @return the current speed
     */
    public int getCurrentSpeed(){
        return currentSpeed;
    }

    /**
     * sets the current speed to the parameter
     * @param other int
     */
    public void setCurrentSpeed(int other){
        currentSpeed=other;
    }

    /**
     * Returns the Speed stat for the Pokémon
     * @return int
     */
    public int getSpeedStat(){
        return speedStat;
    }

    /**
     * Returns move 1
     * @return string
     */
    public String getMove1(){
        return move1;
    }

    /**
     * Returns move 2
     * @return string
     */
    public String getMove2(){
        return move2;
    }

    /**
     * Returns move 3
     * @return string
     */
    public String getMove3(){
        return move3;
    }

    /**
     * Returns move 4
     * @return string
     */
    public String getMove4(){
        return move4;
    }

    /**
     * Method to calculate damage using the generation one formula for damage calculation.
     */
    public int calcDamage(Move moveUsed, Pokemon other) throws FileNotFoundException {
        //Decision statement for each move type
            if (moveUsed.getTypeOfMove().equalsIgnoreCase("Attack")) {
                //Make the move
                    AttackMove damageMove=(AttackMove)moveUsed;

                //Variables that control damage modifiers
                    int damage;
                    double STAB;
                    int crit;
                    boolean critMade = false;
                    boolean sameType = false;
                    double type1Effect;
                    double type2Effect;

                //Get the type effects
                    type1Effect = getTypeEffect(damageMove.getType(), other.getType1());

                    if(type2!=null){
                        type2Effect = getTypeEffect(damageMove.getType(), other.getType2());
                    } else {
                        type2Effect=1;
                    }

                //Random
                    Random rn = new Random();

                //Calculation for STAB
                        if (damageMove.getType().equalsIgnoreCase(type1)) {
                            STAB = 1.5;
                            sameType = true;
                        } else {
                            STAB = 1.0;
                        }

                        if(type2!=null) {
                            if (damageMove.getType().equalsIgnoreCase(type2) && !sameType) {
                                STAB = 1.5;
                                sameType = true;
                            } else {
                                STAB = 1.0;
                            }
                        }


                //Calculation for crit
                    int threshold = getSpeedStat() / 2;

                    if (rn.nextInt(255) < threshold) {
                        critMade = true;
                        crit = 2;
                    } else {
                        crit = 1;
                    }

                //In depth calculations for damage
                    damage=2;
                    damage=damage*100;
                    damage=damage*crit;
                    damage=damage/5;
                    damage=damage+2;
                    damage=damage* ((AttackMove) moveUsed).getPower();
                    damage= (int) (damage*((double)this.getCurrentAttack()/(double)other.getCurrentDefence()));
                    damage=damage/50;
                    damage=damage+2;
                    damage= (int) (damage*STAB);
                    damage= (int) (damage*type1Effect*type2Effect);

                //Recognition for a crit
                    if (critMade) {
                        System.out.println("\tYou got a crit!");
                    }

                //Recognition for super effective attacks
                    if(type1Effect*type2Effect>2){
                        System.out.println("\n\tThis move was super effective!");
                    } else if(type1Effect*type2Effect<1&&type1Effect*type2Effect>0){
                        System.out.println("\n\tThis move was ineffective");
                    } else if(type1Effect*type2Effect==0){
                        System.out.println("\n\tThis move has no effect...");
                    }

                return damage;

            } else if (moveUsed.getTypeOfMove().equalsIgnoreCase("Special")) {
                SpecialMove damageMove=(SpecialMove)moveUsed;

                //Variables that control damage modifiers
                int damage;
                double STAB;
                int crit;
                boolean critMade = false;
                boolean sameType = false;
                double type1Effect;
                double type2Effect;

                //Get the type effects
                type1Effect = getTypeEffect(damageMove.getType(), other.getType1());

                if(type2!=null){
                    type2Effect = getTypeEffect(damageMove.getType(), other.getType2());
                } else {
                    type2Effect=1;
                }

                //Random
                Random rn = new Random();

                //Calculation for STAB
                if (damageMove.getType().equalsIgnoreCase(type1)) {
                    STAB = 1.5;
                    sameType = true;
                } else {
                    STAB = 1.0;
                }

                if(type2!=null) {
                    if (damageMove.getType().equalsIgnoreCase(type2) && !sameType) {
                        STAB = 1.5;
                        sameType = true;
                    } else {
                        STAB = 1.0;
                    }
                }


                //Calculation for crit
                int threshold = getSpeedStat() / 2;

                if (rn.nextInt(255) < threshold) {
                    critMade = true;
                    crit = 2;
                } else {
                    crit = 1;
                }

                //In depth calculations for damage
                double inside1=(((2.0 * 100 * crit) / 5 + 2))*(damageMove.getPower() * ((double) this.getCurrentSpecialAttack() / other.getCurrentSpecialDefence())) /50+2;
                double outside=STAB * type1Effect * type2Effect;

                //Calculation for damage
                damage=(int)(inside1*outside);

                //Recognition for a crit
                if (critMade) {
                    System.out.println("\tYou got a crit!");
                }

                //Recognition for super effective attacks
                if(type1Effect*type2Effect>2){
                    System.out.println("\n\tThis move was super effective!");
                } else if(type1Effect*type2Effect<1){
                    System.out.println("\n\tThis move was ineffective");
                } else if(type1Effect*type2Effect==0){
                    System.out.println("\n\tThis move has no effect...");
                }

                return damage;
            }

        //Return
            return 0;
    }

    /**
     * Method that calculates the damage and effects that status may do
     */
    public void calcStatusDamage(){
        // Adjusted calcStatusDamage() to be a switch statement for better presentation
        // and easier access.
        switch (status){
            case "Poisoned" -> {
                dealDamage(healthStat / 8);
                System.out.println("\t"+getName()+" has taken "+healthStat/8+" poison damage");
            }
            case "Burned" -> {
                dealDamage(healthStat / 16);
                setCurrentAttack(attackStat / 2);
                System.out.println("\t"+getName()+" has taken "+healthStat/8+" burn damage");
            }
            case "Frozen" -> {
                setCurrentAttack(0);
                setCurrentSpecialAttack(0);
                System.out.println("\t"+getName()+" is frozen");
            }
            case "Paralyzed" -> {
                setCurrentSpeed(speedStat / 4);
                if (new Random().nextInt(100) > 75){
                    setCurrentAttack(0);
                    setCurrentSpecialAttack(0);
                    System.out.println("\t"+getName()+" is paralyzed");

                }
            }
            case "Asleep" -> {
                    setCurrentAttack(0);
                    setCurrentSpecialAttack(0);
                if (new Random().nextInt(100) > 75){
                    setCurrentAttack(attackStat);
                    setCurrentSpecialAttack(specialAttackStat);
                    setStatus("none");
                } else {
                    System.out.println("\t"+getName()+" is fast asleep");
                }

            }
        }
    }

    /**
     * Resets everything changed in the last method
     */
    public void statusReset(){
        // Explicitly reverses stat changes
            currentAttack = attackStat;
            currentSpecialAttack = specialAttackStat;
            currentSpeed = speedStat;
            currentDefence=defenceStat;
            currentSpecialDefence=specialDefenceStat;
    }

    /**
     * This method will get the type match-up for the move and Pokémon
     * @param moveType type of the move used
     * @param pokemonType type of the Pokémon
     * @return the multiplier
     * @throws FileNotFoundException if the file cannot be found
     */
        public double getTypeEffect(String moveType, String pokemonType) throws FileNotFoundException {
            //Scanner for the file
                Scanner weaknessScan=new Scanner(new File("WeaknessChart.csv"));

            //2d arraylist for comparing the types
                ArrayList<ArrayList<String>> typeChart =new ArrayList<>();

                //While loop to run through the file
                    while(weaknessScan.hasNextLine()){
                        //Arraylist for every single row
                            ArrayList<String> row=new ArrayList<>();

                        //read the whole line
                            String line=weaknessScan.nextLine();

                        //Make a scanner for the line
                            Scanner lineScan=new Scanner(line);
                            lineScan.useDelimiter(",");

                        //For loop to go through and add each value from in the whole row
                            for(int i=0;lineScan.hasNext();i++){
                                row.add(lineScan.next());
                            }

                        //Add to the larger arraylist
                            typeChart.add(row);
                    }


            //Get the value
                int moveTypeNum=0;

            //For loop to go through and find the value that matches the type
                for(int i=0;i<typeChart.size();i++){

                    //First value is the string type
                        if(typeChart.get(i).getFirst().equalsIgnoreCase(moveType)){
                            moveTypeNum=i;
                        }
                }

                //Get the type of the Pokémon on the chart
                    int pokeTypeNum=0;
                    ArrayList<String> firstRow=typeChart.get(1);

                    for(int i=0;i<firstRow.size();i++){
                        if(firstRow.get(i).equalsIgnoreCase(pokemonType)){
                            pokeTypeNum=i;
                        }
                    }


                //Get the multiplier by comparing both values to create a 2d coordinate
                    double multNum=1;
                    ArrayList<String> temp=typeChart.get(moveTypeNum);
                    String multStr=temp.get(pokeTypeNum);

                //Decision statement to see what multiplier it is
                    if(multStr.equalsIgnoreCase("0x")){
                        multNum=0;
                    } else if(multStr.equalsIgnoreCase("1/2x")){
                        multNum= 0.5;
                    } else if(multStr.equalsIgnoreCase("2x")){
                        multNum=2;
                    }

                //Return
                    return multNum;

        }
}
