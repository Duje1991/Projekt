package at.spengergasse.projekt_clickergame.ViewControl;

import at.spengergasse.projekt_clickergame.Model.Model;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Controller {
    private Model model = new Model();
    private View mainView = new View();
    private UpgradeView upgradeView = new UpgradeView();
    private Stage upgradeStage;

    public void initialize() {
        model.loadGameState();
        updateMainUI();

        mainView.getClickButton().setOnAction(e -> {
            int points = 1;
            if (model.isDoubleClickActive()) points = 2;
            if (model.isMegaClickActive()) points = 50;
            model.incrementPoints(points);
            updateMainUI();
        });

        mainView.getUpgradeMenuButton().setOnAction(e -> showUpgradeMenu());

        mainView.getSaveButton().setOnAction(e -> {
            model.saveGameState();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Spielstand wurde gespeichert!");
            alert.showAndWait();
        });

        mainView.getResetButton().setOnAction(e -> {
            model.resetGameState();
            updateMainUI();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Spielstand wurde gelöscht! Neuer Anfang!");
            alert.showAndWait();
        });

        mainView.getBonusButton().setOnAction(e -> {
            model.bonusButtonClicked();
            mainView.getBonusButton().setVisible(false);
            mainView.getBonusButton().setDisable(true);
            updateMainUI();
        });

        upgradeView.getDoubleClickButton().setOnAction(e -> {
            model.buyDoubleClickUpgrade();
            updateUpgradeUI();
            updateMainUI();
        });

        upgradeView.getAutoClickerButton().setOnAction(e -> {
            model.buyAutoClickerUpgrade();
            updateUpgradeUI();
            updateMainUI();
        });

        upgradeView.getMegaClickButton().setOnAction(e -> {
            model.buyMegaClickUpgrade();
            updateUpgradeUI();
            updateMainUI();
        });

        upgradeView.getSuperAutoClickerButton().setOnAction(e -> {
            model.buySuperAutoClickerUpgrade();
            updateUpgradeUI();
            updateMainUI();
        });

        upgradeView.getPointBoostButton().setOnAction(e -> {
            model.buyPointBoostUpgrade();
            updateUpgradeUI();
            updateMainUI();
        });

        upgradeView.getTripleClickButton().setOnAction(e -> {
            model.buyTripleClickUpgrade();
            updateUpgradeUI();
            updateMainUI();
        });

        upgradeView.getPrestigeButton().setOnAction(e -> {
            model.prestige();
            updateUpgradeUI();
            updateMainUI();
        });

        upgradeView.getCloseButton().setOnAction(e -> {
            if (upgradeStage != null) {
                upgradeStage.close();
            }
        });

        // Autoclicker-Thread
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    if (model.isAutoClickerActive()) {
                        model.incrementPoints(1);
                    }
                    if (model.isSuperAutoClickerActive()) {
                        model.incrementPoints(5);
                    }
                    if (model.isAutoClickerActive() || model.isSuperAutoClickerActive()) {
                        javafx.application.Platform.runLater(this::updateMainUI);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // Bonus-Button-Thread
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(15000); // Alle 15 Sekunden
                    javafx.application.Platform.runLater(() -> {
                        double[] pos = model.getRandomButtonPosition(
                                mainView.getMainLayout().getWidth(),
                                mainView.getMainLayout().getHeight()
                        );
                        mainView.getBonusButton().setLayoutX(pos[0]);
                        mainView.getBonusButton().setLayoutY(pos[1]);
                        mainView.getBonusButton().setVisible(true);
                        mainView.getBonusButton().setDisable(false); // Button aktiv
                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.setOnFinished(e -> {
                            mainView.getBonusButton().setVisible(false);
                            mainView.getBonusButton().setDisable(true); // Deaktiviere nach Ablauf
                        });
                        pause.play();
                    });
                    Thread.sleep(3000); // Warten, bis der Button verschwindet
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void updateMainUI() {
        mainView.getPointsLabel().setText("Points: " + model.getPoints());
        mainView.getPrestigeLabel().setText("Prestige Level: " + model.getPrestigeLevel());
    }

    private void updateUpgradeUI() {
        upgradeView.getPointsLabel().setText("Points: " + model.getPoints());
        upgradeView.getDoubleClickButton().setDisable(model.getPoints() < 10 || model.isDoubleClickActive());
        upgradeView.getAutoClickerButton().setDisable(model.getPoints() < 50 || model.isAutoClickerActive());
        upgradeView.getMegaClickButton().setDisable(model.getPoints() < 500 || model.isMegaClickActive());
        upgradeView.getSuperAutoClickerButton().setDisable(model.getPoints() < 1000 || model.isSuperAutoClickerActive());
        upgradeView.getPointBoostButton().setDisable(model.getPoints() < 250 || model.isPointBoostActive());
        upgradeView.getTripleClickButton().setDisable(model.getPoints() < 100 || model.getMultiplier() >= 3);
        upgradeView.getPrestigeButton().setText("Prestige (" + model.getPrestigeCost() + " Punkte)");
        upgradeView.getPrestigeButton().setDisable(model.getPoints() < model.getPrestigeCost());
    }

    private void showUpgradeMenu() {
        if (upgradeStage == null) {
            upgradeStage = new Stage();
            upgradeStage.setTitle("Upgrade-Menü");
            upgradeStage.initModality(Modality.NONE); // Keine Modalität, Hauptfenster bleibt sichtbar
            upgradeStage.setScene(new Scene(upgradeView.getUpgradeLayout()));
            upgradeStage.getScene().getStylesheets().add("/styles.css");
            upgradeStage.setMinWidth(400); // Mindestbreite für sichtbaren Text
            upgradeStage.setMinHeight(500); // Mindesthöhe für alle Buttons
            upgradeStage.setWidth(400); // Standardbreite
            upgradeStage.setHeight(500); // Standardhöhe
        }
        updateUpgradeUI();
        upgradeStage.show(); // Nicht modal, zeigt das Fenster ohne showAndWait
    }

    public View getMainView() {
        return mainView;
    }
}