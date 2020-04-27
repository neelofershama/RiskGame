package App_Risk_Game.src.main.java.Model.Players;

import java.io.IOException;

public interface PlayerBehaviour {
    public void reinforce(Player p) throws IOException;
    public void attack();
    public boolean fortify();
}
