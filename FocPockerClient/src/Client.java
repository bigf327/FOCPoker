import GUI.gameWindows.MainWindow;
import domain.Game;
import valueObjects.Player;

public class Client  {

    private Player player;
    public  Client (){
        this.player = new Player(5,"Bob",2500,"Bob");
        Game game = new Game();
        MainWindow window = new MainWindow(this.player,game);
        game.startGameLoop();

    }

    public static void main(String[] args){

        new Client();
    }

}
