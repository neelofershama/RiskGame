package App_Risk_Game.src.interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Observable {

    public void attachObserver(App_Risk_Game.src.interfaces.Observer observer);
    public void dettachObserver(App_Risk_Game.src.interfaces.Observer observer);
    public  void notifyObserver(App_Risk_Game.src.interfaces.Observable o) throws IOException;
}
