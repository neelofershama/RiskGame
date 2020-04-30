package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.interfaces.Observer;
import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Cards.Card;
import App_Risk_Game.src.main.java.Model.Cards.CardsCollection;
import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollectionTest;
import App_Risk_Game.src.main.java.Model.Turns.Turns;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class CardsController implements Observer, Initializable {

    @FXML
    public Label playerNameLabel;

    @FXML
    public ComboBox listOfCards;

    @FXML
    VBox root;

    public static CardsCollection cardsCollection;
    Player currentPlayer;
    HashMap<String, List<Card>> playersCards;
    static VBox displayRoot;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentPlayer = PlayerCollectionTest.getTurn();
        playerNameLabel.setText(currentPlayer.getName());
        playerNameLabel.setTextFill(Color.web(currentPlayer.getColor()));
        playersCards = cardsCollection.getCardDetails();
        addToListOfCards();
    }

    public static void onDisplayCards(Parent loadRoot, VBox tempRoot) throws IOException {
        displayRoot = tempRoot;
        cardsCollection.displayCards(loadRoot);

    }

    public void onClickedRedeemCards(ActionEvent event) throws IOException {
        int troopsAssigned = cardsCollection.redeemCards();
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
        Stage prevStage = (Stage) displayRoot.getScene().getWindow();
        prevStage.close();

        cardsCollection.clickedBack();
        System.out.println("*****************" + Integer.toString(troopsAssigned));
        System.out.println("*****************" + Integer.toString(ReinforceTest.getMaxTroop()));
        if (ReinforceTest.getMaxTroop() > 3){
            troopsAssigned += ReinforceTest.getMaxTroop();
            ReinforceTest.setMaxTroop(troopsAssigned -3);
        }
        else{
            ReinforceTest.setMaxTroop(troopsAssigned);
        }
        Parent reinforceRoot = FXMLLoader.load(GameScreenTest.class.getResource("/App_Risk_Game/src/main/java/View/ReinforceTest.fxml"));
        Scene loadReinforceScene = new Scene(reinforceRoot);
        Stage loadReinforceStage = new Stage();
        loadReinforceStage.setTitle("REINFORCEMENT PHASE LOADED");
        loadReinforceStage.setScene(loadReinforceScene);
        loadReinforceStage.show();
    }

    public void onClickedBack(ActionEvent event) {
        cardsCollection.clickedBack();
    }

    public void addToListOfCards(){
        HashMap<String, Integer> cardDetails = new HashMap<String, Integer>();
        Player player = PlayerCollectionTest.getTurn();
        List<Card> cards = playersCards.get(player.getName());
        if (cards!= null){
            for (Card c: cards){
                cardDetails.put(c.getLocation() + " | " + c.getCardType() + "| " + Integer.toString(c.getValue()),c.getValue());
            }

            if (!cardDetails.isEmpty()) { // HAVE TO MAKE SURE ITS NULL OR EMPTY
                Iterator iterator = cardDetails.entrySet().iterator();

                while (iterator.hasNext()) {
                    Map.Entry mapElement = (Map.Entry) iterator.next();
                    listOfCards.getItems().add((String) mapElement.getKey());
                }
                listOfCards.getSelectionModel().selectFirst();
            }
        }
        else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText(player.getName() + " HAS NO CARDS");
            a.show();
        }
    }

    /**
     * controller is responsible for assigning territories to players for phase 0.
     * this controller is a observer for board model
     * @param observable
     */
    @Override
    public void update(App_Risk_Game.src.interfaces.Observable observable) throws IOException {
        List<String> territories = new ArrayList<>();
        Iterator iterator = ((Board)observable).getTiles().keySet().iterator();
        while (iterator.hasNext()){
            String name = (String) iterator.next();
            territories.add(name);
        }
        cardsCollection = new CardsCollection(territories, 5);
        List<Player> players = PlayerCollectionTest.players;
        CardsCollection.distributeCards(players);
        for (Player p:players
             ) {
            System.out.println(p.getName());

            System.out.println(p.getTerritories());
        }
        //cardsCollection.rebundleCards();
        Turns.turns.setCurrentPlayerID(players.get(0).getId());
        Turns.turns.setCurrent_player(players.get(0));
    }
}
