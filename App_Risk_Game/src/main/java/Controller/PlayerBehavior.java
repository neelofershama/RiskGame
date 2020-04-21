package App_Risk_Game.src.main.java.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PlayerBehavior  implements Initializable {
    @FXML
    ComboBox cb_behavior;
    List<String> player_behaviors = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        player_behaviors.add("Human Player");
        player_behaviors.add("Aggressive Player");
        player_behaviors.add("Conservative Player");
        player_behaviors.add("Random Player");
        player_behaviors.add("Cheater Player");
        cb_behavior.getItems().addAll(player_behaviors);
        cb_behavior.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (oldValue == null){
                    try {
                        selectPlayerBehavior(newValue);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }}
        });
    }


    void selectPlayerBehavior(String newValue) throws IOException {
        Object behavior_selected =  newValue;
        if(behavior_selected != null){
            player_behaviors.remove(behavior_selected);
            cb_behavior.getItems().removeAll(cb_behavior.getItems());}
        cb_behavior.getItems().addAll(player_behaviors);
    }
}
