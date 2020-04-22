package App_Risk_Game.src.main.java.Model.Turns;

import App_Risk_Game.src.main.java.Model.Players.Player;
import App_Risk_Game.src.main.java.Model.Players.PlayerCollectionTest;

import java.util.Iterator;

/**
 * This class is used to maintain Turns module and its functionality.
 * Below are the 4 actions you’ll do on each turn.
 * 1. Place Your Troops
 * 2. Attack
 * 3. Maneuver Your Troops
 * 4. Draw a Card if You Can
 *
 * @author Akhila
 * @version 1.0.0
 */

public class Turns {
    private
    boolean map_created;
    boolean game_begin;
    int current_playerID;
    TurnPhase current_phase;
    boolean attack_result;

    public int getDefenceplayerid() {

        return defenceplayerid;
    }

//    public void setDefenceplayerid(String name) {
//        Iterator it = PlayerCollection.players.listIterator();
//        while (it.hasNext())
//        {
//            Player p = (Player) it.next();
//            if(p.getName().equals(name))
//                defenceplayerid = p.getId();
//        }
//        System.out.println(defenceplayerid);
//    }

    int defenceplayerid;
    public Player getDefenceplayer() {
        return defenceplayer;
    }

    //identifies defence layer during attack
    public void setDefenceplayer(String defenceterritory) {
        Iterator it = PlayerCollectionTest.players.listIterator();
        while (it.hasNext())
        {
            Player p = (Player) it.next();
            if(p.getTerritories().containsKey(defenceterritory)) {
                defenceplayer = p;
                //defenceplayerid = p.getId();
                break;
            }
        }
System.out.println(defenceplayer);
    }

    Player defenceplayer;
    public Player getCurrent_player() {
        return current_player;
    }

    public void setCurrent_player(Player current_player) {
        this.current_player = current_player;
    }

    Player current_player;
public static Turns turns = new Turns();
    public boolean mapCreated() {
        return map_created;
    }

    public void setMapCreated(boolean mapCreated) {
        this.map_created = mapCreated;
    }

    public boolean isGameBegin() {
        return game_begin;
    }

    public void setGameBegin(boolean gameBegin) {
        this.game_begin = gameBegin;
    }

    public int getCurrentPlayerID() {
        return current_playerID;
    }

    public void setCurrentPlayerID(int current_PlayerID) {
        this.current_playerID = current_PlayerID;
    }

    public TurnPhase getCurrentPhase() {
        return current_phase;
    }

    public void setCurrentPhase(TurnPhase current_Phase) {
        this.current_phase = current_Phase;
    }

    public boolean attackResult() {
        return attack_result;
    }

    public void setAttackResult(boolean attack_Result) {
        this.attack_result = attack_Result;
    }

    /**
     * This the constructor of the class it initialize properties of the object
     */
    public Turns() {

        map_created = false;
        game_begin = false;
        current_playerID = -1;
        current_phase = TurnPhase.PreGame;
        /**
         * missing this should change once attack feature added
         *
         */
        attack_result = true;
    }

    /**
     * This method set game-started flag
     */
    public void GameBegin() {
        this.game_begin = true;
    }

    /**
     * this method verifies if game started
     */
    public boolean gameBegin() {
        return this.game_begin;
    }

    /**
     * This method set the current phase
     *
     * @param currentPhase, which its type is enum, will replace the current phase
     */
    public void SetCurrentPhase(TurnPhase currentPhase) {
        this.current_phase = currentPhase;
    }

    /**
     * This method returns the current phase
     *
     * @return which its type is enum, is the current phase of the game
     */
    public TurnPhase GetcurrentPhase() {
        return this.current_phase;
    }

    // tbd
    // in the build1 we didn't have phase attack, however in the future it should be
    // implemented

    /**
     * This method compute the next phase and return it
     *
     * @return which its type is enum, the current phase of the game
     */

    public TurnPhase GetUpcomingPhase() {
        switch (this.current_phase) {
            case StartGame:
                return TurnPhase.Reinforcement;
            case Reinforcement:
                return TurnPhase.Fortification;
            case Fortification:
                return TurnPhase.Reinforcement;
            default:
                return TurnPhase.PreGame;
        }
    }

    /**
     * This method set the current playerId
     *
     * @param playerId, which its type is integer, will replace the current player ID
     */
    public void SetCurrentPlayerID(int playerId) {
        this.current_playerID = playerId;
    }

    /**
     * this method returns the current playerId
     *
     * @return the id of the current player
     */
    public int currentPlayerID() {
        return current_playerID;
    }

    /**
     * This method verifies if map is loaded
     */
    public boolean MapCreated() {
        return this.map_created;
    }

}

