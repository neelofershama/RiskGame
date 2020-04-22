package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.interfaces.Observer;
import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Cards.CardsCollection;
import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollectionTest;
import App_Risk_Game.src.main.java.Model.Turns.Turns;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CardsController implements Observer {

    /**
     * controller is responsible for assigning territories to players for phase 0.
     * this controller is a observer for board model
     * @param observable
     */
    @Override
    public void update(App_Risk_Game.src.interfaces.Observable observable) {
        List<String> territories = new ArrayList<>();
        Iterator iterator = ((Board)observable).getTiles().keySet().iterator();
        while (iterator.hasNext()){
            String name = (String) iterator.next();
            territories.add(name);
        }
        CardsCollection cardsCollection = new CardsCollection(territories, 5);
        List<Player> players = PlayerCollectionTest.players;
        CardsCollection.distributeCards(players);
        for (Player p:players
             ) {
            System.out.println(p.getName());

            System.out.println(p.getTerritories());
        }
        Turns.turns.setCurrentPlayerID(players.get(0).getId());
        Turns.turns.setCurrent_player(players.get(0));
    }
}
