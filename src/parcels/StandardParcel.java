package parcels;

public class StandardParcel extends Parcel {
    private static final int STAND_PRICE = 2;

    public StandardParcel(String description, double weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public double calculateDeliveryCost() {
        return getWeight() * STAND_PRICE;
    }

    @Override
    public String toString() {
        return "\nСтандартная послыка \n" + super.toString();
    }
}