package iterative;

import engine.CryptoEngine;
import util.CryptoException;

import java.util.Random;


/**
 * iterative implementation of the matrix encryption
 */
public class Matrix implements CryptoEngine {


    /**
     * not supported
     * @param input text to encrypt
     * @param key key to use
     * @return encrypted text
     * @throws CryptoException always
     */
    @Override
    public String encrypt(String input, int key) throws CryptoException {
        throw new CryptoException();
    }

    /**
     * not supported
     * @param input text to encrypt
     * @param key text key to use
     * @return encrypted text
     * @throws CryptoException always
     */
    @Override
    public String encrypt(String input, String key) throws CryptoException {
        throw new CryptoException();
    }

    /**
     * encrypts a given text using matrix encryption.
     * size of the matrix is determined automatically.
     * empty cells will be filled using capital letters
     * @param input text to encrypt
     * @return encypted text
     */
    public String encrypt(String input) {
        // next integer that is greater that the square root of the input length
        String cleaned = cleanString(input);
        int dimension = (int) Math.ceil(Math.sqrt(cleaned.length()));
        // construct array
        char[][] array = new char[dimension][dimension];

        // keep track of the current position within the string
        int index = 0;

        // write columns first
        // for each cell of the array...
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                // ... either write a letter from the string
                if (index < cleaned.length()) {
                    array[x][y] = cleaned.charAt(index++);
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

    /**
     * not supported
     * @param input text to decrypt
     * @param key key to use
     * @return decrypted text
     * @throws CryptoException always
     */
    @Override
    public String decrypt(String input, int key) throws CryptoException {
        throw new CryptoException();
    }

    /**
     * not supported
     * @param input text to decrypt
     * @param key text key to use
     * @return decrypted text
     * @throws CryptoException always
     */
    @Override
    public String decrypt(String input, String key) throws CryptoException {
        throw new CryptoException();
    }

    /**
     * decrypts a text using matrix encryption.
     * decrypted text might contain additional tailing characters if they were added during
     * encryption or the string does not completely fill the matrix
     * @param input text to decrypt
     * @return decrypted text
     */
    public String decrypt(String input) {
        // decryption is the same as encryption
        return encrypt(input);
    }
}
