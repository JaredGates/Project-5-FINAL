/**
 * This class is for special moves as they can both damage and deal status effects.
 * This class deals with the power of the move, the chance it afflicts a status affect, and the name of
 * the status effect.
 *
 * Version 1.0
 * Author Jared Gates and Josh Fair
 */

public class SpecialMove extends Move {
    //Variables
    private int power;
    private String status;
    private double statusChance;

    public SpecialMove(String name, double accuracy, String type, String status, double statusChance, int power){
        super(name, accuracy, type, "Special");
        this.status=status;
        this.statusChance=statusChance;
        this.power=power;
    }

    public SpecialMove(SpecialMove other){
        super(other);
        this.status=other.status;
        this.statusChance=other.statusChance;
        this.power=other.power;
    }

    public SpecialMove newCopy(){
        return new SpecialMove(this);
    }

    /**
     * Returns the power of the move
     * @return int
     */
    public int getPower(){
        return power;
    }

    /**
     * This method will determine what the pokemon is afflicted by based on the move.
     * Then it returns the string of that effect.
     * @return string
     */
    public String getStatus(){
        return status;
    }

    /**
     * returns the status chance of the move
     * @return double
     */
    public double getStatusChance(){
        return statusChance;
    }

    /**
     * Returns if the status was afflicted or not
     * @return boolean
     */
    public boolean statusAfflict(){
        return false;
    }
}
