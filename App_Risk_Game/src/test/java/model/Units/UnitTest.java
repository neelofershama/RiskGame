package App_Risk_Game.src.test.java.model.Units;

import App_Risk_Game.src.main.java.Controller.GameScreenTest;
import App_Risk_Game.src.main.java.Controller.LoadMap;
import App_Risk_Game.src.main.java.Controller.ReinforcePhase;
import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollection;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Testcase for Units module, contains the initializeCheck() method.
 *
 * @throws SecurityException, IOException
 */
public class UnitTest extends Application {

    @Test
    public void checkReinforcement() throws IOException {

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                new JFXPanel(); // Initializes the JavaFx Platform
                //Application.launch(UnitTest.class, new String[0]);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {

							Scanner sc = new Scanner(System.in);
							String path = "E:\\MACS-SEM1\\APP\\GitHub\\WithTesecases\\RiskGame\\mapinput.txt";

							HashMap<String, Integer> territories = new HashMap<>();
							territories.put("NA1", 2);
							territories.put("NA2", 2);
							territories.put("AS2", 5);

							ReinforcePhase rp = new ReinforcePhase();
							rp.player = new Player("Tanvi", "NA", "blue", 1, territories);
							PlayerCollection.players.add(rp.player);

							LoadMap.loadMap(path);

							GameScreenTest gt = new GameScreenTest();
							gt.reinforcement();

							Player player = PlayerCollection.players.get(0);

							assertEquals(player.getTerritories().toString(), rp.player.getTerritories().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        t.setDaemon(true);
        t.start();


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
//	 public static void main(String args[]) throws IOException {
//		UnitTest ut = new UnitTest();
//    	ut.checkReinforcement();
//	 }
}
