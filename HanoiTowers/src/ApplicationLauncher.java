import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApplicationLauncher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        Parent root = FXMLLoader.load(getClass().getResource("hanoi.fxml"));
        primaryStage.setScene(new Scene(root, 800, 640));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
