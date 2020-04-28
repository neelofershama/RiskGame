package App_Risk_Game.src.test.java.model.Cards;

import App_Risk_Game.src.main.java.Model.Cards.Card;
import App_Risk_Game.src.main.java.Model.Cards.CardsCollection;
import App_Risk_Game.src.main.java.Model.Players.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class CardsTest
{
    List<String> locations = new ArrayList<String>();
    int value=3;
    int noOfLocations =3;
    Card card = new Card();
    CardsCollection cc= new CardsCollection(locations,value);
    List<Card> cards = new ArrayList<Card>();
    HashMap<String, List<Card>> playerDetails = new HashMap<String, List<Card>>();
    public CardsTest()
    { }

    @Before
    public void setup() throws Exception
    {
         //List<String> locations = new ArrayList<String>();
         HashMap<String, List<Card>> playersCards = new HashMap<String, List<Card>>();
         locations.add("America");
         locations.add("India");
         locations.add("Nepal");
         //List<String> players = new ArrayList<>();
         //players.add("PlayerA");
         //players.add("PlayerB");
         //players.add("PlayerC");
         cards = CardsCollection.cardCollection;
         ListIterator<Card> iterator = cards.listIterator();
         List<String> loc = new ArrayList<String>();
         List<Integer> val = new ArrayList<Integer>();
         while (iterator.hasNext())
         {
           Card c = (Card) iterator.next();
           loc.add(c.location);
           val.add(c.value);
         }
        Assert.assertNotNull(cards);
        System.out.println(locations.size());
        System.out.println(cards.size());
//        assertEquals(locations.size(), cards.size());
//        Assert.assertTrue(loc.containsAll(locations));
//        val.forEach((v) -> {
//            Assert.assertTrue(v >= 0 && v <= value);
//        });
    }

    @Test
    public void distributeTest() throws Exception
    {
        locations.add("America");
        locations.add("India");
        locations.add("Nepal");
        locations.add("Egypt");
        locations.add("Sri Lanka");
        locations.add("Japan");
        List<Player> players = new ArrayList<>();
        List<String> player = new ArrayList<>();
        player.add("Player A");
        player.add("Player B");
        player.add("Player C");
        CardsCollection cc = new CardsCollection(locations, value);
        cards = CardsCollection.cardCollection;
        CardsCollection.distributeCards(players);
        playerDetails = CardsCollection.playersCards;
        Assert.assertNotNull(playerDetails);
        System.out.println(playerDetails.get("PlayerA").size());
        System.out.println(playerDetails.get("PlayerB").size());
        Assert.assertNotEquals(playerDetails.get("PlayerA").size(), locations.size() / players.size());
        Assert.assertNotEquals(playerDetails.get("PlayerB").size(), locations.size() / players.size());
        Assert.assertNotEquals(playerDetails.get("PlayerC").size(), locations.size() / players.size());


    }

    @Test
    public void assignCardToPlayerTest()
    {
        locations.add("America");
        locations.add("India");
        locations.add("Nepal");
        locations.add("Egypt");
        locations.add("Sri Lanka");
        locations.add("Japan");
        List<String> players = new ArrayList<>();
        players.add("PlayerA");
        players.add("PlayerB");
        players.add("PlayerC");
        HashMap<String, List<Card>> newPlayer = new HashMap<String, List<Card>>();
        CardsCollection cc = new CardsCollection(locations, value);
        cards = CardsCollection.cardCollection;
        Card newCard = CardsCollection.createCard("Connecticut", 2, "Infantry");
        CardsCollection.assignCardToPlayer("New Player", newCard);
        List<Card> pclist = new ArrayList<>();
        pclist = CardsCollection.cardsOfPlayer("New Player");
        Assert.assertNotNull(newCard);
        Assert.assertTrue(pclist.contains(newCard));

    }

}
