package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollection;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollectionTest;
import UI.src.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;

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

    @FXML
    public void loadGame() throws FileNotFoundException {
        System.out.println("Game Loaded");
        String file_name = "App_Risk_Game/src/main/java/Resources/game_data.txt";
        Scanner scanner = new Scanner(new File(file_name));
        if(!scanner.hasNextLine()){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("OOPS YOU DON'T HAVE ANY SAVED DATA");
            a.show();
            return ;
        }
        int number_of_players = Integer.parseInt(scanner.nextLine());
        PlayerCollectionTest.number_of_players = number_of_players;
        // System.out.println(number_of_players);
        ArrayList<String> territories_list = new ArrayList<>();

        int player_id = 0;
        String player_name = "";
        String player_color = "";
        String player_behavior_type = "";

        for(int i=0;i<=2*number_of_players;i++){
            String line = scanner.nextLine();
            if(i%2==0 && i!=2*number_of_players){
                String[] player_details = line.split(",");
                player_id = Integer.parseInt(player_details[0]);
                player_name = player_details[1];
                player_color = player_details[2];
                player_behavior_type = player_details[3];
                PlayerCollectionTest.createPlayersLoadGame(player_id, player_name, player_color, player_behavior_type);
            }
            else{
                line = line.replace("{","");
                line = line.replace("}","");
                territories_list.add(line);
            }
            if(i == 2*number_of_players){
                PlayerCollectionTest.turn_index = Integer.parseInt(line);
            }
            // System.out.println(line);
        }

        for(int i=0;i<PlayerCollectionTest.players.size();i++){
            HashMap<String, Integer> territories = new HashMap<>();
            String territory = territories_list.get(i);
            Player p = PlayerCollectionTest.players.get(i);
            String temp[] = territory.split(", ");
            for(int j=0; j<temp.length; j++){
                String territory_troops = temp[j];
                String[] final_split = territory_troops.split("=");
                territories.put(final_split[0], Integer.parseInt(final_split[1]));
            }
            p.setTerritories(territories);
        }

        System.out.println(PlayerCollectionTest.number_of_players);
        for(int i=0;i<PlayerCollectionTest.players.size();i++){
            Player p = PlayerCollectionTest.players.get(i);
            System.out.println(p.getId()+","+p.getName()+","+p.getColor()+","+p.getBehaviorType());
            System.out.println(p.getTerritories().toString());
        }
        Stage stage = (Stage) new_game.getScene().getWindow();
        stage.close();
        LoadMap.loadMap("mapinput.txt");
        PlayerCollectionTest.goBackToGameScreen();

    }
}
