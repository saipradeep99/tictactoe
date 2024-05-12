package org.example.models;

import org.example.strategies.BotPlayingStrategy.BotPlayingStrategy;
import org.example.strategies.BotPlayingStrategy.BotPlayingStrategyFactory;

public class Bot extends Player {

    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(long id, String name, Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
        super(id, name, symbol, PlayerType.BOT);
        this.botDifficultyLevel=botDifficultyLevel;
        this.botPlayingStrategy= BotPlayingStrategyFactory.getBotPlayingStrategyFactory(botDifficultyLevel);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public Move makeMove(Board board){
        Move move=botPlayingStrategy.makeMove(board);
        move.setPlayer(this);
        return move;
    }
}
