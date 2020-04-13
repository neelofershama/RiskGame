package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollection;
import App_Risk_Game.src.main.java.Model.Board.Tile;

import App_Risk_Game.src.main.java.Model.Turns.Turns;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
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
    Text current_player;
    /**
     * setter method to display current player name
     */
@FXML
ComboBox<Integer> troopstoattack;
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
defendList = LoadMap.board.getNeighbourTile(attacking_country);
        Iterator it = defendList.listIterator();
        while (it.hasNext()){
            String country = (String)it.next();
            if(p.getTerritories().containsKey(country)){
                it.remove();
            }
        }
        attackToList.getItems().addAll(defendList);
int troopsinattackingcountry = players.get(Turns.turns.getCurrentPlayerID()-1).territories.get(attacking_country);
List<Integer> nooftroops = new ArrayList<>();
if (troopsinattackingcountry == 1)
    nooftroops.add(0);
else {
for (int i =1;i<=troopsinattackingcountry-1;i++) {
    nooftroops.add(i);
}}
troopstoattack.getItems().addAll(nooftroops);
    }


    public List<String> getAttackList() {
        attackList.addAll(p.getTerritories().keySet());
        return attackList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        current_player.setText(Turns.turns.getCurrent_player());
        getAttackFromList();

    }

    public void defendingCountryClicked(ActionEvent actionEvent) {
    }

    public ComboBox<String> getAttackToList() {
        defendList.addAll(tile.getNeighbourTile());
        return attackToList;
    }


}

