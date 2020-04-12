package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollection;
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
    public void setPlayerInPhase(Text playerInPhase) {
        this.playerInPhase = playerInPhase;
    }


    @FXML
    private ComboBox<String> attackFromList;

    @FXML
    void attackingCountryClicked(ActionEvent event) {
    }

    List<Player> players = PlayerCollection.players;
    Player p = players.get(0);
    List<String> attackList = new ArrayList<String>();

    public List<String> getAttackList() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}

