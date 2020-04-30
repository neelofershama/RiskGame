package App_Risk_Game.src.test.java.model;
import App_Risk_Game.src.interfaces.Observable;
import App_Risk_Game.src.interfaces.Observer;
import App_Risk_Game.src.main.java.Common.Common;
import App_Risk_Game.src.main.java.Controller.LoadMap;
import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Board.Tile;
import App_Risk_Game.src.main.java.Model.Players.*;
import App_Risk_Game.src.main.java.Model.Score.Dice;
import App_Risk_Game.src.main.java.Model.Turns.Turns;
import App_Risk_Game.src.main.java.Model.Cards.*;
import App_Risk_Game.src.interfaces.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
public class ConservativeTest {
    public Board board;

    List<String> neighbours;
    ArrayList<String> player_list;
    public HashMap<String, Integer> Continents;
    HashMap<String, List<String>> neighborsList;
    HashMap<String, Integer> territories;
    List<String> continents_owned;
    ArrayList<String> player_behavior;

    @Before
    public void setup() {


        Continents = new HashMap<String, Integer>();
        board = new Board();
        neighbours = new ArrayList<>();
        continents_owned = new ArrayList<>();
        neighborsList = new HashMap<>();
        territories = new HashMap<>();
        Continents.put("Asia", 3);
        Continents.put("Europe", 2);
        continents_owned.add("asia");
        continents_owned.add("europe");
        territories.put("india", 1);
        territories.put("pakistan", 2);
        territories.put("srilanka", 3);
        String player_name = "p1";
        String player_color = "blue";
        int player_id = 1;
        Player p = new Player(player_name, player_color, player_id, territories, continents_owned, "a");
        ConservativePlayer c = new ConservativePlayer();
    }

    @Test
    public void phase1Test() {
        Continents.put("Asia", 3);
        Continents.put("Europe", 2);
        board.setContinents(Continents);
        continents_owned.add("asia");
        continents_owned.add("europe");
        territories = new HashMap<>();
        territories.put("india", 1);
        territories.put("pakistan", 2);
        territories.put("srilanka", 3);
        String player_name = "p1";
        String player_color = "blue";
        int player_id = 1;
        Player p1 = new Player(player_name, player_color, player_id, territories, continents_owned, "a");
        ConservativePlayer c = new ConservativePlayer();
        c.reinforce(p1);

        Assert.assertNotEquals(p1.getTerritories().get("pakistan") + p1.getTerritories().get("india") + p1.getTerritories().get("srilanka"), 6);

    }

    @Test
    public void phase2Test() {
        Continents.put("Asia", 3);
        Continents.put("Europe", 2);
        board.setContinents(Continents);
        continents_owned.add("asia");
        continents_owned.add("europe");
        territories = new HashMap<>();
        territories.put("india", 1);
        territories.put("pakistan", 2);
        territories.put("srilanka", 3);
        String player_name = "p1";
        String player_color = "blue";
        int player_id = 1;
        Player p1 = new Player(player_name, player_color, player_id, territories, continents_owned, "a");
        ConservativePlayer c = new ConservativePlayer();
        c.reinforce(p1);

        Assert.assertEquals(p1.getTerritories().get("pakistan") + p1.getTerritories().get("india") + p1.getTerritories().get("srilanka"), 6);

    }
}