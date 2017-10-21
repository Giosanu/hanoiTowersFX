package items;
import java.util.ArrayList;
import java.util.Arrays;

public class State {

    private boolean visited;
    private int Towers;
    private int[] Disks;
    private int Score;
    private ArrayList<State> Neighbours;

    public State(){
        Neighbours = new ArrayList<>();
    }

    public State(int towers, int ... disks) {
        Towers = towers;
        Disks = disks;
        Neighbours = new ArrayList<>();
    }

    public void Initialize(int numOfTowers, int numOfDisks){
        this.Towers = numOfTowers;
        this.Disks = new int[numOfDisks];
        Arrays.fill(Disks, 1);
    }

    public ArrayList<State> getNeighbours() {
        return Neighbours;
    }

    public boolean IsFinalState(){
        for (int Disk : Disks)
            if (Disk != Towers)
                return false;
        return true;
    }
}
