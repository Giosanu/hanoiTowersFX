package strategies;

import items.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

interface IStrategy {
    ArrayList<State> visitedStates = new ArrayList<>();
    HashMap<String, State> statePool = new HashMap<>();
    ArrayList<State> solve(int numOfTowers, int numOfDisks);

    default boolean isNeighbour(State state, State possibleNeighbour){
        return state.getNeighbours().contains(possibleNeighbour.ID);
    }

    default boolean isAlreadyVisited(State state){
        return visitedStates.contains(state);
    }

    default boolean isBlockingState(State state){
        for(String nghbr : state.getNeighbours()){
            if(!visitedStates.contains(statePool.get(nghbr))){
                return false;
            }
        }
        return true;
    }

    default void insertIntoPool(ArrayList<int[]> statesToAdd, int NumOfTowers){
        for(int[] state : statesToAdd){
            String stateID = Arrays.toString(state);
            if(!statePool.containsKey(stateID)){
                statePool.put(stateID, new State(NumOfTowers, state));
            }
        }
    }
}
