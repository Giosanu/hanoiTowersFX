package items;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class State {

    private boolean visited;
    private int NumOfTowers;
    private int[] DiskPlacement;
    private int Score;
    private ArrayList<State> Neighbours = new ArrayList<>();

    public State(){
    }

    public State(int numOfTowers, int numOfDisks){
        NumOfTowers = numOfTowers;
        DiskPlacement = new int[numOfDisks];
    }

    public State(int numOfTowers, int ... diskPlacement) {
        NumOfTowers = numOfTowers;
        DiskPlacement = diskPlacement;
    }

    public State(State state) {
        this.NumOfTowers = state.NumOfTowers;
        this.DiskPlacement = Arrays.copyOf(state.DiskPlacement, state.DiskPlacement.length);
    }

    public void ResetState(){
        Arrays.fill(DiskPlacement, 0);
    }

    public void Initialize(int numOfTowers, int numOfDisks){
        this.NumOfTowers = numOfTowers;
        this.DiskPlacement = new int[numOfDisks];
        Arrays.fill(DiskPlacement, 0);
    }

    public ArrayList<State> getNeighbours() {
        return Neighbours;
    }

    public boolean IsFinalState(){
        for (int Disk : DiskPlacement)
            if (Disk != NumOfTowers - 1)
                return false;
        return true;
    }

    public void ComputeNeighbours(){
        for(int i = 0; i< DiskPlacement.length; i++){
            int[] placeable = new int[NumOfTowers];
            Arrays.fill(placeable, 1);
            placeable[DiskPlacement[i]] = 0;
            for(int j = 0; j < i; j++){
                if(DiskPlacement[i] == DiskPlacement[j]) {
                    Arrays.fill(placeable, 0);
                    break;
                }
                placeable[DiskPlacement[j]] = 0;
            }
            for(int k = 0; k < placeable.length; k++){
                if(placeable[k] == 1) {
                    int[] placeholder = Arrays.copyOf(DiskPlacement, DiskPlacement.length);
                    placeholder[i] = k;
                    Neighbours.add(new State(NumOfTowers, placeholder));
                }
            }
        }
    }

    public void RandomizeState() {
        for (int i = 0; i < DiskPlacement.length; i++) {
            Random rndGen = new Random();
            DiskPlacement[i] = rndGen.nextInt(NumOfTowers);
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int Disk:
                DiskPlacement) {
            s.append(Disk);
            s.append(' ');

        }
        return '(' + Integer.toString(NumOfTowers) + ' ' + s.toString() +')';
    }

    @Override
    public boolean equals(Object obj) {
        State toCompare = (State) obj;
        for(int i = 0; i < this.DiskPlacement.length; i++){
            if(this.DiskPlacement[i] != toCompare.DiskPlacement[i]){
                return false;
            }
        }
        return true;
    }
}
