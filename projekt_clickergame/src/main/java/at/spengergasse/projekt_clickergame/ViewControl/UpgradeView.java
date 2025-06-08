package at.spengergasse.projekt_clickergame.ViewControl;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UpgradeView {
    private Label pointsLabel;
    private Button doubleClickButton;
    private Button autoClickerButton;
    private Button megaClickButton;
    private Button superAutoClickerButton;
    private Button pointBoostButton;
    private Button tripleClickButton;
    private Button prestigeButton;
    private Button closeButton;
    private VBox upgradeLayout;

    public UpgradeView() {
        pointsLabel = new Label("Points: 0");
        pointsLabel.getStyleClass().add("points-label");

        doubleClickButton = new Button("Doppelklick (10 Punkte)");
        doubleClickButton.getStyleClass().add("upgrade-button");

        autoClickerButton = new Button("Autoklicker (50 Punkte)");
        autoClickerButton.getStyleClass().add("upgrade-button");

        megaClickButton = new Button("MegaClick (500 Punkte)");
        megaClickButton.getStyleClass().add("upgrade-button");

        superAutoClickerButton = new Button("SuperAutoklicker (1000 Punkte)");
        superAutoClickerButton.getStyleClass().add("upgrade-button");

        pointBoostButton = new Button("PointBoost (250 Punkte)");
        pointBoostButton.getStyleClass().add("upgrade-button");

        tripleClickButton = new Button("Triple Click (100 Punkte)");
        tripleClickButton.getStyleClass().add("upgrade-button");

        prestigeButton = new Button("Prestige (500 Punkte)");
        prestigeButton.getStyleClass().add("upgrade-button");

        closeButton = new Button("Schließen");
        closeButton.getStyleClass().add("close-button");

        upgradeLayout = new VBox(10, pointsLabel, doubleClickButton, autoClickerButton, megaClickButton,
                superAutoClickerButton, pointBoostButton, tripleClickButton, prestigeButton, closeButton);
        upgradeLayout.getStyleClass().add("upgrade-layout");
    }

    public Label getPointsLabel() { return pointsLabel; }
    public Button getDoubleClickButton() { return doubleClickButton; }
    public Button getAutoClickerButton() { return autoClickerButton; }
    public Button getMegaClickButton() { return megaClickButton; }
    public Button getSuperAutoClickerButton() { return superAutoClickerButton; }
    public Button getPointBoostButton() { return pointBoostButton; }
    public Button getTripleClickButton() { return tripleClickButton; }
    public Button getPrestigeButton() { return prestigeButton; }
    public Button getCloseButton() { return closeButton; }
    public VBox getUpgradeLayout() { return upgradeLayout; }
}