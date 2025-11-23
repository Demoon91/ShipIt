package box;

import parcels.Parcel;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private final List<T> parcels;
    private double boxWeight;

    public ParcelBox(double boxWeight) {
        this.parcels = new ArrayList<>();
        this.boxWeight = boxWeight;
    }

    public void add(T parc) {
        double sumWeight = 0;
        for (T parcel : parcels) {
            sumWeight += parcel.getWeight();
        }

        if (sumWeight + parc.getWeight() > boxWeight){
            System.out.println("Вес посылок превышает допустимый вес коробки");
        } else {
            parcels.add(parc);
        }
    }

    public List<T> getAllParcels() {
        return parcels;
    }


    public double getBoxWeight() {
        return boxWeight;
    }

    public void setBoxWeight(double boxWeight) {
        this.boxWeight = boxWeight;
    }

    public void clearBox() {
        parcels.clear();
    }
}