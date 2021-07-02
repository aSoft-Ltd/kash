import kash.Currency;
import kash.Money;
import org.junit.Test;

import static kash.MoneyBuilders.*;

public class MoneyJavaTest {

    @Test
    public void should_instantiate_easilty() {
        var m1 = Money.of(2, Currency.TZS);
        var m2 = TZS(3);
    }
}
