package App_Risk_Game.src.test.java.model.Cards;

import App_Risk_Game.src.main.java.Model.Cards.Card;
import App_Risk_Game.src.main.java.Model.Cards.CardsCollection;
import App_Risk_Game.src.main.java.Model.Players.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;



public class CardsCollectionTest
{
    List<String> locations = new ArrayList<String>();
    int value=2;
    Card card = new Card();
    CardsCollection cc= new CardsCollection(locations,value);
    List<Card> cards = new ArrayList<Card>();
    HashMap<String, List<Card>> playerDetails = new HashMap<String, List<Card>>();
    //public CardsTest()
    //{ }

    @Before
    public void setup() throws Exception
    {
         HashMap<String, List<Card>> playersCards = new HashMap<String, List<Card>>();
         locations.add("America");
         locations.add("India");
         locations.add("Nepal");
         cards = CardsCollection.cardCollection;
         ListIterator<Card> iterator = cards.listIterator();
         List<String> loc = new ArrayList<String>();
         List<Integer> val = new ArrayList<Integer>();
         while (iterator.hasNext())
         {
           Card c = (Card) iterator.next();
           loc.add(c.getLocation());
           val.add(c.getValue());
         }

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
        players.add(new Player("abc",1));
        players.add(new Player("xyz", 2));
        players.add(new Player("pqr",3));
        CardsCollection cc = new CardsCollection(locations, value);
        cards = CardsCollection.cardCollection;
        CardsCollection.distributeCards(players);
        playerDetails = CardsCollection.playersCards;
        Assert.assertNotNull(playerDetails);
        Assert.assertEquals(playerDetails.get("abc").size(), locations.size() / players.size());
        Assert.assertEquals(playerDetails.get("xyz").size(), locations.size() / players.size());
        Assert.assertEquals(playerDetails.get("pqr").size(), locations.size() / players.size());


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
        List<Player> players = new ArrayList<>();
        players.add(new Player("abc",1));
        players.add(new Player("xyz", 2));
        players.add(new Player("pqr",3));
        HashMap<String, List<Card>> newPlayer = new HashMap<String, List<Card>>();
        CardsCollection cc = new CardsCollection(locations, value);
        cards = CardsCollection.cardCollection;
        Card newCard = CardsCollection.createCard("Connecticut", 2, "Infantry");
        CardsCollection.assignCardToPlayer("New Player", newCard);
        List<Card> pclist = new ArrayList<>();
        pclist = CardsCollection.cardsOfPlayer("New Player");
        Assert.assertTrue(pclist.contains(newCard));

    }

}


