package App_Risk_Game.src.main.java.Model.Cards;



import App_Risk_Game.src.interfaces.Observable;
import App_Risk_Game.src.interfaces.Observer;
import App_Risk_Game.src.main.java.Controller.LoadMap;
import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Board.Tile;
import App_Risk_Game.src.main.java.Model.Cards.*;
import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.interfaces.*;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollection;

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
        for (int i = 0; i < noOfLocations; i++) {
            int ub = maxValue;
            int lb = 1;
            Card card = new Card();
            card.location = locations.get(i);
            card.value = (int) (Math.random() * maxValue) + 1; //r.nextInt(ub - lb) + lb;
            cardCollection.add(card);
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
        if (n == 0) {
            int y = 0;
            for (int i = 0; i < noofplayers; i++) {
                List<Card> lc = new ArrayList<Card>();
                lc.addAll(cardCollection.subList(y, y + cardsforeachplayer));
                y += cardsforeachplayer;
                playersCards.put(players.get(i).getName(), lc);

            }
        } else {
            int y = 0;
            for (int i = 0; i < noofplayers; i++) {
                List<Card> lc = new ArrayList<Card>();
                lc.addAll(cardCollection.subList(y, y + cardsforeachplayer));
                y += cardsforeachplayer;
                playersCards.put(players.get(i).getName(), lc);
            }


            //TODO - assign remaining cards to last 2 players
            remainingCardsCount = noOfLocations % noofplayers;
            if (remainingCardsCount == 1) {
                remainingCards.add(cardCollection.get(y));
            } else {
                for (int i = 0; i < remainingCardsCount; i++) {
                    remainingCards.addAll(cardCollection.subList(y, noOfLocations));
                }
            }
//            for (int i = 0; i < remainingcards; i++)
////            {
////              playersCards.get(noofplayers--).add(cardCollection.get(y));
////              y++;
////            }

        }
        PlayerCollection.players.forEach(player ->
        {
            List<Card> cards = playersCards.get(player.getName());

                    for (Card t : cards) {
                        player.territories.put(t.location, t.value);
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
    public static Card createCard(String loc, int v) {
        Card card = new Card();
        card.value = v;
        card.location = loc;
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