/**
 * This class controls status moves which can give opposing pokemon special effect.
 * This class controls the chance of affliction and status of the pokemon
 *
 * Version 1.0
 * Author Jared Gates and Josh Fair
 */

public class StatusMove extends Move {
    //Variables
        private String status;
        private double statusChance;

    public StatusMove(String name, double accuracy, String type, String status, double statusChance){
        super(name, accuracy, type, "Status");
        this.status=status;
        this.statusChance=statusChance;
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

}
