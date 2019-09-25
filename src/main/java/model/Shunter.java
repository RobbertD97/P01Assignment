package model;

public class Shunter {


    /* four helper methods than are used in other methods in this class to do checks */
    private static boolean isSuitableWagon(Train train, Wagon wagon) {
        // trains can only exist of passenger wagons or of freight wagons
        if (train.isFreightTrain()) {
            return wagon instanceof FreightWagon;
        } else {
            return wagon instanceof PassengerWagon;
        }
    }

    private static boolean isSuitableWagon(Wagon one, Wagon two) {
        // passenger wagons can only be hooked onto passenger wagons
        if (one instanceof FreightWagon && two instanceof FreightWagon) {
            return true;
        } else if (one instanceof PassengerWagon && two instanceof PassengerWagon) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean hasPlaceForWagons(Train train, Wagon wagon) {
        // the engine of a train has a maximum capacity, this method checks for a row of wagons
        int trainWagonCapacity = train.getEngine().getMaxWagons();
        int totalWagonsInRow = wagon.getNumberOfWagonsAttached() + 1;
        return train.getNumberOfWagons() + totalWagonsInRow <= trainWagonCapacity;
    }

    private static boolean hasPlaceForOneWagon(Train train, Wagon wagon) {
        // the engine of a train has a maximum capacity, this method checks for one wagon
        int trainWagonCapacity = train.getEngine().getMaxWagons();
        return train.getNumberOfWagons() < trainWagonCapacity;
    }

    public static boolean hookWagonOnTrainRear(Train train, Wagon wagon) {
         /* check if Locomotive can pull new number of Wagons
         check if wagon is correct kind of wagon for train
         find the last wagon of the train
         hook the wagon on the last wagon (see Wagon class)
         adjust number of Wagons of Train */
        if (!hasPlaceForWagons(train, wagon)) return false;
        if (!isSuitableWagon(train, wagon)) return false;
//        if (!train.hasNoWagons()) {
//            Wagon firstWagon = train.getFirstWagon();
//        }
        Wagon lastWagon = train.getWagonOnPosition(train.getNumberOfWagons());
        if (train.hasNoWagons()) {
            train.setFirstWagon(wagon);
            train.resetNumberOfWagons();
            return true;
        } else {
            lastWagon.setNextWagon(wagon);
            train.resetNumberOfWagons();
            return true;
        }
    }

    public static boolean hookWagonOnTrainFront(Train train, Wagon wagon) {
        /* check if Locomotive can pull new number of Wagons
         check if wagon is correct kind of wagon for train
         if Train has no wagons hookOn to Locomotive
         if Train has wagons hookOn to Locomotive and hook firstWagon of Train to lastWagon attached to the wagon
         adjust number of Wagons of Train */
        if (!hasPlaceForWagons(train, wagon)) return false;
        if (!isSuitableWagon(train, wagon)) return false;

        if (train.hasNoWagons()) {
            train.setFirstWagon(wagon);
            return true;
        } else {
            //train.getFirstWagon().setPreviousWagon(wagon.getLastWagonAttached());
            wagon.getLastWagonAttached().setNextWagon(train.getFirstWagon());

            train.setFirstWagon(wagon);
            train.resetNumberOfWagons();
            return true;
        }

    }

    public static boolean hookWagonOnWagon(Wagon first, Wagon second) {
        /* check if wagons are of the same kind (suitable)
         * if so make second wagon next wagon of first */
        if (!isSuitableWagon(first, second)) return false;

        first.setNextWagon(second);
        return true;
    }


    public static boolean detachAllFromTrain(Train train, Wagon wagon) {
        /* check if wagon is on the train
         detach the wagon from its previousWagon with all its successor
         recalculate the number of wagons of the train */
        if (train.getPositionOfWagon(wagon.getWagonId()) == -1) return false;
        if (wagon.hasPreviousWagon()) {
            wagon.getPreviousWagon().setNextWagon(null);
        } else {
            train.setFirstWagon(null);
        }
        train.resetNumberOfWagons();
        return true;
    }

    public static boolean detachOneWagon(Train train, Wagon wagon) {
        /* check if wagon is on the train
         detach the wagon from its previousWagon and hook the nextWagon to the previousWagon
         so, in fact remove the one wagon from the train
        */
        if (train.getPositionOfWagon(wagon.getWagonId()) == -1) return false;
        if (wagon.hasPreviousWagon()) {
            wagon.getPreviousWagon().setNextWagon(wagon.getNextWagon());
            wagon.getNextWagon().setPreviousWagon(wagon.getPreviousWagon());
        } else {
            train.setFirstWagon(wagon.getNextWagon());
        }
        train.resetNumberOfWagons();
        return true;
    }

    public static boolean moveAllFromTrain(Train from, Train to, Wagon wagon) {
        /* check if wagon is on train from
         check if wagon is correct for train and if engine can handle new wagons
         detach Wagon and all successors from train from and hook at the rear of train to
         remember to adjust number of wagons of trains */
        if (detachAllFromTrain(from, wagon) && hookWagonOnTrainRear(to, wagon)) {
            from.resetNumberOfWagons();
            to.resetNumberOfWagons();
        } else {
            return false;
        }
        return true;
    }

    public static boolean moveOneWagon(Train from, Train to, Wagon wagon) {
        // detach only one wagon from train from and hook on rear of train to
        // do necessary checks and adjustments to trains and wagon
        if (detachOneWagon(from, wagon) && hookWagonOnTrainRear(to, wagon)) {
            from.resetNumberOfWagons();
            to.resetNumberOfWagons();
        } else {
            return false;
        }
        return true;
    }
}