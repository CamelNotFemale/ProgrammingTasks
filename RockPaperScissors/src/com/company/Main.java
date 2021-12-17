package com.company;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    private static String menu(String[] moves) {
        Scanner in = new Scanner(System.in);
        String select;
        do {
            System.out.println("Available moves:");
            IntStream.range(0, moves.length)
                    .forEach(index -> System.out.println((index + 1) + ") " + moves[index]));
            System.out.print("0) Exit\n?) Help\n>> ");
            select = in.next();
            if (select.equals("?")) {
                new HelpTable(moves).show();
            }
        } while (select.compareTo("0")<0 || select.compareTo(Integer.toString(moves.length))>0); // need (0 <= select <= moves.length)
        return select.equals("0")?null:moves[Integer.parseInt(select)-1];
    }

    public static void main(String[] args) {
        if (args.length > 2 && args.length % 2 == 1) {
            if (new HashSet<>(Arrays.asList(args)).size() == args.length) {
                GameManager gameManager = new GameManager();
                String computerChoice = gameManager.makeMove(args);
                System.out.println("HMAC: " + gameManager.generateHMACsha256(computerChoice));
                String humanChoice = menu(args);
                if (humanChoice == null) return;
                System.out.println("Your move: " + humanChoice);
                System.out.println("Computer move: " + gameManager.getMove());
                int result = new Referee(args).determineTheWinner(computerChoice, humanChoice);
                System.out.println(result==0?"Draw":result>0?"You won!":"You lost");
                System.out.println("HMAC key: " + gameManager.getSecretKey());
            }
            else {
                System.out.println("Error: Arguments must not be repeated!");
            }
        }
        else {
            System.out.println("Error: The number of moves must exceed 2 and be odd!");
        }
    }
}
