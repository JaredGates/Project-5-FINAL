import java.util.ArrayList;
import java.util.Random;

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
    public void calcDamage(AttackMove moveUsed, Pokemon other){
        int damage;
        double STAB = 0;
        boolean sameType = false;
        
        double type1Effect=getTypeEffect(moveUsed.getType(), other);
        double type2Effect=getTypeEffect(moveUsed.getType(), other);
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

        damage= (int) ((((2*100)/5+2)*moveUsed.getPower()*(this.getAttackStat()/other.getDefenceStat())/50+2)*STAB*(rn.nextInt(255-217+1)-217)/255*type1Effect*type2Effect);
    }
    
    public double getTypeEffect(String moveType, Pokemon other){
        return 1;
    }
}
