package at.spengergasse.projekt_clickergame;

public class DoubleClickUpgrade extends Upgrade {
    public DoubleClickUpgrade() {
        super(10); // Cost of the upgrade
    }

    @Override
    public void applyEffect(GameController controller) {
        controller.buyDoubleClickUpgrade();
    }
}
