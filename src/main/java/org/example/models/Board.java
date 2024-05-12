package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> board;

    public Board(int size){
        this.size=size;
        board=new ArrayList<>();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                board.get(i).add(new Cell(i,j));
            }
        }
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public int getSize() {
        return size;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

        public void print(){
        for(List<Cell> row: board){
            for(Cell cell: row){
                cell.print();
            }
            System.out.println();
        }
    }
}
