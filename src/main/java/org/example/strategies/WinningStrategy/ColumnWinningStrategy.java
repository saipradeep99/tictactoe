package org.example.strategies.WinningStrategy;

import org.example.models.Board;
import org.example.models.Move;
import org.example.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy{
    Map<Integer, Map<Symbol, Integer>> counts = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int column=move.getCell().getColumn();
        Symbol symbol=move.getPlayer().getSymbol();
        if(!counts.containsKey(column))    counts.put(column, new HashMap<>());
        Map<Symbol, Integer> columnMap = counts.get(column);
        if (!columnMap.containsKey(symbol))    columnMap.put(symbol, 0);
        columnMap.put(symbol, columnMap.get(symbol) + 1);
        if (columnMap.get(symbol).equals(board.getSize())) return true;
        return false;
    }
}
