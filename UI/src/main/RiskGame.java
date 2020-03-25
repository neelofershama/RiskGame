package UI.src.main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RiskGame {

    public void start(Stage risk_window) throws FileNotFoundException {
        //Creating an image
        Image image = new Image(new FileInputStream("UI/images/map.png"));

        //Setting the image view
        ImageView imageView = new ImageView(image);

        // Creating VBox layout to insert image and gaming options
        VBox vb = new VBox();
        vb.setPadding(new Insets(10, 10, 10, 10));
        vb.setAlignment(Pos.CENTER);
        vb.setBackground(Background.EMPTY);
        String style = "-fx-background-color: rgba(0, 0, 0, 1);";
        vb.setStyle(style);
        vb.setSpacing(10);
        vb.getChildren().add(imageView);

        risk_window.setTitle("WELCOME TO RISK GAME");

        // Adding Layout to the scene
        Scene game_scene = new Scene(vb);

        // Adding scene to the window
        risk_window.setScene(game_scene);
        risk_window.setTitle("RISK");
        risk_window.sizeToScene();
        risk_window.show();
    }
}
