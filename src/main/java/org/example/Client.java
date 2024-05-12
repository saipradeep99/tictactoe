package org.example;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Enter player name: ");
        String playername=sc.next();
        System.out.println("Hi "+playername+", Enter a symbol to play: ");
        String playerSymbol=sc.next();
        System.out.println("Game starts!");
    }
}