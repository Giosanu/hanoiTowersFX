package strategies;

import items.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RandomStrategy implements IStrategy {

    @Override
    public ArrayList<State> solve(int numOfTowers, int numOfDisks) {
        visitedStates.clear();
        statePool.clear();
        State s = new State();
        State randomState = new State(numOfTowers, numOfDisks);
        int counter = numOfDisks*numOfTowers*2000;

        s.Initialize(numOfTowers, numOfDisks);
        visitedStates.add(s);
        int currentCount = 0;
        insertIntoPool(s.ComputeNeighbours(), numOfTowers);

        long start = System.currentTimeMillis();
        long end = start + 20*1000;

        while(!s.IsFinalState()) {
            if (currentCount < counter && !isBlockingState(s)) {
                if (System.currentTimeMillis() < end) {
                    randomState.RandomizeState();
                    if (!isAlreadyVisited(randomState) && isNeighbour(s, randomState)) {
                        s = new State(randomState);
                        insertIntoPool(s.ComputeNeighbours(), numOfTowers);
                        visitedStates.add(s);
                    } else currentCount++;
                } else { break;}
            } else {
                s.ResetState();
                insertIntoPool(s.ComputeNeighbours(), numOfTowers);
                currentCount = 0;
                visitedStates.clear();
                visitedStates.add(s);
            }
        }

        for(State solution : visitedStates){
            System.out.println(solution.toString());
        }

        return visitedStates;
    }
}
