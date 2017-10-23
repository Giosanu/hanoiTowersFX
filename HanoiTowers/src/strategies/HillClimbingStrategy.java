package strategies;

import items.State;

import java.lang.reflect.Array;
import java.util.*;

public class HillClimbingStrategy implements IStrategy {

    @Override
    public ArrayList<State> solve(int numOfTowers, int numOfDisks) {
        visitedStates.clear();
        statePool.clear();
        State state = new State();
        state.Initialize(numOfTowers, numOfDisks);
        state.ComputeScore();
        insertIntoPool(state.ComputeNeighbours(), numOfTowers);
        visitedStates.add(state);
        int counter = numOfDisks * numOfTowers * 2000;
        int currentCounter = 0;
        Random rnd = new Random();
        boolean good = true;

        while (!state.IsFinalState()) {
            if (good && currentCounter < counter && !isBlockingState(state)) {
                Iterator<String> neighbours = state.getNeighbours().iterator();
                while(neighbours.hasNext()){
                    String nghbr = neighbours.next();
                    statePool.get(nghbr).ComputeScore();
                    if(statePool.get(nghbr).getScore() <  state.getScore())
                        neighbours.remove();
                }

                if (!isLocalOptimum(state)) {
                    good = false;
                    continue;
                }

                int stateToPick = rnd.nextInt(state.getNeighbours().size());
                state = statePool.get(state.getNeighbours().get(stateToPick));
                if(isAlreadyVisited(state)) {
                    currentCounter++;
                    continue;
                }
                visitedStates.add(state);
                insertIntoPool(state.ComputeNeighbours(), numOfTowers);
            } else {
                state.ResetState();
                currentCounter = 0;
                visitedStates.clear();
                visitedStates.add(state);
                insertIntoPool(state.ComputeNeighbours(), numOfTowers);
                state.ComputeScore();
                good = true;
            }
        }
        for(State solution : visitedStates){
            System.out.println(solution.toString());
        }

        return visitedStates;
    }

    private boolean isLocalOptimum(State state){
        for(String nghbr: state.getNeighbours()){
            if(statePool.get(nghbr).getScore() > state.getScore())
                return true;
        }
        return false;
    }
}
