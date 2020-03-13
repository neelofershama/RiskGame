package UI.src.main;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.omg.CORBA.INTERNAL;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StartGame {

    private int number_of_players;
    private ArrayList<String> player_names = new ArrayList<>();
    private ArrayList<Boolean> validate_status = new ArrayList<>();
    private boolean next_scene = false;

    public void start(Stage risk_window){
        // Storing the text field objects
        ArrayList<TextField> player_text_field = new ArrayList<>();
        ArrayList<Boolean> validation_status = new ArrayList<>();

        // Making the window uniform
        risk_window.setWidth(risk_window.getWidth());
        risk_window.setHeight(risk_window.getHeight());

        // Creating Label for combo box
        Label combo_box = new Label("CHOOSE NUMBER OF PLAYERS FOR THE GAME ");

        // Creating combo box to display the user with range of numbers [2, 6]
        ComboBox<Integer> range_of_players = new ComboBox<Integer>();
        range_of_players.getItems().addAll(2, 3, 4, 5, 6);
        range_of_players.setPromptText("ENTER NUMBER OF PLAYERS");

        // Creating button to get value from combo box
        Button validate_number = new Button("Validate");

        // Putting label and combo box next to each other
        HBox hb = new HBox();
        hb.setPadding(new Insets(10, 10, 10, 10));
        hb.setAlignment(Pos.TOP_LEFT);
        hb.setBackground(Background.EMPTY);
        String style = "-fx-background-color: rgba(255, 255, 255, 1);";
        hb.setStyle(style);
        hb.setSpacing(10);

        hb.getChildren().addAll(combo_box, range_of_players);


        validate_number.setOnMouseEntered(e -> validate_number.setCursor(Cursor.HAND));
        // Creating VBox layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setBackground(Background.EMPTY);
        style = "-fx-background-color: rgba(255, 255, 255, 1);";
        grid.setStyle(style);
        //grid.setSpacing(10);

        // Adding combo box and validate button
        grid.add(hb, 0, 0);
        grid.add(validate_number, 1, 1);

        // If the user click validate button we give them option to enter player names
        validate_number.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                player_text_field.clear();
                VBox vb_text_field = new VBox();
                vb_text_field.setPadding(new Insets(10, 10, 10, 10));
                vb_text_field.setAlignment(Pos.TOP_LEFT);
                vb_text_field.setBackground(Background.EMPTY);
                String style = "-fx-background-color: rgba(255, 255, 255, 1);";
                vb_text_field.setStyle(style);
                vb_text_field.setSpacing(10);

                Button submit = new Button("Submit");

                if(range_of_players.getValue()!= null) {
                    number_of_players = range_of_players.getValue();
                }
                for(int p=0;p<number_of_players;p++){
                    Label player_name = new Label("ENTER PLAYER - " + Integer.toString(p+1));
                    TextField t = new TextField();
                    t.setPromptText("PLAYER-"+Integer.toString(p+1));
                    vb_text_field.getChildren().addAll(player_name, t);
                    player_text_field.add(t);
                    if(p+1==number_of_players){
                        vb_text_field.getChildren().add(submit);
                    }
                }
                grid.add(vb_text_field, 0, 2);

                // When user click it we get the player names
                submit.setOnMouseEntered(e -> submit.setCursor(Cursor.HAND));

                // Validating Player Names
                submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        player_names.clear();
                        validation_status.clear();
                        // System.out.println(player_text_field.size());
                        boolean validate = false;
                        int check_number_player = range_of_players.getValue();
                        boolean check_player_count = true;

                        if(check_number_player!=number_of_players){
                            check_player_count = false;
                            Alert a = new Alert(Alert.AlertType.WARNING);
                            a.setContentText("OOPS YOU FORGOT TO VALIDATE THE NUMBER OF PLAYERS");
                            a.show();
                            if(! a.isShowing()){
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
                                }
                                else if (temp.replace(" ", "").length() == 0) {
                                    Alert a = new Alert(Alert.AlertType.WARNING);
                                    a.setContentText("PLAYER " + Integer.toString(i + 1) + " CANT CONTAIN SPACES");
                                    a.show();
                                    validation_status.add(false);
                                }
                                else{
                                    validation_status.add(true);
                                }

                            }
                        }
                        if(! validation_status.contains(false) && !validation_status.isEmpty()) {
                            // System.out.println(player_text_field.size());
                            for (int i = 0; i < player_text_field.size(); i++) {
                                // System.out.println(player_text_field.get(i).getText());
                                player_names.add(player_text_field.get(i).getText());
                                // System.out.println(player_names.size());
                            }
                            next_scene = true;
                        }

                        if(next_scene) {
                            RiskGame game = new RiskGame();
                            try {
                                game.start(risk_window);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }

//                        for(int i=0;i<player_names.size();i++){
//                            System.out.println(player_names.get(i));
//                        }


                    }
                });
            }
        });






        // Creating a scene
        Scene new_game = new Scene(grid);

        risk_window.setTitle("RISK GAME SETUP");
        risk_window.setScene(new_game);
        risk_window.show();
    }
}
