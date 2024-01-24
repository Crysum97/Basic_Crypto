package iterative;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VigereneTest {
    @ParameterizedTest
    @CsvSource({"HALLO", "WELT", "TEST", "SUPER"})
    public void testVigerene(String input) {
        System.out.printf("Input: %s\n", input);
        String reversed = new StringBuilder(input).reverse().toString();
        assertEquals(input, Vigerene.decrpyt(Vigerene.encrypt(input), reversed));
    }
}