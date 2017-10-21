package strategies;

import items.State;

import java.util.ArrayList;

public class RandomStrategy implements IStrategy {
    private ArrayList<State> visitedStates = new ArrayList<>();

    @Override
    public void solve(int numOfTowers, int numOfDisks) {
        State s = new State();
        State randomState = new State(numOfTowers, numOfDisks);
        int counter = 1000;

        s.Initialize(numOfTowers, numOfDisks);
        visitedStates.add(s);
        int currentCount = 0;
        s.ComputeNeighbours();

        while(!s.IsFinalState()){
            if(currentCount < counter){
                randomState.RandomizeState();
                if(!isAlreadyVisited(randomState) && isNeighbour(s, randomState)){
                    s = new State(randomState);
                    s.ComputeNeighbours();
                    visitedStates.add(s);
                } else currentCount++;
            } else {
                s.ResetState();
                s.ComputeNeighbours();
                currentCount = 0;
                visitedStates.clear();
                visitedStates.add(s);
            }
        }

        for(State solution : visitedStates){
            System.out.println(solution.toString());
        }
    }

    private boolean isNeighbour(State state, State possibleNeighbour){
        return state.getNeighbours().contains(possibleNeighbour);
    }

    private boolean isAlreadyVisited(State state){
        return visitedStates.contains(state);
    }
}
