package tiktactoe.player;

import tiktactoe.player.CleverPlayer;
import tiktactoe.player.HumanPlayer;
import tiktactoe.player.Player;

public class PlayerFactory {
    public Player buildPlayer (String type){
        switch (type) {
            case "human":
            case "Human":
                return new HumanPlayer();
            case "whatever":
            case "Whatever":
                return new WhateverPlayer();
            case "clever":
            case "Clever":
                return new CleverPlayer();
            default: return null;
        }
    }
}
