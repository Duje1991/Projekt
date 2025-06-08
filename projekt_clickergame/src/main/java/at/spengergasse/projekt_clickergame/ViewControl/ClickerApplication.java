package at.spengergasse.projekt_clickergame.ViewControl;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class ClickerApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        Controller controller = new Controller();
        controller.initialize();

        primaryStage.setTitle("ClickerWarrior");
        primaryStage.setScene(new Scene(controller.getMainView().getMainLayout()));
        primaryStage.getScene().getStylesheets().add("/styles.css");
        primaryStage.setFullScreen(true); // Vollbildmodus aktivieren
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}