package App_Risk_Game.src.main.java.Model.Cards;



import App_Risk_Game.src.interfaces.Observable;
import App_Risk_Game.src.interfaces.Observer;
import App_Risk_Game.src.main.java.Controller.LoadMap;
import App_Risk_Game.src.main.java.Controller.ReinforceTest;
import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Board.Tile;
import App_Risk_Game.src.main.java.Model.Cards.*;
import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.interfaces.*;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollectionTest;
import com.sun.tools.internal.xjc.model.CNonElement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * The Cards Collection performs all the operations related to Cards
 */
public class CardsCollection implements Observable {

    static int noOfLocations;
    static List<String> locations;
    public static List<Card> cardCollection;
    public static HashMap<String, List<Card>> playersCards;
    public static int remainingCardsCount = 0;
    public static List<Card> remainingCards;
    public static Stack<Card> cardStack;
    public static Stage window;
    public static Scene loadMapScene;
    List<Observer> observers = new ArrayList<>();


    public CardsCollection(List<String> loc, int ub){
        locations = loc;
        noOfLocations = locations.size();
        cardCollection = new ArrayList<Card>();
        playersCards = new HashMap<String, List<Card>>();
        remainingCards = new ArrayList<>();
        cardStack = new Stack<>();
        window = new Stage();
        System.out.println("------------------------enterd");
        initializeCards(ub);
    }

    /**
     * Initialize the card set by creating object of Card and assigning it a location and value
     * @param maxValue The maximum value a card can have ex: The no of stars in Risk game
     */
    public static void initializeCards(int maxValue) {
        Collections.shuffle(locations);
        Random r = new Random();
        Card card = null;
        //Todo: set card types properly
        String[] cardType = {"Infantry", "Cavalry", "Artillery"};
        for (int i = 0; i < noOfLocations; i++) {
            int ub = maxValue;
            int lb = 1;
            card = new Card();
            card.setLocation(locations.get(i));
            //generates random value between zero and maxValue
            int randomValue = (int) (Math.random() * maxValue) + 1;
            System.out.println(randomValue);
            card.setValue(randomValue); //r.nextInt(ub - lb) + lb;
            card.setCardType(cardType[i%3]);
            cardCollection.add(card);
        }
        // Adding wild card to the cards deck and shuffling the cards
        Collections.shuffle(cardCollection);
    }

    public HashMap<String, List<Card>>getCardDetails(){
        return playersCards;
    }

    public void clickedBack(){
        window.close();
    }

    /**
     * shows user all the cards acquired
     */
    @FXML
    public static void displayCards(Parent loadRoot) throws IOException {

        //window.initModality(Modality.APPLICATION_MODAL);
        window.isAlwaysOnTop();
        window.setTitle("Display Cards");
        loadMapScene = new Scene(loadRoot);
        window.setScene(loadMapScene);
        window.show();
    }

    public int redeemCards() {
        System.out.println("entered !!");
        Player player = PlayerCollectionTest.getTurn();
        List<Card> cards = playersCards.get(player.getName());
        ArrayList<String> imageSource = new ArrayList<String>();
        int[] index = new int[3];
        int j=0;
        int totalTroopsGet = 0;
        boolean isInfantry = false;
        boolean isCavalry = false;
        boolean isArtillery = false;
        for (int i =0; i<cards.size();i++){
            if ("Infantry".equals(cards.get(i).getCardType()) && !isInfantry){
                index[j++] = i;
                isInfantry = true;
                totalTroopsGet += cards.get(i).getValue();
            }
            else if("Cavalry".equals(cards.get(i).getCardType()) && !isCavalry){
                index[j++] = i;
                isCavalry = true;
                totalTroopsGet += cards.get(i).getValue();
            }
            else if("Artillery".equals(cards.get(i).getCardType()) && !isArtillery){
                index[j++] = i;
                isArtillery = true;
                totalTroopsGet += cards.get(i).getValue();
            }
            else{
            }
        }

        if(isInfantry && isCavalry && isArtillery){
            for(int i =2; i>=0;i--){
                System.out.println(index[i]);
                playersCards.get(player.getName()).remove(index[i]);
            }
            return totalTroopsGet;
        }
        return 0;
    }

    /**
     * Assign cards to each player
     * @param players The list of players to which the cards should be distributed
     */
    public static void distributeCards(List<Player> players) {

        int n = noOfLocations % players.size();
        int noofplayers = players.size();
        int cardsforeachplayer = noOfLocations / noofplayers;
        int y = 0;
        for (int i = 0; i < noofplayers; i++) {
            List<Card> lc = new ArrayList<Card>();
            lc.addAll(cardCollection.subList(y, y + cardsforeachplayer));
            playersCards.put(players.get(i).getName(), lc);
            y += cardsforeachplayer;

        }
        if (n != 0) {
            //TODO - assign remaining cards to last 2 players
            remainingCardsCount = noOfLocations % noofplayers;
            //remainingCards.clear();
            if (remainingCardsCount == 1) {
                remainingCards.add(cardCollection.get(y));
            } else {
                    remainingCards.addAll(cardCollection.subList(y, noOfLocations));
            }
            for (int i = 0; i < remainingCards.size(); i++)
           {
             playersCards.get(players.get(--noofplayers).getName()).add(cardCollection.get(y));
             y++;
            }

        }
        PlayerCollectionTest.players.forEach(player ->
        {
            List<Card> cards = playersCards.get(player.getName());

                    for (Card t : cards) {
                        player.territories.put(t.getLocation(), t.getValue());
                    }
               // LoadMap.board.setPlayer((String) pair.getKey(),t.location);
        });
    }

    /**
     * Remove all the card assignment of each player and push all the cards to a stack
     */
    public static void rebundleCards() {
        playersCards.clear();
        remainingCards.clear();
        remainingCardsCount = 0;
        cardCollection.add(createCard("None", -1, "ceaseFire"));
        cardStack.addAll(cardCollection);
    }

    public static Card pickCard() {
        Card c = cardStack.peek();
        if (c.getCardType() == "ceaseFire"){
            System.out.println(".....................................................entered");
            LoadMap.LoadMapGlobalVariables.endGame = true;
        }
        return c;
    }

    /**
     * Create a special type of card ex:Cease fire in case of Risk Game
     * @param loc the location
     * @param v the value
     * @return the new Card object
     */
    public static Card createCard(String loc, int v, String cardType) {
        Card card = new Card();
        card.setValue(v);
        card.setLocation(loc);
        card.setCardType(cardType);
        cardCollection.add(card);
        return card;
    }

    /**
     * Allocate the card to the player
     * @param p the player name
     * @param c the card object
     */
    public static void assignCardToPlayer(String p, Card c) {
        List<Card> l = new ArrayList();
        l.add(c);
        playersCards.put(p, l);
    }

    /**
     * Returns the list of cards associated with the player
     * @param player
     * @return the list of Cards
     */
    public static List<Card> cardsOfPlayer(String player) {
        List<Card> ls = playersCards.get(player);
        return ls;
    }
    @Override
    public void attachObserver(App_Risk_Game.src.interfaces.Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void dettachObserver(App_Risk_Game.src.interfaces.Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObserver(App_Risk_Game.src.interfaces.Observable observable) throws IOException {
        for (App_Risk_Game.src.interfaces.Observer o:observers
        ) {
            o.update(observable);
        }
    }

}