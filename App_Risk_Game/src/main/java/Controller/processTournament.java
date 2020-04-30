package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Board.Tile;
import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollectionTest;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class processTournament {

    @FXML
    Button LoadFile;

    static String[][] scoreBoard;
    static Player player;
    /**
     * List of continents
     */
    static HashMap<String, Integer> continents = new HashMap<String, Integer>();
    /**
     * Board initialised
     */
    public static Board board = new Board();


    public static void takeInputMap() throws IOException {
        System.out.println("processTournament class");
        Parent root = FXMLLoader.load(processTournament.class.getResource("/App_Risk_Game/src/main/java/View/LoadTournamentMap.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method is used to load the map from an existing file
     *
     * @throws FileNotFoundException
     */

    public static boolean loadMap(String path) throws FileNotFoundException {

        // ---------------- Reading file-------------------------------
        File file_map = new File(path.trim());
        HashMap<String, List<String>> neighborsList = new HashMap<>();

        if (file_map.exists()) {
            Scanner myReader = new Scanner(file_map);

            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();

                if ("[Continents]".equals(data.trim())) {
                    System.out.println("==>" + data.trim());
                    data = myReader.nextLine();

                    while (!data.equals("[Territories]")) {
                        System.out.println(data);
                        String split[] = data.split("=");
                        String continent_name = split[0];
                        String no_of_countries = split[1];
                        continents.put(continent_name, Integer.parseInt(no_of_countries));
                        data = myReader.nextLine();
                    }
                }

                System.out.println("continents: " + continents.toString());
                Tournament.board.setContinents(continents);

                if ("[Territories]".equals(data)) {

                    while (myReader.hasNextLine()) {
                        String country_input = myReader.nextLine();

                        String split[] = country_input.split(",");
                        String country = split[0];
                        int x = Integer.parseInt(split[1]);
                        int y = Integer.parseInt(split[2]);
                        String continent = split[3];
                        List<String> neighbours = new ArrayList<>();

                        for (int i = 4; i < split.length; i++) {
                            neighbours.add(split[i]);
                        }

                        Tournament.board.createTile(country, x, y, continent);
                        neighborsList.put(country, neighbours);

                    }
                }

                for (Map.Entry entry : neighborsList.entrySet()) {
                    Tournament.board.setNeighbourTile((List<String>) entry.getValue(), (String) entry.getKey());
                }


            }
            System.out.println("Risk Map Loaded!");
            HashMap<String, Tile> map = Tournament.board.getTiles();

            System.out.println("Map" + map.keySet().toString());


            if (Tournament.board.getContinents().size() == 0 || Tournament.board.getTiles().size() == 0)
                return false;
            return true;

        } else {
            System.out.println("File does not exist! ");
            return false;
        }

    }


    /**
     * prompts user to select a map file
     *
     * @param event
     */
    @FXML
    public void onMouseClickedLoadFile(ActionEvent event) {
        try {
            boolean res = takefile();

            if (!res) {
                Stage stg = (Stage) LoadFile.getScene().getWindow();
                stg.close();


            } else {
                Stage stg = (Stage) LoadFile.getScene().getWindow();
                stg.close();

                CardsController cardsController = new CardsController();
                board.attachObserver((App_Risk_Game.src.interfaces.Observer) cardsController);
                board.notifyObservers();
                Tournament.mapCount++;
                for (int i = 1; i <= Tournament.num_of_games; i++) {
                    Tournament.col = i;
                    processTournament();
                }

                if (Tournament.mapCount <= Tournament.num_of_maps) {

                    Tournament.row = Tournament.mapCount;
                    reset();
                    takeInputMap();

                }

                if (Tournament.mapCount > Tournament.num_of_maps)
                    displayScores();


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean takefile() throws FileNotFoundException {
        FileChooser chooser = new FileChooser();
        File selectedFile = chooser.showOpenDialog(null);

        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            System.out.println("File selected: " + filePath);

            boolean result = loadMap(filePath);
            return result;

        } else {
            System.out.println("File selection cancelled.");
            return false;
        }

    }

    public static void processTournament() throws IOException {

        for (int i = 0; i < Tournament.num_of_turns; i++) {

            System.out.println("Turn = " + i);
            player = PlayerCollectionTest.getTurn();

            if (player.getTerritories().size() != 0)
                player.reinforce();

            // ---- Depends on the strategy -------
            if (player.getTerritories().size() != 0)
                player.attack();

//            List<String> removeTerr = new ArrayList<>();
//            Iterator key = player.getTerritories().keySet().iterator();
//            while (key.hasNext()) {
//                String country = key.next().toString();
//                if (player.getTerritories().get(country) == 0)
//                    removeTerr.add(country);
//            }
//
//            Iterator removeTerrIterator = removeTerr.iterator();
//            while (removeTerrIterator.hasNext()) {
//                String country = removeTerrIterator.next().toString();
//                player.getTerritories().remove(country);
//            }

            checkContinentsOwned();


            // --------------------------- Winning check ------------------------------------------

            int total_territories_count = Tournament.board.getTiles().keySet().size();
            int currentplayer_territories_count = player.territories.keySet().size();
//            if (currentplayer_territories_count >= (total_territories_count / (PlayerCollectionTest.players.size())) + 1) {

            double d = ((currentplayer_territories_count * 100) / total_territories_count);
            if (d >= 75) {
                 String winner = player.getBehaviorType() +"-"+
                            player.getName();
                //----------------------------   Storing in scoreboard  -----------------------------------------
                Tournament.scoreBoard[Tournament.row][Tournament.col] = winner;
                break;
            }

            if (player.getTerritories().size() != 0)
                player.fortify();


            PlayerCollectionTest.updateTurn();

        }
        System.out.println("player = " + player.getName() + " " + player.getContinents_owned());
        System.out.println("ROW= " + Tournament.row + "COLUMN = " + Tournament.col);
        if (Tournament.scoreBoard[Tournament.row][Tournament.col] == null)
            Tournament.scoreBoard[Tournament.row][Tournament.col] = "Draw";


    }

    public static void checkContinentsOwned() {

        List<String> countriesOwned = new ArrayList<>();
        Iterator key = player.getTerritories().keySet().iterator();
        while (key.hasNext()) {
            countriesOwned.add(key.next().toString());
        }

        HashMap<String, List<String>> continentsAndCountries = Tournament.board.getContinentsAndCountries();
        System.out.println("continentsAndCountries==" + continentsAndCountries.toString());
        Iterator c = continentsAndCountries.keySet().iterator();
        while (c.hasNext()) {
            String continent = c.next().toString();
            if (countriesOwned.containsAll(continentsAndCountries.get(continent)) && !player.getContinents_owned().contains(continent))
                player.getContinents_owned().add(continent);
        }

    }

    public static void reinforce() {

        int maxTroops = 3;
        HashMap<String, Integer> terr = player.getTerritories();
        System.out.println("Player : " + player.getName() + " territories : " + terr.toString());
        while (maxTroops != 0) {

            int troops = getRandomNumber(maxTroops);
            maxTroops = maxTroops - troops;
            if (terr.size() != 0) {
                String[] keyArray = terr.keySet().toArray(new String[terr.size()]);
                String territory = keyArray[getRandomNumber(terr.size() - 1)];
                player.setTerritory(territory, troops);
            }
        }

    }
//
//    public static void fortify() {
//
//        HashMap<String, Integer> terr = player.getTerritories();
//
//        String[] keyArray = terr.keySet().toArray(new String[terr.size()]);
//
//        String sourceTerritory = keyArray[getRandomNumber(terr.size())];
//        String destTerritory = keyArray[getRandomNumber(terr.size())];
//
//        if (terr.get(sourceTerritory) != 1) {
//            int assignTroops = getRandomNumber(terr.get(sourceTerritory) - 1);
//            player.setTerritory(sourceTerritory, terr.get(sourceTerritory) - assignTroops);
//            player.setTerritory(destTerritory, terr.get(destTerritory) + assignTroops);
//        } else
//            fortify();
//
//    }

    public static void displayScores() {

        System.out.println(" ====== SCOREBOARD ===== ");

        for (int i = 1; i <= Tournament.col; i++) {
            Tournament.scoreBoard[0][i] = "Game" + i;

        }
        for (int j = 1; j <= Tournament.row; j++) {
            Tournament.scoreBoard[j][0] = "Map" + j;

        }

        for (int i = 0; i < Tournament.row; i++) {
            for (int j = 0; j < Tournament.col; j++) {
                System.out.println(Tournament.scoreBoard[i][j] + "   ");
            }
            System.out.println();

        }

        StackPane root = new StackPane();
        TableView view = new TableView();

        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(Tournament.scoreBoard));
        //  data.remove(0);
        for (int i = 0; i < Tournament.scoreBoard[0].length; i++) {
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
        root.getChildren().add(view);
        Stage pri = new Stage();
        pri.setScene(new Scene(root, 300, 250));
        pri.show();
    }


    public static int getRandomNumber(int limit) {
        Random r = new Random();
        return r.nextInt((limit - 0) + 1) + 0;
    }


    public void reset() {
//        Boolean gsFlag = false;
//        Boolean phaseComplete = false;
        CardsController cardsController = new CardsController();
        Tournament.board.attachObserver((App_Risk_Game.src.interfaces.Observer) cardsController);
        continents = new HashMap<String, Integer>();
        Tournament.board = new Board();
        HashMap<String, List<String>> neighborsList = new HashMap<>();
        // int count_turns = 0;

    }
}
