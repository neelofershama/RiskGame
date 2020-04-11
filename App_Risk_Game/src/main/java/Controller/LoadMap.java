package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.interfaces.Observer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Board.Tile;

import java.io.*;
import java.net.URL;
import java.util.*;

public class LoadMap implements Initializable, Observer {

        @Override
        public void update(App_Risk_Game.src.interfaces.Observable observable) {
            CardsController cardsController = new CardsController();
            Board board = new Board();
            board.attachObserver((App_Risk_Game.src.interfaces.Observer)cardsController);
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
    static Board board = new Board();

    @FXML
    Button LoadFile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void onMouseClickedLoadFile(ActionEvent event) {
        try {
            takefile();
            String[][] matrix = getMapMatrix();
            Stage stage = new Stage();
                addtable(stage,matrix );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onMouseClickedNewMap (ActionEvent event) {
        try {
            createMap();
            String[][] matrix = getMapMatrix();
            Stage stage = new Stage();
            addtable(stage,matrix );
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

            printWriter.write(country + "," + "-,-," + country_details.getContinent() + ",");
            for (int j = 0; j < board.getNeighbourTile(country).size(); j++) {
                printWriter.write(board.getNeighbourTile(country).get(j) + ",");
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

    public static String[][] getMapMatrix() {

        HashMap<String, Tile> tiles = board.getTiles();
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

    public void addtable(Stage pri, String[][] a){
        StackPane root = new StackPane();
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
        root.getChildren().add(view);

        VBox layout = new VBox(20);

        Text txt = new Text();
        txt.setText("Risk Map Loaded");
        Font font = new Font("Calibri",20);
        txt.setFont(font);
        layout.getChildren().addAll(txt, root);

        pri.setScene(new Scene(layout, 6000, 6000));
        pri.show();

    }
}





