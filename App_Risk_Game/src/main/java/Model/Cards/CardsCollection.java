package App_Risk_Game.src.main.java.Model.Cards;



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

import java.io.IOException;
import java.util.*;

/**
 * The Cards Collection performs all the operations related to Cards
 */
public class CardsCollection implements Observable {
    public static int noOfLocations;
    public static List<String> locations;
    public static List<Card> cardCollection;
    public static HashMap<String, List<Card>> playersCards;
    public static int remainingCardsCount = 0;
    public static List<Card> remainingCards;
    public static Stack<Card> cardStack;
    List<Observer> observers = new ArrayList<>();
    public CardsCollection(List<String> loc, int ub){
        locations = loc;
        noOfLocations = locations.size();
        cardCollection = new ArrayList<Card>();
        playersCards = new HashMap<String, List<Card>>();
        remainingCards = new ArrayList<>();
        cardStack = new Stack<>();
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
            int randomValue = (int) Math.random()*maxValue + 1;
            card.setValue(randomValue); //r.nextInt(ub - lb) + lb;
            card.setCardType(cardType[i%3]);
            cardCollection.add(card);
//            System.out.println("1"card);
        }
        // Adding wild card to the cards deck and shuffling the cards
        cardCollection.add(createCard(" ", 0," "));
        Collections.shuffle(cardCollection);
    }

    /**
     * shows user all the cards acquired
     *
     * @param event
     */
    @FXML
    public void displayCards(ActionEvent event) throws IOException {
        Player player = PlayerCollectionTest.getTurn();
        List<Card> cards = playersCards.get(player.getName());
        ArrayList<String> imageSource = new ArrayList<String>();
        for (Card c: cards){
            //Todo: update code for displaying cards
            //imageSource.add(c.getCardImagePath());
        }
        Parent loadRoot = FXMLLoader.load(getClass().getResource("/App_Risk_Game/src/main/java/View/showCards.fxml"));
        Scene loadMapScene = new Scene(loadRoot);
        Stage loadMapStage = new Stage();
        loadMapStage.setTitle("Players Cards");
        loadMapStage.setScene(loadMapScene);
        loadMapStage.show();
    }

    public void onClickedRedeemCards() {
        System.out.println("entered !!");
        Player player = PlayerCollectionTest.getTurn();
        List<Card> cards = playersCards.get(player.getName());
        ArrayList<String> imageSource = new ArrayList<String>();
        int[] index = new int[3];
        int totalTroopsGet = 0;
        //Todo: change boolean names according to cardtypes
        boolean isInfantry = false;
        boolean isCavalry = false;
        boolean isArtillery = false;
        for (int i =0; i<cards.size();i++){
            if ("Infantry".equals(cards.get(i).getCardType()) && !isInfantry){
                index[0] = i;
                isInfantry = true;
            }
            else if("Cavalry".equals(cards.get(i).getCardType()) && !isCavalry){
                index[1] = i;
                isCavalry = true;
            }
            else if("Artillery".equals(cards.get(i).getCardType()) && !isArtillery){
                index[2] = i;
                isArtillery = true;
            }
            else{
                continue;
            }
            totalTroopsGet += cards.get(i).getValue();
        }

        if(isInfantry && isCavalry && isArtillery){
            for(int i =0; i<index.length;i++){
                playersCards.get(player.getName()).remove(index[i]);
            }
        }

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
//               System.out.println(playersCards.get(players.get(--noofplayers).getName()).add(cardCollection.get(y)));
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
        cardStack.addAll(cardCollection);
    }

    public static Card pickCard() {
        Card c = cardStack.peek();
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
    public void notifyObserver(App_Risk_Game.src.interfaces.Observable observable) {
        for (App_Risk_Game.src.interfaces.Observer o:observers
        ) {
            o.update(observable);
        }
    }
}