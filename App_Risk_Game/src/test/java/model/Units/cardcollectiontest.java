package App_Risk_Game.src.test.java.model.Units;

import App_Risk_Game.src.interfaces.Observable;
import App_Risk_Game.src.interfaces.Observer;
import App_Risk_Game.src.main.java.Controller.LoadMap;
import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Board.Tile;
import App_Risk_Game.src.main.java.Model.Cards.*;
import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.interfaces.*;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollectionTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
public class cardcollectiontest {
    ArrayList<String> player_names;
    public static HashMap<String, List<Card>> playersCards;
    public static List<Card> cardCollection;
    List<String> locations;
    public static Stack<Card> cardStack;
    public static int remainingCardsCount = 0;
    public static List<Card> remainingCards;
    ArrayList<String> player_behavior;

    @Before
    public void setup() {
        player_names = new ArrayList<>();
        player_behavior = new ArrayList<>();
        playersCards = new HashMap<String, List<Card>>();
//        CardsCollection cc = new CardsCollection();
        cardCollection = new ArrayList<Card>();
        locations = new ArrayList<>();
        cardStack = new Stack<>();
        remainingCards = new ArrayList<>();
        locations.add("india");
        locations.add("china");
        CardsCollection.locations = locations;
        CardsCollection.cardCollection = cardCollection;
        CardsCollection.playersCards = playersCards;
        int noOfLocations = 2;
        int maxValue = 10;
        player_names.add("p1");
        player_names.add("p2");
        int noofplayers = 2;
        player_behavior.add("Aggressive Player");
        player_behavior.add("Cheater Player");
        PlayerCollectionTest.createPlayers(noofplayers, player_names,player_behavior);
        CardsCollection.initializeCards(maxValue);
        CardsCollection.distributeCards(PlayerCollectionTest.players);
//        List<Card> first = playersCards.get("p1");
        List<Card> first = CardsCollection.cardsOfPlayer("p1");
//        List<Card> second = playersCards.get("p2");
        System.out.println(playersCards);
        System.out.println(playersCards.get("p1").size());


    }

    @Test
    public void phase3Test() {
        Assert.assertEquals(PlayerCollectionTest.players.size(), 2);
        Assert.assertEquals(CardsCollection.playersCards.get("p1").size(), 5);


    }

    @Test
    public void phase2Test() {
        CardsCollection.rebundleCards();
        Assert.assertEquals(CardsCollection.cardsOfPlayer("p1"), 0);

    }

    @Test
    public void phase4Test() {
//        Card new = CardsCollection.pickCard();
        Assert.assertEquals(playersCards.get("p1").size(), 5);
        Assert.assertEquals(CardsCollection.cardStack.peek().getValue(), 6);
//
//    }
//    @Test
//    public void phase4Test() {
//        CardsCollection.assignCardToPlayer("p1",);
//        Assert.assertEquals(playersCards.get("p1"), 0);
//
//}

    }
}