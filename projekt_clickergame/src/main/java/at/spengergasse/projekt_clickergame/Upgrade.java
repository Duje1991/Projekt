package at.spengergasse.projekt_clickergame;

public abstract class Upgrade {
    protected int cost;

    public Upgrade(int cost) {
        this.cost = cost;
    }

    public abstract void applyEffect(GameController controller);
}