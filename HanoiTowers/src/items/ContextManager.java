package items;

import strategies.*;

import java.util.ArrayList;

public class ContextManager {
    private static Context context;

    public static ArrayList<State> getSolution(int numOfTowers, int numOfDisks, int strategy) {
        if (strategy == 0) {
            context = new Context(new RandomStrategy());
            return context.executeStrategy(numOfTowers, numOfDisks);
        } else if (strategy == 1) {
            context = new Context(new BacktrackingStrategy());
            return context.executeStrategy(numOfTowers, numOfDisks);
        } else if (strategy == 2) {
            context = new Context(new HillClimbingStrategy());
            return context.executeStrategy(numOfTowers, numOfDisks);
        } else if (strategy == 3){
            context = new Context(new AStarStrategy());
            return context.executeStrategy(numOfTowers, numOfDisks);        } else if(strategy == 4){
            context = new Context(new EfficientRandom());
            return context.executeStrategy(numOfTowers, numOfDisks);
        }
        return null;
    }

}
