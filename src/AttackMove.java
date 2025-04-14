/**
 * This class focuses on the attack moves that primarily deal damage only. This class deals with the power of the move.
 *
 * Version 1.0
 * Author Jared Gates and Josh Fair
 */

public class AttackMove extends Move {
    //Variables
    private int power;

    /**
     * Constructor
     */
    public AttackMove(String name, double accuracy, String type,int power){
        super(name, accuracy, type);
        this.power=power;
    }

    /**
     * Returns the power of the move
     * @return int
     */
    public int getPower(){
        return power;
    }


}
