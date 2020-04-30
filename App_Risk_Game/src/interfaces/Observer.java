package App_Risk_Game.src.interfaces;

import java.io.IOException;

public interface Observer {
    public void update(Observable observable) throws IOException;
}
