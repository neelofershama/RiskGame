//package View;
//
//import Controller.LoadMap;
//import javafx.application.Application;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//import javafx.util.Callback;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Arrays;
//
//public class UiTest extends Application {
//
//    LoadMap loadMap = new LoadMap();
//    Scene scene1, scene2, scene3;
//    Label label2;
//    Label label3;
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Risk Game");
//
////Scene 1
//        Label label1 = new Label("Welcome to the Risk Game!");
//        Button button1 = new Button("Start Game ");
//        button1.setOnAction(e -> primaryStage.setScene(scene2));
//        VBox layout1 = new VBox(20);
//        layout1.getChildren().addAll(label1, button1);
//        scene1 = new Scene(layout1, 300, 250);
//
////Scene 2
//        Button button3 = new Button("1. Create a new map  ");
//        Button button4 = new Button("2. Load from file");
//
//        button4.setOnAction(e -> {
//            Text text = new Text();
//            try {
//                boolean res = takefile();
//                System.out.println("res " + res);
//                if (res)
//                    text.setText("Risk Map loaded!");
//                else
//                    text.setText("File not loaded!");
//            } catch (FileNotFoundException ex) {
//                ex.printStackTrace();
//                text.setText("File not loaded!");
//            }
//
//
//            //setting the position of the text
//            text.setX(50);
//            text.setY(50);
//
//
//            String[][] matrix = loadMap.getMapMatrix();
//            addtable(primaryStage, matrix);
//
//
////            int length = matrix.length;
////            int width = matrix.length;
////
////            GridPane root = new GridPane();
////
////            for(int y = 0; y < length; y++){
////                for(int x = 0; x < width; x++){
////
////                    // Create a new TextField in each Iteration
////                    TextField tf = new TextField();
//////                    tf.setPrefHeight(500);
//////                    tf.setPrefWidth(500);
////                    tf.setAlignment(Pos.CENTER);
////                    tf.setEditable(false);
////                    tf.setText(matrix[y][x]);
////
////                    // Iterate the Index using the loops
////                    root.setRowIndex(tf,y);
////                    root.setColumnIndex(tf,x);
////                    root.getChildren().add(tf);
////                }
////            }
////
////
////            //Creating a scene object
////            Scene scene4 = new Scene(root, 5000, 5000);
////
////            //Adding scene to the stage
////            primaryStage.setScene(scene4);
////
////            //Displaying the contents of the stage
////            primaryStage.show();
//
//        });
//
//        button3.setOnAction(e -> primaryStage.setScene(scene3));
//        VBox layout3 = new VBox(20);
//        layout3.getChildren().addAll(button3, button4);
//        scene2 = new Scene(layout3, 300, 250);
//
//        primaryStage.setScene(scene1);
//        primaryStage.show();
//    }
//
//    boolean takefile() throws FileNotFoundException {
//        FileChooser chooser = new FileChooser();
//        File selectedFile = chooser.showOpenDialog(null);
//
//        if (selectedFile != null) {
//            String filePath = selectedFile.getAbsolutePath();
//            System.out.println("File selected: " + filePath);
//
//            boolean result = loadMap.loadMap(filePath);
//            return result;
//
//        } else {
//            System.out.println("File selection cancelled.");
//            return false;
//        }
//
//    }
////        void getinput(Stage pri,String param){
////            helloWorld world =new helloWorld();
////            Label label2= new Label(world.printhelloWorld(param));
////            VBox layout2= new VBox(20);
////            layout2.getChildren().addAll(label2);
////            scene2= new Scene(layout2,300,250);
////            pri.setScene(scene2);
////
////        }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    public void addtable(Stage pri, String[][] a) {
//        StackPane root = new StackPane();
//        TableView view = new TableView();
//
//        ObservableList<String[]> data = FXCollections.observableArrayList();
//        data.addAll(Arrays.asList(a));
//        //  data.remove(0);
//        for (int i = 0; i < a[0].length; i++) {
//            TableColumn tc = new TableColumn(" ");
//            final int colNo = i;
//            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
//                @Override
//                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
//                    return new SimpleStringProperty((p.getValue()[colNo]));
//                }
//            });
//            tc.setPrefWidth(50);
//            view.getColumns().add(tc);
//        }
//        view.setItems(data);
//        root.getChildren().add(view);
//
//        VBox layout = new VBox(20);
//
//        Text txt = new Text();
//        txt.setText("Risk Map Loaded");
//        Font font = new Font("Calibri", 20);
//        txt.setFont(font);
//        layout.getChildren().addAll(txt, root);
//
//        pri.setScene(new Scene(layout, 6000, 6000));
//        pri.show();
//
//    }
//}
//
//
//
