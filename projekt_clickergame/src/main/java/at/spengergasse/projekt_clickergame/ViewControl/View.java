package at.spengergasse.projekt_clickergame.ViewControl;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class View {
    private Label pointsLabel;
    private Label prestigeLabel;
    private Button clickButton;
    private Button upgradeMenuButton;
    private Button saveButton;
    private Button resetButton;
    private Button bonusButton;
    private Pane mainLayout;

    public View() {
        pointsLabel = new Label("Points: 0");
        pointsLabel.getStyleClass().add("points-label");

        prestigeLabel = new Label("Prestige Level: 0");
        prestigeLabel.getStyleClass().add("prestige-label");

        clickButton = new Button("Klick Mich");
        clickButton.getStyleClass().add("click-button");

        upgradeMenuButton = new Button("Öffne Upgrade-Menü");
        upgradeMenuButton.getStyleClass().add("menu-button");

        saveButton = new Button("Spielstand speichern");
        saveButton.getStyleClass().add("save-button");

        resetButton = new Button("Spielstand löschen");
        resetButton.getStyleClass().add("reset-button");

        bonusButton = new Button("Bonus!");
        bonusButton.getStyleClass().add("bonus-button");
        bonusButton.setVisible(false);

        HBox buttonBox = new HBox(10, clickButton, upgradeMenuButton, saveButton, resetButton);
        buttonBox.getStyleClass().add("button-box");

        mainLayout = new Pane(pointsLabel, prestigeLabel, buttonBox, bonusButton);
        mainLayout.getStyleClass().add("main-layout");
        pointsLabel.setLayoutX(20);
        pointsLabel.setLayoutY(20);
        prestigeLabel.setLayoutX(20);
        prestigeLabel.setLayoutY(60);
        buttonBox.setLayoutX(20);
        buttonBox.setLayoutY(100);
    }

    public Label getPointsLabel() { return pointsLabel; }
    public Label getPrestigeLabel() { return prestigeLabel; }
    public Button getClickButton() { return clickButton; }
    public Button getUpgradeMenuButton() { return upgradeMenuButton; }
    public Button getSaveButton() { return saveButton; }
    public Button getResetButton() { return resetButton; }
    public Button getBonusButton() { return bonusButton; }
    public Pane getMainLayout() { return mainLayout; }
}