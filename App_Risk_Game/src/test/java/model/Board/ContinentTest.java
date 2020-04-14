package App_Risk_Game.src.test.java.model.Board;
import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Score.Dice;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Junit TestCase suite for carrying tests regarding the map elements
 */
public class ContinentTest {
    /**
     * a Hashmap to store the list of continents and the No. of countries in them
     */
    public HashMap<String, Integer> Continents;
    public Board board;

    /**
     * a List to store the neighbours
     */
    List<String> neighbours;

    /**
     * a Hashmap to store the tile and the list of neighbours to it
     */
    HashMap<String, List<String>> neighborsList;

    /**
     * creating an initial setup with the map elements to test.
     */
    @Before
    public void setUp()
    {
        Continents = new HashMap<String, Integer>();
        board =new Board();
        neighbours = new ArrayList<>();
        neighborsList = new HashMap<>();
        Continents.put("Asia",3);
        Continents.put("Europe",2);
        board.setContinents(Continents);
        neighbours.add("India");
        neighbours.add("Pakistan");
        board.createTile("Pakistan", 20, 100, "Asia");
        board.createTile("India", 30, 100, "Asia");
        board.createTile("Sri Lanka", 100, 100, "Asia");
        neighborsList.put("Sri Lanka",neighbours);
        board.setNeighbourTile( neighbours,"Sri Lanka");

    }

    /**
     * JUnit test case for to verify the Continents and the countries in it
     */
    @Test
    public void checkContinents()
    {
        Continents = new HashMap<String, Integer>();
        Continents.put("Asia",3);
        Continents.put("Europe",2);
        assertEquals(board.getContinents(),Continents);
    }

    /**
     * JUnit test case for to verify the neighbours of a particular tile
     */
    @Test
    public void checkNeighbours()
    {
        List<String> neighbour = new ArrayList<>();
        neighbour.add("India");
        neighbour.add("Pakistan");
        assertEquals(board.getNeighbourTile("Sri Lanka"),neighbour);
    }

    /**
     * JUnit test case for checking the size of the neighbours list
     */
    @Test
    public void checkNeighboursSize()
    {
        assertEquals(board.getNeighbourTile("Sri Lanka").size(),2);
    }


}
