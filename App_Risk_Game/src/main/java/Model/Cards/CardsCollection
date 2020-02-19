package Model.Cards;

import java.util.*;

public class CardsCollection {
    static int noOfLocations;
    static List<String> locations;
    public static List<Card> cardCollection;
    public static HashMap<String, List<Card>> playersCards;

    public CardsCollection(List<String> loc, int ub){
        locations = loc;
        noOfLocations = locations.size();
        cardCollection = new ArrayList<Card>();
        playersCards = new HashMap<String, List<Card>>();
        initializeCards(ub);
    }

    public static void initializeCards(int v) {
        Collections.shuffle(locations);
        Random r = new Random();
        for (int i =0; i< noOfLocations; i++) {
            int ub = v;
            int lb = 1;
            Card card = new Card();
            card.location = locations.get(i);
            card.value = (int) (Math.random() * v) + 1; //r.nextInt(ub - lb) + lb;
            cardCollection.add(card);
        }
    }

    public static void distributeCards(List<String> players){
        int n = noOfLocations % players.size();
        int noofplayers = players.size();
        int cardsforeachplayer = noOfLocations / noofplayers;
        if(n == 0)
        {
            int y  =0;
            for (int i = 0 ; i < noofplayers; i++){
                    List<Card> lc = new ArrayList<Card>();
                    lc.addAll(cardCollection.subList(y, y+ cardsforeachplayer));
                    y += cardsforeachplayer;
                    playersCards.put(players.get(i), lc);
        }
        }
        else {
            int y  =0;
            for (int i = 0 ; i < noofplayers; i++){
                List<Card> lc = new ArrayList<Card>();
                lc.addAll(cardCollection.subList(y, y+ cardsforeachplayer));
                y += cardsforeachplayer;
                playersCards.put(players.get(i), lc);
        }
            //TODO - assign remaining cards to last 2 players
            int remainingcards = noOfLocations % noofplayers;
            for (int i = 0; i < remainingcards; i++)
            {
              playersCards.get(noofplayers--).add(cardCollection.get(y));
              y++;
            }
        }

    }
}
