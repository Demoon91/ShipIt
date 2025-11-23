package parcels;

public abstract class Parcel {
    private String description;
    private double weight;
    private String deliveryAddress;
    private int sendDay;
    private static final int BASE_PRICE = 0;

    public Parcel(String description, double weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getSendDay() {
        return sendDay;
    }

    public void setSendDay(int sendDay) {
        this.sendDay = sendDay;
    }

    public void packageItem() {
        System.out.println("Посылка " + description + " упакована");
    }

    public void deliver() {
        System.out.println("Посылка " + description + " доставлена по адресу " + deliveryAddress);
    }

    public double calculateDeliveryCost() {
        return weight * BASE_PRICE;
    }

    @Override
    public String toString() {
        return  "Описание: " + description + ";\n" +
                 "Вес " + weight + ";" +
                "\nАдрес доставки " + deliveryAddress + ";" +
                "\nДень отправки " + sendDay + ";";
    }
}