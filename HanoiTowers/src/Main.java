import strategies.Context;
import strategies.RandomStrategy;

public class Main {
    public static void main(String[] args) {
        Context context = new Context(new RandomStrategy());
        context.executeStrategy(5, 4);
    }
}
