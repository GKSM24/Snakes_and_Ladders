package Version1;

public class Player {
    String playerName;
    int position;

    public Player(){
        playerName = "ID103210";
        position = 0;
    }
    public Player(String playerName){
        this.playerName = playerName;
        this.position = 0;
    }

    public int rollDice(){
        return (int)((Math.random() * 6) + 1);
    }
}
