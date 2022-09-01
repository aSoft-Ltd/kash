import static org.junit.jupiter.api.Assertions.assertEquals;

import kash.Currency;
import kash.Money;

import static kash.MoneyBuilders.*;

import org.junit.jupiter.api.Test;

public class MoneyJavaTest {

    @Test
    public void should_instantiate_easily() {
        var m1 = Money.of(3, Currency.TZS.INSTANCE);
        var m2 = TZS(3);
        var m3 = Currency.TZS.INSTANCE.of(3);
        assertEquals(m1, m2);
        assertEquals(m1, m3);
        assertEquals(m3, m2);
    }
}
