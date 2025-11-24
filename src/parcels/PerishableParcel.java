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

    public  boolean isExpired (int currentDay) {
        int dayAmount = getSendDay() + timeToLive;
        return dayAmount < currentDay;
    }

    @Override
    public double calculateDeliveryCost() {
        return getWeight() * PER_PRICE;
    }

    @Override
    public String toString() {
        return "\nСкоропортящаяся посылка \n" + super.toString() +
                "\nСрок храненния " + timeToLive;
    }
}