package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollection;
import App_Risk_Game.src.main.java.Model.Board.Tile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AttackPhase implements Initializable {

    List<Player> players = PlayerCollection.players;
    Player p = players.get(0);
    String attacking_country;
    List<String> attackList = new ArrayList<String>();
    List<String> defendList = new ArrayList<String>();
    public static Tile tile = new Tile();

   /*public AttackPhase() {

   }*/

    /**
     * Used to display the current player name
     */
    @FXML
    private Text playerInPhase;
    /**
     * setter method to display current player name
     */

    @FXML
    private ComboBox<String> attackFromList;

    @FXML
    private ComboBox<String> attackToList;

    public void getAttackFromList() {
        attackFromList.getItems().addAll(getAttackList());
    }

    @FXML
    void attackingCountryClicked(ActionEvent event) {
        attacking_country=attackFromList.getValue();

    }


    public List<String> getAttackList() {
        attackList.addAll(p.getTerritories().keySet());
        return attackList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAttackFromList();
    }

    public void defendingCountryClicked(ActionEvent actionEvent) {
    }

    public ComboBox<String> getAttackToList() {
        defendList.addAll(tile.getNeighbourTile());
        return attackToList;
    }
}

