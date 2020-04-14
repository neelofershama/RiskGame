package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReinforcePhase implements Initializable {

    Board board = LoadMap.board;
    Stage stage = LoadMap.stage;

    @FXML
    TextField reinforce_destination;
    @FXML
    GridPane reinforcePane2;
    @FXML
    TextField no_troops;

    public static void main(String args[]) {

    }

    @FXML
    public void submit_troops(ActionEvent ae) throws IOException {

        Player player = PlayerCollection.players.get(0);
        String dest = reinforce_destination.getText();
        int troops = Integer.parseInt(no_troops.getText());
        GameScreenTest gt = new GameScreenTest();


        if (troops <= GameScreenTest.reinforceCount) {
            GameScreenTest.reinforceCount = GameScreenTest.reinforceCount - troops;
            System.out.println(dest);
            System.out.println(player.getTerritories());
            player.setTerritory(dest, player.getTerritories().get(dest) + troops);
        }

        if (GameScreenTest.reinforceCount != 0) {
            gt.reinforceStage.close();
            gt.reinforcement();
        } else {
            System.out.println("Player Territories after reinforcement are " + player.getTerritories().toString());
            gt.reinforceStage.close();
        }

    }

    public void checkTroopCount() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
