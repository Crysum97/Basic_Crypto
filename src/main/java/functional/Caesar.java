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

        return IntStream.range(0, input.length())
                // add key
                .mapToObj(index -> (char) (input.charAt(index) + validKey))
                // translate back into bounds if necessary
                .map(sign -> (char) (((sign - 65) % 26) + 65))
                // map to String
                .map(sign -> Character.toString(sign))
                // build result
                .collect(Collectors.joining());
    }

    @Override
    public String encrypt(String input, String key) throws CryptoException {
        throw new CryptoException();
    }

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

    @Override
    public String decrypt(String input, String key) throws CryptoException {
        throw new CryptoException();
    }

    @Override
    public String decrypt(String input) throws CryptoException {
        throw new CryptoException();
    }
}
