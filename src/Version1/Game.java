package Version1;
import java.util.*;

// chutes means snakes.
public class Game {
    Player[] players;
    HashMap<Integer, Integer> ladders = new HashMap<>(), chutes = new HashMap<>();
    int totalNumberOfTurns;

    public Game(){
        players = new Player[2];
    }

    public Game(int numberOfPlayers){
        players = new Player[numberOfPlayers];
    }

    public static void main(String[] args){
        Game game;
        Scanner sc = new Scanner(System.in);
        int numberOfPlayers;
        System.out.println("****Welcome to Snakes and Ladders Game****");
        System.out.println("Two players\nThree players\nFour players\nEnter number of players:");
        numberOfPlayers = sc.nextInt();
        sc.nextLine();
        if (numberOfPlayers > 1 &&  numberOfPlayers <= 4)
            game = new Game(numberOfPlayers);
        else
            game = new Game();
        game.intializeGameParameters();
        System.out.println(game.startGame().playerName + " is the winner.");
        System.out.println("Total number of turns took to complete the game is: "+game.totalNumberOfTurns);
    }

    private void intializeGameParameters(){
        for (int index = 0; index < players.length; index++) {
            System.out.println("Enter the player"+(index+1)+" name:");
            String name = new Scanner(System.in).nextLine();
            players[index] = new Player(name);
        }
        generateLadders();
        generateChutes();
    }

    private Player startGame(){
        int playerIndex = 0;
        while (true){
            if (playerIndex == players.length)
                playerIndex = 0;
            System.out.println("player"+(playerIndex+1)+" turn");
            int diceNumber = players[playerIndex].rollDice();
            System.out.println(players[playerIndex].playerName+" rolled a "+diceNumber);
            if (players[playerIndex].position + diceNumber > 100) {
                System.out.println(players[playerIndex].playerName+" current position is: "+players[playerIndex].position);
                playerIndex++;
                continue;
            }
            else if (players[playerIndex].position + diceNumber == 100) {
                System.out.println("\n");
                return players[playerIndex];
            }
            else {
                int temp = players[playerIndex].position + diceNumber;
                if (ladders.containsKey(temp)) {
                    players[playerIndex].position = ladders.get(temp);
                    System.out.println("\nladder for player: "+players[playerIndex].playerName);
                }
                else if(chutes.containsKey(temp)) {
                    players[playerIndex].position = chutes.get(temp);
                    System.out.println("\nchute for player: "+players[playerIndex].playerName);
                }
                else
                    players[playerIndex].position += diceNumber;
            }
            System.out.println(players[playerIndex].playerName+" current position is: "+players[playerIndex].position);
            playerIndex++;
            totalNumberOfTurns++;
        }
    }

    private void generateLadders(){
        Random random = new Random();
        for (int itr = 1; itr <= 5; ){
            int lower_number = random.nextInt(70);
            if ((lower_number >= 0 && lower_number < 10) || ladders.containsKey(lower_number) || ladders.containsValue(lower_number))
                continue;
            else{
                ladders.put(lower_number, lower_number + 30);
                itr++;
            }
        }
    }

    private void generateChutes() {
        Random random = new Random();
        for (int itr = 1; itr <= 5; ) {
            int higher_number = random.nextInt(98);
            if ((higher_number > 0 && higher_number <= 30)||ladders.containsKey(higher_number) || ladders.containsValue(higher_number) || chutes.containsKey(higher_number) || chutes.containsValue(higher_number))
                continue;
            else {
                chutes.put(higher_number, higher_number - 30);
                itr++;
            }
        }
    }

}
