package com.company;

import java.util.stream.IntStream;

public class HelpTable {
    private final String[] rules;
    private final Referee referee;
    public HelpTable(String[] rules) {
        this.rules = rules;
        this.referee = new Referee(rules);
    }
    public void show() {
        System.out.print("          |");
        IntStream.range(0, rules.length)
                .forEach(i -> System.out.printf("%10s|", rules[i]));
        for (String computer : rules) {
            System.out.printf("\n%10s|", computer);
            for (String human : rules) {
                int result = referee.determineTheWinner(computer, human);
                System.out.printf("%10s|", result == 0 ? "Draw" : result > 0 ? "Win" : "Lose");
            }
        }
        System.out.println("\n");
    }
}
