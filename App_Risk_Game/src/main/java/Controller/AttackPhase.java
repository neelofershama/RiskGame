package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Players.Player;
// import App_Risk_Game.src.main.java.Model.Players.PlayerCollection;
import App_Risk_Game.src.main.java.Model.Board.Tile;

import App_Risk_Game.src.main.java.Model.Players.PlayerCollectionTest;
import App_Risk_Game.src.main.java.Model.Score.Dice;
import App_Risk_Game.src.main.java.Model.Turns.Turns;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    boolean warning_given = false;
   /*public AttackPhase() {

   }*/
@FXML
    Button roll;
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

    }

public void onattackcountryselected(String attacking_country){

    troopsinattackingcountry = players.get(Turns.turns.getCurrentPlayerID() - 1).territories.get(attacking_country);
    System.out.println(troopsinattackingcountry);
    if (troopsinattackingcountry > 1) {
        defendList = LoadMap.board.getNeighbourTile(attacking_country);
        Iterator it = defendList.listIterator();
        while (it.hasNext()) {
            String country = (String) it.next();
            if (p.getTerritories().containsKey(country)) {
                it.remove();
            }
        }
        attackToList.getItems().addAll(defendList);


        //for (int i = 1; i <= troopsinattackingcountry - 1; i++) {
        //nooftroops.add(i);
   // }
        int max_troops =0;
        if(troopsinattackingcountry >3)
            max_troops =3;
        else if(troopsinattackingcountry == 3)
            max_troops =2;
        else if (troopsinattackingcountry == 2)
            max_troops =1;
        for (int i = 1; i <= max_troops; i++) {
            nooftroops.add(i);
        }
        troopstoattack.getItems().addAll(nooftroops);
        System.out.println(troopstoattack.getValue());

    }
    else{
       showWarning();
    }
}
public void showWarning(){
    warning_given = true;
    Alert a = new Alert(Alert.AlertType.WARNING);
    a.setContentText("THERE IS ONLY 1 TROOP IN SELECTED COUNTRY");
    a.show();
}
    public List<String> getAttackList() {
        attackList.addAll(p.getTerritories().keySet());
        return attackList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        current_player.setText(Turns.turns.getCurrent_player());
        attackFromList.getItems().addAll(getAttackList());
        attackFromList.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //TODO
                //incase of attack can be done from only one country at a time
                if(oldValue == null || warning_given){
                attacking_country =newValue;
                warning_given = false;}
                else
                attacking_country = oldValue;
                //attacking_country = newValue;
                onattackcountryselected(attacking_country);
            }
        });
    }

    public void defendingCountryClicked(ActionEvent actionEvent) {
        Turns.turns.setDefenceplayer(attackToList.getValue());
    }

//    public ComboBox<String> getAttackToList() {
//        defendList.addAll(tile.getNeighbourTile());
//        return attackToList;
//    }

@FXML
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

        int current_troop =troopsinattackingcountry-troopsofatk;
    PlayerCollection.players.get(Turns.turns.getCurrentPlayerID()-1).getTerritories().replace(attacking_country,current_troop);
    String f =Turns.turns.getDefenceplayer();
    System.out.println(f);
    System.out.println(Turns.turns.getDefenceplayerid());

    int t = PlayerCollection.players.get(Turns.turns.getDefenceplayerid()-1).getTerritories().get(attackToList.getValue());
    t = t-troopsofdfc;
    PlayerCollection.players.get(Turns.turns.getDefenceplayerid()-1).getTerritories().replace(attackToList.getValue(),t);
    if (current_troop == 0)
    {
        PlayerCollection.players.get(Turns.turns.getCurrentPlayerID()-1).getTerritories().remove(attacking_country);
        PlayerCollection.players.get(Turns.turns.getDefenceplayerid()-1).getTerritories().put(attackToList.getValue(),t);
    }
       if (t == 0)
       {
           PlayerCollection.players.get(Turns.turns.getDefenceplayerid()-1).getTerritories().remove(attackToList.getValue());
           PlayerCollection.players.get(Turns.turns.getCurrentPlayerID()-1).getTerritories().put(attackToList.getValue(),current_troop);
       }
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
       // attackFromList.setValue(null);
        defendList.clear();
        nooftroops.clear();
    //    attackToList.valueProperty().set(null);
    //    troopstoattack.valueProperty().set(null);
        attackToList.getItems().removeAll(attackToList.getItems());
        troopstoattack.getItems().removeAll(troopstoattack.getItems());
//        attackToList.setValue(null);
//        troopstoattack.setValue(null);
        //troopstoattack.setItems();
        DiceValues.setVisible(false);
        //attackingCountryClicked((ActionEvent )mouseEvent);
        onattackcountryselected(attacking_country);
    }
}

