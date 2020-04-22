package App_Risk_Game.src.main.java.Model.Players;

import App_Risk_Game.src.main.java.Controller.LoadMap;
import App_Risk_Game.src.main.java.Model.Cards.CardsCollection;
import App_Risk_Game.src.main.java.Model.Score.Dice;
import App_Risk_Game.src.main.java.Model.Turns.Turns;

import java.util.*;

public class RandomPlayer implements PlayerBehaviour {
    Player p;
    Random random = new Random();
    boolean attack_started;
    @Override
    public void reinforce() {

    }
@Override
    public void attack(Player player) {
    p = player;
    //int n = (int) (Math.random() * 2) + 1;
    int n =1;
    if(n ==1){

        Map.Entry<String, Integer> attacking_from = p.territories.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).findFirst().get();
        //attack_started = false;
    attackAggresively(attacking_from);
    }
else {
    //Conservative behaviour

    fortify();
    }
}

    public void attackAggresively(Map.Entry<String, Integer> attacking_from) {
           //aggressive play
        System.out.println("Attacking from territory "+attacking_from.getKey());
           int troops_in_attacking_territory = attacking_from.getValue()-1;
           if (troops_in_attacking_territory >1) {
               List<String> defendList = LoadMap.board.getNeighbourTile(attacking_from.getKey());
               Iterator it = defendList.listIterator();
               while (it.hasNext()) {
                   String country = (String) it.next();
                   if (p.getTerritories().containsKey(country)) {
                       it.remove();
                   }
               }

               String attacking_on = defendList.get((int) (Math.random() * defendList.size()));
               Turns.turns.setDefenceplayer(attacking_on);

               int max_troops = 1;
               if (troops_in_attacking_territory >= 3)
                   max_troops = 3;
               else if (troops_in_attacking_territory == 2)
                   max_troops = 2;
               else
                   max_troops = 1;
               System.out.println("Attacking with troops "+max_troops);
               //int troops_to_attack = (int) (Math.random() * max_troops) + 1;
               int troops_to_attack = max_troops;
               int o = 0;
               int m = troops_to_attack;
               if (m >= 2)
                   o = 2;
               else if (m == 1)
                   o = 1;
               Dice dice = new Dice();

               List<Integer> troopslost = dice.rollDice(m, o);
               int troopsofatk = troopslost.get(0);
               int troopsofdfc = troopslost.get(1);
               System.out.println("Troops lost by attack " + troopsofatk);
               System.out.println("Troops lost by defence " + troopsofdfc);
               int current_troop = troops_in_attacking_territory - troopsofatk;
               PlayerCollectionTest.players.get(Turns.turns.getCurrentPlayerID() - 1).getTerritories().replace(attacking_from.getKey(), current_troop);
               Player f = Turns.turns.getDefenceplayer();
               System.out.println(f.getName());
                System.out.println(f.getId());

               int t = PlayerCollectionTest.players.get(f.getId()-1).getTerritories().get(attacking_on);
               t = t - troopsofdfc;
               PlayerCollectionTest.players.get(f.getId()-1).getTerritories().replace(attacking_on, t);

               if (t == 0) {
                   PlayerCollectionTest.players.get(f.getId()-1).getTerritories().remove(attacking_on);
                   PlayerCollectionTest.players.get(p.getId() - 1).getTerritories().put(attacking_on, current_troop);
               }
              // attack_started = true;
               List<Player> players = PlayerCollectionTest.players;

               for (Player p:players
               ) {
                   System.out.println(p.getName());

                   System.out.println(p.getTerritories());
               }
               attackAggresively(attacking_from);
           }

       else
       {
           fortify();
       }
    }

    @Override
    public void fortify() {
        System.out.println("Fortify");

    }
}
