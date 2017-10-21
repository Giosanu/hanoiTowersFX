package strategies;

public class Context {
    private IStrategy strategy;

    public Context(IStrategy strategy){
        this.strategy = strategy;
    }

        public void executeStrategy(int numOfTowers, int numOfDisks){
            strategy.solve(numOfTowers, numOfDisks);
    }
}