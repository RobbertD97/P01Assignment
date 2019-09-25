package model;

public class Wagon {
    private int wagonId;
    private Wagon previousWagon;
    private Wagon nextWagon;

    public Wagon(int wagonId, Wagon previousWagon, Wagon nextWagon) {
        this.wagonId = wagonId;
        this.previousWagon = previousWagon;
        this.nextWagon = nextWagon;
    }

    public Wagon(int wagonId) {
        this.wagonId = wagonId;
    }

    public Wagon getLastWagonAttached() {
        // find the last wagon of the row of wagons attached to this wagon
        Wagon currentWagon = this;
        while (currentWagon.hasNextWagon()) {
            setNextWagon(currentWagon);
        }

        // if no wagons are attached return this wagon
        return currentWagon;
    }

    public void setNextWagon(Wagon nextWagon) {
        // when setting the next wagon, set this wagon to be previous wagon of next wagon

        if (nextWagon != null) {
            nextWagon.setPreviousWagon(this);
        }
        this.nextWagon = nextWagon;
    }

    public Wagon getPreviousWagon() {
        return previousWagon;
    }

    public void setPreviousWagon(Wagon previousWagon) {
        this.previousWagon = previousWagon;
    }

    public Wagon getNextWagon() {
        return nextWagon;
    }

    public int getWagonId() {
        return wagonId;
    }

    public int getNumberOfWagonsAttached() {
        return countWagons(this, 0);
    }

    public int countWagons(Wagon wagon, int count) {
        if (!wagon.hasNextWagon()) {
            return count;
        }
        return countWagons(wagon.nextWagon, count + 1);
    }

    public boolean hasNextWagon() {
        return !(nextWagon == null);
    }

    public boolean hasPreviousWagon() {
        return !(previousWagon == null);
    }

    @Override
    public String toString() {
        return String.format("[Wagon %d]", wagonId);
    }
}
