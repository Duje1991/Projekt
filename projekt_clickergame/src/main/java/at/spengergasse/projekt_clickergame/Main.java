package at.spengergasse.projekt_clickergame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private int points = 0;
    private boolean doubleClickActive = false;
    private boolean autoClickerActive = false;
    private Label pointsLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // UI-Elemente erstellen
        pointsLabel = new Label("Points: 0");
        pointsLabel.setStyle("-fx-font-size: 20px;");

        Button clickButton = new Button("Skibidi Ohio Rizz");
        clickButton.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px;");

        Button doubleClickButton = new Button("Doppelklick (10 Punkte)");
        doubleClickButton.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px;");

        Button autoClickerButton = new Button("Autoklicker (50 Punkte)");
        autoClickerButton.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px;");

        // Layout erstellen
        VBox root = new VBox(10, pointsLabel, clickButton, doubleClickButton, autoClickerButton);
        root.setAlignment(javafx.geometry.Pos.CENTER);

        // Event-Handler
        clickButton.setOnAction(e -> {
            points += doubleClickActive ? 2 : 1;
            updateUI(doubleClickButton, autoClickerButton);
        });

        doubleClickButton.setOnAction(e -> {
            if (points >= 10) {
                points -= 10;
                doubleClickActive = true;
                updateUI(doubleClickButton, autoClickerButton);
            }
        });

        autoClickerButton.setOnAction(e -> {
            if (points >= 50) {
                points -= 50;
                autoClickerActive = true;
                updateUI(doubleClickButton, autoClickerButton);
            }
        });

        // Autoclicker-Thread
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    if (autoClickerActive) {
                        points++;
                        javafx.application.Platform.runLater(() -> {
                            pointsLabel.setText("Points: " + points);
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // Scene anzeigen
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setTitle("ClickerWarrior");
        primaryStage.show();
    }

    private void updateUI(Button doubleClickButton, Button autoClickerButton) {
        pointsLabel.setText("Points: " + points);
        doubleClickButton.setDisable(points < 10 || doubleClickActive);
        autoClickerButton.setDisable(points < 50 || autoClickerActive);
    }
}