package controller;

import model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TrainLauncher {

    public static void main(String[] args) {

        /* List<Wagon> wagonList = new ArrayList<>();
        wagonList.add(new PassengerWagon(3, 100));
        wagonList.add(new PassengerWagon(24, 100));
        wagonList.add(new PassengerWagon(17, 140));
        wagonList.add(new PassengerWagon(32, 150));
        wagonList.add(new PassengerWagon(38, 140));
        Locomotive thomas = new Locomotive(2453, 7);
        Train firstTrain  = new Train(thomas, "Amsterdam", "Haarlem");
        for (Wagon w : wagonList) {
            Shunter.hookWagonOnTrainRear(firstTrain, w);
        }


        System.out.println(firstTrain);
        Shunter.hookWagonOnTrainFront(firstTrain, new PassengerWagon(21, 140));
        System.out.println(firstTrain);

        Wagon w1 = new PassengerWagon(11, 100);
        Shunter.hookWagonOnWagon(w1, new PassengerWagon(43, 140));
        Shunter.hookWagonOnTrainRear(firstTrain, w1);
        System.out.println(firstTrain);

        System.out.println("Position of Wagon 21 is: " + firstTrain.getPositionOfWagon(21));
        System.out.println("Wagon on position 5 is: " + firstTrain.getWagonOnPosition(5));
        System.out.println();
        System.out.println("----------------------");
        Locomotive sean = new Locomotive(5277, 8);
        Train secondTrain = new Train(sean, "Cape Town", "Joburg" );

        Shunter.moveOneWagon(firstTrain, secondTrain, wagonList.get(3));
        System.out.println(firstTrain);
        System.out.println(secondTrain);

        System.out.println();
        System.out.println("----------------------");
        Shunter.moveAllFromTrain(firstTrain, secondTrain, wagonList.get(2));
        System.out.println(firstTrain);
        System.out.println(secondTrain);
*/
    }
}
