package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Board.Tile;
import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollectionTest;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Tournament implements Initializable {


    @FXML
    Button submit_no_players;

    @FXML
    ComboBox option1;

    @FXML
    ComboBox option2;

    @FXML
    ComboBox option3;

    @FXML
    ComboBox option4;

    @FXML
    ComboBox no_of_players;

    @FXML
    Button submit_type_of_player;

    @FXML
    TextField no_of_games_per_map;

    @FXML
    TextField no_of_turns;

    @FXML
    TextField no_of_maps;

    static int num_of_players;
    static int num_of_games;
    public static int num_of_turns;
    static int num_of_maps;
    ArrayList<String> players;
    ArrayList<String> player_names = new ArrayList<>();
    static String[][] scoreBoard;
    static int row = 1;
    static int col = 1;
    static int mapCount = 1;
    /**
     * Board initialised
     */
    public static Board board = new Board();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            CardsController cardsController = new CardsController();
            board.attachObserver((App_Risk_Game.src.interfaces.Observer) cardsController);

            no_of_players.setPromptText("CHOOSE NO OF PLAYERS");
            no_of_players.getItems().addAll("2", "3", "4");

            submit_type_of_player.setVisible(false);
            option1.setVisible(false);
            option2.setVisible(false);
            option3.setVisible(false);
            option4.setVisible(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onclickedNoOfPlayers() {

        players = new ArrayList<>();
        num_of_players = Integer.parseInt(no_of_players.getValue().toString());
        num_of_games = Integer.parseInt(no_of_games_per_map.getText());
        num_of_turns = Integer.parseInt(no_of_turns.getText());
        num_of_maps = Integer.parseInt(no_of_maps.getText());

        scoreBoard = new String[num_of_maps+1][num_of_games+1];

        no_of_games_per_map.setVisible(false);
        no_of_turns.setVisible(false);
        no_of_players.setVisible(false);
        no_of_maps.setVisible(false);
        submit_no_players.setVisible(false);
        submit_type_of_player.setVisible(true);

        switch (num_of_players) {

            case 2: {
                option1.setVisible(true);
                option1.setPromptText("CHOOSE TYPE");
                option1.getItems().addAll("Aggressive Player", "Cheater Player", "Conservative Player", "Random Player");


                option2.setVisible(true);
                option2.setPromptText("CHOOSE TYPE");
                option2.getItems().addAll("Aggressive Player", "Cheater Player", "Conservative Player", "Random Player");


            }
            break;

            case 3: {

                option1.setVisible(true);
                option1.setPromptText("CHOOSE TYPE");
                option1.getItems().addAll("Aggressive Player", "Cheater Player", "Conservative Player", "Random Player");


                option2.setVisible(true);
                option2.setPromptText("CHOOSE TYPE");
                option2.getItems().addAll("Aggressive Player", "Cheater Player", "Conservative Player", "Random Player");

                option3.setVisible(true);
                option3.setPromptText("CHOOSE TYPE");
                option3.getItems().addAll("Aggressive Player", "Cheater Player", "Conservative Player", "Random Player");

            }
            break;

            case 4: {

                option1.setVisible(true);
                option1.setPromptText("CHOOSE TYPE");
                option1.getItems().addAll("Aggressive Player", "Cheater Player", "Conservative Player", "Random Player");


                option2.setVisible(true);
                option2.setPromptText("CHOOSE TYPE");
                option2.getItems().addAll("Aggressive Player", "Cheater Player", "Conservative Player", "Random Player");


                option3.setVisible(true);
                option3.setPromptText("CHOOSE TYPE");
                option3.getItems().addAll("Aggressive Player", "Cheater Player", "Conservative Player", "Random Player");


                option4.setVisible(true);
                option4.setPromptText("CHOOSE TYPE");
                option4.getItems().addAll("Aggressive Player", "Cheater Player", "Conservative Player", "Random Player");


            }

        }

    }

    public void onclickedTypeOfPlayers() throws IOException {

        if (option1.getValue() != null)
            players.add(option1.getValue().toString());

        if (option2.getValue() != null)
            players.add(option2.getValue().toString());

        if (option3.getValue() != null)
            players.add(option3.getValue().toString());

        if (option4.getValue() != null)
            players.add(option4.getValue().toString());

        System.out.println(players.toString());

        for(int k=1;k<=num_of_players; k++){
            player_names.add("Player"+k);
        }

        PlayerCollectionTest.createPlayers(num_of_players, player_names, players);

        // --------------------------------------------------------------------

        if (mapCount <= num_of_maps) {
            System.out.println("Tournament class");
            row = mapCount;
            processTournament.takeInputMap();

        }

        //-----------------------------------------------------------------------


    }

}
