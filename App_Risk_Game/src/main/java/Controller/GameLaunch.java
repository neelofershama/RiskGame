package App_Risk_Game.src.main.java.Controller;

import UI.src.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameLaunch implements Initializable {


    @FXML
    Button new_game;
    @FXML
    Button exit_game;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  @FXML
        //    Button submit;
    }

    /**
     * Starting point for a new game
     * @param event
     */
    @FXML
    public void onMouseClickedNewGame(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/App_Risk_Game/src/main/java/View/GameSetup.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("RISK GAME SETUP");
            stage.setScene(scene);
            stage.show();

            Stage stg = (Stage) exit_game.getScene().getWindow();
            stg.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Starting point for tournament
     * @param event
     */
    @FXML
    public void onMouseClickedTournamentMode(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/App_Risk_Game/src/main/java/View/NoOfTournamentPlayer.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("TOURNAMENT");
            stage.setScene(scene);
            stage.show();

            Stage stg = (Stage) exit_game.getScene().getWindow();
            stg.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void onMouseClickedExitGame(ActionEvent event) {
        System.out.println("GAME QUITED");
        Stage stage = (Stage) exit_game.getScene().getWindow();
        stage.close();
    }
}
