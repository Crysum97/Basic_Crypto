package functional;

import engine.CryptoEngine;
import util.CryptoException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * functional implementation of the caesar cipher
 */
public class Caesar implements CryptoEngine {

    /**
     * encrypts a text using an integer value as a key
     * @param input text to encrypt
     * @param key key to use for encryption
     * @return encrypted text
     */
    public String encrypt(String input, int key) {
        // reduce the key to match alphabet range
        int validKey = key % 26;
        String cleaned = cleanString(input);

        return IntStream.range(0, cleaned.length())
                // add key
                .mapToObj(index -> (char) (cleaned.charAt(index) + validKey))
                // translate back into bounds if necessary
                .map(sign -> (char) (((sign - 65) % 26) + 65))
                .map(this::checkBound)
                // map to String
                .map(Character::toString)
                // build result
                .collect(Collectors.joining());
    }

    /**
     * recursivly determines the lowest value that is within the bounds of capital letters
     * @param ascii input value
     * @return lowest ascii value that is within the bounds [65, 90]
     */
    private int checkBound(int ascii) {
        return ascii >= 65 ? ascii : checkBound(ascii + 26);
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
     * not supported
     * @param input text to encrypt
     * @return encrypted text
     * @throws CryptoException always
     */
    @Override
    public String encrypt(String input) throws CryptoException {
        throw new CryptoException();
    }

    /**
     * decrypts a text using an integer value as a key
     * @param input text to decrypt
     * @param key key to use for decryption
     * @return decrypted text
     */
    public String decrypt(String input, int key) {
        return encrypt(cleanString(input), 26 - key);
    }

    /**
     * not supported
     * @param input text to decrypt
     * @param key key to use
     * @return decrypted text
     * @throws CryptoException always
     */
    @Override
    public String decrypt(String input, String key) throws CryptoException {
        throw new CryptoException();
    }

    /**
     * not supported
     * @param input text to decrypt
     * @return decrypted text
     * @throws CryptoException always
     */
    @Override
    public String decrypt(String input) throws CryptoException {
        throw new CryptoException();
    }
}
