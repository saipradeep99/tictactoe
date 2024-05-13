package org.example.strategies.WinningStrategy;

import org.example.models.Board;
import org.example.models.Move;
import org.example.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy{
    private Map<Symbol, Integer> leftDiagCount = new HashMap<>();
    private Map<Symbol, Integer> rightDiagCount = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();
        Symbol symbol = move.getPlayer().getSymbol();
        //left diagonal
        if(row == col){
            if(!leftDiagCount.containsKey(symbol))  leftDiagCount.put(symbol, 0);
            leftDiagCount.put(symbol, leftDiagCount.get(symbol) + 1);
            if(leftDiagCount.get(symbol) == board.getSize())    return true;
        }
        //right diagonal
        if(row + col == board.getSize() - 1){
            if(!rightDiagCount.containsKey(symbol)) rightDiagCount.put(symbol, 0);
            rightDiagCount.put(symbol, rightDiagCount.get(symbol) + 1);
            if(rightDiagCount.get(symbol) == board.getSize()) return true;
        }
        return false;
    }
}
