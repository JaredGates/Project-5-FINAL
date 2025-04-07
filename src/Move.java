/**
 * This class handles moves and is abstract to fit the multitudes of different types of moves. The class handles
 * accuracy and typing of the moves.
 *
 * Version 1.0
 * Author Jared Gates and Josh Fair
 */

public abstract class Move {

    /**
     * Constructor
     */
    public Move(){
        //stub
    }

    /**
     * Return the name of the move
     * @return String
     */
    public String getNameOfMove(){
        //stub
        return "";
    }

    /**
     * Return the accuracy of the move
     * @return double
     */
    public double getAccuracy(){
        //stub
        return -1;
    }

    /**
     * Return the typing of the move
     * @return String
     */
    public String getType(){
        //stub
        return "";
    }
}
