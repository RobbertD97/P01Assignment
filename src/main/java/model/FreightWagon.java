package model;

public class FreightWagon extends Wagon {

    private int maxWeight;

    public FreightWagon(int wagonId, Wagon previousWagon, Wagon nextWagon, int maxWeight) {
        super(wagonId, previousWagon, nextWagon);
        this.maxWeight = maxWeight;
    }

    public FreightWagon(int wagonId, int maxWeight) {
        super(wagonId);
    }

    public int getMaxWeight() {
        return maxWeight;
    }
}
