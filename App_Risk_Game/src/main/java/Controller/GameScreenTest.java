package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollection;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollectionTest;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
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

    @FXML
    Label attack_from;

    @FXML
    Label attack_to;

    @FXML
    Button start;

    ComboBox owned_territories = new ComboBox();
    ComboBox other_territories = new ComboBox();

    @FXML
    HBox hbox_from;

    @FXML
    HBox hbox_to;

    @FXML
    private Button attackButton;


    Player current_player;

    Board board = LoadMap.board;
    public static int reinforceCount = 3;
    public Stage reinforceStage = new Stage();


//    @FXML
//    void attackButtonClicked(ActionEvent event) throws IOException {
//
//        try {
//
//            Parent RootLoad = FXMLLoader.load(getClass().getResource("/App_Risk_Game/src/main/java/View/AttackPhase.fxml"));
//            Scene loadAttackScene = new Scene(RootLoad);
//            Stage loadAttackStage = new Stage();
//            loadAttackStage.setTitle("Attack Phase Loaded");
//            loadAttackStage.setScene(loadAttackScene);
//            loadAttackStage.show();
//        }
//        catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            options.setPromptText("CHOOSE");
            //options.getItems().addAll("STATISTICS","ATTACK", "FORTIFICATION", "SKIP", "REINFORCEMENT");
            options.getItems().addAll("STATISTICS", "ATTACK");
            LoadMap.board.notifyObservers();

            if (!LoadMap.LoadMapGlobalVariables.gsFlag)
                options.setVisible(false);

            else
                options.setVisible(true);

            stack.getChildren().add(addtable(LoadMap.getMapMatrix(board.getTiles())));
            player_details.add(displayPlayers(), 0, 1);
            // This is to show whose turn it is in the game

            //current_player = returnPlayerTurn();

            current_player = PlayerCollectionTest.getTurn();
            current_player_name.setText(current_player.getName());
            current_player_name.setText(current_player.getName());
            current_player_name.setTextFill(javafx.scene.paint.Color.web(current_player.getColor()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //layout.setAlignment(Pos.BASELINE_LEFT);
    }

    // Whenever user click on submit button
    @FXML
    void onStart(ActionEvent event) throws IOException {
        reinforcementTest();
    }

    public TableView addtable(String[][] a) throws IOException {
        TableView view = new TableView();

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

    public VBox displayPlayers() {
        VBox vb = new VBox();
        vb.setSpacing(5);
        List<Player> playerList = PlayerCollectionTest.players;
        for (Player p :
                playerList) {
            Label player_name = new Label(p.getName());
            String color = p.getColor();
            player_name.setTextFill(javafx.scene.paint.Color.web(color));
            vb.getChildren().add(player_name);
        }
        return vb;
    }

//    private void getPlayerterritories(String name) {
//        Menu territoriesList = new Menu();
//        Player p = null;
//        Iterator iterator = PlayerCollection.players.listIterator();
//        while (iterator.hasNext()){
//            Player player = (Player)iterator.next();
//            if(player.getName().equals(name))
//            {
//                p = player;
//                break;
//            }
//        }
//        p.getTerritories().forEach(t-> {
//            territoriesList.getItems().add(new MenuItem(t));
//            territoriesList.show();
//        });

//    }

    // Needed to be updated using Turns Model. For now I am fetching the first player in PlayerCollection
    public Player returnPlayerTurn() {
        //return PlayerCollection.players.get(0).getName();
        return PlayerCollection.players.get(0);
    }

    // When user clicks on submit button, we get combo box value and do appropriate things
    @FXML
    public void setOnMouseClick(ActionEvent actionEvent) throws IOException {
        //System.out.println(options.getValue());
        String choice = (String) options.getValue();
        if (choice.equals("STATISTICS")) {
            // NEED TO POP UP THE USER WITH HIS STATISTICS
            getPlayerStatistics();
        } else if (choice.equals("ATTACK")) {
            attack();
        } else if (choice.equals("FORTIFICATION")) {
            // fortification();
            fortificationTest();
        } else if (choice.equals("REINFORCEMENT")) {
            reinforcementTest();
        } else if (choice.equals("SKIP")) {
            PlayerCollectionTest.updateTurn();
            PlayerCollectionTest.goBackToGameScreen();
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
        }
    }

    public void reinforcement() {

        try {
            GameScreenTest gt = new GameScreenTest();
            Parent reinforceRoot = FXMLLoader.load(GameScreenTest.class.getResource("/App_Risk_Game/src/main/java/View/reinforce.fxml"));
            Label label = (Label) reinforceRoot.getChildrenUnmodifiable().get(3);
            String message = "You have " + reinforceCount + " troops to assign to your territories. Following are the troops for given territories : " + PlayerCollection.players.get(0).getTerritories().toString();
            label.setText(message);
            label.setMinWidth(message.length() * 10);
            label.setMinHeight(message.length());
            Scene scene = new Scene(reinforceRoot);
            reinforceStage.setScene(scene);
            reinforceStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reinforcementTest() throws IOException {
        Parent reinforceRoot = FXMLLoader.load(GameScreenTest.class.getResource("/App_Risk_Game/src/main/java/View/ReinforceTest.fxml"));
        Scene loadReinforceScene = new Scene(reinforceRoot);
        Stage loadReinforceStage = new Stage();
        loadReinforceStage.setTitle("REINFORCEMENT PHASE LOADED");
        loadReinforceStage.setScene(loadReinforceScene);
        loadReinforceStage.show();
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    void fortification() {
        try {
            // Parent reinforceRoot = FXMLLoader.load(getClass().getResource("/App_Risk_Game/src/main/java/View/reinforce.fxml"));
            Parent reinforceRoot = FXMLLoader.load(getClass().getResource("/App_Risk_Game/src/main/java/View/FortifyTest.fxml"));
            Label label = (Label) reinforceRoot.getChildrenUnmodifiable().get(2);
            String message = " Following are the troops for given territories : " + current_player.getTerritories().toString();
            label.setText(message);
            label.setMinWidth(message.length() * 10);
            label.setMinHeight(message.length());
            Scene scene = new Scene(reinforceRoot);
            Stage reinforceStage = new Stage();
            reinforceStage.setScene(scene);
            reinforceStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void fortificationTest() {
        try {
            Parent reinforceRoot = FXMLLoader.load(getClass().getResource("/App_Risk_Game/src/main/java/View/FortifyTest.fxml"));
            Scene loadAttackScene = new Scene(reinforceRoot);
            Stage loadAttackStage = new Stage();
            loadAttackStage.setTitle("FORTIFICATION PHASE LOADED");
            loadAttackStage.setScene(loadAttackScene);
            loadAttackStage.show();
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void getPlayerStatistics() {

        try {

            List<String> list = new ArrayList<>();
            list.add(current_player.getName());
            list.add(current_player.getColor());
            list.add(current_player.getType());
            list.add(String.valueOf(current_player.getId()));
            list.add(current_player.getTerritories().toString());

            TableView view = new TableView();


            TableColumn firstNameCol = new TableColumn("Player Name");
            firstNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            makeHeaderWrappable(firstNameCol);
            firstNameCol.setPrefWidth(100);

            TableColumn secCol = new TableColumn("Color");
            secCol.setCellValueFactory(new PropertyValueFactory<>("color"));
            makeHeaderWrappable(secCol);
            secCol.setPrefWidth(100);

            TableColumn thirdCol = new TableColumn("Type");
            thirdCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            makeHeaderWrappable(thirdCol);
            thirdCol.setPrefWidth(100);

            TableColumn fourCol = new TableColumn("ID");
            fourCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            makeHeaderWrappable(fourCol);
            fourCol.setPrefWidth(100);

            TableColumn fiveCol = new TableColumn("Territories");
            fiveCol.setCellValueFactory(new PropertyValueFactory<>("territories"));
            makeHeaderWrappable(fiveCol);
            fiveCol.setPrefWidth(500);

            view.getColumns().addAll(firstNameCol);
            view.getColumns().addAll(secCol);
            view.getColumns().addAll(thirdCol);
            view.getColumns().addAll(fourCol);
            view.getColumns().addAll(fiveCol);


            view.getItems().add(new Player(current_player.getName(), "", "", current_player.getId(), current_player.getTerritories()));

            Pane layout = new VBox(10);
            layout.setStyle("-fx-padding: 10;");
            layout.getChildren().addAll(view);
            Stage stage = new Stage();
            stage.setScene(new Scene(layout));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void makeHeaderWrappable(TableColumn col) {
        Label label = new Label(col.getText());
        label.setStyle("-fx-padding: 8px;");
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);

        StackPane stack = new StackPane();
        stack.getChildren().add(label);
        stack.prefWidthProperty().bind(col.widthProperty().subtract(5));
        label.prefWidthProperty().bind(stack.prefWidthProperty());
        col.setText(null);
        col.setGraphic(stack);
    }

    private void getPlayerTerritories() {
        HashMap<String, Integer> territories = current_player.getTerritories();

        Iterator iterator = territories.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) iterator.next();
            owned_territories.getItems().add(mapElement.getKey());
            // System.out.println(mapElement.getKey());
        }
    }

    private void attack() {
        System.out.println("Attack");
        try {

            Parent RootLoad = FXMLLoader.load(getClass().getResource("/App_Risk_Game/src/main/java/View/AttackPhase.fxml"));
            Scene loadAttackScene = new Scene(RootLoad);
            Stage loadAttackStage = new Stage();
            loadAttackStage.setTitle("Attack Phase Loaded");
            loadAttackStage.setScene(loadAttackScene);
            loadAttackStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Need to get neighbour countries for the given country STATUS :- PENDING
    private void getAttachableTerritories(String selected_country) {
        System.out.println(selected_country);
    }

}
