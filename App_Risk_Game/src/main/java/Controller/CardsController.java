package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.interfaces.Observer;
import App_Risk_Game.src.main.java.Model.Board.Board;
import App_Risk_Game.src.main.java.Model.Cards.CardsCollection;
import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class CardsController implements Observer {

    @Override
    public void update(App_Risk_Game.src.interfaces.Observable observable) {
        List<String> territories = new ArrayList<>();
        Iterator iterator = ((Board)observable).getTiles().keySet().iterator();
        while (iterator.hasNext()){
            String name = (String) iterator.next();
            territories.add(name);
        }
        CardsCollection cardsCollection = new CardsCollection(territories, 2);
        List<Player> players = PlayerCollection.players;
        CardsCollection.distributeCards(players);
    }
}
