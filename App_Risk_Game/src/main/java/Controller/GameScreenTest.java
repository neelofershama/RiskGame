package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameScreenTest implements Initializable {
    @FXML
    VBox root;

    @FXML
    StackPane stack;

    @FXML
    GridPane player_details;

    ComboBox<String> cb = new ComboBox<>();

    // Create a button to get the value from combo box
    Button submit = new Button("SUBMIT");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            cb.setPromptText("CHOOSE");
            cb.getItems().addAll("STATISTICS", "ATTACK", "FORTIFICATION", "SKIP");
            LoadMap.board.notifyObservers();
            stack.getChildren().add(addtable(LoadMap.getMapMatrix()));
            player_details.add(displayPlayers(), 0, 1);
            // This is to show whose turn it is in the game
            // player_details.getChildren().add(setUpOptionForPlayer());
            player_details.add(setUpListOfPlayers(), 1, 1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //layout.setAlignment(Pos.BASELINE_LEFT);
    }

    private HBox setUpListOfPlayers() {
        HBox hb = new HBox();
        hb.setSpacing(10);

        // Get the player name
        Label player_name = new Label(returnPlayerTurn());
        hb.getChildren().addAll(player_name, cb, submit);
        return hb;
    }

    public TableView addtable(String[][] a) throws IOException {
        TableView view= new TableView();

        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(a));
        //  data.remove(0);
        for (int i = 0; i < a[0].length; i++) {
            TableColumn tc = new TableColumn(" ");
            final int colNo = i;
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc.setPrefWidth(50);
            view.getColumns().add(tc);
        }
        view.setItems(data);
        return view;
    }
    public VBox displayPlayers(){
        VBox vb = new VBox();
        vb.setSpacing(5);
        List<Player> playerList = PlayerCollection.players;
        for (Player p:
                playerList) {
            Label player_name = new Label(p.getName());
            vb.getChildren().add(player_name);
//            b.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event) {
//                    getPlayerterritories(p.getName());
//                }
//            });
        }
        return vb;
    }

    /*
    HBox hb = new HBox();
            Button b = new Button(p.getName());
            Label player_name = new Label(p.getName());
            ComboBox<String> cb = new ComboBox<>();
            cb.setPromptText("CHOOSE");
            cb.getItems().addAll("STATISTICS", "ATTACK", "FORTIFICATION", "SKIP");
     */
    private void getPlayerterritories(String name) {
        Menu territoriesList = new Menu();
        Player p = null;
        Iterator iterator = PlayerCollection.players.listIterator();
        while (iterator.hasNext()){
            Player player = (Player)iterator.next();
            if(player.getName().equals(name))
            {
                p = player;
                break;
            }
        }
//        p.getTerritories().forEach(t-> {
//            territoriesList.getItems().add(new MenuItem(t));
//            territoriesList.show();
//        });

    }

    // USING THIS METHOD TO GET WHOSE TURN IT IS IN THE GAME
    public String returnPlayerTurn(){
        return "a";
    }


}
