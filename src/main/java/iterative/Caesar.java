package iterative;

import engine.CryptoEngine;
import util.CryptoException;


/**
 * iterative implementation of the caesar cipher
 */
public class Caesar implements CryptoEngine {

    /**
     * encrypts a text using an integer value as a key
     * @param input text to encrypt
     * @param key key to use for encryption
     * @return encrypted text
     */
    public String encrypt(String input, int key) {
        // prepare result
        StringBuilder result = new StringBuilder();
        String cleaned = cleanString(input);

        for (int index = 0; index < cleaned.length(); index++) {
            // add key
            int ascii = cleaned.charAt(index) + key;

            // check upper bounds
            while (ascii > 90) {
                ascii -= 26;
            }

            // check lower bounds
            while (ascii < 65) {
                ascii += 26;
            }

            // add letter to result
            result.append((char) ascii);
        }

        return result.toString();
    }

    /**
     * not supported
     * @param input text to encrypt
     * @param key key to use
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
        return encrypt(input, 26 - key);
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
