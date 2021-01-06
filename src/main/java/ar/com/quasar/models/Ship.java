package ar.com.quasar.models;

public abstract class Ship {

    protected Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
