/**
 * This class handles moves and is abstract to fit the multitudes of different types of moves. The class handles
 * accuracy and typing of the moves.
 *
 * Version 1.0
 * Author Jared Gates and Josh Fair
 */

public abstract class Move {
    //Variables
        private String name;
        private double accuracy;
        private String type;
        private String typeOfMove;

    /**
     * Constructor
     */
    public Move(String name, double accuracy, String type,String typeOfMove){
        this.name=name;
        this.accuracy=accuracy;
        this.type=type;
        this.typeOfMove=typeOfMove;
    }

    public String getTypeOfMove(){
        return typeOfMove;
    }

    /**
     * Return the name of the move
     * @return String
     */
    public String getNameOfMove(){
        return name;
    }

    /**
     * Return the accuracy of the move
     * @return double
     */
    public double getAccuracy(){
        return accuracy;
    }

    /**
     * Return the typing of the move
     * @return String
     */
    public String getType(){
        return type;
    }


}
