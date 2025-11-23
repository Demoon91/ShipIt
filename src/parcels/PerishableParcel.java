package parcels;

public class PerishableParcel extends Parcel {
    private int timeToLive;
    private static final int PER_PRICE = 3;

    public PerishableParcel(String description, double weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    @Override
    public double calculateDeliveryCost() {
        return getWeight() * PER_PRICE;
    }

    public  boolean isExpired (int currentDay) {
        int dayAmount = getSendDay() + getTimeToLive();
        return dayAmount < currentDay;
    }

    @Override
    public String toString() {
        return "\nСкоропортящаяся  " + super.toString() +
                "\nСрок храненния " + timeToLive;
    }
}