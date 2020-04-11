package UI.src.main;

import App_Risk_Game.src.main.java.Model.Board.Board;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Stage risk_window;
    Scene welcome_scene;
    Scene setup_scene;
    Scene game_scene;

    Stage save_exit_window = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Board board = new Board();

        Parent root = FXMLLoader.load(getClass().getResource("/App_Risk_Game/src/main/java/View/GameLaunch.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("WELCOME TO RISK GAME");
        primaryStage.show();
    }
}
//        // Trying to load an image
//
//        //Creating an image
//        Image image = new Image(new FileInputStream("UI/images/risk_cover.jpg"));
//
//        //Setting the image view
//        ImageView imageView = new ImageView(image);
//        imageView.resize(800, 800);
//
//        // Creating Buttons
//        Button new_game = new Button("NEW GAME");
//        Button saved_game = new Button("LOAD GAME");
//        Button exit_game = new Button("EXIT");
//
//        // Creating HBox layout to insert game play buttons
//        HBox hb = new HBox();
//        hb.setAlignment(Pos.CENTER);
//        hb.setSpacing(20);
//        hb.getChildren().addAll(new_game, saved_game, exit_game);
//
//        // Creating VBox layout to insert image and gaming options
//        VBox vb = new VBox();
//        vb.setPadding(new Insets(10, 10, 10, 10));
//        vb.setAlignment(Pos.CENTER);
//        vb.setBackground(Background.EMPTY);
//        String style = "-fx-background-color: rgba(0, 0, 0, 1);";
//        vb.setStyle(style);
//        vb.setSpacing(10);
//        vb.getChildren().add(imageView);
//        vb.getChildren().add(hb);
//
//        // Setting the window properties
//        risk_window = primaryStage;
//        risk_window.setTitle("WELCOME TO RISK GAME");
//
//        // Adding Layout to the scene
//        welcome_scene = new Scene(vb);
//
//        // Adding scene to the window
//        risk_window.setScene(welcome_scene);
//        risk_window.sizeToScene();
//        risk_window.show();
//
//        // Changing the mouse pointer to cursor at the buttons
//        new_game.setOnMouseEntered(e ->
//                new_game.setCursor(Cursor.HAND));
//        saved_game.setOnMouseEntered(e ->
//                saved_game.setCursor(Cursor.HAND));
//        exit_game.setOnMouseEntered(e ->
//                exit_game.setCursor(Cursor.HAND));
//
//        // When the user click on exit, the welcome window should close
//        exit_game.setOnMouseClicked(e ->
//        {
//            System.out.println("GAME QUITED");
//            risk_window.close();
//        });
//
//        // When the user click on new game, they will be taken to game set up scene
//        new_game.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                StartGame s  = new StartGame();
//                s.start(risk_window);
//            }
//        });
//    }
//}

// CAN BE REUSED WHEN THE ACTUAL GAME STARTS, NOT NECESSARY FOR THE WELCOME WINDOW

// Popping up window to ask user to save&exit or exit
/*
        exit_game.setOnMouseClicked(new EventHandler<MouseEvent>() {

@Override
public void handle(MouseEvent event) {
        popUp(event);
        }
        });
 */

/*
// Used to handle user event when they click on exit button
    private void popUp(MouseEvent event) {

        // Adding two buttons
        Button yes = new Button("YES");
        Button no = new Button("NO");

        // Creating Label
        Label message = new Label("Do you want to save");
        message.setTextFill(Color.WHITE);

        // Creating layout to hold the buttons
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(10);
        hb.getChildren().addAll(yes, no);

        // Creating layout to hold hb and label
        VBox vb = new VBox();
        vb.setPadding(new Insets(10, 10, 10, 10));
        vb.setSpacing(10);
        vb.setBackground(Background.EMPTY);
        String style = "-fx-background-color: rgba(0, 0, 0, 1);";
        vb.setStyle(style);
        vb.getChildren().addAll(message, hb);

        // Creating scene for the layout
        Scene save_scene = new Scene(vb);

        // Setting properties for stage
        save_exit_window.initModality(Modality.APPLICATION_MODAL);
        save_exit_window.initStyle(StageStyle.UNDECORATED);
        save_exit_window.setScene(save_scene);
        save_exit_window.show();

        yes.setOnMouseClicked(e -> yes.setCursor(Cursor.HAND));
        no.setOnMouseClicked(e -> no.setCursor(Cursor.HAND));

        // Handling the user events
        yes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Has to write the game saving logic
                System.out.println("Game Saved");
                save_exit_window.close();
                welcome_window.close();
            }
        }
        );

        no.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Game Not Saved");
                save_exit_window.close();
                welcome_window.close();
            }
        }
        );


    }
 */