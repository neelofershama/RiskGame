package App_Risk_Game.src.main.java.Model.Players;

import App_Risk_Game.src.main.java.Controller.GameScreenTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HumanPlayer implements PlayerBehaviour {
private Player p;

    public static int reinforceCount = 3;
    @Override
    public void reinforce(Player p) throws IOException {
        Parent reinforceRoot = FXMLLoader.load(GameScreenTest.class.getResource("/App_Risk_Game/src/main/java/View/ReinforceTest.fxml"));
        Scene loadReinforceScene = new Scene(reinforceRoot);
        Stage loadReinforceStage = new Stage();
        loadReinforceStage.setTitle("REINFORCEMENT PHASE LOADED");
        loadReinforceStage.setScene(loadReinforceScene);
        loadReinforceStage.show();
        return;
    }

    @Override
    public void attack() {
        System.out.println(" Human Player Attack");
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


    @Override
    public boolean fortify() {
        try {
            Parent reinforceRoot = FXMLLoader.load(getClass().getResource("/App_Risk_Game/src/main/java/View/FortifyTest.fxml"));
            Scene loadAttackScene = new Scene(reinforceRoot);
            Stage loadAttackStage = new Stage();
            loadAttackStage.setTitle("FORTIFICATION PHASE LOADED");
            loadAttackStage.setScene(loadAttackScene);
            loadAttackStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
return true;
    }
}
