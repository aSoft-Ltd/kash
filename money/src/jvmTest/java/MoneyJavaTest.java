import static org.junit.jupiter.api.Assertions.assertEquals;

import kash.Currency;
import kash.Money;

import org.junit.jupiter.api.Test;

public class MoneyJavaTest {

    @Test
    public void should_instantiate_easily() {
        var m1 = Money.of(3, Currency.TZS.INSTANCE);
        var m2 = Money.of(3, Currency.TZS.INSTANCE);
        assertEquals(m1, m2);
    }
}
