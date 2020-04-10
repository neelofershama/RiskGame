package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.main.java.Model.Players.PlayerCollection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameSetup implements Initializable {

    private int number_of_players;
    private ArrayList<String> player_names = new ArrayList<>();
    private ArrayList<Boolean> validate_status = new ArrayList<>();
    private boolean next_scene = false;

    @FXML
    GridPane grid;
    @FXML
    Label combo_box_label;
    @FXML
    ComboBox combo_box_range_of_players;
    @FXML
    Button validate_number;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combo_box_range_of_players.getItems().removeAll(combo_box_range_of_players.getItems());
        combo_box_range_of_players.getItems().addAll(2, 3, 4, 5, 6);
        combo_box_range_of_players.getSelectionModel().select(0);
    }

    @FXML
    public void setOnMouseClickedValidate(ActionEvent actionEvent) throws IOException {
        // Storing the text field objects
        ArrayList<TextField> player_text_field = new ArrayList<>();
        ArrayList<Boolean> validation_status = new ArrayList<>();

        player_text_field.clear();
        VBox vb_text_field = new VBox();
        vb_text_field.setPadding(new Insets(10, 10, 10, 10));
        vb_text_field.setAlignment(Pos.TOP_LEFT);
        vb_text_field.setBackground(Background.EMPTY);
        String style = "-fx-background-color: rgba(255, 255, 255, 1);";
        vb_text_field.setStyle(style);
        vb_text_field.setSpacing(10);

        Button submit = new Button("Submit");
        submit.setId("submit");

        if (combo_box_range_of_players.getValue() != null) {
            number_of_players = (int) combo_box_range_of_players.getValue();
        }
        for (int p = 0; p < number_of_players; p++) {
            Label player_name = new Label("ENTER PLAYER - " + Integer.toString(p + 1));
            TextField t = new TextField();
            t.setPromptText("PLAYER-" + Integer.toString(p + 1));
            vb_text_field.getChildren().addAll(player_name, t);
            player_text_field.add(t);
            if (p + 1 == number_of_players) {
                vb_text_field.getChildren().add(submit);
            }
        }
        grid.add(vb_text_field, 0, 2);

        // Validating Player Names
        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                player_names.clear();
                validation_status.clear();
                // System.out.println(player_text_field.size());
                boolean validate = false;
                int check_number_player = (int) combo_box_range_of_players.getValue();
                boolean check_player_count = true;

                if (check_number_player != number_of_players) {
                    check_player_count = false;
                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setContentText("OOPS YOU FORGOT TO VALIDATE THE NUMBER OF PLAYERS");
                    a.show();
                    if (!a.isShowing()) {
                        check_player_count = true;
                    }
                }

                // Validating that player name is not blank or space
                if (check_player_count) {
                    for (int i = 0; i < player_text_field.size(); i++) {
                        String name = player_text_field.get(i).getText();
                        String temp = name;
                        if (name.length() == 0) {
                            Alert a = new Alert(Alert.AlertType.WARNING);
                            a.setContentText("PLAYER " + Integer.toString(i + 1) + " CANT BE EMPTY");
                            a.show();
                            validation_status.add(false);
                        } else if (temp.replace(" ", "").length() == 0) {
                            Alert a = new Alert(Alert.AlertType.WARNING);
                            a.setContentText("PLAYER " + Integer.toString(i + 1) + " CANT CONTAIN SPACES");
                            a.show();
                            validation_status.add(false);
                        } else {
                            validation_status.add(true);
                        }

                    }
                }
                if (!validation_status.contains(false) && !validation_status.isEmpty()) {
                    // System.out.println(player_text_field.size());
                    for (int i = 0; i < player_text_field.size(); i++) {
                        // System.out.println(player_text_field.get(i).getText());
                        player_names.add(player_text_field.get(i).getText());
                        // System.out.println(player_names.size());
                    }
                    next_scene = true;
                }

//                if(next_scene) {
//                    RiskGame game = new RiskGame();
//                    try {
//                        game.start(risk_window);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
                PlayerCollection.createPlayers(number_of_players, player_names);
//                System.out.println("Number of players :- " + number_of_players);
//                System.out.println("List of player names :- ");
//                for (int i = 0; i < player_names.size(); i++) {
//                    System.out.println(player_names.get(i));
//                }
                getMap();




//        submit.setOnAction(e -> {
//            getMap();
//        });

    }
        });}
    void getMap(){
        //---------------------- LoadMap-----------------------
        try {
            System.out.println("HERE===>");
            Parent loadRoot = FXMLLoader.load(getClass().getResource("/App_Risk_Game/src/main/java/View/LoadMap.fxml"));

            Scene loadMapScene = new Scene(loadRoot);
            Stage loadMapStage = new Stage();
            loadMapStage.setTitle("Map Loaded");
            loadMapStage.setScene(loadMapScene);
            loadMapStage.show();

            //-------------------------------------------------------
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }}

