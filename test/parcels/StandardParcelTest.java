package parcels;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StandardParcelTest {

    @Test
    void givenStandardParcelWithPositiveWeight_whenCalculateDeliveryCost_thenReturnCorrectCost() {
        StandardParcel parcel = new StandardParcel("Книги", 5.5, "Курск", 15);
        double result = parcel.calculateDeliveryCost();
        assertEquals(11.0, result);
    }
}