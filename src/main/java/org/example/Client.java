package org.example;
import org.example.controllers.GameController;
import org.example.exceptions.BotCountException;
import org.example.exceptions.PlayerCountException;
import org.example.exceptions.UniquePlayerCharactersException;
import org.example.models.*;
import org.example.strategies.WinningStrategy.ColumnWinningStrategy;
import org.example.strategies.WinningStrategy.DiagonalWinningStrategy;
import org.example.strategies.WinningStrategy.RowWinningStrategy;
import org.example.strategies.WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws BotCountException, PlayerCountException, UniquePlayerCharactersException {
        GameController gameController=new GameController();
        int dimension = 3;
        List<Player> players=new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe!");
//        System.out.println("Enter player name: ");
//        String playername=sc.next();
//        System.out.println("Hi "+playername+", Enter a symbol to play: ");
//        char playerSymbol=sc.next().charAt(0);
        players.add(new Player(1,"Pradeep",new Symbol('x'),PlayerType.HUMAN));
        players.add(new Bot(2,"Bot",new Symbol('o'), BotDifficultyLevel.EASY));
        List<WinningStrategy> winningStrategies=new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColumnWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());
        try {
            Game game=gameController.startGame(dimension,players,winningStrategies);
            System.out.println("Game initialized successfully!");
            while(gameController.checkStatus(game).equals(GameState.IN_PROGRESS)){
                gameController.printBoard(game);
                gameController.makeMove(game);
            }
            if(gameController.checkStatus(game).equals(GameState.DRAW)){
                System.out.println("Game has been drawn");
            }
            if(gameController.checkStatus(game).equals(GameState.WIN)){
                System.out.println("Game has been won by "+gameController.getWinner(game).getName());
            }
        } catch (Exception e) {
            throw e;
        }
    }
}