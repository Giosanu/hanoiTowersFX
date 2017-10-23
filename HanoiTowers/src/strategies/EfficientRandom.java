package strategies;

import items.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class EfficientRandom implements IStrategy {

    @Override
    public ArrayList<State> solve(int numOfTowers, int numOfDisks) {
        visitedStates.clear();
        statePool.clear();
        State s = new State();
        State randomState;
        int counter = numOfDisks*numOfTowers*2000;

        s.Initialize(numOfTowers, numOfDisks);
        visitedStates.add(s);
        int currentCount = 0;
        insertIntoPool(s.ComputeNeighbours(), numOfTowers);
        Random rnd = new Random();

        long start = System.currentTimeMillis();
        long end = start + 20*1000;

        while(!s.IsFinalState()) {
            if (currentCount < counter && !isBlockingState(s)) {
                if (System.currentTimeMillis() < end) {
                    int stateToPick = rnd.nextInt(s.getNeighbours().size());
                    randomState = statePool.get(s.getNeighbours().get(stateToPick));
                    if (!isAlreadyVisited(randomState)) {
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
