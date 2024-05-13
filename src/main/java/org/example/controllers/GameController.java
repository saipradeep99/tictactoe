package org.example.controllers;

import org.example.exceptions.BotCountException;
import org.example.exceptions.PlayerCountException;
import org.example.exceptions.UniquePlayerCharactersException;
import org.example.models.Game;
import org.example.models.GameState;
import org.example.models.Player;
import org.example.strategies.WinningStrategy.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int dimensionBoard, List<Player> players, List<WinningStrategy> winningStrategies) throws BotCountException, PlayerCountException, UniquePlayerCharactersException {
        return Game.getBuilder().setDimension(dimensionBoard)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public void makeMove(Game game){
        game.makeMove();
    }

    public void printBoard(Game game){
        game.printBoard();
    }

    public GameState checkStatus(Game game){
        return game.getGameState();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }
}
