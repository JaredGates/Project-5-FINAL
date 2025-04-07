/**
 * This class is for special moves as they can both damage and deal status effects.
 * This class deals with the power of the move, the chance it afflicts a status affect, and the name of
 * the status effect.
 *
 * Version 1.0
 * Author Jared Gates and Josh Fair
 */

public class SpecialMove extends Move {

    public SpecialMove(){
        //stub
    }

    /**
     * Returns the power of the move
     * @return int
     */
    public int getDamage(){
        //stub
        return -1;
    }

    /**
     * This method will determine what the pokemon is afflicted by based on the move.
     * Then it returns the string of that effect.
     * @return string
     */
    public String getStatus(){
        //stub
        return "";
    }

    /**
     * returns the status chance of the move
     * @return double
     */
    public double getStatusChance(){
        //stub
        return -1;
    }

    /**
     * Returns if the status was afflicted or not
     * @return boolean
     */
    public boolean statusAfflict(){
        //stub
        return false;
    }
}
