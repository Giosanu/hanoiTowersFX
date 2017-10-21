package strategies;

import items.State;

import java.util.ArrayList;

public class Context {
    private IStrategy strategy;

    public Context(IStrategy strategy){
        this.strategy = strategy;
    }

        public ArrayList<State> executeStrategy(int numOfTowers, int numOfDisks){
            return strategy.solve(numOfTowers, numOfDisks);
    }
}