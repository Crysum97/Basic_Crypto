package iterative;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatrixTest {
    @ParameterizedTest
    @CsvSource({"Hallo", "Welt", "Test", "SUPER"})
    public void testMatrix(String input) {
        System.out.printf("Input: %s\n", input);
        assertTrue(Matrix.decrypt(Matrix.encrypt(input)).startsWith(input));
    }
}