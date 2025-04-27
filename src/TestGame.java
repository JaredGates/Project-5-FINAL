import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;

public class TestGame {
    HashMap<Integer, Pokemon> database;

    @Test public void HashBounds1() throws FileNotFoundException {
        database=importCSVFile();
        Pokemon poke=database.get(151);

        assertEquals(database.get(151), poke);
    }

    @Test public void HashBounds2() throws FileNotFoundException {
        database=importCSVFile();
        Pokemon poke=database.get(1);

        assertEquals(database.get(1), poke);
    }

    @Test public void HashBounds3() throws FileNotFoundException {
        database=importCSVFile();
        assertNull(database.get(database.size()+1));
    }

    @Test public void HashBounds4() throws FileNotFoundException {
        database=importCSVFile();
        assertNull(database.get(0));
    }

    @Test public void typeEffect1() throws FileNotFoundException {
        database=importCSVFile();
        Pokemon temp = database.get(1);
        assertEquals(temp.getTypeEffect("Normal", "Normal"), 1);
    }

    @Test public void typeEffect2() throws FileNotFoundException {
        database=importCSVFile();
        Pokemon temp = database.get(1);
        assertEquals(temp.getTypeEffect("Grass", "Water"), 2);
    }

    @Test public void typeEffect3() throws FileNotFoundException {
        database=importCSVFile();
        Pokemon temp = database.get(1);
        assertEquals(temp.getTypeEffect("Bug", "Poison"), .5);
    }

    @Test public void typeEffect4() throws FileNotFoundException {
        database=importCSVFile();
        Pokemon temp = database.get(1);
        assertEquals(temp.getTypeEffect("Electric", "Ground"), 0);
    }

    @Test public void typeEffect5() throws FileNotFoundException {
        database=importCSVFile();
        Pokemon temp = database.get(1);
        assertEquals(temp.getTypeEffect("Dragon", "Dragon"), 2);
    }



    /**
     * This method will go through the CSV file provided (or potentially one made) and put all Pokémon from it into a hashmap
     * @return a hashmap with an integer key and Pokémon value
     * @throws FileNotFoundException if file not found
     */
    public static HashMap<Integer, Pokemon> importCSVFile() throws FileNotFoundException {
        //Scanner to read in the file
        Scanner fileScan = new Scanner(new File("COMP 220 Final Project Excel.csv"));

        //Read in the blank line at the beginning
        fileScan.nextLine();

        //HashMap to get all the Pokémon into a hashmap to be returned, keys are the Pokédex entries
        HashMap<Integer, Pokemon> newDatabaseEntry = new HashMap<>();


        //While loop to read in each line of the CSV file and get each of the Pokémon's stats
        while (fileScan.hasNext()) {

            //Create another scanner that reads each line using a delimiter of a comma due to the CSV format
            String newLine=fileScan.nextLine();
            Scanner lineScan = new Scanner(newLine);
            lineScan.useDelimiter(",");

            //Reads in the first value which is its Pokédex entry, this will be the key
            int pokeNum = parseInt(lineScan.next());
            Pokemon newPoke;

            //Read in each of the stats of the Pokémon
            String name = lineScan.next();
            String type1 = lineScan.next();
            String type2 = lineScan.next();
            int currentHealth = parseInt(lineScan.next());
            int healthStat = currentHealth;
            int attackStat = parseInt(lineScan.next());
            int defenceStat = parseInt(lineScan.next());
            int sAttackStat = parseInt(lineScan.next());
            int sDefenceStat = parseInt(lineScan.next());
            int speedStat = parseInt(lineScan.next());
//                    String move1=lineScan.next();
//                    String move2=lineScan.next();
//                    String move3=lineScan.next();
//                    String move4=lineScan.next();

            String move1="Attack";
            String move2="Special";
            String move3="Status";
            String move4="Protection";

            //Create a Pokémon object, depending on if type2 was null there will be a different object created
            if (type2 == null) {
                newPoke = new Pokemon(name, type1, healthStat, attackStat, defenceStat, sAttackStat, sDefenceStat, speedStat, move1, move2, move3, move4);
            } else {
                newPoke = new Pokemon(name, type1, type2, healthStat, attackStat, defenceStat, sAttackStat, sDefenceStat, speedStat, move1, move2, move3, move4);
            }

            //Create a new key and object value for the hashmap
            newDatabaseEntry.put(pokeNum, newPoke);
        }

        //Return
        return newDatabaseEntry;
    }
}
