import box.ParcelBox;
import parcels.FragileParcel;
import parcels.Parcel;
import parcels.PerishableParcel;
import parcels.StandardParcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<FragileParcel> fragileParcels = new ArrayList<>();

    private static ParcelBox<StandardParcel> standardParcelParcelBox =
            new ParcelBox<>(30.00);
    private static ParcelBox<FragileParcel> fragileParcelParcelBox =
            new ParcelBox<>(50.00);
    private static ParcelBox<PerishableParcel> perishableParcelParcelBox =
            new ParcelBox<>(5.00);

    public static void main(String[] args) {

        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    tracking();
                    break;
                case 5:
                    getAllParcelsFromBox();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Трекинг");
        System.out.println("5 — Показать коробки");
        System.out.println("0 — Завершить");
    }


    private static void addParcel() {
        boolean backMain = false;
        while (!backMain) {
            System.out.println("Выберите тип посылки");
            showMenu1();
            int type = Integer.valueOf(scanner.nextLine());
            switch (type) {
                case 1:
                    StandardParcel standard = createStandard();
                    allParcels.add(standard);
                    standardParcelParcelBox.add(standard);
                    break;
                case 2:
                    FragileParcel fragile = createFragile();
                    allParcels.add(fragile);
                    fragileParcels.add(fragile);
                    fragileParcelParcelBox.add(fragile);
                    break;
                case 3:
                    PerishableParcel perishable = createPerishable();
                    allParcels.add(perishable);
                    perishableParcelParcelBox.add(perishable);
                    break;
                case 0:
                    backMain = true;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }


    private static void sendParcels() {
        if (allParcels.isEmpty()) {
            System.out.println("В очереди нет посылок на отправку или все посылки отправлены.");
        }

        for (Parcel parcelList : allParcels) {
            parcelList.packageItem();
            parcelList.deliver();
        }

        //После отправки всех посылок очищаем коробки и список посылок
        allParcels.clear();
        fragileParcelParcelBox.clearBox();
        standardParcelParcelBox.clearBox();
        perishableParcelParcelBox.clearBox();
    }

    private static void calculateCosts() {
        if (allParcels.isEmpty()) {
            System.out.println("Список пуст, для расчета добавьте посылки в список отправок");
        }

        double sumCost = 0;
        for (Parcel parcel : allParcels) {
            sumCost += parcel.calculateDeliveryCost();
        }
        System.out.println(sumCost);
    }

    private static void tracking () {
        if (allParcels.isEmpty()) {
            System.out.println("Список посылок пуст, добавьте посылки!!!");
        }

        System.out.println("Выберете у какой посылки хотите изменить адрес");
        printFragileList();
        int number = Integer.parseInt(scanner.nextLine());
        while (number >= fragileParcels.size() && number > 0) {
            System.out.println("Ошибка: номер посылки должен быть не более " + fragileParcels.size());
            System.out.print("Введите номер посылки снова: ");
            number = Integer.parseInt(scanner.nextLine());
        }
        FragileParcel fragileParcel = fragileParcels.get(number - 1);

        System.out.println("Введите новый адрес посылки");
        String location = scanner.nextLine();

        if (fragileParcel != null) {
            fragileParcel.reportStatus(location);
        }
    }

    public static void showMenu1() {
        System.out.println("1 — Стандартная");
        System.out.println("2 — Хрупкая");
        System.out.println("3 — Скоропортящая");
        System.out.println("0 — Завершить");
    }

    private static StandardParcel createStandard() {
        System.out.println("Введите описание посылки");
        String description = scanner.nextLine();
        System.out.println("Введите вес посылки");
        double weight = Double.parseDouble(scanner.nextLine());
        System.out.println("Введите адрес назначения");
        String destinationAddress = scanner.nextLine();
        System.out.println("Введите день отправки");
        int deliveryDay = Integer.parseInt(scanner.nextLine());
        while (deliveryDay > 30 || deliveryDay < 1) { // исправил < 0 на < 1
            System.out.println("Ошибка: день должен быть от 1 до 30");
            System.out.print("Введите день отправки снова: ");
            deliveryDay = Integer.parseInt(scanner.nextLine());
        }

        return new StandardParcel(description, weight, destinationAddress, deliveryDay);
    }

    private static FragileParcel createFragile() {
        System.out.println("Введите описание посылки");
        String description = scanner.nextLine();
        System.out.println("Введите вес посылки");
        double weight = Double.parseDouble(scanner.nextLine());
        System.out.println("Введите адрес назначения");
        String destinationAddress = scanner.nextLine();
        System.out.println("Введите день отправки");
        int deliveryDay = Integer.parseInt(scanner.nextLine());

        while (deliveryDay > 30 || deliveryDay < 1) { // исправил < 0 на < 1
            System.out.println("Ошибка: день должен быть от 1 до 30");
            System.out.print("Введите день отправки снова: ");
            deliveryDay = Integer.parseInt(scanner.nextLine());
        }

        return new FragileParcel(description, weight, destinationAddress, deliveryDay);
    }

    private static PerishableParcel createPerishable() {
        System.out.println("Введите описание посылки");
        String description = scanner.nextLine();
        System.out.println("Введите вес посылки");
        double weight = Double.parseDouble(scanner.nextLine());
        System.out.println("Введите адрес назначения");
        String destinationAddress = scanner.nextLine();
        System.out.println("Введите день отправки");
        int deliveryDay = Integer.parseInt(scanner.nextLine());

        while (deliveryDay > 30 || deliveryDay < 1) { // исправил < 0 на < 1
            System.out.println("Ошибка: день должен быть от 1 до 30");
            System.out.print("Введите день отправки снова: ");
            deliveryDay = Integer.parseInt(scanner.nextLine());
        }

        System.out.println("Срок хранения");
        int saveDay = Integer.parseInt(scanner.nextLine());
        return new PerishableParcel (description, weight, destinationAddress, deliveryDay, saveDay);
    }

    private static void printFragileList() {
        int i = 1;
        for (FragileParcel fragileParcel : fragileParcels){
            System.out.println(i++ + ") " + fragileParcel.getDescription());
        }
    }

    private static void getAllParcelsFromBox() {
        boolean backMain = false;
        while (!backMain) {
            System.out.println("Выберите тип коробки");
            showMenu1();
            int type = scanner.nextInt();
            switch (type) {
                case 1:
                    System.out.println(standardParcelParcelBox.getAllParcels());
                    break;
                case 2:
                    System.out.println(fragileParcelParcelBox.getAllParcels());
                    break;
                case 3:
                    System.out.println(perishableParcelParcelBox.getAllParcels());
                    break;
                case 0:
                    backMain = true;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
        scanner.nextLine();
    }
}