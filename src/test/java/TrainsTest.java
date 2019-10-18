import model.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TrainsTest {

    private ArrayList<PassengerWagon> pwList;
    private Train firstPassengerTrain;
    private Train secondPassengerTrain;
    private Train firstFreightTrain;
    private Train secondFreightTrain;

    @BeforeEach
    private void makeListOfPassengerWagons() {
        pwList = new ArrayList<>();
        pwList.add(new PassengerWagon(3, 100));
        pwList.add(new PassengerWagon(24, 100));
        pwList.add(new PassengerWagon(17, 140));
        pwList.add(new PassengerWagon(32, 150));
        pwList.add(new PassengerWagon(38, 140));
        pwList.add(new PassengerWagon(11, 100));
    }

    private void makeTrains() {
        Locomotive thomas = new Locomotive(2453, 7);
        Locomotive gordon = new Locomotive(5277, 8);
        Locomotive emily = new Locomotive(4383, 11);
        Locomotive rebecca = new Locomotive(2275, 4);

        firstPassengerTrain = new Train(thomas, "Amsterdam", "Haarlem");
        for (Wagon w : pwList) {
            Shunter.hookWagonOnTrainRear(firstPassengerTrain, w);
        }
        secondPassengerTrain = new Train(gordon, "Joburg", "Cape Town");
    }

    @Test
    public void checkNumberOfWagonsOnTrain() {
        makeTrains();

        // Lines for Debug purposes
//        System.out.println(firstPassengerTrain.getWagonOnPosition(1));
//        System.out.println(firstPassengerTrain.getWagonOnPosition(2));
//        System.out.println(firstPassengerTrain.getWagonOnPosition(3));
//        System.out.println(firstPassengerTrain.getWagonOnPosition(4));
//        System.out.println(firstPassengerTrain.getWagonOnPosition(5));
//        System.out.println(firstPassengerTrain.getWagonOnPosition(6));

        assertEquals(6, firstPassengerTrain.getNumberOfWagons(), "Train should have 6 wagons");
        System.out.println(firstPassengerTrain);
    }

    @Test
    public void checkNumberOfSeatsOnTrain() {
        makeTrains();
        assertEquals( 730, firstPassengerTrain.getNumberOfSeats());
        System.out.println(firstPassengerTrain);
    }

    @Test
    public void checkPositionOfWagons() {
        makeTrains();
        int position = 1;
        for (PassengerWagon pw : pwList) {
            assertEquals( position, firstPassengerTrain.getPositionOfWagon(pw.getWagonId()));
            position++;
        }

    }

    @Test
    public void checkHookOneWagonOnTrainFront() {
        makeTrains();
        Shunter.hookWagonOnTrainFront(firstPassengerTrain, new PassengerWagon(21, 140));
        assertEquals( 7, firstPassengerTrain.getNumberOfWagons(), "Train should have 7 wagons");
        assertEquals( 1, firstPassengerTrain.getPositionOfWagon(21));

    }

    @Test
    public void checkHookRowWagonsOnTrainRearFalse() {
        makeTrains();
        Wagon w1 = new PassengerWagon(11, 100);
        Shunter.hookWagonOnWagon(w1, new PassengerWagon(43, 140));
        Shunter.hookWagonOnTrainRear(firstPassengerTrain, w1);
        assertEquals(6, firstPassengerTrain.getNumberOfWagons(), "Train should have still have 6 wagons, capacity reached");
        assertEquals( -1, firstPassengerTrain.getPositionOfWagon(43));
    }

    @Test
    public void checkMoveOneWagon() {
        makeTrains();
        Shunter.moveOneWagon(firstPassengerTrain, secondPassengerTrain, pwList.get(3));
        assertEquals(5, firstPassengerTrain.getNumberOfWagons(), "Train should have 5 wagons");
        assertEquals( -1, firstPassengerTrain.getPositionOfWagon(32));
        assertEquals(1, secondPassengerTrain.getNumberOfWagons(), "Train should have 1 wagon");
        assertEquals( 1, secondPassengerTrain.getPositionOfWagon(32));

    }

    @Test
    public void checkMoveRowOfWagons() {
        makeTrains();
        Wagon w1 = new PassengerWagon(11, 100);
        Shunter.hookWagonOnWagon(w1, new PassengerWagon(43, 140));
        Shunter.hookWagonOnTrainRear(secondPassengerTrain, w1);
        Shunter.moveAllFromTrain(firstPassengerTrain, secondPassengerTrain, pwList.get(2));
        assertEquals(2, firstPassengerTrain.getNumberOfWagons(), "Train should have 2 wagons");
        assertEquals( 2, firstPassengerTrain.getPositionOfWagon(24));
        assertEquals(6, secondPassengerTrain.getNumberOfWagons(), "Train should have 6 wagons");
        assertEquals( 4, secondPassengerTrain.getPositionOfWagon(32));
    }

}
