package strategies;

import items.State;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class BacktrackingStrategy implements IStrategy {
    private Deque<State> currentStack = new ArrayDeque<>();
    private ArrayList<ArrayList<State>> solutions =  new ArrayList<>();
    private ArrayList<State> visitedStates = new ArrayList<>();
    private ArrayList<State> currentSolution = new ArrayList<>();

    @Override
    public ArrayList<State> solve(int numOfTowers, int numOfDisks) {
        State state = new State();
        state.Initialize(numOfTowers, numOfDisks);
        currentStack.addFirst(state);


        while(!currentStack.isEmpty()){
            state = currentStack.poll();
            if(visitedStates.contains(state)){
                continue;
            }
            currentSolution.add(state);

            if(state.IsFinalState()){
                for (State st : currentSolution) {
                    System.out.println(st);
                }
                return currentSolution;
            }

            visitedStates.add(state);
            state.ComputeNeighbours();
            if(isBlockingState(state)){
                currentSolution.remove(state);
                continue;
            }
            for(State nghbr : state.getNeighbours()){
                currentStack.addFirst(nghbr);
            }
        }

        for (State st : solutions.get(0)) {
            System.out.println(st);
        }

        return null;
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

}
