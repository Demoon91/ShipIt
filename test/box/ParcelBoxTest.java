package box;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parcels.FragileParcel;
import parcels.PerishableParcel;
import parcels.StandardParcel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParcelBoxTest {

    private ParcelBox<StandardParcel> standardParcelBox;
    private ParcelBox<FragileParcel> fragileParcelBox;
    private ParcelBox<PerishableParcel> perishableParcelBox;

    @BeforeEach
    void setUp() {
        standardParcelBox = new ParcelBox<>(10.0); // коробка на 10 кг
        fragileParcelBox = new ParcelBox<>(5.0);   // коробка на 5 кг
        perishableParcelBox = new ParcelBox<>(8.0); // коробка на 8 кг
    }

    @Test
    void givenEmptyBoxAndParcelUnderWeightLimit_whenAddParcel_thenParcelAdded() {
        StandardParcel parcel = new StandardParcel("Книги", 3.0, "Курск", 15);
        standardParcelBox.add(parcel);
        List<StandardParcel> parcels = standardParcelBox.getAllParcels();
        assertEquals(1, parcels.size());
        assertEquals(parcel, parcels.get(0));
    }

    @Test
    void givenBoxWithSomeParcelsAndNewParcelUnderWeightLimit_whenAddParcel_thenParcelAdded() {
        StandardParcel parcel1 = new StandardParcel("Книги", 2.0, "Курск", 10);
        StandardParcel parcel2 = new StandardParcel("Одежда", 2.0, "Москва", 11);
        standardParcelBox.add(parcel1);
        standardParcelBox.add(parcel2);
        StandardParcel newParcel = new StandardParcel("Обувь", 3.0, "Мурманск", 12);
        standardParcelBox.add(newParcel);
        List<StandardParcel> parcels = standardParcelBox.getAllParcels();
        assertEquals(3, parcels.size());
        assertEquals(newParcel, parcels.get(2));
    }

    @Test
    void givenEmptyBoxAndParcelOverWeightLimit_whenAddParcel_thenParcelNotAdded() {
        FragileParcel parcel = new FragileParcel("Ваза", 6.0, "Курск", 12);
        fragileParcelBox.add(parcel);
        List<FragileParcel> parcels = fragileParcelBox.getAllParcels();
        assertTrue(parcels.isEmpty());
    }

    @Test
    void givenBoxWithParcelsAndNewParcelOverWeightLimit_whenAddParcel_thenParcelNotAdded() {
        FragileParcel parcel1 = new FragileParcel("Ваза", 2.0, "Курск", 10);
        FragileParcel parcel2 = new FragileParcel("Статуэтка", 2.0, "Москва", 11);
        fragileParcelBox.add(parcel1);
        fragileParcelBox.add(parcel2);
        FragileParcel newParcel = new FragileParcel("Зеркало", 2.0, "Иркутск", 12);
        fragileParcelBox.add(newParcel);
        List<FragileParcel> parcels = fragileParcelBox.getAllParcels();
        assertEquals(2, parcels.size());
        assertFalse(parcels.contains(newParcel));
    }

    @Test
    void givenBoxAndParcelWithExactWeightLimit_whenAddParcel_thenParcelAdded() {
        PerishableParcel parcel = new PerishableParcel("Фрукты", 8.0, "Москва", 15, 5);
        perishableParcelBox.add(parcel);
        List<PerishableParcel> parcels = perishableParcelBox.getAllParcels();
        assertEquals(1, parcels.size());
        assertEquals(parcel, parcels.get(0));
    }
}