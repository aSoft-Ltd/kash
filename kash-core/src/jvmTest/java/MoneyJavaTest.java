import kash.Currency;
import kash.Money;
import org.junit.Test;

import static kash.MoneyBuilders.*;
import static org.junit.Assert.assertEquals;

public class MoneyJavaTest {

    @Test
    public void should_instantiate_easilty() {
        var m1 = Money.of(3, Currency.TZS);
        var m2 = TZS(3);
        var m3 = Currency.TZS.of(3);
        assertEquals(m1, m2);
        assertEquals(m1, m3);
        assertEquals(m3, m2);
    }
}
