package com.company;

import java.util.Arrays;

public class Referee {
    private final String[] rules;
    public Referee(String[] rules) {
        this.rules = rules;
    }
    /** @return 0 - Draw, -1 - Computer, 1 - Human */
    public int determineTheWinner(String computer, String human) {
        int n = rules.length;
        int a = Arrays.asList(rules).indexOf(computer)+1;
        int b = Arrays.asList(rules).indexOf(human)+1;
        return (n+a-b)%n == 0 ? 0 : (n+a-b)%n%2 == 0 ? 1 : -1;
    }
}
