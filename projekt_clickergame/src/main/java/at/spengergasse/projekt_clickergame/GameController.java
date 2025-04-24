package at.spengergasse.projekt_clickergame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameController {
    @FXML private Label pointsLabel;
    @FXML private Button clickButton;
    @FXML private Button doubleClickUpgradeButton;
    @FXML private Button autoClickerUpgradeButton;

    private int points = 0;
    private boolean doubleClickActive = false;
    private boolean autoClickerActive = false;

    @FXML
    public void initialize() {
        updateUI();

        // Autoclicker-Thread
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    if (autoClickerActive) {
                        points++;
                        updateUI();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @FXML
    private void handleClick() {
        points += doubleClickActive ? 2 : 1;
        updateUI();
    }

    @FXML
    public void buyDoubleClickUpgrade() {
        if (points >= 10) {
            points -= 10;
            doubleClickActive = true;
            updateUI();
        }
    }

    @FXML
    public void buyAutoClickerUpgrade() {
        if (points >= 50) {
            points -= 50;
            autoClickerActive = true;
            updateUI();
        }
    }

    private void updateUI() {
        pointsLabel.setText("Points: " + points);
        doubleClickUpgradeButton.setDisable(points < 10 || doubleClickActive);
        autoClickerUpgradeButton.setDisable(points < 50 || autoClickerActive);
    }
}