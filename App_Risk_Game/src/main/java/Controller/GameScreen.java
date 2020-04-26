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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameScreen implements Initializable {
    @FXML
    VBox layout;
    @FXML
    StackPane root;
    @FXML
    HBox playerdetails;

    Board board = LoadMap.board;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            LoadMap.board.notifyObservers();
            root.getChildren().add(addtable(LoadMap.getMapMatrix(board.getTiles())));
            playerdetails.getChildren().addAll(displayPlayers());
        } catch (IOException e) {
            e.printStackTrace();
        }
        layout.setAlignment(Pos.BASELINE_LEFT);
    }

    /**
     * creates a table view for displaying the countries and its neighbors
     * @param a
     * @return
     * @throws IOException
     */
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

    /**
     * creates players based on no of players selected for the game
     * @return
     */
    public List<Button> displayPlayers(){
        List<Button> list = new ArrayList<>();
        List<Player> playerList = PlayerCollection.players;
        for (Player p:
             playerList) {
            Button b = new Button(p.getName());
          b.setOnMouseClicked(new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent event) {
                  getPlayerterritories(p.getName());
              }
          });
            list.add(b);
        }
        return list;
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


}
