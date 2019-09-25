package model;

public class PassengerWagon extends Wagon {

    private int numberOfSeats;

    public PassengerWagon(int wagonId, Wagon previousWagon, Wagon nextWagon, int numberOfSeats) {
        super(wagonId, previousWagon, nextWagon);
        this.numberOfSeats = numberOfSeats;
    }

    public PassengerWagon(int wagonId, int numberOfSeats) {
        super(wagonId);
    }

    public int getSeats() {
        return numberOfSeats;
    }
}
