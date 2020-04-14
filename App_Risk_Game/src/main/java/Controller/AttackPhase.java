package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Players.Player;
// import App_Risk_Game.src.main.java.Model.Players.PlayerCollection;
import App_Risk_Game.src.main.java.Model.Board.Tile;

import App_Risk_Game.src.main.java.Model.Players.PlayerCollectionTest;
import App_Risk_Game.src.main.java.Model.Score.Dice;
import App_Risk_Game.src.main.java.Model.Turns.Turns;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.*;

public class AttackPhase implements Initializable {

    List<Player> players = PlayerCollectionTest.players;
    Player p = players.get(0);
    String attacking_country;
    List<String> attackList = new ArrayList<String>();
    List<String> defendList = new ArrayList<String>();
    List<Integer> nooftroops = new ArrayList<>();
    public static Tile tile = new Tile();
    int troopsinattackingcountry;
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
    AnchorPane DiceValues;

@FXML
    TextField atkrdice;
    @FXML
    TextField dfcdice;
    @FXML
    private ComboBox<String> attackFromList;

    @FXML
    private ComboBox<String> attackToList;

    public void getAttackFromList() {
        attackFromList.getItems().addAll(getAttackList());
    }

    @FXML
    void attackingCountryClicked(ActionEvent event) {
       attackList.clear();
        defendList.clear();
        attackToList.getItems().addAll(defendList);
        nooftroops.clear();
        troopstoattack.getItems().addAll(nooftroops);
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

        Turns.turns.setDefenceplayer(attackToList.getValue());
        troopsinattackingcountry= players.get(Turns.turns.getCurrentPlayerID()-1).territories.get(attacking_country);

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

//    public ComboBox<String> getAttackToList() {
//        defendList.addAll(tile.getNeighbourTile());
//        return attackToList;
//    }


    public void rolldice(MouseEvent mouseEvent) {
        Dice dice = new Dice();
        int n=0;
        int m = troopstoattack.getValue();
        if(m>=2)
            n= 2;
        else if(m ==1)
            n=1;
        List<Integer> troopslost = dice.rollDice(m,n);
        int troopsofatk = troopslost.get(0);
        int troopsofdfc = troopslost.get(1);
        HashMap<String, Integer> g =PlayerCollectionTest.players.get(Turns.turns.getCurrentPlayerID()-1).getTerritories();

        g.replace(attacking_country,troopsinattackingcountry-troopsofatk);
        String f =attackToList.getValue();
        HashMap<String, Integer> d = PlayerCollectionTest.players.get(Turns.turns.getCurrentPlayerID()-1).getTerritories();
        //int t = g.get(attackToList.getValue());
       // t = t-troopsofdfc;
       // g.replace(attackToList.getValue(),t);
        for (Player p:players
        ) {
            System.out.println(p.getName());
            System.out.println(p.getTerritories());
        }
        String atkdice = null;
        String dfdice = null;
        atkdice = dice.dice_value.toString();
        dfdice = dice.dice_value1.toString();
atkrdice.setText(atkdice);
        dfcdice.setText(dfdice);
        DiceValues.setVisible(true);
    }

    public void movetoreinforcement(MouseEvent mouseEvent) {

    }

    public void attackAgain(MouseEvent mouseEvent) {
        attackToList.setValue(null);
        troopstoattack.setValue(null);
        //troopstoattack.setItems();
        DiceValues.setVisible(false);
    }
}

