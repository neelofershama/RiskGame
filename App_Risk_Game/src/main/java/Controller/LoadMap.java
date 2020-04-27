package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.interfaces.Observer;
import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Board.Tile;
import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollection;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollectionTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

public class LoadMap implements Initializable, Observer {

    @Override
    public void update(App_Risk_Game.src.interfaces.Observable observable) {

    }

    /**
     * scanner is initialised
     */
    private static Scanner sc = new Scanner(System.in);
    /**
     * List of continents
     */
    static HashMap<String, Integer> continents = new HashMap<String, Integer>();
    /**
     * Board initialised
     */
    public static Board board = new Board();

    @FXML
    Button LoadFile;
    static Stage stage = new Stage();

    public static String[][] neighbourMatrix;
    HashMap<String, Integer> playerTerritories = new HashMap<>();
    public static Player player;

    @FXML
    Button NewMap;
    @FXML
    TextField no_of_continents;
    @FXML
    TextField continent_name;
    @FXML
    TextField no_of_countries_in_continent;
    @FXML
    TextField no_of_countries;
    @FXML
    TextField country_name;
    @FXML
    TextField adj_countries;
    @FXML
    TextField continent_name_belongs;
    @FXML
    TextField X;
    @FXML
    TextField Y;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CardsController cardsController = new CardsController();

        board.attachObserver((App_Risk_Game.src.interfaces.Observer) cardsController);

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

