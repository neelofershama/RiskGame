package App_Risk_Game.src.main.java.Controller;

import App_Risk_Game.src.interfaces.Observer;
import App_Risk_Game.src.main.java.Model.Board.Board;

import java.util.Observable;

public class LoadMap implements Observer {

    @Override
    public void update(App_Risk_Game.src.interfaces.Observable observable) {
        CardsController cardsController = new CardsController();
        Board board = new Board();
        board.attachObserver((App_Risk_Game.src.interfaces.Observer)cardsController);
    }
}