//package App_Risk_Game.src.test.java.model.Cards;
//
//import App_Risk_Game.src.main.java.Model.Cards.Card;
//import App_Risk_Game.src.main.java.Model.Cards.CardsCollection;
//import App_Risk_Game.src.main.java.Model.Players.Player;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.*;
//
///**
// * Test CardsCollection class
// */
//
//public class CardsCollectionTest {
//    Random r = new Random();
//
//    List<String> locations = new ArrayList<String>();
//
//    public CardsCollectionTest() {
//
//    }
//
//    /**
//     * Test creation of cards is equal to the number of locations provided and the associate a value with each card
//     */
//    @Test
//    public void initializeCards_shouldInitializeCardsWithLocationAndValue() {
//        //Arrange
//        int min = 0;
//        int value = 2; //r.nextInt(max - min) + min;
//        locations.add("India");
//        locations.add("Russia");
//        locations.add("China");
//        locations.add("Australia");
//        locations.add("Canada");
//        locations.add("England");
//        locations.add("California");
//        List<Card> cards = new ArrayList<Card>();
//
//        //Act
//        CardsCollection cc = new CardsCollection(locations, value);
//        cards = CardsCollection.cardCollection;
//        ListIterator<Card>
//                iterator = cards.listIterator();
//        List<String> resultloc = new ArrayList<String>();
//        List<Integer> resultvalue = new ArrayList<Integer>();
//        while (iterator.hasNext()) {
//            Card c = (Card) iterator.next();
//            resultloc.add(c.location);
//            resultvalue.add(c.value);
//        }
//
//        //Assert
//        Assert.assertNotNull(cards);
//        Assert.assertEquals(locations.size(), cards.size());
//        Assert.assertTrue(resultloc.containsAll(locations));
//        resultvalue.forEach((v) -> {
//            Assert.assertTrue(v >= min && v <= value);
//        });
//
//    }
//
//    /**
//     * Test equal distribution of cards to each player when no of cards is a multiple of no of players
//     */
//    @Test
//    public void distributeCards_shouldAssignEqualNoOfCardsToPlayers() {
//        //Arrange
//        int min = 0;
//        int value = 2; //r.nextInt(max - min) + min;
//        locations.add("India");
//        locations.add("Russia");
//        locations.add("China");
//        locations.add("Australia");
//        locations.add("Canada");
//        locations.add("England");
//        List<Card> cards = new ArrayList<Card>();
//        HashMap<String, List<Card>> playerDetails = new HashMap<String, List<Card>>();
//        List<Player> players = new ArrayList<>();
//        players.add("Player1");
//        players.add("Player2");
//        players.add("Player3");
//
//        //Act
//        CardsCollection cc = new CardsCollection(locations, value);
//        cards = CardsCollection.cardCollection;
//        CardsCollection.distributeCards(players);
//        playerDetails = CardsCollection.playersCards;
//
//        //Assert
//        Assert.assertNotNull(playerDetails);
//        Assert.assertEquals(playerDetails.get("Player1").size(), locations.size() / players.size());
//        Assert.assertEquals(playerDetails.get("Player2").size(), locations.size() / players.size());
//        Assert.assertEquals(playerDetails.get("Player3").size(), locations.size() / players.size());
//    }
//
//    /**
//     * Test equal distribution of cards to players when no of locations is not a multiple of no of players and keep track of extra cards
//     */
//    @Test
//    public void distributeCards_shouldAssignCardsToPlayers_whenNoOfLocationsIsNotMultipleOfNoOfPlayers() {
//        //Arrange
//        int min = 0;
//        int value = 2; //r.nextInt(max - min) + min;
//        locations.add("India");
//        locations.add("Russia");
//        locations.add("China");
//        locations.add("Australia");
//        locations.add("Canada");
//        locations.add("England");
//        locations.add("California");
//        List<Card> cards = new ArrayList<Card>();
//        HashMap<String, List<Card>> playerDetails = new HashMap<String, List<Card>>();
//        List<String> players = new ArrayList<>();
//        players.add("Player1");
//        players.add("Player2");
//        players.add("Player3");
//
//        //Act
//        CardsCollection cc = new CardsCollection(locations, value);
//        cards = CardsCollection.cardCollection;
//        CardsCollection.distributeCards(players);
//        playerDetails = CardsCollection.playersCards;
//
//        //Assert
//        Assert.assertNotNull(playerDetails);
//        Assert.assertEquals(playerDetails.get("Player1").size(), locations.size() / players.size());
//        Assert.assertEquals(playerDetails.get("Player2").size(), locations.size() / players.size());
//        Assert.assertEquals(playerDetails.get("Player3").size(), locations.size() / players.size());
//        Assert.assertEquals(CardsCollection.remainingCardsCount, locations.size() % players.size());
//    }
//
//    /**
//     * Test rebundle operation removes any association of cards with players and pushes all the cards to a stack
//     */
//    @Test
//    public void rebundleCards_shouldUnAssignCardsToPlayers() {
//        int min = 0;
//        int value = 2; //r.nextInt(max - min) + min;
//        locations.add("India");
//        locations.add("Russia");
//        locations.add("China");
//        locations.add("Australia");
//        locations.add("Canada");
//        locations.add("England");
//        locations.add("California");
//        List<Card> cards = new ArrayList<Card>();
//        HashMap<String, List<Card>> playerDetails = new HashMap<String, List<Card>>();
//        List<String> players = new ArrayList<>();
//        players.add("Player1");
//        players.add("Player2");
//        players.add("Player3");
//        HashMap<String, List<Card>> emptyPlayerDetails = new HashMap<String, List<Card>>();
//
//        //Act
//        CardsCollection cc = new CardsCollection(locations, value);
//        cards = CardsCollection.cardCollection;
//        CardsCollection.distributeCards(players);
//        playerDetails = CardsCollection.playersCards;
//
//        //Assert
//        Assert.assertNotNull(playerDetails);
//        CardsCollection.rebundleCards();
//        Assert.assertEquals(CardsCollection.playersCards, emptyPlayerDetails);
//        Assert.assertNotNull(CardsCollection.cardStack);
//        ListIterator<Card>
//                iterator = CardsCollection.cardStack.listIterator();
//        List<String> resultLocations = new ArrayList<String>();
//        while (iterator.hasNext()) {
//            Card c = (Card) iterator.next();
//            resultLocations.add(c.location);
//        }
//        Assert.assertTrue(resultLocations.containsAll(locations));
//    }
//
//    /**
//     * Test creation of a new card, assign this card to a player and listing the cards of the player includes the newest card
//     */
//    @Test
//    public void assignCardToPlayer_shouldAddGivenCardToPlayerCardsList() {
//        int min = 0;
//        int value = 2; //r.nextInt(max - min) + min;
//        locations.add("India");
//        locations.add("Russia");
//        locations.add("China");
//        locations.add("Australia");
//        locations.add("Canada");
//        locations.add("England");
//        locations.add("California");
//        List<Card> cards = new ArrayList<Card>();
//        HashMap<String, List<Card>> playerDetails = new HashMap<String, List<Card>>();
//        List<String> players = new ArrayList<>();
//        players.add("Player1");
//        players.add("Player2");
//        players.add("Player3");
//        HashMap<String, List<Card>> emptyPlayerDetails = new HashMap<String, List<Card>>();
//
//        //Act
//        CardsCollection cc = new CardsCollection(locations, value);
//        cards = CardsCollection.cardCollection;
//        Card newCard = CardsCollection.createCard("Australia", 2);
//        CardsCollection.assignCardToPlayer("NewPlayer", newCard);
//        List<Card> playerCardsList = new ArrayList<>();
//        playerCardsList = CardsCollection.cardsOfPlayer("NewPlayer");
//
//        //Assert
//        Assert.assertNotNull(newCard);
//        Assert.assertTrue(playerCardsList.contains(newCard));
//    }
//
//
//}
//
//
