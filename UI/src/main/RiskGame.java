package UI.src.main;

import App_Risk_Game.src.main.java.Model.Board.Tile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class RiskGame {

    public void start(Stage risk_window) throws FileNotFoundException {
        //Creating an image
        Image image = new Image(new FileInputStream("UI/images/map.png"));

        //Setting the image view
        ImageView imageView = new ImageView(image);

        //Dividing screen into 2 - game and player details side by side
        HBox mainscreen = new HBox(5);
        mainscreen.setPadding(new Insets(10, 10, 10, 10));

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
        HBox playerDetails = new HBox(5);

        //Select territory to attack
        ArrayList<Tile> neighbors = Tile.neighbour_tile;
        Label combo_box = new Label("ATTACK");
        ComboBox<String> territories = new ComboBox<>();
        territories.getItems().addAll(String.valueOf(neighbors));
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

        Button play = new Button("ROLL DICE");
        play.setOnMouseEntered(e -> play.setCursor(Cursor.HAND));

        risk_window.setTitle("WELCOME TO RISK GAME");
playerDetails.getChildren().addAll(vb,attack,troops,play);

mainscreen.getChildren().addAll(playerDetails);
        // Adding Layout to the scene
        Scene game_scene = new Scene(mainscreen);

        // Adding scene to the window
        risk_window.setScene(game_scene);
        risk_window.setTitle("RISK");
        risk_window.sizeToScene();
        risk_window.show();
    }
}
