package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.main.java.Common.BehaviourStrategies;
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
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class AttackPhase implements Initializable {

    List<Player> players = PlayerCollectionTest.players;
    Player p ;
    String attacking_country;
    String defence_country;
    List<String> attackList = new ArrayList<String>();
    List<String> defendList = new ArrayList<String>();
    List<Integer> nooftroops = new ArrayList<>();
    public static Tile tile = new Tile();
    int troops_in_attacking_country;
    boolean warning_given = false;

    /**
     * Button for rolling dice
     */
    @FXML
    Button roll;
    /**
     * Button for ending attack phase
     */
    @FXML
Button end;
    /**
     * Used to display the current player name
     */
    @FXML
    Text current_player;
    /**
     * setter method to display current player name
     */
    /**
     * Dropdown menu for selecting no of territories available for attacking
     */
    @FXML
ComboBox<Integer> troopstoattack;

    /**
     * Pane showing the out come of rolling dice.visibility set to false initially but made visble on rolling dice
     */
    @FXML
    AnchorPane DiceValues;

    /**
     * Textfield showing values of attacker dice
     */
    @FXML
    TextField atkrdice;
    /**
     * Textfield showing values of defence dice
     */
    @FXML
    TextField dfcdice;
    /**
     * Combobox for selecting  territory to attack from
     */
    @FXML
    private ComboBox<String> attackFromList;

    /**
     * Combobox for selecting  territory to attack to
     */
    @FXML
    private ComboBox<String> attackToList;

    public void getAttackFromList() {

    }

    /**
     *
     * @param attacking_country
     */
    public void onattackcountryselected(String attacking_country){

        troops_in_attacking_country = players.get(Turns.turns.getCurrentPlayerID() - 1).territories.get(attacking_country);
    //System.out.println(troopsinattackingcountry);
    if (troops_in_attacking_country > 1) {
        defendList = LoadMap.board.getNeighbourTile(attacking_country);
        Iterator it = defendList.listIterator();
        while (it.hasNext()) {
            String country = (String) it.next();
            if (p.getTerritories().containsKey(country)) {
                it.remove();
            }
        }
        attackToList.getItems().addAll(defendList);



        int max_troops =0;
        if(troops_in_attacking_country >3)
            max_troops =3;
        else if(troops_in_attacking_country == 3)
            max_troops =2;
        else if (troops_in_attacking_country == 2)
            max_troops =1;
        for (int i = 1; i <= max_troops; i++) {
            nooftroops.add(i);
        }
        troopstoattack.getItems().addAll(nooftroops);
        //System.out.println(troopstoattack.getValue());

    }
    else{
       showWarning();
    }
}

    /**
     * Showing warning if country selected to attack from has only one 1 troop
     */
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
        p  = PlayerCollectionTest.getTurn();
        current_player.setText(p.getName());
if(p.getType()== BehaviourStrategies.RandomPlayer){
    p.attack();
}
else {
    //current_player.setText(Turns.turns.getCurrent_player());
    attackFromList.getItems().addAll(getAttackList());
    attackFromList.valueProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            //TODO
            //incase of attack can be done from only one country at a time
            if (oldValue == null || warning_given) {
                attacking_country = newValue;
                warning_given = false;
            } else
                attacking_country = oldValue;
            //attacking_country = newValue;
            System.out.println("Atacking from " + attacking_country);
            onattackcountryselected(attacking_country);
        }
    });
}
    }

    /**
     * Selecting defence country
     * @param actionEvent
     */
    public void defendingCountryClicked(ActionEvent actionEvent) {
        defence_country = attackToList.getValue();
        System.out.println("Attacking on " + defence_country);
        Turns.turns.setDefenceplayer(defence_country);
    }

//    public ComboBox<String> getAttackToList() {
//        defendList.addAll(tile.getNeighbourTile());
//        return attackToList;
//    }

    /**
     * on rolling a dice the dice values are compared and accordingly troops are updated
     * @param mouseEvent
     */
    @FXML
    public void rolldice(MouseEvent mouseEvent) {
if(attackToList.getValue() == null)
{
    defence_country = defendList.get(0);
}
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
    System.out.println("Troops lost by attack " + troopsofatk);
    System.out.println("Troops lost by defence " + troopsofdfc);
        int current_troop =troops_in_attacking_country-troopsofatk;
    PlayerCollectionTest.players.get(Turns.turns.getCurrentPlayerID()-1).getTerritories().replace(attacking_country,current_troop);
    Player f =Turns.turns.getDefenceplayer();
    //System.out.println(f);
   // System.out.println(Turns.turns.getDefenceplayerid());

    int t = PlayerCollectionTest.players.get(Turns.turns.getDefenceplayerid()-1).getTerritories().get(defence_country);
    t = t-troopsofdfc;
    PlayerCollectionTest.players.get(Turns.turns.getDefenceplayerid()-1).getTerritories().replace(defence_country,t);

       if (t == 0)
       {
           PlayerCollectionTest.players.get(Turns.turns.getDefenceplayerid()-1).getTerritories().remove(defence_country);
           PlayerCollectionTest.players.get(Turns.turns.getCurrentPlayerID()-1).getTerritories().put(defence_country,current_troop);
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

    /**
     * resetting the comboboxes on attack again
     * @param mouseEvent
     */
    public void attackAgain(MouseEvent mouseEvent) {

        defendList.clear();
        nooftroops.clear();
        defence_country = null;
        attackToList.getItems().removeAll(attackToList.getItems());
        troopstoattack.getItems().removeAll(troopstoattack.getItems());

        DiceValues.setVisible(false);
System.out.println("Attacking again ");
        onattackcountryselected(attacking_country);
    }

    /**
     * End attack closes attack screen and moves to game screen
     * @param mouseEvent
     */
    public void endAttack(MouseEvent mouseEvent) {
        Stage stage = (Stage) end.getScene().getWindow();
        stage.close();

        GameScreenTest gs = new GameScreenTest();
        gs.fortificationTest();
    }
}

