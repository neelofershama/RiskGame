package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollectionTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

// import App_Risk_Game.src.main.java.Model.Players.PlayerCollection;

public class ReinforceTest implements Initializable {

    @FXML
    Label player_name;

    @FXML
    VBox root;

    @FXML
    Label territories_owned;

    @FXML
    Label troop_update;

    @FXML
    ComboBox territory;

    @FXML
    ComboBox troops;

    private Player current_player;
    private HashMap<String, Integer> territories;
    private int max_troop;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        max_troop = 3;
        current_player = PlayerCollectionTest.players.get(0); // Need to get current player turn from TURNS MODULE
        player_name.setText(current_player.getName());
        player_name.setTextFill(Color.web(current_player.getColor()));
        reinforce();
    }

    private void reinforce() {
        troop_update.setText("YOU HAVE " + max_troop + " TO REINFORCE YOUR TERRITORIES");
        troops.getItems().clear();
        troops.getSelectionModel().selectFirst();
        territories = current_player.getTerritories();
        territories_owned.setText("TERRITORIES OWNED :- " + territories.toString());
        if (!territories.isEmpty()) { // HAVE TO MAKE SURE ITS NULL OR EMPTY
            Iterator iterator = territories.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry) iterator.next();
                territory.getItems().add((String) mapElement.getKey());
            }
            territory.getSelectionModel().selectFirst();
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText(current_player.getName() + " HAS NO TERRITORIES");
            a.show();
        }

        // Setting up the troop values
        for (int i = 1; i <= max_troop; i++) {
            troops.getItems().add(i);
        }

    }

    public void onClicked(ActionEvent actionEvent) {
        int troop_selected = (int) troops.getValue();
        String territory_selected = (String) territory.getValue();
        System.out.println(territory_selected);
        System.out.println(current_player.getTerritories().get(territory_selected));
        current_player.setTerritory(territory_selected, current_player.getTerritories().get(territory_selected) + troop_selected);
        max_troop = max_troop - troop_selected;
        if (max_troop != 0) {
            reinforce();
        } else {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
            // Logic to update turn and loading gamescreetest
            //PlayerCollectionTest.updateTurn();
            GameScreenTest gameScreen = new GameScreenTest();
            LoadMap.LoadMapGlobalVariables.gsFlag = true;
            PlayerCollectionTest.goBackToGameScreen();
        }
    }
}
