public class PlayerFactory {
    public Player buildPlayer (String type){
        switch (type) {
            case "human":
                return new HumanPlayer();
            default: return null;
        }
    }
}
