package iterative;

import java.util.Random;

public class Matrix {

    public static String encrypt(String input) {
        // next integer that is greater that the square root of the input length
        int dimension = (int) Math.ceil(Math.sqrt(input.length()));
        // construct array
        char[][] array = new char[dimension][dimension];

        // keep track of the current position within the string
        int index = 0;

        // write columns first
        // for each cell of the array...
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                // ... either write a letter from the string
                if (index < input.length()) {
                    array[x][y] = input.charAt(index++);
                // ... or write a random capital letter
                } else {
                    Random random = new Random();
                    array[x][y] = (char)random.nextInt(65 ,91);
                }
            }
        }

        // prepare result
        StringBuilder result = new StringBuilder();

        // read rows first
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                result.append(array[x][y]);
            }
        }

        return result.toString();
    }

    public static String decrypt(String input) {
        // decryption is the same as encryption
        return Matrix.encrypt(input);
    }
}
