package UI.src.main;

import App_Risk_Game.src.main.java.Model.Board.Tile;
import App_Risk_Game.src.main.java.Model.Score.Dice;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class RiskGame {

    public void start(Stage risk_window, ArrayList<String> player_names) throws FileNotFoundException {

        final String[] territory_to_attack = {null};
        //Creating an image
        Image image = new Image(new FileInputStream("UI/images/map.png"));

        //Setting the image view
        ImageView imageView = new ImageView(image);

        //Dividing screen into 2 - game and player details side by side
        HBox screen = new HBox(5);
        screen.setPadding(new Insets(10, 10, 10, 10));

        // Creating VBox layout to insert image and gaming options

        VBox vb = new VBox();
        vb.setPadding(new Insets(10, 10, 10, 10));
        vb.setAlignment(Pos.CENTER);
        vb.setBackground(Background.EMPTY);
        String style = "-fx-background-color: rgba(0, 0, 0, 1);";
        vb.setStyle(style);
        vb.setSpacing(10);
        vb.getChildren().add(imageView);

        //PLAYER DETAILS
        VBox playerDetails = new VBox(10);
        Label player_details = new Label("PLAYER DETAILS");
        player_details.setAlignment(Pos.CENTER);
        //Select territory to attack
        ArrayList<Tile> neighbors = Tile.neighbour_tile;

        //TODO replace with neighboring countries list later
       ArrayList<String> terr = new ArrayList<>();
terr.add("ABC");
terr.add("XYZ");

        Label combo_box = new Label("ATTACK");
        ComboBox<String> territories = new ComboBox<>();
        //territories.getItems().addAll(String.valueOf(neighbors));
        territories.getItems().addAll(terr);
        territories.setPromptText("Select Territory");
        HBox attack = new HBox();
        attack.setPadding(new Insets(10, 10, 10, 10));
        attack.setAlignment(Pos.TOP_LEFT);
        attack.setBackground(Background.EMPTY);
        String style1 = "-fx-background-color: rgba(255, 255, 255, 1);";
        attack.setStyle(style1);
        attack.setSpacing(10);
        attack.getChildren().addAll(combo_box, territories);

        //Select no.of troops to attack
        Label no_of_troops = new Label("No of Troops");
        ComboBox<Integer> range_of_troops = new ComboBox<Integer>();
        range_of_troops.getItems().addAll(1,2, 3);
        range_of_troops.setPromptText("0");
        HBox troops = new HBox();
        troops.setPadding(new Insets(10, 10, 10, 10));
        troops.setAlignment(Pos.TOP_LEFT);
        troops.setBackground(Background.EMPTY);
        String style2 = "-fx-background-color: rgba(255, 255, 255, 1);";
        troops.setStyle(style2);
        troops.setSpacing(10);

        troops.getChildren().addAll(no_of_troops, range_of_troops);

        Button roll_dice = new Button("ROLL DICE");
        roll_dice.setOnMouseEntered(e -> roll_dice.setCursor(Cursor.HAND));

        risk_window.setTitle("WELCOME TO RISK GAME");
playerDetails.getChildren().addAll(player_details,attack,troops,roll_dice);

screen.getChildren().addAll(vb,playerDetails);
        // Adding Layout to the scene
        Scene game_scene = new Scene(screen);


        roll_dice.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                territory_to_attack[0] = territories.getValue();
                int troop_to_attack = range_of_troops.getValue();
                AttackPhase phase = new AttackPhase();
                phase.start(risk_window);
                if (troop_to_attack == 3){
                    Dice.setNoOfDice(3);
                    List <Integer> attack_scores = Dice.getScore();
                }

            }});

        // Adding scene to the window
        risk_window.setScene(game_scene);
        risk_window.setTitle("RISK");
        risk_window.sizeToScene();
        risk_window.show();
    }
}
