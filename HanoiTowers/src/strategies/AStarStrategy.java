package strategies;

import items.State;

import java.util.ArrayList;
import java.util.HashMap;

public class AStarStrategy implements IStrategy {
    private ArrayList<State> visitedStates = new ArrayList<>(); //closedSet
    ArrayList<State> openSet = new ArrayList<>();
    HashMap<String, String> bestParentMap = new HashMap<>(); //cameFrom
    HashMap<String, Double> gScore = new HashMap<>();
    HashMap<String, Double> fScore = new HashMap<>();
    HashMap<String, State> statePool = new HashMap<>();

    @Override
    public ArrayList<State> solve(int numOfTowers, int numOfDisks) {
        State state = new State();
        state.Initialize(numOfTowers, numOfDisks);
        statePool.put(state.ID, state);
        openSet.add(state);
        gScore.put(state.ID, 0.0);
        fScore.put(state.ID, heuristic(state, numOfTowers));

        while(!openSet.isEmpty()){
            state = lowestFScoreState();
            if(state.IsFinalState()){
                ArrayList<State> solution = reconstruct_path(bestParentMap, state.ID);
                for(State so : solution){
                    System.out.println(so.toString());
                }
                return solution;
            }
            openSet.remove(state);
            visitedStates.add(state);

            state.ComputeNeighbours();
            for(State nghbr : state.getNeighbours()) {
                statePool.put(nghbr.ID, nghbr);
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

    private boolean isAlreadyVisited(State state){
        return visitedStates.contains(state);
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
