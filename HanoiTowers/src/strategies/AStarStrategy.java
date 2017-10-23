package strategies;

import items.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class AStarStrategy implements IStrategy {
    private ArrayList<State> openSet = new ArrayList<>();
    private HashMap<String, String> bestParentMap = new HashMap<>(); //cameFrom
    private HashMap<String, Double> gScore = new HashMap<>();
    private HashMap<String, Double> fScore = new HashMap<>();

    @Override
    public ArrayList<State> solve(int numOfTowers, int numOfDisks) {
        visitedStates.clear();
        statePool.clear();
        State state = new State();
        state.Initialize(numOfTowers, numOfDisks);
        statePool.put(state.ID, state);
        openSet.add(state);
        gScore.put(state.ID, 0.0);
        fScore.put(state.ID, heuristic(state, numOfTowers));

        while(!openSet.isEmpty()){
            state = lowestFScoreState();
            statePool.put(state.ID, state);
            if(state.IsFinalState()){
                ArrayList<State> solution = reconstruct_path(bestParentMap, state.ID);
                Collections.reverse(solution);
                for(State so : solution){
                    System.out.println(so.toString());
                }
                return solution;
            }
            openSet.remove(state);
            visitedStates.add(state);

            insertIntoPool(state.ComputeNeighbours(), numOfTowers);
            for(String nghbrID : state.getNeighbours()) {
                State nghbr = statePool.get(nghbrID);
                if (!gScore.containsKey(nghbr.ID))
                    gScore.put(nghbr.ID, Double.POSITIVE_INFINITY);
                if (isAlreadyVisited(nghbr))
                    continue;
                if (!openSet.contains(nghbr))
                    openSet.add(nghbr);

                double tentative_gScore = gScore.get(state.ID) + 1;
                if (tentative_gScore >= gScore.get(nghbr.ID))
                    continue;

                bestParentMap.put(nghbr.ID, state.ID);
                gScore.put(nghbr.ID, tentative_gScore);
                fScore.put(nghbr.ID, gScore.get(nghbr.ID) + heuristic(nghbr, numOfTowers));
            }
        }
        return null;
    }

    private ArrayList<State> reconstruct_path(HashMap<String, String> bestParentMap, String stateID) {
        ArrayList<State> path = new ArrayList<>();
        path.add(statePool.get(stateID));
        while(bestParentMap.containsKey(stateID)){
            path.add(statePool.get(bestParentMap.get(stateID)));
            stateID = statePool.get(bestParentMap.get(stateID)).ID;
        }
        return path;
    }

    private double heuristic(State state, int numOfTowers){
        int diskNotOnLast = 0;
        int disksOnLast = 0;
        for(int i = 0; i < state.DiskPlacement.length; i++){
            if(state.DiskPlacement[i] != numOfTowers - 1)
                diskNotOnLast++;
            else {
                for (int j = i + 1; j < state.DiskPlacement.length && j != i; j++) {
                    if(state.DiskPlacement[j] != numOfTowers -1) {
                        disksOnLast++;
                        break;
                    }
                }
            }
        }
        return diskNotOnLast + 2*disksOnLast;
    }

    private State lowestFScoreState(){
        State current = openSet.get(0);
        for(State s : openSet){
            if(fScore.get(s.ID) < fScore.get(current.ID))
                current = s;
        }
        return current;
    }
}
