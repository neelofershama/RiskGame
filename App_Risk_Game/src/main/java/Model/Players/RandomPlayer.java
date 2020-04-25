package App_Risk_Game.src.main.java.Model.Players;

import App_Risk_Game.src.main.java.Common.Common;
import App_Risk_Game.src.main.java.Controller.LoadMap;
import App_Risk_Game.src.main.java.Model.Cards.CardsCollection;
import App_Risk_Game.src.main.java.Model.Score.Dice;
import App_Risk_Game.src.main.java.Model.Turns.Turns;

import java.util.*;
import java.util.stream.Collectors;

public class RandomPlayer implements PlayerBehaviour {
    Player p;
    Random random = new Random();
    boolean attack_started;
    int type_of_player = (int) (Math.random() * 2) + 1;
    @Override
    public void reinforce(Player player) {
        System.out.println("Random player Reinforce Phase");
        p = player;
        p = player;
        int maxTroops = 3;
        HashMap<String, Integer> terr = player.getTerritories();
        System.out.println("territories : " + terr.toString());
        while (maxTroops != 0) {

            int troops = Common.generateRandomNumber(maxTroops);
            maxTroops = maxTroops - troops;
            String[] keyArray = terr.keySet().toArray(new String[terr.size()]);
            String territory = keyArray[Common.generateRandomNumber(terr.size() - 1)];
            player.setTerritory(territory, troops);

        }
        return;

    }
@Override
    public void attack() {

    //int type_of_player =
    //int type_of_player =2;
    if(type_of_player ==1){

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
           if (troops_in_attacking_territory >= 1) {
               List<String> defendList = LoadMap.board.getNeighbourTile(attacking_from.getKey());
               Iterator it = defendList.listIterator();
               while (it.hasNext()) {
                   String country = (String) it.next();
                   if (p.getTerritories().containsKey(country)) {
                       it.remove();
                   }
               }
               if (defendList.size() > 0) {
                   String attacking_on = defendList.get((int) (Math.random() * defendList.size()));
                   Turns.turns.setDefenceplayer(attacking_on);
                   int troops_defence = Turns.turns.getDefenceplayer().territories.get(attacking_on);
                   if (troops_defence <=0)return ;
                   int max_troops = 1;
                   if (troops_in_attacking_territory >= 3)
                       max_troops = 3;
                   else if (troops_in_attacking_territory == 2)
                       max_troops = 2;
                   else
                       max_troops = 1;
                   System.out.println("Attacking with troops " + max_troops);
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
//                   System.out.println(f.getName());
//                   System.out.println(f.getId());

                   int t = PlayerCollectionTest.players.get(f.getId() - 1).getTerritories().get(attacking_on);
                   t = t - troopsofdfc;
                   PlayerCollectionTest.players.get(f.getId() - 1).getTerritories().replace(attacking_on, t);

                   if (t == 0) {
                       PlayerCollectionTest.players.get(f.getId() - 1).getTerritories().remove(attacking_on);
                       PlayerCollectionTest.players.get(p.getId() - 1).getTerritories().put(attacking_on, current_troop);
                   }
                   // attack_started = true;
                   List<Player> players = PlayerCollectionTest.players;

                   for (Player p : players
                   ) {
                       System.out.println(p.getName());

                       System.out.println(p.getTerritories());
                   }
                   attackAggresively(attacking_from);
               }
               fortify();
           }
       else
       {
           fortify();
       }
    }

    @Override
    public boolean fortify() {
        System.out.println("Random player Fortify Phase");
if (type_of_player ==1){
    //fortify aggressively
    return  fortifyAggressively();
}
else
    //fortify conservatively
      return  fortifyConservatively();
    }

    private boolean fortifyAggressively() {
        Map.Entry<String, Integer> territoritory_with_second_highest_troops = p.territories.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).collect(Collectors.toList()).get(1);
        List<String> neighboring_territories = LoadMap.board.getNeighbourTile(territoritory_with_second_highest_troops.getKey());
        Iterator it = neighboring_territories.listIterator();
        while (it.hasNext()) {
            String country = (String) it.next();
            if (!p.getTerritories().containsKey(country)) {
                it.remove();
            }
        }
        Optional<Map.Entry<String, Integer>> neighbor_with_highes_troops = p.territories.entrySet().stream().filter(x -> neighboring_territories.contains(x.getKey())).sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).findFirst();
        if (!neighbor_with_highes_troops.equals(Optional.empty())) {
            Map.Entry<String, Integer> neighbours = neighbor_with_highes_troops.get();
            int troops = neighbours.getValue() - 1;
            p.territories.replace(territoritory_with_second_highest_troops.getKey(), territoritory_with_second_highest_troops.getValue() + troops);
            p.territories.replace(neighbours.getKey(), 1);
        }
        System.out.println("After Fortifying");
        List<Player> players = PlayerCollectionTest.players;

        for (Player p : players
        ) {
            System.out.println(p.getName());

            System.out.println(p.getTerritories());
        }
        return  true;
    }

    private boolean fortifyConservatively() {

        Map.Entry<String, Integer> territoritory_with_highest_troops = p.territories.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).findFirst().get();
        List<String> neighboring_territories = LoadMap.board.getNeighbourTile(territoritory_with_highest_troops.getKey());
        Iterator it = neighboring_territories.listIterator();
        while (it.hasNext()) {
            String country = (String) it.next();
            if (!p.getTerritories().containsKey(country)) {
                it.remove();
            }
        }
        Optional<Map.Entry<String, Integer>> neighbor_with_lowest_troops = p.territories.entrySet().stream().filter(x -> neighboring_territories.contains(x.getKey())).sorted(Map.Entry.<String, Integer>comparingByValue()).findFirst();
        if (!neighbor_with_lowest_troops.equals(Optional.empty())) {
            Map.Entry<String, Integer> neighbours = neighbor_with_lowest_troops.get();
            int troops_lowest = neighbours.getValue();
            int troops_highest = territoritory_with_highest_troops.getValue();
            if(troops_lowest != 0 && troops_highest !=0 ) {
                int average = (int) (troops_highest + troops_lowest) / 2;
                p.territories.replace(territoritory_with_highest_troops.getKey(), troops_highest - troops_lowest);
                p.territories.replace(neighbours.getKey(), troops_lowest + average);
            }}
        System.out.println("After Fortifying");
        List<Player> players = PlayerCollectionTest.players;

        for (Player p : players
        ) {
            System.out.println(p.getName());

            System.out.println(p.getTerritories());
        }
        return  true;
    }
}
