package App_Risk_Game.src.main.java.Model.Players;

import App_Risk_Game.src.main.java.Common.Common;
import App_Risk_Game.src.main.java.Controller.LoadMap;

import java.util.*;

public class ConservativePlayer implements PlayerBehaviour {
Player p;
    @Override
    public void reinforce(Player player) {
        System.out.println("Conservative Reinforce Phase");
p =player;
        p = player;
        int maxTroops = 3;
        HashMap<String, Integer> terr = player.getTerritories();
        System.out.println("territories : " + terr.toString());
        while (maxTroops != 0) {

            int troops = Common.generateRandomNumber(maxTroops);
            maxTroops = maxTroops - troops;
            if (terr.size() != 0 && terr.size()!=1) {
                String[] keyArray = terr.keySet().toArray(new String[terr.size()]);
                String territory = keyArray[Common.generateRandomNumber(terr.size() - 1)];
                player.setTerritory(territory, troops);
            } else if(terr.size()==1){
                String[] keyArray = terr.keySet().toArray(new String[terr.size()]);
                String territory = keyArray[0];
                player.setTerritory(territory, troops);
            }
        }
        return;
    }

    @Override
    public void attack() {
//fortify();
        return;
    }


    //Fortifcation phase-shows troops in each territory
    @Override
    public boolean fortify() {
        System.out.println("Conservative Fortify Phase");
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
            }
            System.out.println("After Fortifying");
        }
        List<Player> players = PlayerCollectionTest.players;

        for (Player p : players
        ) {
            System.out.println(p.getName());

            System.out.println(p.getTerritories());
        }
        return true;
    }
}
