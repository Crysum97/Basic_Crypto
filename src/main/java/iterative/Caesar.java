package iterative;

import engine.CryptoEngine;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
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

        for (int index = 0; index < input.length(); index++) {
            // add key
            int ascii = input.charAt(index) + key;

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

    @Override
    public String encrypt(String input, String key) throws CryptoException {
        throw new CryptoException("");
    }

    @Override
    public String encrypt(String input) throws CryptoException {
        throw new CryptoException("");
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

    @Override
    public String decrypt(String input, String key) throws CryptoException {
        throw new CryptoException("");
    }

    @Override
    public String decrypt(String input) throws CryptoException {
        throw new CryptoException("");
    }
}
