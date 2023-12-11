public class PlayerFactory {
    public Player buildPlayer (String type){
        return new HumanPlayer();
    }
}
