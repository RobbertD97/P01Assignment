package model;

public class PassengerWagon extends Wagon {

    private int numberOfSeats;

    public PassengerWagon(int wagonId, int numberOfSeats) {
        super(wagonId);
        this.numberOfSeats = numberOfSeats;
    }

    public int getSeats() {
        return numberOfSeats;
    }
}
