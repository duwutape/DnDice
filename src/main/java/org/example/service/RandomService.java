package org.example.service;

import org.example.Constants;

import java.util.ArrayList;
import java.util.Random;

import static org.example.Constants.DICE;

public class RandomService {
    private final Random random;

    public RandomService(Random random) {
        this.random = random;
    }

    public RandomService() {
        this.random = new Random();
    }

    public int roll(int die) {
        return random.nextInt(die) + 1;
    }

    public int roll(ArrayList<Integer> amounts) {
        int result = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < amounts.get(i); j++) {
                result += roll(DICE.get(i));
            }
        }
        return result;
    }

    public int addBonus(int roll, int bonus) {
        return roll + bonus;
    }
}
