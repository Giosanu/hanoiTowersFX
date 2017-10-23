package strategies;

import items.ProblemDomain;
import items.State;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class BacktrackingStrategy implements IStrategy {

    ArrayList<String> added = new ArrayList<>();
    static int counter = 99999;

    private static String START ;
    private static String END ;
    LinkedList<String> bestSol = new LinkedList<>();

    @Override
    public ArrayList<State> solve(int numOfTowers, int numOfDisks) {
        ProblemDomain problemDomain = new ProblemDomain();
        State state = new State(numOfTowers,numOfDisks);
        state.ResetState();
        START = state.ID;
        END = "[";
        for (int i=0;i<numOfDisks-1;i++){
            END = END + (numOfTowers-1) + ", ";
        }
        END = END+ (numOfTowers-1) + "]";
        LinkedList<String> visited = new LinkedList();
        visited.add(state.ID);

        addNodes(problemDomain, state, numOfTowers);
        depthFirst(problemDomain, visited);

        ArrayList<State> solution = new ArrayList<>();

        for (String s : bestSol){
            System.out.print(s + " ");
            solution.add(new State(numOfTowers,numOfDisks,s));
        }
        return solution;
    }

    private void depthFirst(ProblemDomain graph, LinkedList<String> visited) {
        LinkedList<String> nodes = graph.adjacentNodes(visited.getLast());
        for (String node : nodes) {
            if (visited.contains(node)) {
                continue;
            }
            if (node.equals(END)) {
                visited.add(node);
                printPath(visited);
                visited.removeLast();
                break;
            }
        }
        for (String node : nodes) {
            if (visited.contains(node) || node.equals(END)) {
                continue;
            }
            visited.addLast(node);
            depthFirst(graph, visited);
            visited.removeLast();
        }
    }

    private void printPath(LinkedList<String> visited) {
        if (visited.size()<counter){
            System.out.println("YUOLO");
            counter = visited.size();
            bestSol = new LinkedList<>(visited);
        }
    }

    private void addNodes(ProblemDomain problemDomain, State state, int numOfTowers) {
        added.add(state.ID);
        insertIntoPool(state.ComputeNeighbours(), numOfTowers);
        for (String st : state.getNeighbours()) {
            State intermediary = statePool.get(st);
            problemDomain.addEdge(state.ID,intermediary.ID);
            if(!added.contains(intermediary.ID))
                addNodes(problemDomain, intermediary, numOfTowers);
        }
    }

}
