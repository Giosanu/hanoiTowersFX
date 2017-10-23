package items;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class State {

    public String ID;
    private int NumOfTowers;
    public int[] DiskPlacement;
    private int Score;
    private ArrayList<String> Neighbours = new ArrayList<>();

    public int getScore() {
        return Score;
    }

    public State(){
    }

    public State(int numOfTowers, int numOfDisks, String id){
        NumOfTowers = numOfTowers;
        String[] strings =  id.replace("[", "").replace("]", "").split(", ");
        DiskPlacement = new int[numOfDisks];
        for (int i = 0; i < DiskPlacement.length; i++) {
            DiskPlacement[i] = Integer.parseInt(strings[i]);
        }
    }


    public State(int numOfTowers, int numOfDisks){
        NumOfTowers = numOfTowers;
        DiskPlacement = new int[numOfDisks];
        ID = Arrays.toString(DiskPlacement);
    }

    public State(int numOfTowers, int... diskPlacement) {
        NumOfTowers = numOfTowers;
        DiskPlacement = diskPlacement;
        ID = Arrays.toString(diskPlacement);
    }

    public State(State state) {
        this.NumOfTowers = state.NumOfTowers;
        this.DiskPlacement = Arrays.copyOf(state.DiskPlacement, state.DiskPlacement.length);
        ID = Arrays.toString(this.DiskPlacement);
    }

    public void ResetState(){
        Arrays.fill(DiskPlacement, 0);
        ID = Arrays.toString(DiskPlacement);
    }

    public void Initialize(int numOfTowers, int numOfDisks){
        this.NumOfTowers = numOfTowers;
        this.DiskPlacement = new int[numOfDisks];
        Arrays.fill(DiskPlacement, 0);
        ID = Arrays.toString(DiskPlacement);
    }

    public ArrayList<String> getNeighbours() {
        return Neighbours;
    }

    public boolean IsFinalState(){
        for (int Disk : DiskPlacement)
            if (Disk != NumOfTowers - 1)
                return false;
        return true;
    }

    public ArrayList<int[]> ComputeNeighbours(){
        Neighbours.clear();
        ArrayList<int[]> neighboursList = new ArrayList<>();
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
                    Neighbours.add(Arrays.toString(placeholder));
                    neighboursList.add(placeholder);
                }
            }
        }
        return neighboursList;
    }

    public void RandomizeState() {
        for (int i = 0; i < DiskPlacement.length; i++) {
            Random rndGen = new Random();
            DiskPlacement[i] = rndGen.nextInt(NumOfTowers);
        }
        ID = Arrays.toString(DiskPlacement);
    }

    public void ComputeScore(){
        Score = 0;
        for (int aDiskPlacement :DiskPlacement) {
            Score+=aDiskPlacement;
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
        return '(' + Integer.toString(NumOfTowers) + ' ' + s.toString().trim() +')';
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
