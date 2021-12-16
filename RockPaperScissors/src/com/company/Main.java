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
            if (select.equals('?')) {
                // print HELP
            }
        } while (select.compareTo("0")<0 || select.compareTo(Integer.toString(moves.length))>0); // need (0 < select < moves.length)

        return moves[Integer.parseInt(select)-1];
    }

    public static void main(String[] args) {
        if (args.length % 3 == 0) {
            if (new HashSet<>(Arrays.asList(args)).size() == args.length) {
                GameManager gameManager = new GameManager();

                System.out.println("HMAC: " + gameManager.makeAMove(args));
                String humanChoice = menu(args);
                System.out.println(humanChoice);
            }
            else {
                System.out.println("Error: Arguments must not be repeated!");
            }
        }
        else {
            System.out.println("Error: The number of arguments must be a multiple of 3!");
        }
    }
}
