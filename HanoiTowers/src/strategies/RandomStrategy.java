package strategies;

import items.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RandomStrategy implements IStrategy {
    private ArrayList<State> visitedStates = new ArrayList<>();
    HashMap<String, State> statePool = new HashMap<>();

    @Override
    public ArrayList<State> solve(int numOfTowers, int numOfDisks) {
        State s = new State();
        State randomState = new State(numOfTowers, numOfDisks);
        int counter = numOfDisks*numOfTowers*2000;

        s.Initialize(numOfTowers, numOfDisks);
        visitedStates.add(s);
        int currentCount = 0;
        insertIntoPool(s.ComputeNeighbours(), numOfTowers);

        long start = System.currentTimeMillis();
        long end = start + 20*1000;
        boolean timeflag = false;

        while(!s.IsFinalState()) {
            if (currentCount < counter && !isBlockingState(s)) {
                if (System.currentTimeMillis() < end) {
                    randomState.RandomizeState();
                    if (!isAlreadyVisited(randomState) && isNeighbour(s, randomState)) {
                        s = new State(randomState);
                        insertIntoPool(s.ComputeNeighbours(), numOfTowers);
                        visitedStates.add(s);
                    } else currentCount++;
                } else {timeflag = true; break;}
            } else {
                s.ResetState();
                insertIntoPool(s.ComputeNeighbours(), numOfTowers);
                currentCount = 0;
                visitedStates.clear();
                visitedStates.add(s);
            }
        }

        if(timeflag){
            System.out.println("Program kekked");
            return null;
        }

        for(State solution : visitedStates){
            System.out.println(solution.toString());
        }

        return visitedStates;
    }
}
