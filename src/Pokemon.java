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
    //Variables
        private String name;
        private ArrayList<String> types=new ArrayList<>();
        private String type1;
        private String type2;
        private int currentHealth;
        private int healthStat;
        private int attackStat;
        private int defenceStat;
        private int specialAttackStat;
        private int specialDefenceStat;
        private int speedStat;

    /**
     * Constructor for the class
     */
    public Pokemon(String name, String type1, int currentHealth, int healthStat, int attackStat, int defenceStat, int specialAttackStat, int specialDefenceStat, int speedStat){
        this.name=name;
        this.type1=type1;
        type2=null;
        this.currentHealth=currentHealth;
        this.healthStat=healthStat;
        this.attackStat=attackStat;
        this.defenceStat=defenceStat;
        this.specialAttackStat=specialAttackStat;
        this.specialDefenceStat=specialDefenceStat;
        this.speedStat=speedStat;
    }

    public Pokemon(String name, String type1, String type2, int currentHealth, int healthStat, int attackStat, int defenceStat, int specialAttackStat, int specialDefenceStat, int speedStat){
        this.name=name;
        this.type1=type1;
        this.type2=type2;
        this.currentHealth=currentHealth;
        this.healthStat=healthStat;
        this.attackStat=attackStat;
        this.defenceStat=defenceStat;
        this.specialAttackStat=specialAttackStat;
        this.specialDefenceStat=specialDefenceStat;
        this.speedStat=speedStat;
    }

    /**
     * Gets the name of the pokemon
     * @return string of name
     */
    public String getName(){
        return name;
    }

    /**
     * returns the typing of the pokemon
     * @param numTypes which indicates the potential typing of the pokemon
     * @return a list that contains all the typing of the pokemon
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

    public String getType1(){
        return type1;
    }

    public String getType2(){
        return type2;
    }

    /**
     * Returns the current health of the pokemon
     * @return integer
     */
    public int getCurrentHealth(){
        return currentHealth;
    }

    public void setCurrentHealth(int damage){
        currentHealth=currentHealth-damage;
    }

    /**
     * Returns the Health stat of the pokemon which is also used as the max health of the pokemon
     * @return integer
     */
    public int getHealthStat(){
        return healthStat;
    }

    /**
     * Returns the Damage stat for the pokemon
     * @return int
     */
    public int getAttackStat(){
        return attackStat;
    }

    /**
     * Returns the Defence stat for the pokemon
     * @return int
     */
    public int getDefenceStat(){
        return defenceStat;
    }

    /**
     * Returns the Special Damage stat for the pokemon
     * @return int
     */
    public int getSAttackStat(){
        return specialAttackStat;
    }

    /**
     * Returns the Special Defence stat for the pokemon
     * @return int
     */
    public int getSDefenceStat(){
        return specialDefenceStat;
    }

    /**
     * Returns the Speed stat for the pokemon
     * @return int
     */
    public int getSpeedStat(){
        return speedStat;
    }

    /**
     * Method to calculate damage using the generation one formula for damage calculation.
     */
    public int calcDamage(AttackMove moveUsed, Pokemon other) throws FileNotFoundException {
        int damage;
        double STAB = 0;
        int crit;
        boolean critMade=false;
        boolean sameType = false;
        double type1Effect;
        double type2Effect;
        
        type1Effect=getTypeEffect(moveUsed.getType(), other.getType1());
        try {
            type2Effect = getTypeEffect(moveUsed.getType(), other.getType2());
        } catch (Exception e){
            type2Effect=1;
        }

        Random rn=new Random();

        //Calculation for STAB
            for(int i=0;i<types.size();i++){
                if(moveUsed.getType().equalsIgnoreCase(types.get(i))&& !sameType){
                    STAB=1.5;
                    sameType=true;
                } else {
                    STAB=1.0;
                }
            }

        //Calculation for crit
            int threshold = getSpeedStat()/2;

            if(rn.nextInt(255)<threshold){
                critMade=true;
                crit=2;
            } else {
                crit=1;
            }

        damage= (int) (((double) (((2 * 100 * crit) / 5 + 2) * moveUsed.getPower() * (this.getAttackStat() / other.getDefenceStat())) /50+2)*STAB*(rn.nextInt(255-217+1)-217)/255*type1Effect*type2Effect);

        if(critMade) {
            System.out.println("You got a crit!");
        }

        return damage;
    }
    
    public double getTypeEffect(String moveType, String pokemonType) throws FileNotFoundException {
        Scanner weaknessScan=new Scanner(new File("WeaknessChart.csv"));

        //2d arraylist for comparing the types
            ArrayList<ArrayList<String>> typeChart =new ArrayList<>();

            while(weaknessScan.hasNextLine()){
                ArrayList<String> row=new ArrayList<>();

                String line=weaknessScan.nextLine();

                Scanner lineScan=new Scanner(line);
                lineScan.useDelimiter(",");

                for(int i=0;lineScan.hasNext();i++){
                    row.add(lineScan.next());
                }

                typeChart.add(row);
            }


        //Get the value
            int moveTypeNum=0;

            for(int i=0;i<typeChart.size();i++){
                ArrayList<String> temp = typeChart.get(i);
                if(temp.get(1).equalsIgnoreCase(moveType)){
                    moveTypeNum=i;
                }
            }

            int pokeTypeNum=0;
            ArrayList<String> firstRow=typeChart.get(1);

            for(int i=0;i<firstRow.size();i++){
                if(firstRow.get(i).equalsIgnoreCase(pokemonType)){
                    pokeTypeNum=i;
                }
            }


        //Get number
            double multNum=1;
            ArrayList<String> temp=typeChart.get(pokeTypeNum);
            String multStr=temp.get(pokeTypeNum);

            if(multStr.equalsIgnoreCase("0x")){
                multNum=0;
            } else if(multStr.equalsIgnoreCase("1/2x")){
                multNum= 0.5;
            } else if(multStr.equalsIgnoreCase("2x")){
                multNum=2;
            }

            return multNum;

    }
}
