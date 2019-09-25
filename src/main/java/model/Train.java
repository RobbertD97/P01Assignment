package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Train<Train> implements Iterable<Train> {
    private Locomotive engine;
    private Wagon firstWagon;
    private String destination;
    private String origin;
    private int numberOfWagons;

    private List<Train> trainList = new ArrayList<>();

    public void add (Train train){
        trainList.add(train);
    }

    public Train(Locomotive engine, String origin, String destination) {
        this.engine = engine;
        this.destination = destination;
        this.origin = origin;
    }

    public Train(Locomotive engine, Wagon firstWagon, String destination, String origin, int numberOfWagons) {
        this.engine = engine;
        this.firstWagon = firstWagon;
        this.destination = destination;
        this.origin = origin;
        this.numberOfWagons = numberOfWagons;
    }

    public Wagon getFirstWagon() {
        return firstWagon;
    }

    public void setFirstWagon(Wagon firstWagon) {
        this.firstWagon = firstWagon;
    }

    public void resetNumberOfWagons() {
       /*  when wagons are hooked to or detached from a train,
         the number of wagons of the train should be reset
         this method does the calculation */
        if (hasNoWagons()) {
            this.numberOfWagons = 0;
        } else {
            this.numberOfWagons = this.firstWagon.getNumberOfWagonsAttached() + 1;
        }

    }

    public int getNumberOfWagons() {
        return numberOfWagons;
    }


    /* three helper methods that are useful in other methods */

    public boolean hasNoWagons() {
        return (firstWagon == null);
    }

    public boolean isPassengerTrain() {
        return firstWagon instanceof PassengerWagon;
    }

    public boolean isFreightTrain() {
        return firstWagon instanceof FreightWagon;
    }


    public int getPositionOfWagon(int wagonId) {
        // find a wagon on a train by id, return the position (first wagon had position 1)
        // if not found, than return -1
        if (this.hasNoWagons()) {
            return -1;
        } else {
            int position = 1;
            Wagon currentWagon = this.firstWagon;

            while (currentWagon.hasNextWagon()) {
                if (currentWagon.getWagonId() == wagonId) {
                    return position;
                }
                currentWagon = currentWagon.getNextWagon();
                position++;
            }
            return -1;
        }
    }

    public Wagon getWagonOnPosition(int position) throws IndexOutOfBoundsException {
        /* find the wagon on a given position on the train
         position of wagons start at 1 (firstWagon of train)
         use exceptions to handle a position that does not exist */
        Wagon currentWagon = this.firstWagon;

        try {
            for (int i = 1; i < position; i++) {
                currentWagon = currentWagon.getNextWagon();
            }
        } catch (Exception e) {
            System.out.println("Given position is invalid!");
        }
        return currentWagon;
    }

    public int getNumberOfSeats() {
        /* give the total number of seats on a passenger train
         for freight trains the result should be 0 */
        Wagon currentWagon = this.firstWagon;

        int totalNumberOfSeats = 0;

        if (this.isFreightTrain()) {
            return totalNumberOfSeats;
        } else {
            for (int i = 0; i < this.getNumberOfWagons(); i++) {
                totalNumberOfSeats += ((PassengerWagon) currentWagon).getSeats();
                currentWagon = currentWagon.getNextWagon();
            }
            return totalNumberOfSeats;
        }
    }

    public int getTotalMaxWeight() {
        /* give the total maximum weight of a freight train
         for passenger trains the result should be 0 */
        Wagon currentWagon = this.firstWagon;

        int totalMaxWeight = 0;

        if (this.isPassengerTrain()) {
            return totalMaxWeight;
        } else {
            while (currentWagon.hasNextWagon()) {
                totalMaxWeight += ((FreightWagon) currentWagon).getMaxWeight();
                currentWagon = currentWagon.getNextWagon();
            }
            return totalMaxWeight;
        }
    }

    public Locomotive getEngine() {
        return engine;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(engine.toString());
        Wagon next = this.getFirstWagon();
        while (next != null) {
            result.append(next.toString());
            next = next.getNextWagon();
        }
        result.append(String.format(" with %d wagons and %d seats from %s to %s", numberOfWagons, getNumberOfSeats(), origin, destination));
        return result.toString();
    }

    @Override
    public Iterator<Train> iterator() {
        return new TrainIterator<Train>(trainList);
    }
}


class TrainIterator<E> implements Iterator<E> {

    int indexPosition = 0;
    List<E> internalList;

    public TrainIterator(List<E> internalList) {
        this.internalList = internalList;
    }

    @Override
    public boolean hasNext() {
        if(internalList.size() >= indexPosition + 1){
            return true;
        }
        return false;
    }

    @Override
    public E next() {
        E val = internalList.get(indexPosition);
        indexPosition++;
        return val;
    }
}
