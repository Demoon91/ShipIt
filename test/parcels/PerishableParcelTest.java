package parcels;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PerishableParcelTest {

    @Test
    void givenExpiredParcel_whenCheckIsExpired_thenReturnTrue() {
        PerishableParcel parcel = new PerishableParcel("Мясо", 3.0, "Москва", 10, 5);
        int currentDay = 16;
        boolean result = parcel.isExpired(currentDay);
        assertTrue(result);
    }

    @Test
    void givenNotExpiredParcel_whenCheckIsExpired_thenReturnFalse() {
        PerishableParcel parcel = new PerishableParcel("Мясо", 3.0, "Москва", 10, 5);
        int currentDay = 14;
        boolean result = parcel.isExpired(currentDay);
        assertFalse(result);
    }

    @Test
    void givenParcelOnLastValidDay_whenCheckIsExpired_thenReturnFalse() {
        PerishableParcel parcel = new PerishableParcel("Мясо", 3.0, "Москва", 10, 5);
        int currentDay = 15;
        boolean result = parcel.isExpired(currentDay);
        assertFalse(result);
    }

    @Test
    void givenPerishableParcelWithPositiveWeight_whenCalculateDeliveryCost_thenReturnCorrectCost() {
        PerishableParcel parcel = new PerishableParcel("Фрукты", 4, "Москва", 10, 3);
        double result = parcel.calculateDeliveryCost();
        assertEquals(12, result);
    }
}