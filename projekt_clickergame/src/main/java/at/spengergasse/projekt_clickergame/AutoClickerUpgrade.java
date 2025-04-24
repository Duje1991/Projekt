package at.spengergasse.projekt_clickergame;

public class AutoClickerUpgrade extends Upgrade {
    public AutoClickerUpgrade() {
        super(50); // Cost of the upgrade
    }

    @Override
    public void applyEffect(GameController controller) {
        controller.buyAutoClickerUpgrade();
    }
}