                Parent loadRoot = FXMLLoader.load(getClass().getResource("/App_Risk_Game/src/main/java/View/incorrectMap.fxml"));
                Scene incorrectMapScene = new Scene(loadRoot);
                Stage incorrectStage = new Stage();
                incorrectStage.setTitle("Incorrect MAP");
                incorrectStage.setScene(incorrectMapScene);
                incorrectStage.show();
                System.out.println("GAME QUITED");

            } else {
                Stage stg = (Stage) LoadFile.getScene().getWindow();
                stg.close();
                String[][] matrix = getMapMatrix(board.getTiles());
                Parent loadRoot = FXMLLoader.load(getClass().getResource("/App_Risk_Game/src/main/java/View/GameScreenTest.fxml"));
                Scene loadMapScene = new Scene(loadRoot);
                Stage loadMapStage = new Stage();
                loadMapStage.setTitle("Map Loaded");
                loadMapStage.setScene(loadMapScene);
                loadMapStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //prompts user to create a new map
    @FXML
    public void onMouseClickedNewMap(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/App_Risk_Game/src/main/java/View/createMap.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method is used to load the map from an existing file
     *
     * @throws FileNotFoundException
     */

    public static boolean loadMap(String path) throws FileNotFoundException {

//        System.out.println(" Enter map file address");
//        String path = sc.next();

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
                board.setContinents(continents);

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

                        board.createTile(country, x, y, continent);
                        neighborsList.put(country, neighbours);

                    }
                }

                for (Map.Entry entry : neighborsList.entrySet()) {
                    board.setNeighbourTile((List<String>) entry.getValue(), (String) entry.getKey());
                }


            }
            System.out.println("Risk Map Loaded!");
            HashMap<String, Tile> map = board.getTiles();

            System.out.println("Map" + map.keySet().toString());

            if (board.getContinents().size() == 0 || board.getTiles().size() == 0)
                return false;
            return true;

        } else {
            System.out.println("File does not exist! ");
            return false;
        }

    }

    /**
     * Method is used to create a map via console by taking arguments from the gamer
     *
     * @throws IOException
     */
    public static void createMap() throws IOException {

        int no_of_continents;
        HashMap<String, List<String>> neighborsList = new HashMap<>();

        System.out.println("Enter the number of Continents");
        if (sc.hasNextInt()) {
            no_of_continents = sc.nextInt();

            for (int i = 0; i < no_of_continents; i++) {

                System.out.println("Enter continent name");
                String continent = sc.next();

                System.out.println("Enter no of countries in continent");

                int no_of_countries = 0;
                if (sc.hasNextInt())
                    no_of_countries = sc.nextInt();

                continents.put(continent, no_of_countries);
            }
        } else {
            System.out.println("Invalid input! Enter again :");
            sc.next();
        }

        System.out.println("Enter no of countries");
        int no_of_countries = sc.nextInt();
        List<String> neighbours = new ArrayList<>();

        for (int i = 0; i < no_of_countries; i++) {

            System.out.println("Enter country name");
            String country = sc.next();

            System.out.println("Enter x and y co-ordinate");
            int x = sc.nextInt();
            int y = sc.nextInt();

            System.out.println("Enter continent it belongs to");
            String continent = sc.next();

            System.out.println("Enter no of adjacent countries");
            int no_adj_c = sc.nextInt();
            while (no_adj_c != 0) {
                System.out.println("Enter adjacent country");
                neighbours.add(sc.next());
                no_adj_c--;
            }

            board.createTile(country, x, y, continent);
            neighborsList.put(country, neighbours);
        }

        for (Map.Entry entry : neighborsList.entrySet()) {
            board.setNeighbourTile((List<String>) entry.getValue(), (String) entry.getKey());
        }

        System.out.println("Risk Map Loaded!");
        System.out.println("Storing to file");
        storeMap();

    }

    /**
     * Method is used to write the map to a text file created by gamer.
     *
     * @throws IOException
     */
    public static String storeMap() throws IOException {

        FileWriter fileWriter = new FileWriter("Map.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("[Continents]");
        for (String continent : continents.keySet()) {
            printWriter.write(continent + "=" + continents.get(continent));
            printWriter.println();
        }

        printWriter.println("[Territories]");

        HashMap<String, Tile> map = board.getTiles();
        Iterator i = map.keySet().iterator();

        while (i.hasNext()) {

            String country = i.next().toString();
            Tile country_details = map.get(country);

            printWriter.write(country + "," + country_details.getXCoordinate() + "," + country_details.getYCoordinate() + "," + country_details.getContinent());
            for (int j = 0; j < board.getNeighbourTile(country).size(); j++) {
                printWriter.write("," + board.getNeighbourTile(country).get(j));
            }
            printWriter.println();

        }

        fileWriter.close();
        printWriter.close();

        FileReader fir = new FileReader("Map.txt");
        BufferedReader bir = new BufferedReader(fir);
        String toReturn = bir.readLine();
        fir.close();
        bir.close();
        return toReturn;
    }

    //map matrix describing the relation between countries
    public static String[][] getMapMatrix(HashMap<String, Tile> tiles) {

        Iterator<String> tileList = tiles.keySet().iterator();
        String[][] matrix = new String[tiles.keySet().size() + 1][tiles.keySet().size() + 1];

        // Initialize matrix
        int i = 1;
        matrix[0][0] = "";
        while (i <= tiles.keySet().size()) {
            if (tileList.hasNext()) {
                String tileName = tileList.next();
                matrix[i][0] = tileName;
                matrix[0][i] = tileName;
                i++;
            }
        }


        for (int j = 1; j <= tiles.keySet().size(); j++) {

            Tile t = tiles.get(matrix[j][0]);

            List<String> neighbours = t.getNeighbourTile();

            int k = 1;

            while (k <= tiles.keySet().size()) {

                if (neighbours.contains(matrix[0][k]))
                    matrix[j][k] = "Yes";
                else
                    matrix[j][k] = "No ";

                k++;
            }
        }

        // Print matrix
        for (int j = 0; j <= tiles.keySet().size(); j++) {

            for (int k = 0; k <= tiles.keySet().size(); k++) {

                if (matrix[j][k].length() == 2)
                    matrix[j][k] = matrix[j][k] + " ";

                System.out.print(matrix[j][k] + "       ");
            }
            System.out.println();
        }

        return matrix;
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

    static String[][] getNeighboursPath(String[][] inputMatrix) {

        int n = inputMatrix.length;

        for (int i = 1; i < n; i++) {
            List<Integer> yesList = new ArrayList<>();
            for (int j = 1; j < n; j++) {
                if (i != j) {
                    if (inputMatrix[i][j].toLowerCase().equals("yes")) {
                        yesList.add(j);
                    }
                }
            }
            for (int k : yesList) {
                for (int j = 1; j < n; j++) {
                    if (inputMatrix[k][j].toLowerCase().equals("yes")) {
                        if (i != j) {
                            inputMatrix[i][j] = "yes";
                        }
                    }
                }
            }
        }

        System.out.println("THE MATRIX");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(inputMatrix[i][j] + "\t");
            }
            System.out.println();
        }

        return inputMatrix;
    }

    @FXML
    public void createMap(ActionEvent event) throws IOException {
        LoadMapGlobalVariables.count = Integer.parseInt(no_of_continents.getText());
        Parent root = FXMLLoader.load(getClass().getResource("/App_Risk_Game/src/main/java/View/continentsinfo.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Continent " + LoadMapGlobalVariables.i);
        LoadMapGlobalVariables.i++;
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    public void dynamic(ActionEvent event) throws IOException {
        continents.put(continent_name.getText(), Integer.parseInt(no_of_countries_in_continent.getText()));
        LoadMapGlobalVariables.count--;
        if (LoadMapGlobalVariables.count > 0) {
            Parent root = FXMLLoader.load(getClass().getResource("/App_Risk_Game/src/main/java/View/continentsinfo.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Continent " + LoadMapGlobalVariables.i);
            LoadMapGlobalVariables.i++;
            stage.setScene(scene);
            stage.show();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/App_Risk_Game/src/main/java/View/noofcountries.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void dynamic3(ActionEvent event) throws IOException {
        LoadMapGlobalVariables.count = Integer.parseInt(no_of_countries.getText());
        Parent root = FXMLLoader.load(getClass().getResource("/App_Risk_Game/src/main/java/View/countryInfo.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Country " + LoadMapGlobalVariables.i);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void dynamic4(ActionEvent event) throws IOException {
        board.createTile(country_name.getText(), Integer.parseInt(X.getText()), Integer.parseInt(Y.getText()), continent_name_belongs.getText());
        LoadMapGlobalVariables.neighborsList.put(country_name.getText(), Arrays.asList(adj_countries.getText().split(",")));
        LoadMapGlobalVariables.count--;
        if (LoadMapGlobalVariables.count > 0) {
            Parent root = FXMLLoader.load(getClass().getResource("/App_Risk_Game/src/main/java/View/countryInfo.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Country " + LoadMapGlobalVariables.i);
            LoadMapGlobalVariables.i++;
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("neigbourslist: " + LoadMapGlobalVariables.neighborsList.toString());
            for (Map.Entry entry : LoadMapGlobalVariables.neighborsList.entrySet()) {
                System.out.println("[]".equals(((List<String>) entry.getValue()).toString()));
                if (!("[]".equals(((List<String>) entry.getValue()).toString())))
                    board.setNeighbourTile((List<String>) entry.getValue(), (String) entry.getKey());
            }
            storeMap();
            Parent loadRoot = FXMLLoader.load(getClass().getResource("/App_Risk_Game/src/main/java/View/GameScreenTest.fxml"));
            Scene loadMapScene = new Scene(loadRoot);
            Stage loadMapStage = new Stage();
            loadMapStage.setTitle("Map Loaded");
            loadMapStage.setScene(loadMapScene);
            loadMapStage.show();

        }
    }

    public static class LoadMapGlobalVariables {
        static HashMap<String, List<String>> neighborsList = new HashMap<>();
        static int count = 0;
        static int i = 0;
        static public Boolean gsFlag = false;
        static public Boolean phaseComplete = false;
       // static Player current_player = PlayerCollection.players.get(0);
        //static Player current_player = PlayerCollectionTest.getTurn();
static public boolean endgame = false;
        static     boolean game_started = false;
    }

}






