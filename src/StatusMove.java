/**
 * This class controls status moves which can give opposing pokemon special effect.
 * This class controls the chance of affliction and status of the pokemon
 *
 * Version 1.0
 * Author Jared Gates and Josh Fair
 */

public class StatusMove extends Move {
    //Variables
    String status;
    double statusChance;

    public StatusMove(){
        //stub
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
