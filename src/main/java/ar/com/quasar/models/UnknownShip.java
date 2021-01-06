package ar.com.quasar.models;

public class UnknownShip extends Ship {

    private String message;
    private Position position;

    public UnknownShip(Position position, String message){
        super();
        this.position = position;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

}
