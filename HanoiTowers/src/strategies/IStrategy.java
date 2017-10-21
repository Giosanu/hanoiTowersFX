package strategies;

import items.State;

import java.util.ArrayList;

interface IStrategy {
    ArrayList<State> solve(int numOfTowers, int numOfDisks);
}
