package parcels;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FragileParcelTest {

    @Test
    void givenFragileParcelWithPositiveWeight_whenCalculateDeliveryCost_thenReturnCorrectCost() {
        FragileParcel parcel = new FragileParcel("Ваза", 3.0, "Москва", 12);
        double result = parcel.calculateDeliveryCost();
        assertEquals(12.0, result);
    }
}

