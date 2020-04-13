package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ReinforcePhase implements Initializable {

    Board board = LoadMap.board;
    Stage stage = LoadMap.stage;

    @FXML
    TextField destination_territory;
    @FXML
    GridPane reinforcePane;

    public static void main(String args[]) {

    }

    @FXML
    public void onEnter(ActionEvent ae) throws IOException {

        String dest = destination_territory.getText();
        String[][] matrix = LoadMap.getMapMatrix(LoadMap.board.getTiles());
        String neighbourMatrix[][] = LoadMap.getNeighboursPath(matrix);
        int x = 0;
        List<String> possibleSoures = new ArrayList<>();

        for (int i = 1; i < neighbourMatrix.length; i++) {

            System.out.println(neighbourMatrix[i][0] + " " + dest);
            if (neighbourMatrix[i][0].trim().equals(dest.trim())) {
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

        Player player = PlayerCollection.players.get(0);
        Set keys = player.getTerritories().keySet();
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

        TextField tf = new TextField("Enter the source");
        TextField tf2 = new TextField("no of troops");
        Button submit = new Button("submit");
        reinforcePane.add(new Label("The possible sources are : " + possibleSoures.toString()), 0, 0);
        reinforcePane.add(tf, 0, 15);
        reinforcePane.add(tf2, 0, 20);
        reinforcePane.add(submit, 0, 30);
        submit.setOnAction(e -> dynamic(tf.getText(), dest, Integer.parseInt(tf2.getText())));

    }

    void dynamic(String source, String dest, int qant) {
        GameScreenTest gameScreenTest = new GameScreenTest();
        List<Player> players = PlayerCollection.players;
        Player player = players.get(0);
        HashMap<String, Integer> territories = player.getTerritories();
        int destTroops = territories.get(dest);
        int sourceTroops = territories.get(source);

        if ((sourceTroops - qant) != 0) {

            player.setTerritories(dest, destTroops + qant);
            player.setTerritories(source, sourceTroops - qant);

        } else {
            Label tf = new Label("Reinforcement failed as troops at " + source + " get 0 after reinforcement.");
            reinforcePane.add(tf, 0, 50);
        }
        System.out.println(player.getTerritories().toString());

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
