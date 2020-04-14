package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Players.Player;
// import App_Risk_Game.src.main.java.Model.Players.PlayerCollection;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollectionTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class FortifyTest implements Initializable {

    @FXML
    Label player_name;

    @FXML
    VBox root;

    @FXML
    Label label_from;

    @FXML
    Label territories_owned;

    @FXML
    ComboBox<String> from;

    HashMap<String, Integer> territories;
    Board board = LoadMap.board;
    Stage stage = LoadMap.stage;
    String source_territory;
    String destination_territory;

    private Player current_player;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        current_player = PlayerCollectionTest.getTurn(); // Need to get current player turn from TURNS MODULE
        player_name.setText(current_player.getName());
        player_name.setTextFill(Color.web(current_player.getColor()));
        territories = current_player.getTerritories();
        foritfy();
    }

    private void foritfy() {
        territories_owned.setText("TERRITORIES OWNED :- " + territories.toString());
        label_from.setText("MOVE THE TROOPS FROM ");


        if(!territories.isEmpty()){ // HAVE TO MAKE SURE ITS NULL OR EMPTY
            Iterator iterator = territories.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry)iterator.next();
                int troops = (int) mapElement.getValue();
                if(troops > 1)
                    from.getItems().add((String) mapElement.getKey());
                // System.out.println(mapElement.getKey());
            }
            from.setPromptText("TERRITORY");
        }
        else{
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText(current_player.getName() + " HAS NO TERRITORIES");
            a.show();
        }

    }


    public void onEnter(ActionEvent actionEvent) throws IOException {
        // Fixing bugs
        System.out.println("Root size :- " + root.getChildren().size());
        int root_size = root.getChildren().size();
        if(root_size > 4){
            root.getChildren().remove(4,root_size-1);
        }
        source_territory = from.getValue();
        String[][] matrix = LoadMap.getMapMatrix(LoadMap.board.getTiles());
        String neighbourMatrix[][] = LoadMap.getNeighboursPath(matrix);
        int x = 0;
        List<String> possibleSoures = new ArrayList<>();

        for (int i = 1; i < neighbourMatrix.length; i++) {

            System.out.println(neighbourMatrix[i][0] + " " + source_territory);
            if (neighbourMatrix[i][0].trim().equals(source_territory.trim())) {
                System.out.println(i);
                x = i;
                break;

            }
        }

        for (int i = 0; i < neighbourMatrix.length; i++) {
            if (neighbourMatrix[x][i].toLowerCase().equals("yes")) {
                System.out.println(neighbourMatrix[0][i]);
                possibleSoures.add(neighbourMatrix[0][i]);

            }
        }

//        Player current_player = PlayerCollection.players.get(0);
        Set keys = current_player.getTerritories().keySet();
        List<String> removeSources = new ArrayList<>();
        System.out.println("player territories " + keys.toString());
        System.out.println("possible sources " + possibleSoures.toString());


        for(int i=0;i<possibleSoures.size();i++) {
            String source= possibleSoures.get(i);
            System.out.println("Checking for " + source);
            if(!(keys.contains(possibleSoures.get(i)))){
                System.out.println("Removing " + source);
                removeSources.add(source);
            }
        }
        possibleSoures.removeAll(removeSources);

        // CREATING COMBOBOX TO GET THE NUMBER OF TROOPS FROM THE PLAYER FOR CHOOSE SOURCE
        Label lable_troop = new Label("CHOOSE THE NUMBER OF TROOPS TO MOVE ");
        root.getChildren().add(lable_troop);
        ComboBox<Integer> troops = new ComboBox<>();
        troops.setPromptText("TROOPS");
        int number_of_troops = territories.get(source_territory);
        System.out.println("NUMBER OF TROOPS " + number_of_troops);
        for(int i=1; i<=number_of_troops-1;i++){
            troops.getItems().add(i);
        }
        root.getChildren().add(troops);

        // CREATING COMBOBOX TO GET THE SOURCE TERRITORY
        Label label_to = new Label("CHOOSE THE TERRITORY TO MOVE THE TROOPS ");
        root.getChildren().add(label_to);
        ComboBox<String> to = new ComboBox<>();
        to.setPromptText("DESTINATION");

        if(!possibleSoures.isEmpty()){
            for(int i=0;i<possibleSoures.size();i++){
                to.getItems().add(possibleSoures.get(i));
            }
            root.getChildren().add(to);
        }

        else{
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("THERE IS NO CONNECTION BETWEEN THE CHOICE OF YOUR TERRITORIES");
            a.show();
        }
//        TextField tf = new TextField("Enter the source");
//        TextField tf2 = new TextField("no of troops");
        Button submit = new Button("submit");
//        root.getChildren().add(new Label("The possible sources are : " + possibleSoures.toString()));
//        root.getChildren().add(tf);
//        root.getChildren().add(tf2);
        root.getChildren().add(submit);
        submit.setOnAction(e -> {
            try {
                dynamic(source_territory, to.getValue(), troops.getValue());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }

    void dynamic(String source, String dest, int qant) throws IOException {
//        GameScreenTest gameScreenTest = new GameScreenTest();
//        List<Player> players = PlayerCollectionTest.players;
//        Player player = players.get(0);
        HashMap<String, Integer> territories = current_player.getTerritories();
        System.out.println(territories);
        System.out.println("Source :- " + source);
        System.out.println("Destination :- " + dest);
        int destTroops = territories.get(dest);
        int sourceTroops = territories.get(source);

        if ((sourceTroops - qant) != 0) {

            current_player.setTerritories(dest, destTroops + qant);
            current_player.setTerritories(source, sourceTroops - qant);

        } else {
            Label tf = new Label("Reinforcement failed as troops at " + source + " get 0 after reinforcement.");
            root.getChildren().add(tf);
        }
        System.out.println(current_player.getTerritories().toString());
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
        PlayerCollectionTest.updateTurn();
        PlayerCollectionTest.goBackToGameScreen();

    }


}
