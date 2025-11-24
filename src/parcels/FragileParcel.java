package parcels;

public class FragileParcel extends Parcel implements Trackable {

    private static final int FRAG_PRICE = 4;

    public FragileParcel(String description, double weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка " + getDescription() + " обёрнута в защитную плёнку");
        super.packageItem();
    }

    @Override
    public double calculateDeliveryCost() {
        return getWeight() * FRAG_PRICE;
    }

    @Override
    public void reportStatus(String newLocation) {
        setDeliveryAddress(newLocation);
        System.out.println("Хрупкая посылка " + getDescription() + " изменила местоположение на " + newLocation);
    }

    @Override
    public String toString() {
        return "\nХрупкая посылка  \n" + super.toString();
    }
}