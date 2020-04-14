package App_Risk_Game.src.main.java.Model.Players;

import App_Risk_Game.src.interfaces.Observable;
import App_Risk_Game.src.interfaces.Observer;
import App_Risk_Game.src.main.java.Controller.LoadMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerCollectionTest implements Observable {

    List<Observer> observers = new ArrayList<>();
    /**
     * Store the list of players
     */
    public static List<Player> players = new ArrayList<Player>();

    // To the player index
    private static int turn_index = 0;
    private static int number_of_players;


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

    /**
     * creates player object and assigns them name and color and places in the players list
     * @param number_of_players
     * @param player_names
     */
    public static void createPlayers(int number_of_players, ArrayList<String> player_names) {
        PlayerCollectionTest.number_of_players = number_of_players;
        ArrayList<Integer> color_index = new ArrayList<>(); // To generate random number within range of players playing
        for(int i=0;i< player_names.size(); i++)
        {
            color_index.add(i);
            Player player = new Player(player_names.get(i),i+1);
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

    // returns current player
    public static Player getTurn(){
        System.out.println("Turn index :- " + turn_index);
        return players.get(turn_index);
    }

    public static void updateTurn(){
        LoadMap.LoadMapGlobalVariables.gsFlag = false;
        System.out.println("Number of players :- " + number_of_players);
        // System.out.println("Turn index :- " + turn_index);
        if(turn_index == number_of_players -1 ){
            turn_index = 0;
        }
        else{
            turn_index = turn_index + 1;
        }
    }

    public static void goBackToGameScreen() {
        try {

            Parent RootLoad = FXMLLoader.load(PlayerCollectionTest.class.getResource("/App_Risk_Game/src/main/java/View/GameScreenTest.fxml"));
            Scene loadAttackScene = new Scene(RootLoad);
            Stage loadAttackStage = new Stage();
            loadAttackStage.setTitle("Attack Phase Loaded");
            loadAttackStage.setScene(loadAttackScene);
            loadAttackStage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
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
