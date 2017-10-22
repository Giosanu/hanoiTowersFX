package strategies;

import items.State;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class HillClimbingStrategy implements IStrategy {
    private ArrayList<State> visitedStates = new ArrayList<>();
    @Override
    public ArrayList<State> solve(int numOfTowers, int numOfDisks) {
        State state = new State();
        state.Initialize(numOfTowers, numOfDisks);
        state.ComputeScore();
        state.ComputeNeighbours();
        visitedStates.add(state);
        int counter = numOfDisks * numOfTowers * 2000;
        int currentCounter = 0;
        Random rnd = new Random();
        boolean good = true;

        while (!state.IsFinalState()) {
            if (good && currentCounter < counter && !isBlockingState(state)) {


                Iterator<State> neighbours = state.getNeighbours().iterator();
                while(neighbours.hasNext()){
                    State nghbr = neighbours.next();
                    nghbr.ComputeScore();
                    if(nghbr.getScore() <  state.getScore())
                        neighbours.remove();
                }

                if (!isLocalOptimum(state)) {
                    good = false;
                    continue;
                }

                int stateToPick = rnd.nextInt(state.getNeighbours().size());
                state = state.getNeighbours().get(stateToPick);
                if(isAlreadyVisited(state)) {
                    currentCounter++;
                    continue;
                }
                visitedStates.add(state);
                state.ComputeNeighbours();
            } else {
                state.ResetState();
                currentCounter = 0;
                visitedStates.clear();
                visitedStates.add(state);
                state.ComputeNeighbours();
                state.ComputeScore();
                good = true;
            }
        }
        for(State solution : visitedStates){
            System.out.println(solution.toString());
        }

        return visitedStates;
    }

    private boolean isNeighbour(State state, State possibleNeighbour){
        return state.getNeighbours().contains(possibleNeighbour);
    }

    private boolean isAlreadyVisited(State state){
        return visitedStates.contains(state);
    }

    private boolean isBlockingState(State state){
        for(State nghbr : state.getNeighbours()){
            if(!visitedStates.contains(nghbr)){
                return false;
            }
        }
        return true;
    }

    private boolean isLocalOptimum(State state){
        for(State nghbr: state.getNeighbours()){
            if(nghbr.getScore() > state.getScore())
                return true;
        }
        return false;
    }
}
