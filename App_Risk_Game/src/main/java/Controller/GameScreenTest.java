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

    @FXML
    ComboBox options;

    // Create a button to get the value from combo box
    @FXML
    Button submit = new Button("SUBMIT");

    @FXML
    Label current_player_name;

    Player current_player;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            options.setPromptText("CHOOSE");
            options.getItems().addAll("STATISTICS", "ATTACK", "FORTIFICATION", "SKIP");
            LoadMap.board.notifyObservers();
            stack.getChildren().add(addtable(LoadMap.getMapMatrix()));
            player_details.add(displayPlayers(), 0, 1);
            // This is to show whose turn it is in the game
            current_player = returnPlayerTurn();
            current_player_name.setText(current_player.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //layout.setAlignment(Pos.BASELINE_LEFT);
    }



    // Whenever user click on submit button


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

    // Needed to be updated using Turns Model. For now I am fetching the first player in PlayerCollection
    public Player returnPlayerTurn(){
        //return PlayerCollection.players.get(0).getName();
        return PlayerCollection.players.get(0);
    }

    // When user clicks on submit button, we get combo box value and do appropriate things
    @FXML
    public void setOnMouseClick(ActionEvent actionEvent) throws IOException {
        //System.out.println(options.getValue());
        String choice = (String) options.getValue();
        if(choice.equals("STATISTICS")){
            // NEED TO POP UP THE USER WITH HIS STATISTICS
            getPlayerStatistics();
        }

        else if(choice.equals("ATTACK")){
            attack();
        }
    }

    private void attack() {
    }

    private void getPlayerStatistics() {
//        String player_name = current_player.getName();
//        String player_color = current_player.getColor();
//        HashMap<String, Integer> territories = current_player.getTerritories();


        System.out.println(current_player.getName());
        System.out.println(current_player.getColor());
        System.out.println(current_player.getType());
        System.out.println(current_player.getId());
        System.out.println(current_player.getTerritories());

    }

}
