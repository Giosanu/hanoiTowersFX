package items;
import java.util.ArrayList;
import java.util.Arrays;

public class State {

    private boolean visited;
    private int NumOfTowers;
    private int[] DiskPlacement;
    private int Score;
    private ArrayList<State> Neighbours;

    public State(){
        Neighbours = new ArrayList<>();
    }

    public State(int numOfTowers, int ... diskPlacement) {
        NumOfTowers = numOfTowers;
        DiskPlacement = diskPlacement;
        Neighbours = new ArrayList<>();
    }

    public void Initialize(int numOfTowers, int numOfDisks){
        this.NumOfTowers = numOfTowers;
        this.DiskPlacement = new int[numOfDisks];
        Arrays.fill(DiskPlacement, 1);
    }

    public ArrayList<State> getNeighbours() {
        return Neighbours;
    }

    public boolean IsFinalState(){
        for (int Disk : DiskPlacement)
            if (Disk != NumOfTowers)
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
}
