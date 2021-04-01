package g56055.luckynumbers;

import g56055.luckynumbers.controller.Controller;
import g56055.luckynumbers.model.Game;
import g56055.luckynumbers.model.Model;
import g56055.luckynumbers.view.MyView;
import g56055.luckynumbers.view.View;

/**
 * Main class of the LuckyNumbers Games !
 *
 * @author Duran Rehan g56055
 */
public class LuckyNumbers {

    public static void main(String[] args) {
        Model game = new Game();
        View view = new MyView(game);
        Controller controller = new Controller(game, view);
        controller.play();

        /*
        Fin Iteration 1.
         */
    }
}
