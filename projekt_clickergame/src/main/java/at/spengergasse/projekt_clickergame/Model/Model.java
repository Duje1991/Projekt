package at.spengergasse.projekt_clickergame.Model;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Model {
    private int points = 0;
    private boolean doubleClickActive = false;
    private boolean autoClickerActive = false;
    private boolean megaClickActive = false;
    private boolean superAutoClickerActive = false;
    private boolean pointBoostActive = false;
    private int prestigeLevel = 0;
    private int multiplier = 1;
    private static final String SAVE_FILE = "clicker_save.txt";
    private static final Random random = new Random();

    public int getPoints() { return points; }
    public boolean isDoubleClickActive() { return doubleClickActive; }
    public boolean isAutoClickerActive() { return autoClickerActive; }
    public boolean isMegaClickActive() { return megaClickActive; }
    public boolean isSuperAutoClickerActive() { return superAutoClickerActive; }
    public boolean isPointBoostActive() { return pointBoostActive; }
    public int getPrestigeLevel() { return prestigeLevel; }
    public int getMultiplier() { return multiplier; }

    public int getPrestigeCost() {
        if (prestigeLevel >= 5) return 2000;
        return 500 * (prestigeLevel + 1); // 500, 1000, 1500, 2000 für die ersten 4 Prestiges
    }

    public void incrementPoints(int amount) { points += amount * multiplier; }
    public void buyDoubleClickUpgrade() {
        if (points >= 10) {
            points -= 10;
            doubleClickActive = true;
        }
    }
    public void buyAutoClickerUpgrade() {
        if (points >= 50) {
            points -= 50;
            autoClickerActive = true;
        }
    }
    public void buyMegaClickUpgrade() {
        if (points >= 500) {
            points -= 500;
            megaClickActive = true;
        }
    }
    public void buySuperAutoClickerUpgrade() {
        if (points >= 1000) {
            points -= 1000;
            superAutoClickerActive = true;
        }
    }
    public void buyPointBoostUpgrade() {
        if (points >= 250) {
            points -= 250;
            pointBoostActive = true;
            multiplier *= 2; // Verdoppelt den Multiplikator
        }
    }
    public void buyTripleClickUpgrade() {
        if (points >= 100) {
            points -= 100;
            multiplier = 3;
        }
    }
    public void prestige() {
        if (points >= getPrestigeCost()) {
            points -= getPrestigeCost();
            prestigeLevel++;
            points = 0;
            doubleClickActive = false;
            autoClickerActive = false;
            megaClickActive = false;
            superAutoClickerActive = false;
            pointBoostActive = false;
            multiplier = 1 + prestigeLevel * 2;
        }
    }

    public void bonusButtonClicked() {
        points += 20 * multiplier; // Einmalige Extrapunkte
    }

    // Zufällige Position für Bonus-Button
    public double[] getRandomButtonPosition(double maxWidth, double maxHeight) {
        double x = random.nextDouble() * (maxWidth - 100); // 100 ist Button-Breite
        double y = random.nextDouble() * (maxHeight - 50); // 50 ist Button-Höhe
        return new double[]{x, y};
    }

    public void saveGameState() {
        String state = points + "," + (doubleClickActive ? 1 : 0) + "," + (autoClickerActive ? 1 : 0) + "," +
                prestigeLevel + "," + multiplier + "," + (megaClickActive ? 1 : 0) + "," +
                (superAutoClickerActive ? 1 : 0) + "," + (pointBoostActive ? 1 : 0);
        try {
            Files.writeString(Paths.get(SAVE_FILE), state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadGameState() {
        try {
            if (Files.exists(Paths.get(SAVE_FILE))) {
                String state = Files.readString(Paths.get(SAVE_FILE));
                String[] parts = state.split(",");
                points = Integer.parseInt(parts[0]);
                doubleClickActive = parts[1].equals("1");
                autoClickerActive = parts[2].equals("1");
                prestigeLevel = Integer.parseInt(parts[3]);
                multiplier = Integer.parseInt(parts[4]);
                megaClickActive = parts.length > 5 ? parts[5].equals("1") : false;
                superAutoClickerActive = parts.length > 6 ? parts[6].equals("1") : false;
                pointBoostActive = parts.length > 7 ? parts[7].equals("1") : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            points = 0;
            doubleClickActive = false;
            autoClickerActive = false;
            prestigeLevel = 0;
            multiplier = 1;
            megaClickActive = false;
            superAutoClickerActive = false;
            pointBoostActive = false;
        }
    }

    public void resetGameState() {
        points = 0;
        doubleClickActive = false;
        autoClickerActive = false;
        megaClickActive = false;
        superAutoClickerActive = false;
        pointBoostActive = false;
        prestigeLevel = 0;
        multiplier = 1;
        try {
            Files.deleteIfExists(Paths.get(SAVE_FILE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}