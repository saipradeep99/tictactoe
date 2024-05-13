package org.example.models;

import org.example.exceptions.BotCountException;
import org.example.exceptions.PlayerCountException;
import org.example.exceptions.UniquePlayerCharactersException;
import org.example.strategies.WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private Board board;
    private GameState gameState;
    private int nextMovePlayerIndex;
    private List<WinningStrategy> winningStrategies;

    public Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.players = players;
        this.moves = new ArrayList<>();
        this.board = new Board(dimension);
        this.gameState = GameState.IN_PROGRESS;
        this.nextMovePlayerIndex = 0;
        this.winningStrategies = winningStrategies;
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public void printBoard(){
        this.board.print();
    }

    public boolean checkWinner(Move move){
        for(WinningStrategy winningStrategy: winningStrategies){
            if(winningStrategy.checkWinner(board, move))   return true;
        }
        return false;
    }

    public void makeMove(){
        Player currentMovePlayer=this.players.get(nextMovePlayerIndex);
        System.out.println("This is "+currentMovePlayer.getName()+"'s turn");
        Move move=currentMovePlayer.makeMove(board);
        if(!validateMove(move)){
            System.out.println("Invalid move!");
            return;
        }
        int row=move.getCell().getRow();
        int column=move.getCell().getColumn();
        Cell boardCell=this.board.getBoard().get(row).get(column);
        boardCell.setCellState(CellState.FILLED);
        boardCell.setPlayer(currentMovePlayer);
        Move finalMove=new Move(boardCell,currentMovePlayer);
        this.moves.add(finalMove);
        nextMovePlayerIndex++;
        nextMovePlayerIndex%=players.size();
        if(checkWinner(finalMove)){
            gameState = GameState.WIN;
            winner=currentMovePlayer;
        }
        if(moves.size()==this.board.getSize()*this.board.getSize()){
            gameState=GameState.DRAW;
        }
    }

    public boolean validateMove(Move move){
        int row=move.getCell().getRow();
        int column=move.getCell().getColumn();
        if(row>board.getSize() || column>board.getSize()) return false;
        if(board.getBoard().get(row).get(column).getCellState().equals(CellState.EMPTY))    return true;
        return false;
    }

    public static class Builder{
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int dimension;

        private Builder(){
            this.players=new ArrayList<>();
            this.winningStrategies=new ArrayList<>();
            this.dimension=0;
        }

        public Builder setPlayers(List<Player> players){
            this.players=players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies){
            this.winningStrategies=winningStrategies;
            return this;
        }

        public Builder setDimension(int dimension){
            this.dimension=dimension;
            return this;
        }

        public Builder addPlayer(Player player) {
            this.players.add(player);
            return this;
        }

        public Builder addWinningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategies.add(winningStrategy);
            return this;
        }

        public Game build(){
            try {
                validate();
            } catch (Exception e) {
                System.out.println("Exception found while validation!");
                throw new RuntimeException(e);
            }
            return new Game(dimension,players,winningStrategies);
        }

        public void validate() throws Exception{
            validateBotCount();
            validatePlayerCount();
            validateUniquePlayerCharacters();
        }

        public void validateBotCount() throws BotCountException {
            int botCount=0;
            for(Player player:players){
                if(player.getPlayerType().equals(PlayerType.BOT))   botCount++;
            }
            if(botCount>1)  throw new BotCountException();
        }

        public void validatePlayerCount() throws PlayerCountException {
            if(players.size()!=dimension-1) throw new PlayerCountException();
        }

        public void validateUniquePlayerCharacters() throws UniquePlayerCharactersException {
            Set<Character> symbols = new HashSet<>();
            for(Player player: players){
                if(symbols.contains(player.getSymbol().getCh()))    throw new UniquePlayerCharactersException();
                symbols.add(player.getSymbol().getCh());
            }
        }

    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }

    public void setNextMovePlayerIndex(int nextMovePlayerIndex) {
        this.nextMovePlayerIndex = nextMovePlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }
}
