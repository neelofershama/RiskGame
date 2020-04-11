package App_Risk_Game.src.main.java.Model.Players;



import App_Risk_Game.src.interfaces.Observable;
import App_Risk_Game.src.interfaces.Observer;
import App_Risk_Game.src.main.java.Model.Cards.Card;
import App_Risk_Game.src.main.java.Model.Cards.CardsCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class PlayerCollection implements Observable {

    List<Observer> observers = new ArrayList<>();
    /**
     * Store the list of players
     */
    public static List<Player> players = new ArrayList<Player>();


    private int noofPlayers;

    private List<String> namesPlayers;


    public static void createPlayers(int number_of_players, ArrayList<String> player_names) {

        for(int i=0;i< player_names.size(); i++)
        {
            Player player = new Player(player_names.get(i),i+1);
            players.add(player);
        }
        ListIterator<Player>
                iterator = PlayerCollection.players.listIterator();
        while(iterator.hasNext()){
            Player p = (Player)iterator.next();
            System.out.println(p.getName());
            System.out.println(p.getId());
        }

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
    public void notifyObserver(Observable observable) {
        for (Observer o:observers
             ) {
            o.update(observable);
        }
    }
}
