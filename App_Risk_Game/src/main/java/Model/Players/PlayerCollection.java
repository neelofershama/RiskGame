package App_Risk_Game.src.main.java.Model.Players;



import App_Risk_Game.src.interfaces.Observable;
import App_Risk_Game.src.interfaces.Observer;
import App_Risk_Game.src.main.java.Model.Cards.Card;
import App_Risk_Game.src.main.java.Model.Cards.CardsCollection;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

    private static ArrayList<String> color_name = new ArrayList<String>(){
        {
            add("RED");
            add("GREEN");
            add("BLUE");
            add("ORANGE");
            add("MAROON");
            add("CYAN");
        }
    };

    public static void createPlayers(int number_of_players, ArrayList<String> player_names,List<PlayerBehaviour> player_behaviors) {
        ArrayList<Integer> color_index = new ArrayList<>(); // To generate random number within range of players playing
        for(int i=0;i< player_names.size(); i++)
        {
            color_index.add(i);
            Player player = new Player(player_names.get(i),i+1,player_behaviors.get(i));
            players.add(player);
        }
        // shuffling the colors of the player
        Collections.shuffle(color_index);

        // Assigning the color name to players
        for(int i=0;i< players.size(); i++)
        {
            Player p = players.get(i);
            int index = color_index.get(i);
            p.setColor(color_name.get(index));
            System.out.println(p.getName());
            System.out.println(p.getColor());
        }

//        ListIterator<Player>
//                iterator = PlayerCollection.players.listIterator();
//        while(iterator.hasNext()){
//            Player p = (Player)iterator.next();
//            System.out.println(p.getName());
//            System.out.println(p.getId());
//        }

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
    public void notifyObserver(Observable observable) throws IOException {
        for (Observer o:observers
             ) {
            o.update(observable);
        }
    }
}
